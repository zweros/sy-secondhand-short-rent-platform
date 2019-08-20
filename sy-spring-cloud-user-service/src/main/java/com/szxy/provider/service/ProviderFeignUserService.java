package com.szxy.provider.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
public interface ProviderFeignUserService {

    @RequestMapping(value="/registerCheck",method = RequestMethod.GET)
    public int registerCheck(@RequestParam("phone") String phone);

}
