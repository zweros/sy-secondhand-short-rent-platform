package com.szxy.service;

import com.szxy.pojo.Orders;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/11/9 14:19
 * @Description:com.szxy.service
 * @Version:1.0
 **/
public interface ProviderOrdersService {

    /**
     * 添加订单
     *
     * @param order Order 对象
     */
    void addOrders(Orders order);

    /**
     * 以买家身份查询订单
     *
     * @param userId
     * @return
     */
    List<Orders> findOrdersByUserIdOfBuyer(Integer userId);

    /**
     * 以卖家身份查询订单
     *
     * @param userId
     * @return
     */
    List<Orders> findOrdersByUserIdOfSeller(Integer userId);

    /**
     * 更新订单状态
     *
     * @param orders
     */
    void updateOrdersStateByOrdersNum(Orders orders);
}
