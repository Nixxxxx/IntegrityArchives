package com.iotlab.integrityarchives.controller.user;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.CleanArchive;
import com.iotlab.integrityarchives.service.CleanArchiveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@SuppressWarnings("all")
@RequestMapping("/user/cleanArchive")
@Api(tags = "廉政档案控制逻辑", value = "测试")
public class CleanArchiveController extends BaseController {

    @Autowired
    private CleanArchiveService cleanArchiveService;

    @GetMapping(value = "/findByUserId")
    public ResponseCode findByUserId(@RequestParam("id") Integer userId) {
        return ResponseCode.success(cleanArchiveService.findByUserId(userId));
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody CleanArchive cleanArchive) {
        try {
            cleanArchive.setLastEditTime(new Date());
            cleanArchiveService.update(cleanArchive);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
