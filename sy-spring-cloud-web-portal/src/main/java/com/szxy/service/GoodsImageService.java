package com.szxy.service;

import com.szxy.provider.service.ProviderFeignGoodsImageService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 商品图片服务
 */
@FeignClient("sy-spring-cloud-goods-service")
public interface GoodsImageService extends  ProviderFeignGoodsImageService{

}
