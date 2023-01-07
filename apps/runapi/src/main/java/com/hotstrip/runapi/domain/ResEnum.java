package com.hotstrip.runapi.domain;

public enum ResEnum {
    RES_400("400", "处理失败"),
    ;
    private String code;
    private String msg;

    ResEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
