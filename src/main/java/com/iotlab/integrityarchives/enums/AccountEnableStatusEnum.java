package com.iotlab.integrityarchives.enums;

/**
 * 状态枚举
 * @author JH
 *
 */
public enum AccountEnableStatusEnum {

	FAIL(-1, "未绑定"),
	VERIFY(0, "审核中"),
	PASS(1, "审核通过");
	
	private Integer code;
	private String message;
	
	private AccountEnableStatusEnum(Integer code, String message) {
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
