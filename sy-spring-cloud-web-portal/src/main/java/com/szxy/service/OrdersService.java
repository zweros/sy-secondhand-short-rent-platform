package com.szxy.service;

import com.szxy.pojo.Orders;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 订单  feign 客户端
 *
 * @Auther:zwer
 * @Date:2019/11/9 11:41
 * @Description:com.szxy.service
 * @Version:1.0
 **/
@FeignClient(name="sy-spring-clound-zuul-gateway")
public interface OrdersService {

    /**
     * 添加订单
     *
     * @param order Orders 对象
     */
    @RequestMapping(value = "/goods/order/addOrders", method = RequestMethod.POST)
    void addOrders(@RequestBody Orders order);

    /**
     * 以买家身份查看订单列表
     *
     * @param userId 买家用户 ID
     * @return
     */
    @RequestMapping(value = "/goods/order/findOrdersByUserIdOfBuyer", method = RequestMethod.GET)
    List<Orders> findOrdersByUserIdOfBuyer(@RequestParam("userId") Integer userId);

    /**
     * 以卖家身份查看订单列表
     *
     * @param userId  卖家用户 ID
     * @return
     */
    @RequestMapping(value = "/goods/order/findOrdersByUserIdOfSeller", method = RequestMethod.GET)
    List<Orders> findOrdersByUserIdOfSeller(@RequestParam("userId") Integer userId);

    /**
     * 更新订单状态
     *
     * @param orders   Orders 对象
     */
    @RequestMapping(value = "/goods/order/updateOrdersStateByOrdersNum", method = RequestMethod.POST)
    void updateOrdersStateByOrdersNum(@RequestBody  Orders orders);

}
