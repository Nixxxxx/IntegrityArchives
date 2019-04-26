package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.enums.StatusEnums;
import com.iotlab.integrityarchives.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/26
 * @备注
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping("/admin")
@Api(tags="管理员控制API",value="测试")
public class AdminLoginController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String VerityLogin( @RequestParam(value = "userNumber", required = false) String usernumber,
                               @RequestParam(value = "userPasswd", required = false) String userpasswd,
                               @RequestParam(value = "remember", required = false) String remember){
        System.out.println(usernumber+"//"+userpasswd+"//"+remember);
return "redirct: /admin/page/admin";
    }

    @RequestMapping("/logout")public String Logout(){

return "redirct:/admin/user";
    }




}
