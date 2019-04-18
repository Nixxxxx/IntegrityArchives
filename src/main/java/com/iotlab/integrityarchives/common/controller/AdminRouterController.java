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
     * 发布文章页
     *
     * @return
     */
    @GetMapping(value = {"/user"})
    public String publish(Model model) {
        return "admin/page/user";
    }
}
