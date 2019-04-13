package com.iotlab.integrityarchives.entity;

import java.util.Date;

public class Admin {
    private Integer adminId;
    private String username;
    private String password;
    private Integer level;
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;

    public Admin() {
    }

    public Admin(Integer adminId, Integer enableStatus) {
        this.adminId = adminId;
        this.enableStatus = enableStatus;
    }

    public Admin(Integer adminId) {
        this.adminId = adminId;
    }

    public Admin(Integer adminId, String username, String password, Integer level, Date createTime, Date lastEditTime, Integer enableStatus) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.level = level;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.enableStatus = enableStatus;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
