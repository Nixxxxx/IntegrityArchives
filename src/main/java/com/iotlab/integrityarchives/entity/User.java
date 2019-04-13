package com.iotlab.integrityarchives.entity;

import java.util.Date;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private String info;
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;

    public User() {
    }

    public User(Integer userId, Integer enableStatus) {
        this.userId = userId;
        this.enableStatus = enableStatus;
    }

    public User(Integer userId, String username, String password, String info, Date createTime, Date lastEditTime, Integer enableStatus) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.info = info;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.enableStatus = enableStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }
}
