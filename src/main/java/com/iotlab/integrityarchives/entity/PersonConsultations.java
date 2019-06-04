package com.iotlab.integrityarchives.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注 廉政档案实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_person_consultations")
public class PersonConsultations {

	@Id
    private Integer id;    //id 唯一标识，自增长策略
    private Integer userId;   //外键关联，TODO 需要加上事务
    private String year; //本年已经上传内容
    private String path; //文件上传路径
    private String name; //姓名
}
