package com.szxy.service.impl;

import com.szxy.mapper.OrdersMapper;
import com.szxy.pojo.Orders;
import com.szxy.service.ProviderOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/11/9 14:20
 * @Description:com.szxy.service.impl
 * @Version:1.0
 **/
@Service
public class ProviderOrdersServiceImpl implements ProviderOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public void addOrders(Orders order) {
        try{
            this.ordersMapper.addOrders(order);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Orders> findOrdersByUserIdOfBuyer(Integer userId) {
        return this.ordersMapper.findOrdersByUserIdOfBuyer(userId);
    }

    @Override
    public void updateOrdersStateByOrdersNum(Orders orders) {
        this.ordersMapper.updateOrdersStateByOrdersNum(orders);
    }

    @Override
    public List<Orders> findOrdersByUserIdOfSeller(Integer userId) {
        return this.ordersMapper.findOrdersByUserIdOfSeller(userId);
    }

}
