/*
package com.iotlab.integrityarchives.controller.user;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.service.UserFamilyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SuppressWarnings("all")
@RequestMapping("/user/userFamily")
@Api(tags = "用户家人信息控制API", value = "测试")
public class UserFamilyController extends BaseController {

    @Autowired
    private UserFamilyService userFamilyService;

    @GetMapping(value = "/findByUserId")
    public ResponseCode findByUserId(@RequestParam("id") Integer userId) {
        return ResponseCode.success(userFamilyService.findByUserId(userId));
    }
}
*/
