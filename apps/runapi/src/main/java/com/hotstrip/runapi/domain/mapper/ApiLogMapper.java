package com.hotstrip.runapi.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.ApiModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiLogMapper extends BaseMapper<ApiLog> {
}
