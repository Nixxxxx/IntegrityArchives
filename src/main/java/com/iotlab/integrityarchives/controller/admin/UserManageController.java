package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.CleanArchive;
import com.iotlab.integrityarchives.entity.PersonDecla;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.CleanArchiveService;
import com.iotlab.integrityarchives.service.PersonDeclaService;
import com.iotlab.integrityarchives.service.UserInfoService;
import com.iotlab.integrityarchives.service.UserService;
import com.iotlab.integrityarchives.util.Md5Util;
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
    private CleanArchiveService cleanArchiveService;


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
            user.setUserPasswd(Md5Util.MD5Encode(user.getUserPasswd(), "utf8"));
            //判断用户工号（登录帐号）是否存在
            if (userService.countUserNumber(user.getUserNumber()) >= 1) {
                return ResponseCode.RepeaterrorType();
            } else {
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

                CleanArchive cleanArchive = new CleanArchive();
                cleanArchive.setUserId(user.getId());
                cleanArchive.setCreateTime(user.getCreateTime());
                cleanArchive.setLastEditTime(user.getLastEditTime());
                cleanArchive.setEnableStatus(1);
                cleanArchiveService.save(cleanArchive);

                return ResponseCode.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody User user) {
        try {
            user.setLastEditTime(new Date());

            //判断管理员工号是否存在
            List<String> numberList=userService.numberList();

            //得到原来的number
            String oldNumber=userService.findoldNumberById(user.getId());
            //得到现在的number
            String currentNumber=user.getUserNumber();


            if(currentNumber.equals(oldNumber)){
                userService.update(user);
                return ResponseCode.success();
            }else if(numberList.contains(currentNumber)){
                return ResponseCode.RepeaterrorType();
            }else{    userService.update(user);
                return ResponseCode.success();}


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
            cleanArchiveService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


}
