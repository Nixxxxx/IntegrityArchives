package com.iotlab.integrityarchives.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserRouterController {

    /**
     * 用户信息页
     *
     * @return
     */
    @GetMapping(value = {"/info"})
    public String admin() {
        return "user/page/info";
    }

    /**
     *  用户家庭成员页
     *
     * @return
     */
    @GetMapping(value = {"/family"})
    public String user() {
        return "user/page/family";
    }

}
