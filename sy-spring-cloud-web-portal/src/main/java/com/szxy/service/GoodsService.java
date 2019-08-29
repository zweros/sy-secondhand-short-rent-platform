package com.szxy.service;


import com.szxy.provider.service.ProviderFeignGoodsService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * There was an unexpected error (type=Internal Server Error, status=500).
 * status 404 reading GoodsService#findByCatelogGoodsIdService(Integer,Integer); content: {"timestamp":1566974951215,"status":404,"error":"Not Found","message":"No message available",
 * "path":"/catelogGoods"}
 */

/**
 * 物品服务
 *  Feign 注意： Only single inheritance supported: GoodsService
 */
//@FeignClient(name="sy-spring-cloud-goods-service",fallback = GoodsServiceFallback.class)
@FeignClient(name="sy-spring-cloud-goods-service")
public interface GoodsService extends ProviderFeignGoodsService {

}
