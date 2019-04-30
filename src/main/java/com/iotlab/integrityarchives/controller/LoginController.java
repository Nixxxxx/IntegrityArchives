package com.iotlab.integrityarchives.controller;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.UserToken;
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

    @ResponseBody
    @RequestMapping("/login/admin")
    public ResponseCode adminLogin(Model model,
                              @RequestParam(value = "number", required = false) String number,
                              @RequestParam(value = "password", required = false) String password,
                              @RequestParam(value = "remember", required = false) String remember) {
        if (number != null && password != null) {
            Subject subject = SecurityUtils.getSubject();
            UserToken token = new UserToken(number, Md5Util.MD5Encode(password, "utf8"), "Admin");
            System.out.println(Md5Util.MD5Encode(password, "utf8"));
            if (remember != null && remember.equals("true")) {
                token.setRememberMe(true);
            } else {
                token.setRememberMe(false);
            }
            try {
                subject.login(token);
                return ResponseCode.success(adminService.findByNumber(number));
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
                return ResponseCode.success(userService.findByNumber(number));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseCode.loginError();
    }

    @RequestMapping(value="/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }
}
