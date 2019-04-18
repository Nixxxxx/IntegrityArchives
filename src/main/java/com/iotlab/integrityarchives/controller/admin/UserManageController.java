package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.service.AdminService;
import com.iotlab.integrityarchives.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private UserService userService;

    /**
     * 测试成功 URL:http://127.0.0.1:8080/manage/user/findById?id=1
     * @param id
     * @return
     */
    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(userService.findById(id));
    }

    /**
     * 测试成功 url:http://127.0.0.1:8080/manage/user/findAll
     * @return
     */
    @GetMapping(value="/findAll")
    public List<User> findAll(){
        System.out.println("进入访问用户列表路径");
        return userService.findAll();
    }






}
