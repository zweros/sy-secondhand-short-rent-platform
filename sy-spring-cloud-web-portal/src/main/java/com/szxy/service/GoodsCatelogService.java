package com.szxy.service;

import com.szxy.pojo.Catelog;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/25 18:49
 * @Description:com.szxy.service
 * @Version:1.0
 **/
@FeignClient(name="sy-spring-clound-zuul-gateway")
public interface GoodsCatelogService{

    @RequestMapping(value="/goods/catelog",method= RequestMethod.GET)
    public List<Catelog> findAllCatelog();

    @RequestMapping(value="/goods/catelogById",method= RequestMethod.GET)
    public Catelog findCatelogByIdService(@RequestParam("id") Integer  id);

}
