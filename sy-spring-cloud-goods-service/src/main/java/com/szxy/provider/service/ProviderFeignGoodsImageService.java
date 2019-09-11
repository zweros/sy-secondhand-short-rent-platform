package com.szxy.provider.service;

import com.szxy.pojo.Image;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@RequestMapping("/goods")
public interface ProviderFeignGoodsImageService  {

    @RequestMapping(value="/getImg",method= RequestMethod.GET)
    public Image findGoodsImageByGoodIdService(@RequestParam("goodId") Integer goodId);

    @RequestMapping(value="/addImg",method= RequestMethod.POST)
    void addGoodsImageService(@RequestBody  Image image);
}
