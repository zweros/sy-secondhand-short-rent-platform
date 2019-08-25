package com.szxy.web.controller;


import com.netflix.discovery.converters.Auto;
import com.szxy.pojo.Catelog;
import com.szxy.pojo.Goods;
import com.szxy.pojo.Image;
import com.szxy.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsServiceImpl goodsService;

    /**
     * 首页显示物品信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/homeGoods.html" ,method=RequestMethod.GET)
    public String showHomeGoods(Model model){
        List<Map<Goods, Image>> list = this.goodsService.findAllCatelogGoods();
        for (int i = 0; i < 8; i++) {
            model.addAttribute(i==0?"catelogGoods":"catelogGoods"+i,list.get(i));
        }
        return "/goods/homeGoods";
    }

    /**
     * 展示单个物品详细信息
     * @param id 物品 ID
     * @return
     */
    @RequestMapping(value="/goodsId/{id}.html")
    public String showDetailGoods(@PathVariable  Integer id,Model model){
        Map<String,Object> map = this.goodsService.findDetailGoods(id);
        Catelog catelog =
                this.goodsService.findCatelogInfoById(((Goods) map.get("goods")).getCatelogId());
        model.addAttribute("goods",map.get("goods"));
        model.addAttribute("img",map.get("img"));
        model.addAttribute("seller",map.get("seller"));
        model.addAttribute("catelog",catelog);
        return "/goods/detailGoods";
    }

    /**
     * 根据物品分类目录显示物品信息
     * 注意： Restful 风格通过地址栏传参时，参数前不可加 @RequestParam(required = false)
     *        required = false 表示可以不需要该参数，但是 Rest 风格通过地址栏传参，这
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping(value="/catelog/{cid}.html",method = RequestMethod.GET )
    public String showGoodsCatelogSingle(@PathVariable Integer cid, Model model){
        Map<Goods, Image> goodsImageMap = this.goodsService.findAllGoodsByCid(cid);
        Catelog catelog = this.goodsService.findCatelogInfoById(cid);
        model.addAttribute("goodsExtendList",goodsImageMap);
        model.addAttribute("catelog",catelog);
        return "/goods/catelogGoods";
    }


}
