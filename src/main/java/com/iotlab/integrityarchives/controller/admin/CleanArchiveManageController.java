package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.CleanArchive;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.CleanArchiveService;
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
@RequestMapping("/manage/cleanArchive")
@Api(tags = "廉政档案控制逻辑", value = "测试")
public class CleanArchiveManageController extends BaseController {

    @Autowired
    private CleanArchiveService cleanArchiveService;

    /**
     * 分页查询
     *
     * @param queryPage
     * @param user
     * @return
     */
    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, CleanArchive cleanArchive) {
        //根据条件模糊查询
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> cleanArchiveService.findByPage(cleanArchive)));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(cleanArchiveService.findById(id));
    }

    @GetMapping(value = "/findByUserId")
    public ResponseCode findByUserId(@RequestParam("userId") Integer userId) {
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


    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody CleanArchive cleanArchive) {
        try {
            cleanArchive.setEnableStatus(EnableStatusEnum.PASS.getCode());
            cleanArchive.setCreateTime(new Date());
            cleanArchive.setLastEditTime(cleanArchive.getCreateTime());
            cleanArchiveService.save(cleanArchive);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            cleanArchiveService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }



}
