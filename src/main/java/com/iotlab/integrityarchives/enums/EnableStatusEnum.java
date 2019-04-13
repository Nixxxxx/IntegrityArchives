package com.iotlab.integrityarchives.enums;

public enum EnableStatusEnum {
    DELETED(-1, "已删除"),
    VERIFY(0, "审核中"),
    PUBLISHED(1, "已发布");

    private Integer code;
    private String message;

    private EnableStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
