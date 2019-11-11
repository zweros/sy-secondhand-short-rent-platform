package com.szxy.provider.service.impl;

import com.szxy.mapper.PurseMapper;
import com.szxy.pojo.Purse;
import com.szxy.provider.service.ProviderPurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther:zwer
 * @Date:2019/11/8 21:10
 * @Description:com.szxy.provider.service.impl
 * @Version:1.0
 **/
@Service
public class ProviderPurseServiceImpl implements ProviderPurseService {

    @Autowired
    private PurseMapper purseMapper;

    @Override
    public Purse findPurseByUserId(Integer userid) {
        return this.purseMapper.findPurseByUserId(userid);
    }

    @Override
    public void addPurse(Integer userId) {
        this.purseMapper.addPurse(userId);
    }

    @Override
    public void updatePurseByUserId(Purse purse) {
        this.purseMapper.updatePurseByUserId(purse);
    }
}
