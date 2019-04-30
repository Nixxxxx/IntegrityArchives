package com.iotlab.integrityarchives.Testmokuai;

import com.iotlab.integrityarchives.dao.UserInfoDao;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.entity.Userfamily;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/29
 * @备注
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class TestWord {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    @Ignore
    public void testuserInfoDao() {
        System.out.println(userInfoDao.findUserInfoByuserId(43));
    }


    @Test
    public void test() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        UserInfo userInfo = userInfoDao.findUserInfoByuserId(43);
        List<Userfamily> userFamilyList = userInfo.getUserFamilyList();

        try {
            dataMap.put("name", userInfo.getName());
            dataMap.put("gender", userInfo.getGender());
            dataMap.put("dateOfBirth", userInfo.getDateOfBirth());
            dataMap.put("nation", userInfo.getNation());
            dataMap.put("nativePlace", userInfo.getNativePlace());
            dataMap.put("chushengdi",userInfo.getPlaceOfBirth());
            dataMap.put("rudangdate", userInfo.getDateOfJoinParty());
            dataMap.put("canjiadate", userInfo.getDateOfJoinWork());
            dataMap.put("zhuanchang", userInfo.getFamiliarMajorAndSpecialty());
            dataMap.put("jiangcheng", userInfo.getRewardsAndPunishment());
            dataMap.put("niandukaohe",userInfo.getAnnualAssessmentResults());
            dataMap.put("zhuanye", userInfo.getTechnicalPosition());
            dataMap.put("jiankang", userInfo.getPhysicalCondition());
            dataMap.put("quanrizhi", userInfo.getFullTimeDegree());
            dataMap.put("yuanxiao", userInfo.getFullTimeGraduatedUniversityAndMajor());
            dataMap.put("zaizhi", userInfo.getPartTimeDegree());
            dataMap.put("biyeyuanxiao", userInfo.getPartTimeGraduatedUniversityAndMajor());

            dataMap.put("xianren", userInfo.getCurrentPosition());
            dataMap.put("jianli", userInfo.getResume());


        /* for(int i=0;i<userFamilyList.size();i++)
         {
             dataMap.put("")
         }*/



            //日期
            // dataMap.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(new SimpleDateFormat("yyyy-MM-dd").parse("2018-09-19")));
            //

            //Configuration 用于读取ftl文件
            Configuration configuration = new Configuration(new Version("2.3.8"));
            configuration.setDefaultEncoding("utf-8");

            /**
             * 以下是两种指定ftl文件所在目录路径的方式，注意这两种方式都是
             * 指定ftl文件所在目录的路径，而不是ftl文件的路径
             */
            //指定路径的第一种方式（根据某个类的相对路径指定）
            System.out.println(this.getClass());
            configuration.setClassForTemplateLoading(this.getClass(), "/");

            //指定路径的第二种方式，我的路径是C：/a.ftl
            //configuration.setDirectoryForTemplateLoading(new File("c:/"));

            //输出文档路径及名称
            File outFile = new File("D:/" +  userInfo.getName() + "报销信息导出.doc");

            //以utf-8的编码读取ftl文件
            Template template = configuration.getTemplate("干部基本信息表.ftl", "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
            template.process(dataMap, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
