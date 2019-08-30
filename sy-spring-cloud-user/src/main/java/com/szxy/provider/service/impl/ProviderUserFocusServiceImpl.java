package com.szxy.provider.service.impl;

import com.szxy.mapper.UserFocusMapper;
import com.szxy.pojo.Focus;
import com.szxy.provider.service.ProviderUserFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/28 15:29
 * @Description:com.szxy.provider.service.impl
 * @Version:1.0
 **/
@Service
public class ProviderUserFocusServiceImpl implements ProviderUserFocusService {

    @Autowired
    private UserFocusMapper  userFocusMapper;

    @Override
    public void addGoodsFocusService(Integer goodId, Integer userId) {
        this.userFocusMapper.addGoodsFocusMapper(goodId,userId);
    }

    @Override
    public List<Focus> findGoodsFocusByUserIdService(Integer userId) {
        return this.userFocusMapper.selGoodsFocusByUserIdMapper(userId);
    }

    @Override
    @Transactional
    public void delFocusGoodsService(Integer goodId) {
        this.userFocusMapper.delFocusGoodsMapper(goodId);
    }

}
