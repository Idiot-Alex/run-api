package com.hotstrip.runapi.domain.mapstruct;

import com.alibaba.fastjson.JSON;
import com.hotstrip.runapi.RunapiApplicationTests;
import com.hotstrip.runapi.domain.model.ApiConfigModel;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test ApiLogMapstruct
 * @author hotstrip
 * @since 2022-12-31
 */
@Slf4j
class ApiLogMapstructTest extends RunapiApplicationTests {
    @Resource
    private ApiLogMapstruct apiLogMapstruct;

    @Test
    void translate() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        ApiConfigModel apiConfigModel = ApiConfigModel.builder()
                .async(true)
                .url("https://www.baidu.com")
                .method("get")
                .body("body")
                .headers(headers)
                .build();
        ApiModel apiModel = ApiModel.builder()
                .code("200")
                .res("..")
                .msg("success")
                .config(apiConfigModel)
                .build();
        ApiLog apiLog = apiLogMapstruct.translate(apiModel);
        log.info("apiLog: {}", JSON.toJSONString(apiLog));
        assertEquals(apiLog.getCode(), apiModel.getCode());
    }
}