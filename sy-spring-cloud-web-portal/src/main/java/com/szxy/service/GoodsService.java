package com.szxy.service;


import com.szxy.provider.service.ProviderFeignGoodsCatelogService;
import com.szxy.provider.service.ProviderFeignGoodsService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 物品服务
 *  Feign 注意： Only single inheritance supported: GoodsService
 */
@FeignClient("sy-spring-cloud-goods-service")
public interface GoodsService extends ProviderFeignGoodsService{
}
