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
 * @备注 廉政档案实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cleanarchives")
public class CleanArchives {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="")
    private Integer id;    //id 唯一标识，自增长策略

    private Integer user_id;   //外键关联，TODO 需要加上事务

    private String  user_name; //用户姓名

    private String shoushou;  //收受红包、礼金、有价证券及其他受馈赠的情况。

    private String geren;    //个人操办婚丧嫁娶报备及执行情况

    private String peiou;   //配偶及成年子女就业及所在国籍情况

    private String zaiqi;   //个人在企业、社会及其酬取情况

    private String shifou;   //个人是否参与涉矿、涉矿企业经营活动或参与分红情况

    private String niandu; //个人年度科研经费入账使用及财务个人借款情况

    private String yinsi;  //个人因私出入国（境）情况


}
