package com.iotlab.integrityarchives.service.impl;

import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.CleanArchiveDao;
import com.iotlab.integrityarchives.entity.CleanArchive;
import com.iotlab.integrityarchives.service.CleanArchiveService;
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
public class CleanArchiveServiceImpl extends BaseServiceImpl<CleanArchive> implements CleanArchiveService {

    @Autowired
    private CleanArchiveDao cleanArchiveDao;


    @Override
    public CleanArchive findByUserId(Integer userId) {
        return cleanArchiveDao.findByUserId(userId);
    }

    @Override
    public CleanArchive findById(Integer id) {
        return cleanArchiveDao.findById(id);
    }


    @Override
    public List<CleanArchive> findByPage(CleanArchive cleanArchive) {
        if (cleanArchive.getUserName() != null) {
            return cleanArchiveDao.findListByWord(cleanArchive.getUserName());
        } else
            return cleanArchiveDao.findAllList();
    }


    @Override
    public void update(CleanArchive cleanArchive) {
        if (cleanArchive.getId() != 0) {
            try {
                this.updateNotNull(cleanArchive);
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
                this.batchDelete(ids, "userId", CleanArchive.class);
            } catch (Exception e) {
                e.printStackTrace();
                // throw new GlobalException(e.getMessage());
            }
        }

    }
}
