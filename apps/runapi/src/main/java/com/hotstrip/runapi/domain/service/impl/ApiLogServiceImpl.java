package com.hotstrip.runapi.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotstrip.runapi.domain.mapper.ApiLogMapper;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.dto.ApiLogDto;
import com.hotstrip.runapi.domain.service.ApiLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiLogServiceImpl extends ServiceImpl<ApiLogMapper, ApiLog> implements ApiLogService {
    @Resource
    private ApiLogMapper apiLogMapper;
    @Override
    public Page<ApiLogDto> listPage(Integer pageNo, Integer pageSize, ApiLog info) {
        return apiLogMapper.listPage(new Page(pageNo, pageSize), info);
    }
}
