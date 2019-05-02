package com.iotlab.integrityarchives.controller.user;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.UserFamily;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.UserInfoService;
import com.iotlab.integrityarchives.util.ImageUtil;
import com.iotlab.integrityarchives.util.PrintUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
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
        return ResponseCode.success(userInfoService.findUserInfoByuserId(userId));
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody UserInfo userInfo, @RequestParam("image") MultipartFile file) {
        try {
            userInfo.setLastEditTime(new Date());
            userInfo.setAvater(ImageUtil.imagePath(file));
            userInfoService.update(userInfo);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/testupload")
    public void tesetUpload(@RequestParam("fileName") MultipartFile file) {
        System.out.println(ImageUtil.imagePath(file));
    }


    @GetMapping("/print")
    public void printUserInfo(@RequestParam("userId") Integer userId, HttpServletResponse response) {
        //Map<String, Object> dataMap = new HashMap<String, Object>();
        UserInfo userInfo = userInfoService.findUserInfoByuserId(userId);
        List<UserFamily> userFamilyList = userInfo.getUserFamilyList();
        try {
            Map<String, Object> dataMap = userInfoService.exportWordFile(userInfo);
            PrintUtil.exportMillCertificateWord(response, dataMap, "d:/", "干部基本信息表.ftl");
        } catch (IOException e) {
            e.printStackTrace();
         System.out.println("遇到错误了");
        }

    }

}