package com.hotstrip.runapi.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiModel implements Serializable {
    private ApiConfigModel config;
    private String code;
    private String msg;
    private String res;
}
