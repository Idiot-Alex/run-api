package com.hotstrip.runapi.domain.service;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.RunapiApplicationTests;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.dto.ApiLogDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test ApiLogService
 * @author hotstrip
 * @since 2022-12-31
 */
@Slf4j
class ApiLogServiceTest extends RunapiApplicationTests {

    @Resource
    private ApiLogService apiLogService;

    @Test
    @Rollback
    @Transactional
    public void testInsert() {
        Long id = IdUtil.getSnowflake().nextId();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        ApiLog apiLog = ApiLog.builder()
                .id(id)
                .url("https://www.google.com")
                .method("get")
                .headers(JSON.toJSONString(headers))
                .body("")
                .res("...")
                .code("200")
                .msg("success")
                .build();

        boolean flag = apiLogService.save(apiLog);
        assertEquals(flag, true);

        ApiLog apiLog1 = apiLogService.getById(id);
        log.info("select apiLog: {} with id: {}", JSON.toJSONString(apiLog1), id);
        assertEquals(apiLog.getUrl(), apiLog1.getUrl());
    }

    @Test
    public void listPage() {
        Page<ApiLogDto> page = apiLogService.listPage(1, 10, new ApiLog());
        log.info("page: {}", JSON.toJSONString(page));
        assertTrue(page.getTotal() >= 0);
    }
}