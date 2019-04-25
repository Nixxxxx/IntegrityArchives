package com.iotlab.integrityarchives.service.impl;

import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.PersonDeclaDao;
import com.iotlab.integrityarchives.entity.PersonDecla;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.PersonDeclaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注
 **/
@Service
@SuppressWarnings("all")
public class PersonDeclaServiceImpl extends BaseServiceImpl<PersonDecla> implements PersonDeclaService {

    @Autowired
    private PersonDeclaDao personDeclaDao;

    @Override
    public PersonDecla findByUserId(Integer id) {
        return personDeclaDao.findPersonDecalByUserId(id);
    }



    @Override
    public List<PersonDecla> findByPage(PersonDecla personDecla) {
        System.out.println("从前端得到的实体为"+personDecla);
        return personDeclaDao.findListByWord(personDecla.getUserName());
    }

    @Override
    public void update(PersonDecla personDecla) {
        if (personDecla.getId() != 0) {
            try {
                this.updateNotNull(personDecla);
            } catch (Exception e) {
                e.printStackTrace();
                //throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            try {
                //通用mapper自带的批量删除
                this.batchDelete(ids, "id", PersonDecla.class);
            } catch (Exception e) {
                e.printStackTrace();
                // throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void save(PersonDecla personDecla) {
        try {
            //passwordHelper.encryptPassword(user); //加密
           personDeclaDao.insert(personDecla);
        } catch (Exception e) {
            e.printStackTrace();
            // throw new GlobalException(e.getMessage());
        }
    }


}
