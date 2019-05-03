package com.iotlab.integrityarchives.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserRouterController {


    @GetMapping(value = {"/index"})
    public String index() {
        return "user/index";
    }
    /**
     * 用户信息页
     *
     * @return
     */
    @GetMapping(value = {"/info"})
    public String info() {
        return "user/page/info";
    }

    @GetMapping(value = {"/personDecla"})
    public String personDecla() {
        return "user/page/personDecla";
    }

    @GetMapping(value = {"/cleanArchive"})
    public String cleanArchive() {
        return "user/page/cleanArchive";
    }

}
