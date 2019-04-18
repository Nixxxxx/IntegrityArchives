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
@Table(name = "tb_admin")
public class Admin {

    @Id
    private Integer id;

    @NotNull
    private String adminNumber;
    @NotNull
    private String adminPasswd;
    private String level;

    private Date createTime;
    private Date lastEditTime;
    private String enableStatus;


}
