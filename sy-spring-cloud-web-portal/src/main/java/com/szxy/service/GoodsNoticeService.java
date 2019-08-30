package com.szxy.service;

import com.szxy.provider.service.ProviderFeignGoodsNoticeService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Auther:zwer
 * @Date:2019/8/29 16:36
 * @Description:com.szxy.service
 * @Version:1.0
 **/

@FeignClient("sy-spring-cloud-goods-service")
public interface GoodsNoticeService extends ProviderFeignGoodsNoticeService {

}
