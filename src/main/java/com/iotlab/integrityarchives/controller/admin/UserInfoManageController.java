package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/17
 * @备注
 **/
@RestController
@SuppressWarnings("all")
@RequestMapping("/manage/userinfo")
@Api(tags="干部信息控制API",value="测试")
public class UserInfoManageController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/findById")
    public ResponseCode findUserInfoById(@RequestParam("id") Integer id) {
        return ResponseCode.success(userInfoService.findByUserId(id));
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody UserInfo userinfo) {
        try {
            userInfoService.save(userinfo);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody UserInfo userinfo) {
        try {
            userInfoService.update(userinfo);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
