package com.hotstrip.runapi.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotstrip.runapi.domain.model.ApiLog;
import com.hotstrip.runapi.domain.model.dto.ApiLogDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ApiLogMapper extends BaseMapper<ApiLog> {
    @Select({" select url, method, group_concat(code) as codes ",
            " FROM PUBLIC.api_log ",
            " GROUP BY url, METHOD "})
    Page<ApiLogDto> listPage(Page objectPage, ApiLog info);
}
