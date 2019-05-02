package com.iotlab.integrityarchives.controller.user;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.UserFamily;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.UserInfoService;
import com.iotlab.integrityarchives.util.ImageUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@SuppressWarnings("all")
@RequestMapping("/user/userInfo")
@Api(tags = "干部信息控制API", value = "测试")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/findByUserId")
    public ResponseCode findByUserId(@RequestParam("userId") Integer userId) {
        return ResponseCode.success(userInfoService.findByUserId(userId));
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody UserInfo userInfo) {
        try {
            userInfo.setLastEditTime(new Date());
            //userInfo.setAvater(ImageUtil.imagePath(file));
            userInfoService.update(userInfo);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    @PostMapping(value = "/testupload")
    public void tesetUpload(@RequestParam("fileName") MultipartFile file){
        System.out.println(ImageUtil.imagePath(file));
    }



    @GetMapping("/print")
    public ResponseCode printUserInfo(@RequestParam("userId") Integer userId) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        UserInfo userInfo = userInfoService.findUserInfoByuserId(userId);
        List<UserFamily> userFamilyList = userInfo.getUserFamilyList();
        try {
            dataMap.put("name", userInfo.getName());
            dataMap.put("gender", userInfo.getGender());
            dataMap.put("dateOfBirth", userInfo.getDateOfBirth());
            dataMap.put("nation", userInfo.getNation());
            dataMap.put("nativePlace", userInfo.getNativePlace());
            dataMap.put("chushengdi", userInfo.getPlaceOfBirth());
            dataMap.put("rudangdate", userInfo.getDateOfJoinParty());
            dataMap.put("canjiadate", userInfo.getDateOfJoinWork());
            dataMap.put("zhuanchang", userInfo.getFamiliarMajorAndSpecialty());
            dataMap.put("jiangcheng", userInfo.getRewardsAndPunishment());
            dataMap.put("niandukaohe", userInfo.getAnnualAssessmentResults());
            dataMap.put("zhuanye", userInfo.getTechnicalPosition());
            dataMap.put("jiankang", userInfo.getPhysicalCondition());
            dataMap.put("quanrizhi", userInfo.getFullTimeDegree());
            dataMap.put("yuanxiao", userInfo.getFullTimeGraduatedUniversityAndMajor());
            dataMap.put("zaizhi", userInfo.getPartTimeDegree());
            dataMap.put("biyeyuanxiao", userInfo.getPartTimeGraduatedUniversityAndMajor());
            dataMap.put("xianren", userInfo.getCurrentPosition());
            dataMap.put("jianli", userInfo.getResume());


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

            configuration.setClassForTemplateLoading(this.getClass(), "/");

            //指定路径的第二种方式，我的路径是C：/a.ftl
            //configuration.setDirectoryForTemplateLoading(new File("c:/"));

            //输出文档路径及名称
            File outFile = new File("D:/" + userInfo.getName() + "-信息导出.doc");

            //以utf-8的编码读取ftl文件
            Template template = configuration.getTemplate("干部基本信息表.ftl", "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
            template.process(dataMap, out);
            out.close();
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.Syserror();
        }
    }







}