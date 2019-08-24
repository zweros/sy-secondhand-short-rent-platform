package com.szxy.service;


import com.szxy.provider.service.ProviderFeignGoodsService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("sy-spring-cloud-goods-service")
public interface GoodsService extends ProviderFeignGoodsService {
}
