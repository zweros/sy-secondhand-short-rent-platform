package com.szxy.service;

import com.szxy.pojo.Image;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品图片服务
 */
@FeignClient(name="sy-spring-clound-zuul-gateway")
public interface GoodsImageService{

    @RequestMapping(value="/goods/getImg",method= RequestMethod.GET)
    public Image findGoodsImageByGoodIdService(@RequestParam("goodId") Integer goodId);

    @RequestMapping(value="/goods/addImg",method= RequestMethod.POST)
    void addGoodsImageService(@RequestBody Image image);

}
