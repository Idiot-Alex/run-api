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
public class ApiConfigModel implements Serializable {
    private String headers;
    private String method;
    private String url;
    private Boolean async;
    private Boolean withCredentials;
    private String body;

}
