/*
package com.iotlab.integrityarchives.controller.user;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.PersonDecla;
import com.iotlab.integrityarchives.entity.Userfamily;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.UserFamilyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
@SuppressWarnings("all")
@RequestMapping("/user/userFamily")
@Api(tags = "用户家人信息控制API", value = "测试")
public class UserFamilyController extends BaseController {

    @Autowired
    private UserFamilyService userFamilyService;

    @PostMapping("/update")
    public ResponseCode update(@RequestBody Userfamily userfamily) {
        try {
            userfamily.setLastEditTime(new Date());
            userFamilyService.update(userfamily);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody Userfamily userfamily) {
        try {
            userfamily.setCreateTime(new Date());
            userFamilyService.setLastEditTime(personDecla.getCreateTime());
            personDeclaService.save(personDecla);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            personDeclaService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


}
*/
