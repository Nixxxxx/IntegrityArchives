package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list(Integer page) {
        List<User> userList = userService.findByPage(page, 10);
        return userList;
    }

    @PostMapping("/create")
    public Map<String, Object> create(User user) {
        Map<String, Object> map = new HashMap<>();
        if(userService.create(user)) {
            map.put("success", true);
            map.put("msg", "添加成功");
        }else {
            map.put("success", false);
            map.put("msg", "添加失败");
        }
        return map;
    }

    @PostMapping("/del")
    public Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<>();
        if(userService.delete(id)) {
            map.put("success", true);
            map.put("msg", "删除成功");
        }else {
            map.put("success", false);
            map.put("msg", "删除失败");
        }
        return map;
    }

    @PostMapping("/update")
    public Map<String, Object> update(User user) {
        Map<String, Object> map = new HashMap<>();
        if(userService.update(user)) {
            map.put("success", true);
            map.put("msg", "更新成功");
        }else {
            map.put("success", false);
            map.put("msg", "更新失败");
        }
        return map;
    }
}
