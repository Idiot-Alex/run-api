package com.hotstrip.runapi.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.Res;
import com.hotstrip.runapi.domain.model.dto.ApiLogDto;
import com.hotstrip.runapi.domain.service.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ApiLog controller
 * @author hotstrip
 * @since 2022-12-31
 */
@RestController
@Slf4j
public class ApiLogController {
    @Resource
    private ApiLogService apiLogService;

    @GetMapping(value = "/api-log/list")
    public Res list(@RequestParam(defaultValue = "1") Integer pageNo,
                    @RequestParam(defaultValue = "10") Integer pageSize,
                    ApiLog info) {
        log.info("/api-log/list...pageNo: {}, pageSize: {}", pageNo, pageSize);
        Page<ApiLogDto> page = apiLogService.listPage(pageNo, pageSize, info);
        log.info("res: {}", JSON.toJSONString(page));
        return Res.okData(page);
    }
}
