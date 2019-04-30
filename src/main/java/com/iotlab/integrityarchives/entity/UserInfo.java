package com.iotlab.integrityarchives.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

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
@ToString
@Table(name = "tb_user_info")
public class UserInfo {

    @Id
    private Integer id;

    @NotNull
    private Integer userId; //教工Id

    @Column(name = "name")
    private String name;      //教工姓名

    @Column(name = "avater")
    private String avater;

    @Column(name = "gender")
    private String gender;                              //性别

    @Column(name = "date_of_birth")
    private String dateOfBirth;                         //出生年月

    @Column(name = "nation")
    private String nation;                              //民族

    @Column(name = "native_place")
    private String nativePlace;//籍贯

    @Column(name = "place_of_birth")
    private String placeOfBirth;                       //出生地

    @Column(name = "date_of_join_party")
    private String dateOfJoinParty;                     //入党时间

    @Column(name = "date_of_join_work")
    private String dateOfJoinWork;                      //参加工作时间

    @Column(name = "physical_condition")
    private String physicalCondition;                   //健康情况

    @Column(name = "technical_position")
    private String technicalPosition;                   //专业技术职务

    @Column(name = "familiar_major_and_specialty")
    private String familiarMajorAndSpecialty;           //熟悉专业有何专长

    @Column(name = "full_time_degree")
    private String fullTimeDegree;                      //全日制学历学位

    @Column(name = "full_time_graduated_university_and_major")
    private String fullTimeGraduatedUniversityAndMajor; //全日制毕业院校系及专业

    @Column(name = "part_time_degree")
    private String partTimeDegree;                      //在职学历学位

    @Column(name = "part_time_graduated_university_and_major")
    private String partTimeGraduatedUniversityAndMajor; //在职毕业院校系及专业

    @Column(name = "current_position")
    private String currentPosition;                     //现任职务

    @Column(name = "resume")
    private String resume;                              //简历

    @Column(name = "rewards_and_punishment")
    private String rewardsAndPunishment;                //奖惩情况

    @Column(name = "annual_assessment_results")
    private String annualAssessmentResults;             //年度考核结果

    @Transient
    private List<Userfamily> userFamilyList;

    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;

    public UserInfo(@NotNull  Integer userId,String name,Integer enableStatus ) {
       this.userId=userId;
       this.name=name;
       this.enableStatus=enableStatus;
    }

    public UserInfo(Integer id, @NotNull Integer userId, String name, String gender, String dateOfBirth, String nation) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.nation = nation;
    }
}
