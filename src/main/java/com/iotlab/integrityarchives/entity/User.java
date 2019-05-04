package com.iotlab.integrityarchives.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {
    @Id
    @NotNull
    private Integer id;

    @NotBlank(message = "用户工号不允许为空")
    @Length(min=6,max=10,message = "长度不符合要求")
    private String userNumber;

    @NotBlank(message = "用户密码不允许为空")
    private String userPasswd;

    @NotNull
    private String name;
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;

}
