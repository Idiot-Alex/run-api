package com.hotstrip.runapi.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Objects;

/**
 * @since 2022-12-03
 * @author hotstrip
 * init SQL file when DataSource is ready
 */
@Slf4j
@Configuration
public class DataBaseInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            DataSource dataSource = (DataSource) bean;
            StringWriter writer = new StringWriter();

            try (Connection connection = dataSource.getConnection()) {

                ScriptRunner scriptRunner = new ScriptRunner(connection);
                scriptRunner.setAutoCommit(true);
                scriptRunner.setDelimiter(";");
                scriptRunner.setFullLineDelimiter(false);
                scriptRunner.setSendFullScript(false);
                scriptRunner.setStopOnError(false);
                scriptRunner.setLogWriter(new PrintWriter(writer));

                InputStream inputStream = this.getClass().getResourceAsStream("/init.sql");
                Objects.requireNonNull(inputStream, "The init SQL file cannot be null");
                scriptRunner.runScript(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                log.info("==============init SQL start============");
                log.info("\n" + writer.getBuffer().toString());
                log.info("==============init SQL end============");
            } catch (Exception e) {
                log.error("Exec init SQL file error...message: {}, cause: {}", e.getMessage(), e.getCause(), e);
                throw new RuntimeException(e);
            }
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
