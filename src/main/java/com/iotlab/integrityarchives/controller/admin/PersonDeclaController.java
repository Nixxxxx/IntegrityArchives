package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.PersonDecla;
import com.iotlab.integrityarchives.service.PersonDeclaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注
 **/
@RestController
@SuppressWarnings("all")
@RequestMapping("/manage/admin/manageperson")
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


}
