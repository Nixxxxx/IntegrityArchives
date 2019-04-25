package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.entity.CleanArchives;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.CleanArchivesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注
 **/
@RestController
@SuppressWarnings("all")
@RequestMapping("/manage/cleanArchives")
@Api(tags = "廉政档案控制逻辑", value = "测试")
public class CleanArchivesController extends BaseController {

    @Autowired
    private CleanArchivesService cleanArchivesService;

    /**
     * 分页查询
     *
     * @param queryPage
     * @param user
     * @return
     */
    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, CleanArchives cleanArchives) {
        //根据条件模糊查询
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> cleanArchivesService.findByPage(cleanArchives)));
    }

    @GetMapping(value = "/findById")  //TODO
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(cleanArchivesService.findByUserId(id));
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


    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody CleanArchives cleanArchives) {
        try {
            cleanArchives.setEnableStatus(EnableStatusEnum.PASS.getCode());
            cleanArchives.setCreateTime(new Date());
            cleanArchives.setLastEditTime(cleanArchives.getCreateTime());
            cleanArchivesService.save(cleanArchives);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            cleanArchivesService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }



}
