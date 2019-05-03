package com.iotlab.integrityarchives.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminRouterController {

    @GetMapping(value = {"/index"})
    public String index() {
        return "admin/index";
    }

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

    @GetMapping(value = {"/userFamily"})
    public String userFamily() {
        return "admin/page/family";
    }

    @GetMapping(value = {"/personDecla"})
    public String personDecla() {
        return "admin/page/personDecla";
    }

    @GetMapping(value = {"/cleanArchive"})
    public String cleanArchive() {
        return "admin/page/cleanArchive";
    }
}
