package com.szxy.provider.service;

import com.szxy.pojo.Orders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 服务提供端   订单服务
 *
 * @Auther:zwer
 * @Date:2019/11/9 14:15
 * @Description:com.szxy.provider.service
 * @Version:1.0
 **/
public interface ProviderFeignOrdersService {

    /**
     * 添加订单
     *
     * @param order Order  对象
     */
    @RequestMapping(value = "/order/addOrders", method = RequestMethod.POST)
    void addOrders(@RequestBody Orders order);

    /**
     * 以买家身份查看订单列表
     *
     * @param userId 买家用户 ID
     * @return
     */
    @RequestMapping(value = "/order/findOrdersByUserIdOfBuyer", method = RequestMethod.GET)
    List<Orders> findOrdersByUserIdOfBuyer(@RequestParam("userId") Integer userId);

    /**
     * 以卖家身份查看订单列表
     *
     * @param userId 卖家用户 ID
     * @return
     */
    @RequestMapping(value = "/order/findOrdersByUserIdOfSeller", method = RequestMethod.GET)
    List<Orders> findOrdersByUserIdOfSeller(@RequestParam("userId") Integer userId);

    /**
     * 卖家发货，更新订单状态
     *
     * @param orders  Orders 对象
     */
    @RequestMapping(value = "/order/updateOrdersStateByOrdersNum", method = RequestMethod.POST)
    void updateOrdersStateByOrdersNum(@RequestBody  Orders orders);

}
