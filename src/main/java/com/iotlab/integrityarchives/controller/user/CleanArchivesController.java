package com.iotlab.integrityarchives.controller.user;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.CleanArchives;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.CleanArchivesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@SuppressWarnings("all")
@RequestMapping("/user/cleanArchive")
@Api(tags = "廉政档案控制逻辑", value = "测试")
public class CleanArchivesController extends BaseController {

    @Autowired
    private CleanArchivesService cleanArchivesService;

    @GetMapping(value = "/findByUserId")
    public ResponseCode findByUserId(@RequestParam("id") Integer userId) {
        return ResponseCode.success(cleanArchivesService.findByUserId(userId));
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody CleanArchives cleanArchives) {
        try {
            cleanArchives.setLastEditTime(new Date());
            cleanArchivesService.update(cleanArchives);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
