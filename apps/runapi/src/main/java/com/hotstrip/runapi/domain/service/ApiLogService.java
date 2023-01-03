package com.hotstrip.runapi.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.dto.ApiLogDto;

public interface ApiLogService extends IService<ApiLog> {
    Page<ApiLogDto> listPage(Integer pageNo, Integer pageSize, ApiLog info);
}
