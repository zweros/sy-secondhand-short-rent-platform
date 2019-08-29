package com.szxy.service;

import com.szxy.pojo.Catelog;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/25 18:38
 * @Description:com.szxy.service
 * @Version:1.0
 **/
public interface ProviderGoodsCatelogService {

    List<Catelog> findAllCatelogService();

    Catelog  findCatelogById(Integer id);
}
