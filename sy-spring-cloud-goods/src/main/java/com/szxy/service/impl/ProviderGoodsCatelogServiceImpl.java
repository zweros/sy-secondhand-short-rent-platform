package com.szxy.service.impl;

import com.szxy.mapper.GoodsCatelogMapper;
import com.szxy.pojo.Catelog;
import com.szxy.service.ProviderGoodsCatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/25 18:39
 * @Description:com.szxy.service.impl
 * @Version:1.0
 **/
@Service
public class ProviderGoodsCatelogServiceImpl implements ProviderGoodsCatelogService {

    @Autowired
    private GoodsCatelogMapper goodsCatelogMapper;

    @Override
    public List<Catelog> findAllCatelogService() {
        return this.goodsCatelogMapper.selAllCatelogmMapper();
    }

    @Override
    public Catelog findCatelogById(Integer id) {
        return this.goodsCatelogMapper.selCatelogByIdMapper(id);
    }
}
