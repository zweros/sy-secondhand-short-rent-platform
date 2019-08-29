package com.szxy.provider.service;

import com.szxy.pojo.Catelog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Request;

import java.util.List;

/**
 * 物品类别
 * @Auther:zwer
 * @Date:2019/8/25
 * @Description:com.szxy.provider.service
 * @Version:1.0
 **/

@RequestMapping("/goods")
public interface ProviderFeignGoodsCatelogService {

    @RequestMapping(value="/catelog",method= RequestMethod.GET)
    public List<Catelog> findAllCatelog();

    @RequestMapping(value="/catelogById",method= RequestMethod.GET)
    public Catelog findCatelogByIdService(@RequestParam("id") Integer  id);

}
