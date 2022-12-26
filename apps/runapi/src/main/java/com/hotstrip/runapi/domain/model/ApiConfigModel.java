package com.hotstrip.runapi.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@NoArgsConstructor
@Data
public class ApiConfigModel implements Serializable {
    private Map<String, String> headers;
    private String method;
    private String url;
    private Boolean async;
    private Boolean withCredentials;
    private String body;

}
