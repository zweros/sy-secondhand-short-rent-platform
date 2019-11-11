package com.szxy.web.controller;

import com.szxy.pojo.Orders;
import com.szxy.provider.service.ProviderFeignOrdersService;
import com.szxy.service.ProviderOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/11/9 14:17
 * @Description:com.szxy.web.controller
 * @Version:1.0
 **/
@RestController
public class OrdersController implements ProviderFeignOrdersService {

    @Autowired
    private ProviderOrdersService providerOrdersService;

    @Override
    @RequestMapping(value="/order/addOrders", method= RequestMethod.POST)
    public void addOrders(@RequestBody Orders order){
        this.providerOrdersService.addOrders(order);
    }

    @Override
    @RequestMapping(value = "/order/findOrdersByUserIdOfBuyer", method = RequestMethod.GET)
    public List<Orders> findOrdersByUserIdOfBuyer(Integer userId) {
        return this.providerOrdersService.findOrdersByUserIdOfBuyer(userId);
    }

    @Override
    @RequestMapping(value = "/order/findOrdersByUserIdOfSeller", method = RequestMethod.GET)
    public List<Orders> findOrdersByUserIdOfSeller(Integer userId) {
        return this.providerOrdersService.findOrdersByUserIdOfSeller(userId);
    }

    @Override
    @RequestMapping(value = "/order/updateOrdersStateByOrdersNum", method = RequestMethod.POST)
    public void updateOrdersStateByOrdersNum(@RequestBody Orders orders) {
        this.providerOrdersService.updateOrdersStateByOrdersNum(orders);
    }


}
