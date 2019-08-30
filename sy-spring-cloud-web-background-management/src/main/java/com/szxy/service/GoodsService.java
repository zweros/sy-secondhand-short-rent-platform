package com.szxy.service;

import com.szxy.provider.service.ProviderFeignGoodsService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Auther:zwer
 * @Date:2019/8/29 20:32
 * @Description:com.szxy.service
 * @Version:1.0
 **/
@FeignClient(name="sy-spring-cloud-goods-service")
public interface GoodsService extends ProviderFeignGoodsService {

}