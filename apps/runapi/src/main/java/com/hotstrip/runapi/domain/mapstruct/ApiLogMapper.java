package com.hotstrip.runapi.domain.mapstruct;

import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.ApiModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApiLogMapper {
    ApiLogMapper INSTANCE = Mappers.getMapper(ApiLogMapper.class);

    ApiLog translate(ApiModel apiModel);
}
