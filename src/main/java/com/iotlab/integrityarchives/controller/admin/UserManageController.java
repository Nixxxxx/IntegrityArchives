package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/17
 * @备注
 **/
@RestController
@SuppressWarnings("all")
@RequestMapping("/manage/user")
@Api(tags="用户信息控制API",value="测试")
public class UserManageController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 根据id查询指定的用户
     * 测试成功 URL:http://127.0.0.1:8080/manage/user/findById?id=1
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(userService.findById(id));
    }

    /**
     * 查询所有
     * 测试成功 url:http://127.0.0.1:8080/manage/user/findAll
     *
     * @return
     */
    @GetMapping(value = "/findAll")
    public List<User> findAll() {
        System.out.println("进入访问用户列表路径");
        return userService.findAll();
    }



    /**
     * 分页查询
     * @param queryPage
     * @param user
     * @return
     */
    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, User user) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> userService.findByPage(user)));
    }
    
    
    
    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody User user) {
        try {
            userService.save(user);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody User user) {
        try {
            userService.update(user);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            userService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


}
