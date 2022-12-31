package com.hotstrip.runapi.domain.mapstruct;

import cn.hutool.core.util.IdUtil;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.vo.ApiModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * use spring control
 * @author hotstrip
 * @since 2022-12-31
 */
@Mapper(componentModel = "spring", imports = {IdUtil.class})
public interface ApiLogMapstruct {

    @Mapping(target = "id", expression = "java(IdUtil.getSnowflake().nextId())")
    @Mapping(source = "config.headers", target = "headers")
    @Mapping(source = "config.url", target = "url")
    @Mapping(source = "config.method", target = "method")
    @Mapping(source = "config.body", target = "body")
    ApiLog toModel(ApiModel apiModel);
}
