package com.hotstrip.runapi;

import com.hotstrip.runapi.config.PlaywrightServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableScheduling
public class RunapiApplication {

	public static void main(String[] args) {
		PlaywrightServer.startPlaywright();
		SpringApplication.run(RunapiApplication.class, args);
	}

	@PreDestroy
	public void destroy() {
		PlaywrightServer.closePlaywrightServer();
	}

}
