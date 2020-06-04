package com.example.shopping;

import java.io.Serializable;

public class CommonBean implements Serializable {
    private String msg;
    private String code;

    @Override
    public String toString() {
        return "CommonBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
