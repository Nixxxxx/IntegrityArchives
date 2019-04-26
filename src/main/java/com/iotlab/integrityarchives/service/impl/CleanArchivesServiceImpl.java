package com.iotlab.integrityarchives.service.impl;

import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.CleanArchivesDao;
import com.iotlab.integrityarchives.entity.CleanArchives;
import com.iotlab.integrityarchives.service.CleanArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CleanArchivesServiceImpl extends BaseServiceImpl<CleanArchives> implements CleanArchivesService {

    @Autowired
    private CleanArchivesDao cleanArchivesDao;


    @Override
    public CleanArchives findByUserId(Integer userId) {
        return cleanArchivesDao.findByUserId(userId);
    }

    @Override
    public CleanArchives findById(Integer id) {
        return cleanArchivesDao.findById(id);
    }


    @Override
    public List<CleanArchives> findByPage(CleanArchives cleanArchives) {
        if (cleanArchives.getUserName() != null) {
            return cleanArchivesDao.findListByWord(cleanArchives.getUserName());
        } else
            return cleanArchivesDao.findAllList();
    }


    @Override
    public void update(CleanArchives cleanArchives) {
        if (cleanArchives.getId() != 0) {
            try {
                this.updateNotNull(cleanArchives);
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
                this.batchDelete(ids, "userId", CleanArchives.class);
            } catch (Exception e) {
                e.printStackTrace();
                // throw new GlobalException(e.getMessage());
            }
        }

    }
}
