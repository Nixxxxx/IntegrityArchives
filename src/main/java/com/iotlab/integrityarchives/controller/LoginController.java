/*
package com.iotlab.integrityarchives.controller;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

*/
/**
 * @author TyCoding
 * @date 2018/10/3
 *//*

@Controller
@SuppressWarnings("all")
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    */
/**
     * 跳转到后台首页
     *
     * @return
     *//*

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("user", this.getCurrentUser());
        return "admin/index";
    }

    */
/**
     * 跳转到登录页
     *
     * @return
     *//*

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @ResponseBody
    @RequestMapping("/admin/login")
    public ResponseCode login(Model model,
                              @RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              @RequestParam(value = "remember", required = false) String remember) {
        if (username != null && password != null) {
            Subject subject = getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            if (remember != null) {
                if (remember.equals("true")) {
                    //说明选择了记住我
                    token.setRememberMe(true);
                } else {
                    token.setRememberMe(false);
                }
            } else {
                token.setRememberMe(false);
            }
            try {
                subject.login(token);

                //记录登录日志
                LoginLog log = new LoginLog();
                //获取HTTP请求
                HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
                String ip = IPUtil.getIpAddr(request);
                log.setIp(ip);
                log.setUsername(super.getCurrentUser().getUsername());
                log.setLocation(AddressUtil.getAddress(ip));
                log.setCreateTime(new Date());
                String header = request.getHeader("User-Agent");
                UserAgent userAgent = UserAgent.parseUserAgentString(header);
                Browser browser = userAgent.getBrowser();
                OperatingSystem operatingSystem = userAgent.getOperatingSystem();
                log.setDevice(browser.getName() + " -- " + operatingSystem.getName());
                loginLogService.saveLog(log);

                model.addAttribute("username", getSubject().getPrincipal());
                return ResponseCode.success();
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        } else {
            throw new GlobalException("用户名或密码错误");
        }
    }

    @GetMapping(value = "/admin/logout")
    public String logout() {
        Subject subject = getSubject();
        subject.logout();
        return "redirect:/admin";
    }

    @GetMapping("/admin/info")
    @ResponseBody
    public ResponseCode getInfo() {
        return ResponseCode.success(this.getCurrentUser());
    }
}
*/
