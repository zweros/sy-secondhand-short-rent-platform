package com.szxy.service.impl;

import com.szxy.pojo.Goods;
import com.szxy.pojo.Orders;
import com.szxy.pojo.Purse;
import com.szxy.pojo.User;
import com.szxy.service.GoodsService;
import com.szxy.service.OrdersService;
import com.szxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单服务业务层   假的实现类
 *
 * @Auther:zwer
 * @Date:2019/11/9 11:40
 * @Description:com.szxy.service.impl
 * @Version:1.0
 **/
@Service
public class OrdersServiceImpl {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

    /**
     * 添加订单
     *
     * @param order
     * @param goodsId
     * @param goodsName
     */
    @Transactional
    public void addOrders(Orders order, Integer goodsId, String goodsName, HttpServletRequest request) {
        try {
            // 补充 Order 中 Goods 数据
            Goods goods = new Goods();
            goods.setName(goodsName);
            goods.setId(goodsId);
            order.setGoods(goods);
            // 补充 OrderDate 时间
            String orderDateStr = new SimpleDateFormat("yyyy-MM-dd mm:HH:ss").format(new Date());
            order.setOrderDate(orderDateStr);
            // 设置订单状态信息  1待发货 2待收货 3已完成
            order.setOrderState(1);
            //  补充卖家用户 ID
            User user = getUserFromSession(request);
            order.setUserId(user.getId());
            this.ordersService.addOrders(order);
            // 购买商品后，做下架处理，修改商品状态  上架1 下架0
            goods.setStatus(0);
            // 更新商品信息
            this.goodsService.updateGoodsService(goods);
            // 扣除商品价格
            Purse purse = this.userService.findPurseByUserId(user.getId());
            purse.setBalance(purse.getBalance() - order.getOrderPrice());
            this.userService.updatePurseByUserId(purse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据用户 ID 查找订单
     *
     * @param request
     * @param model
     */
    public void findOrdersByUserId(HttpServletRequest request, Model model) {
        try {
            User user = getUserFromSession(request);
            //  买家角色 订单
            List<Orders> ordersOfBuyer = this.ordersService.findOrdersByUserIdOfBuyer(user.getId());
            //  卖家角色 订单
            List<Orders> ordersOfSeller = this.ordersService.findOrdersByUserIdOfSeller(user.getId());
            // 查询用户钱包
            Purse purse = this.userService.findPurseByUserId(user.getId());
            // 将数据放入 model 中
            model.addAttribute("myPurse", purse);
            model.addAttribute("orders", ordersOfBuyer);
            model.addAttribute("ordersOfSell", ordersOfSeller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从 session 中获取用户信息
     *
     * @param request HttpServletRequest 对象
     * @return
     */
    private User getUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("cur_user");
    }

    /**
     * 以卖家身份发货， 更新订单状态
     *
     * @param orders
     */
    public void updateOrdersStateByOrdersNum(Orders orders) {
        try {
            this.ordersService.updateOrdersStateByOrdersNum(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 买家收货， 卖家收钱
     *
     * @param orderNum
     * @param orderPrice
     * @param goodsId
     */
    public void receipt(Long orderNum, Float orderPrice, Integer goodsId) {
        // 买家 收货,更新商品当前物流状态  订单状态 1待发货 2待收货 3已完成
        Orders orders = new Orders();
        orders.setOrderNum(orderNum);
        orders.setOrderPrice(orderPrice);
        orders.setOrderState(3); //完成
        // 更新订单信息
        this.ordersService.updateOrdersStateByOrdersNum(orders);

        // 根据 goodsID 查询卖家用户 ID
        Goods goods = this.goodsService.findByGoodIdService(goodsId);
        Purse purse = this.userService.findPurseByUserId(goods.getUserId());
        purse.setBalance(purse.getBalance() + orderPrice);
        // 卖家钱包信息更新，增加金额
        this.userService.updatePurseByUserId(purse);
    }
}

