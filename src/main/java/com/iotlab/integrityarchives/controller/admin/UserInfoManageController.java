package com.iotlab.integrityarchives.controller.admin;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/17
 * @备注
 **/
@RestController
@SuppressWarnings("all")
@RequestMapping("/manage/userInfo")
@Api(tags="干部信息控制API",value="测试")
public class UserInfoManageController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/findByUserId")  //TODO   根据UserId查询的时候查不到数据的时候返回ResponseCode.error()
    public ResponseCode findByUserId(@RequestParam("userId") Integer userId) {
        return ResponseCode.success(userInfoService.findUserInfoByuserId(userId));
    }

    //导出word
    @GetMapping("/print")
    public void printUserInfo(@RequestParam("userId") Integer userId, HttpServletResponse response) {
        //Map<String, Object> dataMap = new HashMap<String, Object>();
        UserInfo userInfo = userInfoService.findUserInfoByuserId(userId);
        List<UserFamily> userFamilyList = userInfo.getUserFamilyList();
        try {
            Map<String, Object> dataMap = userInfoService.exportWordFile(userInfo);
            PrintUtil.exportMillCertificateWord(response, dataMap, "d:/", "干部基本信息表.ftl",userInfo.getName());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("遇到错误了");
        }
    }

    @PostMapping("/update")
    public ResponseCode update(UserInfo userInfo, @RequestParam(value = "image",required = false) MultipartFile file,HttpServletRequest request) {
        try {
            userInfo.setLastEditTime(new Date());
            if(file != null)
                userInfo.setAvatar(ImageUtil.imagePath(file,userInfo.getName()));
            userInfoService.update(userInfo);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            userInfoService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
