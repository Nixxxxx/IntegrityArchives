package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.UserFamily;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.UserInfoService;
import com.iotlab.integrityarchives.util.FilePathUtil;

import com.iotlab.integrityarchives.util.PrintUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
@Api(tags = "干部信息控制API", value = "测试")
public class UserInfoManageController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/findByUserId")  //TODO   根据UserId查询的时候查不到数据的时候返回ResponseCode.error()
    public ResponseCode findByUserId(@RequestParam("userId") Integer userId) {
        return ResponseCode.success(userInfoService.ExportUserInfo(userId));
    }

    //导出word
    @GetMapping("/print")
    public void printUserInfo(@RequestParam("userId") Integer userId, HttpServletResponse response) {
        //查询数据
        UserInfo userInfo = userInfoService.ExportUserInfo(userId);

        List<UserFamily> userFamilyList = userInfo.getUserFamilyList();
        try {
            //生成数据到word,
            Map<String, Object> dataMap = userInfoService.exportUserInfoToWordFile(userInfo);
            //导出word并下载
            PrintUtil.exportMillCertificateWord(response, dataMap, "d:/", "干部基本信息表.ftl", userInfo.getName());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("遇到错误了");
        }
    }

    @PostMapping("/update")
    public ResponseCode update(UserInfo userInfo, @RequestParam(value = "image", required = false) MultipartFile file, HttpServletRequest request) {
        try {

            System.out.println("从前端得到的UserInfo数据为：" + userInfo);
            userInfo.setLastEditTime(new Date());

            String os = System.getProperty("os.name");
            //如果图片不为空 则处理图片逻辑
            if (file != null) {
                String fileName = userInfo.getName() + file.getOriginalFilename();
                //保证图片名唯一性：用户名+图片本来名字
                if (os.toLowerCase().startsWith("linux")) {
                    userInfo.setAvatar("http://www.springboot.xyz:8080/pictures/" + FilePathUtil.PathUtil(file,fileName));
                } else
                    userInfo.setAvatar(FilePathUtil.PathUtil(file,fileName));
            }
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
