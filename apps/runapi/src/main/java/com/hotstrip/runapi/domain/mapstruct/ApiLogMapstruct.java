package com.hotstrip.runapi.domain.mapstruct;

import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.ApiModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * use spring control
 * @author hotstrip
 * @since 2022-12-31
 */
@Mapper(componentModel = "spring")
public interface ApiLogMapstruct {

    @Mapping(source = "config.headers", target = "headers")
    @Mapping(source = "config.url", target = "url")
    @Mapping(source = "config.method", target = "method")
    @Mapping(source = "config.body", target = "body")
    ApiLog translate(ApiModel apiModel);
}
