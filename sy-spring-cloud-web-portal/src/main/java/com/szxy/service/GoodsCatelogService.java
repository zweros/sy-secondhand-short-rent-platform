package com.szxy.service;

import com.szxy.provider.service.ProviderFeignGoodsCatelogService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Auther:zwer
 * @Date:2019/8/25 18:49
 * @Description:com.szxy.service
 * @Version:1.0
 **/
@FeignClient("sy-spring-cloud-goods-service")
public interface GoodsCatelogService extends ProviderFeignGoodsCatelogService {
}
