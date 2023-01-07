package com.hotstrip.runapi.domain.model;

import com.hotstrip.runapi.domain.ResEnum;

import java.util.HashMap;

public class Res extends HashMap<String, Object> {
    private static final String CODE = "code";
    private static final String MSG = "msg";
    private static final String DATA = "data";

    private static final String CODE_OK = "0";
    private static final String MSG_OK = "ok";

    public Res add(String key, Object value) {
        super.put(key, value);
        return this;
    }
    public static Res ok() {
        return new Res()
                .add(CODE, CODE_OK).add(MSG, MSG_OK);
    }

    public static Res okData(Object data) {
        return new Res()
                .add(CODE, CODE_OK).add(MSG, MSG_OK)
                .add(DATA, data);
    }

    public static Res error(ResEnum resEnum) {
        return new Res()
                .add(CODE, resEnum.getCode()).add(MSG, resEnum.getMsg());
    }

    public static Res error(String code, String msg) {
        return new Res()
                .add(CODE, code).add(MSG, msg);
    }
}
