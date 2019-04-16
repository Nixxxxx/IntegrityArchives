package com.iotlab.integrityarchives.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TyCoding
 * @date 2018/10/3
 */
@Controller
@RequestMapping("/admin")
public class RouterController {

    /**
     * 发布文章页
     *
     * @return
     */
    @GetMapping(value = {"/article/publish"})
    public String publish(Model model) {
        return "admin/page/publish";
    }

    /**
     * 跳转到文件编辑页
     *
     * @param id
     * @return
     */
    @RequestMapping(value = {"/article/edit/{id}"})
    public String edit(@PathVariable("id") Long id, Model model) {
        if (id == null || id == 0) {
            return "admin/page/article";
        } else {
            return "admin/page/edit";
        }
    }

    /**
     * 文章管理页
     *
     * @return
     */
    @GetMapping(value = {"/article"})
    public String article(Model model) {
        return "admin/page/article";
    }

    /**
     * 评论管理页
     *
     * @return
     */
    @GetMapping(value = {"/comments"})
    public String comment(Model model) {
        return "admin/page/comments";
    }

    /**
     * 分类标签页
     *
     * @return
     */
    @GetMapping(value = {"/category"})
    public String category(Model model) {
        return "admin/page/category";
    }

    /**
     * 封面管理页
     *
     * @return
     */
    @GetMapping(value = {"/cover"})
    public String attach(Model model) {
        return "admin/page/cover";
    }

    /**
     * 友链管理页
     *
     * @return
     */
    @GetMapping(value = {"/links"})
    public String links(Model model) {
        return "admin/page/links";
    }

    /**
     * 用户管理页
     *
     * @return
     */
    @GetMapping(value = {"/user"})
    public String users(Model model) {
        return "admin/page/user";
    }

    /**
     * 系统设置页
     *
     * @return
     */
    @GetMapping(value = {"/setting"})
    public String setting(Model model) {
        return "admin/page/setting";
    }
}
