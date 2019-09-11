package com.szxy.provider.service;

import com.szxy.pojo.Focus;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/28 15:29
 * @Description:com.szxy.provider.service
 * @Version:1.0
 **/
public interface ProviderUserFocusService {

    void addGoodsFocusService(Integer goodId, Integer userId);

    List<Focus> findGoodsFocusByUserIdService(Integer userId);

    void delFocusGoodsService(Integer goodId);
}
