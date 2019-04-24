package com.iotlab.integrityarchives.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注 个人申报记录实体
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_persondecla")
public class PersonDecla {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "")
    private Integer id;

    private Integer user_id;

    private String user_name;

    private String renmian;         //任免情况

    private String renshi;           //人事档案情况

    private String yinbu;          //因不如实报告个人有关事项受到处理的情况

    private String xunshi;         //巡视视察、信访、案件监督管理以及其他方面移交的问题线索和处理情况

    private String kaizhan;     //开展谈话函询、初步核实、审查调查、以及其他工作形成的材料

    private String dangfeng;  //党风廉政意见回复材料

    private String qita;  //其他反映廉政情况的材料


}
