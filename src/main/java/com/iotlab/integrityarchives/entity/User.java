package com.iotlab.integrityarchives.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {
    @Id
    private Integer id;

    @NotNull
    private String userNumber;

    @NotNull
    private String userPasswd;

    @NotNull
    private String name;
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;

}
