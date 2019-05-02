package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.UserInfoService;
import com.iotlab.integrityarchives.util.ImageUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/update")
    public ResponseCode update(@RequestBody UserInfo userInfo,@RequestParam("image") MultipartFile file,HttpServletRequest request) {
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
