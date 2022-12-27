package com.hotstrip.runapi.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotstrip.runapi.domain.mapper.ApiModelMapper;
import com.hotstrip.runapi.domain.model.ApiModel;
import com.hotstrip.runapi.domain.service.ApiModelService;
import org.springframework.stereotype.Service;

@Service
public class ApiModelServiceImpl extends ServiceImpl<ApiModelMapper, ApiModel> implements ApiModelService {
}
