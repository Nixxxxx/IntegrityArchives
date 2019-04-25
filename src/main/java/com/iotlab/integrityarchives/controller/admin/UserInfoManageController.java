package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/17
 * @备注
 **/
@RestController
@SuppressWarnings("all")
@RequestMapping("/manage/userInfo")
@Api(tags="干部信息控制API",value="测试")
public class UserInfoManageController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/findById")  //TODO   根据UserId查询的时候查不到数据的时候返回ResponseCode.error()
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(userInfoService.findByUserId(id));
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody UserInfo userinfo) {
        try {
            userinfo.setLastEditTime(new Date());
            userInfoService.update(userinfo);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
