package com.iotlab.integrityarchives.enums;

/**
 * enableStatus状态 1：审核通过  2：待审核  3：未通过审核
 */
public enum EnableStatusEnum {
    PASS(1, "审核通过"),
    VERIFYING(2, "审核中"),
    REJECTED(3, "未通过审核");

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
