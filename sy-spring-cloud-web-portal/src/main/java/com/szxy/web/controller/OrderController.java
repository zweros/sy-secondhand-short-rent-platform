package com.szxy.web.controller;

import com.szxy.pojo.Orders;
import com.szxy.service.impl.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther:zwer
 * @Date:2019/11/9 10:58
 * @Description:com.szxy.web.controller
 * @Version:1.0
 **/
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrdersServiceImpl ordersService;

    /**
     * 生成订单
     *
     * @param order 订单对象
     * @return
     */
    @RequestMapping(value = "/addOrders", method = RequestMethod.POST)
    public String addOrders(Orders order, Integer goodsId, String goodsName, HttpServletRequest request) {
        this.ordersService.addOrders(order, goodsId, goodsName, request);
        return "redirect:/orders/myOrders";
    }

    /**
     * 订单中心
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/myOrders", method = RequestMethod.GET)
    public String findOrdersByUserId(HttpServletRequest request, Model model) {
        this.ordersService.findOrdersByUserId(request, model);
        return "/user/orders";  // 跳转到订单页面
    }

    /**
     * 订单状态变更
     *
     * @param ordersNum
     * @return
     */
    @RequestMapping(value = "/deliver/{ordersNum}", method = RequestMethod.GET)
    public String deliver(@PathVariable Long ordersNum) {
        // 卖家 发货,更新商品当前物流状态  订单状态 1待发货 2待收货 3已完成
        Orders orders = new Orders();
        orders.setOrderNum(ordersNum);
        orders.setOrderState(2);
        this.ordersService.updateOrdersStateByOrdersNum(orders);
        // 通知卖家 已发货，等待签收
        // TODO: 2019/11/10
        return "redirect:/orders/myOrders";  // 跳转到订单页面
    }

    /**
     * 买家收货
     *
     * @param orderNum   订单号
     * @param orderPrice 订单金额
     * @param goodsId    物品 ID
     * @return
     */
    @RequestMapping(value = "/receipt", method = RequestMethod.GET)
    public String receipt(Long orderNum, Float orderPrice, Integer goodsId) {
         this.ordersService.receipt(orderNum, orderPrice, goodsId);
        return "redirect:/orders/myOrders";
    }
}
