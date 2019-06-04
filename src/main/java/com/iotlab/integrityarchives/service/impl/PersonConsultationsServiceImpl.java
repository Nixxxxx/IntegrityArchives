package com.iotlab.integrityarchives.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.PersonConsultationsDao;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.entity.CleanArchive;
import com.iotlab.integrityarchives.entity.PersonConsultations;
import com.iotlab.integrityarchives.service.PersonConsultationsService;

import tk.mybatis.mapper.entity.Example;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注
 **/
@Service
@SuppressWarnings("all")
public class PersonConsultationsServiceImpl extends BaseServiceImpl<PersonConsultations> implements PersonConsultationsService {

    @Autowired
    private PersonConsultationsDao personConsultationsDao;

    @Override
    public List<PersonConsultations> findByUserId(Integer userId) {
        return personConsultationsDao.findAllList(userId);
    }

	@Override
	public void save(PersonConsultations personConsultations) {
		personConsultationsDao.save(personConsultations);
	}

	@Override
	public PersonConsultations findByUserIdAndYear(PersonConsultations personConsultations) {
		return personConsultationsDao.findByUserIdAndYear(personConsultations);
	}

	@Override
    public List<PersonConsultations> findByPage(PersonConsultations admin) {
        Example example = new Example(PersonConsultations.class);
        if (!StringUtils.isEmpty(admin.getName())) {
            example.createCriteria().andLike("name", "%" + admin.getName() + "%");
        }
        List<PersonConsultations> list = personConsultationsDao.selectByExample(example);
        return list;
    }

	@Override
	public void del(String id) {
		personConsultationsDao.del(Integer.parseInt(id));
	}

}
