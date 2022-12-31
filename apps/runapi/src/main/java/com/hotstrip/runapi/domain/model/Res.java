package com.hotstrip.runapi.domain.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Res<T> extends HashMap {
    private String code;
    private String msg;
    private T data;

    private static final String CODE_OK = "0";
    private static final String MSG_OK = "ok";

    public static Res ok() {
        return Res.builder().code(CODE_OK).msg(MSG_OK)
                .build();
    }

    public static Res okData(Object data) {
        return Res.builder().code(CODE_OK).msg(MSG_OK).data(data)
                .build();
    }

    public static Res okPageList(Page<?> data) {
        return Res.builder().code(CODE_OK).msg(MSG_OK).data(data)
                .build();
    }

    public static Res error(String code, String msg) {
        return Res.builder().code(code).msg(msg)
                .build();
    }
}
