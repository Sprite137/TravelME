package org.example.entity;

public enum HttpEnum {
    LOGINERROR(601,"用户名或者密码错误");

    final int code;
    final String msg;

    HttpEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
