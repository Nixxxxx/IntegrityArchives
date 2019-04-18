package com.iotlab.integrityarchives.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
    @Column(name = "user_number")
    private String userNumber; //教工工号

    @Column(name = "name")
    private String name;      //教工姓名

    @Column(name = "gender")
    private String gender;                              //性别

    @Column(name = "date_of_birth")
    private String dateOfBirth;                         //出生年月

    private String nation;                              //民族

    private String nativePlace;                         //籍贯

    private String placeOfBirth;                       //出生地

    @Column(name = "date_of_join_party")
    private String dateOfJoinParty;                     //入党时间

    private String dateOfJoinWork;                      //参加工作时间

    private String physicalCondition;                   //健康情况

    private String technicalPosition;                   //专业技术职务

    private String familiarMajorAndSpecialty;           //熟悉专业有何专长

    private String fullTimeDegree;                      //全日制学历学位

    private String fullTimeGraduatedUniversityAndMajor; //全日制毕业院校系及专业

    private String partTimeDegree;                      //在职学历学位

    private String partTimeGraduatedUniversityAndMajor; //在职毕业院校系及专业

    private String currentPosition;                     //现任职务

    private String resume;                              //简历

    private String rewardsAndPunishment;                //奖惩情况

    private String annualAssessmentResults;             //年度考核结果

    private Date createTime;
    private Date lastEditTime;
    private String enableStatus;


}
