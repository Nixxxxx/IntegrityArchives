package com.iotlab.integrityarchives.controller;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserToken;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.enums.StatusEnums;
import com.iotlab.integrityarchives.service.AdminService;
import com.iotlab.integrityarchives.service.UserService;
import com.iotlab.integrityarchives.util.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.subject.Subject;

@Controller
@SuppressWarnings("all")
@RequestMapping("/")
public class LoginController extends BaseController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;


    @GetMapping(value = {"/login"})
    public String login() {
        return "login/login";
    }

    @RequestMapping(value="/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    @ResponseBody
    @RequestMapping("/login/admin")
    public ResponseCode adminLogin(Model model,
                              @RequestParam(value = "number", required = false) String number,
                              @RequestParam(value = "password", required = false) String password,
                              @RequestParam(value = "remember", required = false) String remember) {
        if (number != null && password != null) {
            Subject subject = SecurityUtils.getSubject();
            UserToken token = new UserToken(number, Md5Util.MD5Encode(password, "utf8"), "Admin");
            if (remember != null && remember.equals("true")) {
                token.setRememberMe(true);
            } else {
                token.setRememberMe(false);
            }
            try {
                subject.login(token);
                Admin admin = adminService.findByNumber(number);
                if(admin.getEnableStatus() != EnableStatusEnum.PASS.getCode()){
                    return ResponseCode.error(StatusEnums.ACCOUNT_REJECTED);
                }
                return ResponseCode.success(admin);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseCode.loginError();
    }

    @ResponseBody
    @RequestMapping("/login/user")
    public ResponseCode userLogin(Model model,
                              @RequestParam(value = "number", required = false) String number,
                              @RequestParam(value = "password", required = false) String password,
                              @RequestParam(value = "remember", required = false) String remember) {
        if (number != null && password != null) {
            Subject subject = SecurityUtils.getSubject();
            UserToken token = new UserToken(number, Md5Util.MD5Encode(password, "utf8"), "User");
            if (remember.equals("true")) {
                token.setRememberMe(true);
            } else {
                token.setRememberMe(false);
            }
            try {
                subject.login(token);
                User user = userService.findByNumber(number);
                if(user.getEnableStatus() != EnableStatusEnum.PASS.getCode()){
                    return ResponseCode.error(StatusEnums.ACCOUNT_REJECTED);
                }
                return ResponseCode.success(userService.findByNumber(number));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseCode.loginError();
    }

}
