package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.enums.AccountEnableStatusEnum;
import com.iotlab.integrityarchives.service.AdminService;
import com.iotlab.integrityarchives.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage/admin")
public class AdminManageController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public Admin login(String username, String password) {
        if(adminService.login(username, password))
            return adminService.findByUsername(username);
        return null;
    }

    @PostMapping("/list")
    public List<Admin> list(Integer page) {
        List<Admin> adminList = adminService.findByPage(page, 10);
        return adminList;
    }

    @PostMapping("/create")
    public Map<String, Object> create(Admin admin) {
        return OperationResult.returnOperationResult(adminService.create(admin), "创建");
    }

    @PostMapping("/del")
    public Map<String, Object> delete(Integer id){
        return OperationResult.returnOperationResult(adminService.delete(id), "删除");
    }

    @PostMapping("/update")
    public Map<String, Object> update(Admin admin) {
        return OperationResult.returnOperationResult(adminService.update(admin), "更新");
    }

    @PostMapping("/changeStatus")
    public Map<String, Object> changeEnableStatus(Integer id, Integer status) {
        Admin admin = new Admin(id);
        if(status == AccountEnableStatusEnum.PASS.getCode()){
            admin.setEnableStatus(AccountEnableStatusEnum.PASS.getCode());
            return OperationResult.returnOperationResult(adminService.update(admin), "恢复使用");
        }else if(status == AccountEnableStatusEnum.FAIL.getCode()){
            admin.setEnableStatus(AccountEnableStatusEnum.FAIL.getCode());
            return OperationResult.returnOperationResult(adminService.update(admin), "禁用");
        }else {
            admin.setEnableStatus(AccountEnableStatusEnum.FAIL.getCode());
            return OperationResult.returnOperationResult(adminService.update(admin), "禁用");
        }
    }
}
