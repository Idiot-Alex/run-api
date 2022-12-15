package com.hotstrip.runapi.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class ApiModel implements Serializable {
    private ApiConfigModel config;
    private Integer code;
    private String msg;
    private Object res;
}
