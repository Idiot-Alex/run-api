package com.hotstrip.runapi.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotstrip.runapi.domain.mapper.ApiLogMapper;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.service.ApiLogService;
import org.springframework.stereotype.Service;

@Service
public class ApiLogServiceImpl extends ServiceImpl<ApiLogMapper, ApiLog> implements ApiLogService {
}
