package com.iotlab.integrityarchives.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/16
 * @备注
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user_info")
public class UserInfo {


    @Id
    private Integer Id;

    @NotNull
    private String userNumber;

    @NotNull
    private String userPasswd;

    private String userInfo;

    private String salt;

    private Date createTime;

    private Date lastEdittime;


    private String enableStatus;



}
