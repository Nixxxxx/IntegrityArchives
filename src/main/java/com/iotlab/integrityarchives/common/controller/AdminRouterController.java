package com.iotlab.integrityarchives.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminRouterController {

    /**
     * 管理员管理页
     *
     * @return
     */
    @GetMapping(value = {"/admin"})
    public String admin() {
        return "admin/page/admin";
    }

    /**
     *  用户管理页
     *
     * @return
     */
    @GetMapping(value = {"/user"})
    public String user() {
        return "admin/page/user";
    }


}
