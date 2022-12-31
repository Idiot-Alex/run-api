package com.hotstrip.runapi.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * api_log
 * @author hotstrip
 * @since 2022-12-29
 */
@NoArgsConstructor
@Data
@TableName(schema = "public", value = "api_log")
public class ApiLog implements Serializable {
    @TableId(type = IdType.INPUT)
    private Integer id;
    private String url;
    private String method;
    private String code;
    private String msg;
    private Map<String, String> headers;
    private String body;
    private String res;
}
