package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.CleanArchives;
import com.iotlab.integrityarchives.entity.PersonDecla;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.CleanArchivesService;
import com.iotlab.integrityarchives.service.PersonDeclaService;
import com.iotlab.integrityarchives.service.UserInfoService;
import com.iotlab.integrityarchives.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@Api(tags = "用户信息控制API", value = "测试")
public class UserManageController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PersonDeclaService personDeclaService;
    @Autowired
    private CleanArchivesService cleanArchivesService;


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
     *
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
            user.setEnableStatus(EnableStatusEnum.PASS.getCode());
            user.setCreateTime(new Date());
            user.setLastEditTime(user.getCreateTime());
            //插入后，直接把id返回给主键
            userService.insertUserReturnId(user);
            System.out.println("用户id为" + user.getId());


            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(user.getId());
            userInfo.setName(user.getName());
            userInfo.setCreateTime(user.getCreateTime());
            userInfo.setLastEditTime(user.getLastEditTime());
            userInfo.setEnableStatus(1);
            userInfoService.save(userInfo);

            PersonDecla personDecla = new PersonDecla();
            personDecla.setUserId(user.getId());
            personDecla.setCreateTime(user.getCreateTime());
            personDecla.setLastEditTime(user.getLastEditTime());
            personDecla.setEnableStatus(1);
            personDeclaService.save(personDecla);

            CleanArchives cleanArchives=new CleanArchives();
            cleanArchives.setUserId(user.getId());
            cleanArchives.setCreateTime(user.getCreateTime());
            cleanArchives.setLastEditTime(user.getLastEditTime());
            cleanArchives.setEnableStatus(1);
            cleanArchivesService.save(cleanArchives);

            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody User user) {
        try {
            user.setLastEditTime(new Date());
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
            userInfoService.delete(ids);
            personDeclaService.delete(ids);
            cleanArchivesService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


}
