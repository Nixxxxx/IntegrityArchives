package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.PersonDecla;
import com.iotlab.integrityarchives.entity.PersonDecla;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.PersonDeclaService;
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
@RequestMapping("/manage/personDecla")
@Api(tags = "个人申报记录实体控制", value = "测试")
public class PersonDeclaController extends BaseController {

    @Autowired
    private PersonDeclaService personDeclaService;

    /**
     * 分页查询
     *
     * @param queryPage
     * @param user
     * @return
     */
    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, PersonDecla personDecla) {
        //根据条件模糊查询
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> personDeclaService.findByPage(personDecla)));
    }


    @GetMapping(value = "/findById")  //TODO
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(personDeclaService.findByUserId(id));
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody PersonDecla personDecla) {
        try {
            personDecla.setLastEditTime(new Date());
            personDeclaService.update(personDecla);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody PersonDecla personDecla) {
        try {
            personDecla.setEnableStatus(EnableStatusEnum.PASS.getCode());
            personDecla.setCreateTime(new Date());
            personDecla.setLastEditTime(personDecla.getCreateTime());
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
