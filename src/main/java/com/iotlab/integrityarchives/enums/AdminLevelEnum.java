package com.iotlab.integrityarchives.enums;

/**
 * 管理员类型枚举
 * @author JH
 *
 */
public enum AdminLevelEnum {

	SUPER_ADMIN(1, "超级管理员"),
	COLLEGE_ADMIN(2, "学院管理员");
	
	private Integer code;
	private String message;
	
	private AdminLevelEnum(Integer code, String message) {
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