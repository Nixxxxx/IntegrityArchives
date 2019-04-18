package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.service.AdminService;
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
@RequestMapping("/manage/admin")
public class AdminManageController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(adminService.findById(id));
    }

    @GetMapping(value="/findAll")
    public List<Admin> findAll(){
        System.out.println("进入访问管理员列表路径");
        return adminService.findAll();
    }

}
