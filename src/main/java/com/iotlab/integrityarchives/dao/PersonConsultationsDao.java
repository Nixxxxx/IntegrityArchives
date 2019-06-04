package com.iotlab.integrityarchives.dao;

import java.util.List;

import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.PersonConsultations;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注
 **/
public interface PersonConsultationsDao extends MyMapper<PersonConsultations> {

	/**
	 * 根据用户id查询哪些年已经上传过个人述职述联内容
	 * @param userId
	 * @return
	 */
    List<PersonConsultations> findAllList(Integer userId);

	void save(PersonConsultations personConsultations);

	PersonConsultations findByUserIdAndYear(PersonConsultations personConsultations);

	void del(int parseInt);

}





