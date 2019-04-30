package com.iotlab.integrityarchives.controller.user;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.UserInfoService;
import com.iotlab.integrityarchives.service.UserService;
import com.iotlab.integrityarchives.util.Md5Util;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@SuppressWarnings("all")
@RequestMapping("/user/user")
@Api(tags="用户权限控制API",value="测试")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    //TODO 加上用户登录方法（enableStatus==1的用户才可以登录）

    /**
     * 通过用户id查询用户登录信息
     * @param id
     * @return
     */
    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(userService.findById(id));
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody User user) {
        try {
            user.setLastEditTime(new Date());
            if(user.getUserPasswd() != null && !user.getUserPasswd().isEmpty())
                user.setUserPasswd(Md5Util.MD5Encode(user.getUserPasswd(), "utf8"));
            userService.update(user);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
