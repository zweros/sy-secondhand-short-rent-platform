package com.szxy.service;

import com.szxy.provider.service.ProviderFeignGoodsCommentsService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Auther:zwer
 * @Date:2019/8/26 14:54
 * @Description:com.szxy.service
 * @Version:1.0
 **/
@FeignClient("sy-spring-cloud-goods-service")
public interface GoodsCommentsService extends ProviderFeignGoodsCommentsService {
}
