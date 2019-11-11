package com.szxy.mapper;

import com.szxy.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/11/9 14:21
 * @Description:com.szxy.mapper
 * @Version:1.0
 **/
public interface OrdersMapper {

    void addOrders(Orders order);

    List<Orders> findOrdersByUserIdOfBuyer(@Param("buyerId") Integer userId);

    List<Orders> findOrdersByUserIdOfSeller(@Param("sellerId") Integer userId);

    void updateOrdersStateByOrdersNum(Orders orders);
}
