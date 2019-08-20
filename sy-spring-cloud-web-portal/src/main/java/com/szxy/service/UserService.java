package com.szxy.service;

import com.szxy.provider.service.ProviderFeignUserService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;

@FeignClient("sy-spring-cloud-user")
public  interface UserService extends ProviderFeignUserService {

}
