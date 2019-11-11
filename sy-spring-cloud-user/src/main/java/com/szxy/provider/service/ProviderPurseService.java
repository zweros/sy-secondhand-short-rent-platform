package com.szxy.provider.service;

import com.szxy.pojo.Purse;

/**
 * @Auther:zwer
 * @Date:2019/11/8 21:09
 * @Description:com.szxy.provider.service
 * @Version:1.0
 **/
public interface ProviderPurseService {
    /**
     * 根据用户 ID 查询用户钱包信息
     *
     * @param userid
     * @return
     */
    Purse findPurseByUserId(Integer userid);

    /**
     * 添加用户钱包
     *
     * @param userId
     */
    void addPurse(Integer userId);

    /**
     * 更新钱包信息
     *
     * @param purse
     */
    void updatePurseByUserId(Purse purse);
}
