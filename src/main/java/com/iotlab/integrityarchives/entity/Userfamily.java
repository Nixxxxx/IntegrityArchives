package com.iotlab.integrityarchives.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user_family")
public class UserFamily {

    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "user_id")
    private Integer userId;



    @Column(name = "appellation")
    private String appellation;  //称谓
    @Column(name = "user_family_name")
    private String userFamilyName;        //姓名
    @Column(name = "age")
    private String age;        // 年龄
    @Column(name = "politics_status")
    private String politicsStatus; //政治面貌

    @Column(name = "work_unit_and_position")
    private String workUnitAndPosition; //工作单位及职务
    @Column(name = "create_time")
    private Date createTime;

    private Date lastEditTime;


}
