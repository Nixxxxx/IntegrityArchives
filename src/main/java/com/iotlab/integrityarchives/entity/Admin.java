package com.iotlab.integrityarchives.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_admin")
public class Admin {

    @Id
    @NotNull
    private Integer id;

    @NotBlank(message = "管理员工号不允许为空")
    private String adminNumber;

    @NotBlank(message = "管理员密码不允许为空")
    private String adminPasswd;
    private String level;

    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;

}
