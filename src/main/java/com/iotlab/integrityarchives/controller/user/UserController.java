package com.iotlab.integrityarchives.controller.user;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.UserInfoService;
import com.iotlab.integrityarchives.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@SuppressWarnings("all")
@RequestMapping("/user/user")
@Api(tags="用户信息控制API",value="测试")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    //TODO 加上用户登录方法（enableStatus==1的用户才可以登录）

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(userService.findById(id));
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody User user) {
        try {
            //先保存用户
            user.setEnableStatus(EnableStatusEnum.PASS.getCode());
            user.setCreateTime(new Date());
            user.setLastEditTime(user.getCreateTime());
            userService.save(user);
            //在保存用户信息
            UserInfo userInfo = new UserInfo(user.getId(), user.getName(), EnableStatusEnum.PASS.getCode());
            userInfo.setCreateTime(user.getCreateTime());
            userInfo.setLastEditTime(user.getCreateTime());
            userInfoService.save(userInfo);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody User user) {
        try {
            user.setLastEditTime(new Date());
            userService.update(user);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
