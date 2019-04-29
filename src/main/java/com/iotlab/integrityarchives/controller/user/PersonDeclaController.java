package com.iotlab.integrityarchives.controller.user;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.PersonDecla;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.PersonDeclaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@SuppressWarnings("all")
@RequestMapping("/user/personDecla")
@Api(tags = "个人申报记录实体控制", value = "测试")
public class PersonDeclaController extends BaseController {

    @Autowired
    private PersonDeclaService personDeclaService;

    @GetMapping(value = "/findByUserId")
    public ResponseCode findByUserId(@RequestParam("id") Integer userId) {
        return ResponseCode.success(personDeclaService.findByUserId(userId));
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody PersonDecla personDecla) {
        try {
            personDecla.setLastEditTime(new Date());
            personDecla.setEnableStatus(EnableStatusEnum.VERIFYING.getCode());
            personDeclaService.update(personDecla);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
