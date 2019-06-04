package com.iotlab.integrityarchives.service;

import java.util.List;

import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.PersonConsultations;

public interface PersonConsultationsService extends BaseService<PersonConsultations> {

    /**
     * 通过指定用户id查询到用户的信息
     *
     * @param userId
     * @return
     */
	List<PersonConsultations> findByUserId(Integer userId);

	void save(PersonConsultations personConsultations);

	PersonConsultations findByUserIdAndYear(PersonConsultations personConsultations);

	List<PersonConsultations> findByPage(PersonConsultations personConsultations);

	void del(String id);

}
