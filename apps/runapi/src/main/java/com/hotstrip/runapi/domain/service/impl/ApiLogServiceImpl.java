package com.hotstrip.runapi.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotstrip.runapi.domain.mapper.ApiLogMapper;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.service.ApiLogService;
import org.springframework.stereotype.Service;

@Service
public class ApiLogServiceImpl extends ServiceImpl<ApiLogMapper, ApiLog> implements ApiLogService {
    @Override
    public Page<ApiLog> listPage(Integer pageNo, Integer pageSize, ApiLog info) {
        return this.lambdaQuery()
                .orderByAsc(ApiLog::getId)
                .page(new Page<>(pageNo, pageSize));
    }
}
