package com.hotstrip.runapi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.Res;
import com.hotstrip.runapi.domain.service.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
    public Res list(Integer pageNo,
                    Integer pageSize,
                    ApiLog info) {
        Page<ApiLog> page = apiLogService.listPage(pageNo, pageSize, info);
        return Res.okPageList(page);
    }
}
