package com.szxy.web.controller;


import com.szxy.pojo.*;
import com.szxy.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
            //System.out.println(list.get(i));
        }
        List<Catelog> catelogs = this.goodsService.getAllCatelog();
        model.addAttribute("catelogs",catelogs);
        return "/goods/homeGoods";
    }

    /**
     * 展示单个物品详细信息
     * @param id 物品 ID
     * @return
     */
    @RequestMapping(value="/goodsId/{id}.html")
    public String showDetailGoods(@PathVariable  Integer id,Model model){
        this.goodsService.findDetailGoods(id,model);
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

    /**
     * 加载物品评论模块
     * @return
     */
    @RequestMapping(value = "/detailGoodsComments/{goodsId}",method = RequestMethod.GET)
    public String showComments(@PathVariable Integer goodsId,Model model){
        List<Comments> comments = this.goodsService.getCmtByGoodsIdService(goodsId);
        model.addAttribute("goodsId",goodsId);
        model.addAttribute("comments",comments);
        return "/goods/detailGoodsComments";
    }


    /**
     * 添加评论
     * @param comment         Comments 对象
     * @param goods_id       被评论物品的 ID
     * @param req           HttpServletRequest 对象
     * @return
     */
    @RequestMapping(value="/addComments",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addComments(Comments comment,
                                          Integer goods_id,
                                          HttpServletRequest req){
        comment.setGoodsId(goods_id);
        Map<String,String> map = this.goodsService.addCommentsService(comment,req);
        return map;
    }

    /**
     * 显示我要发布页面
     * @return
     */
    @RequestMapping(value="/publishGoods",method = RequestMethod.GET)
    public String publishGoods(){
        return "goods/pubGoods";
    }

    /**
     * 提交发布物品的信息
     * @param goods
     * @return
     */
    @RequestMapping(value="/publishGoodsSubmit",method = RequestMethod.POST)
    public String publishGoodsSubmit(Goods goods,MultipartFile myfile,HttpServletRequest req){
        this.goodsService.addGoodsAndImg(goods,myfile,req);
        return "redirect:/user/allGoods"; //发布成功后跳转的页面
    }

    /**
     * 上传物品图片
     * @return
     */
     @RequestMapping(value="/uploadFile",method = RequestMethod.POST)
     @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile myfile) {
         System.out.println(myfile.getOriginalFilename());
         Map<String, Object> map = new HashMap<>();
         map.put("response","http://test/");
         return map;
     }

    /**
     * 搜索物品
     * @param str  搜索关键字
     * @return
     */
     @RequestMapping(value="/search",method = RequestMethod.POST)
    public String searchGoods(String str,Model model) {
         List<GoodsExtend>  goodsExtendList = this.goodsService.searchGoodsService(str);
         model.addAttribute("goodsExtendList",goodsExtendList);
         return "/goods/searchGoods";
     }


    /**
     * 删除物品，不真正删除，仅把物品做下架处理
     * @param goodId
     * @return
     */
    @RequestMapping(value="/deleteGoods/{goodId}",method = RequestMethod.DELETE)
    public String deleteGoods(@PathVariable Integer goodId) {
        // TODO: 2019/8/28  删除
         return "/user/home";
     }

    /**
     * 预更新物品信息
     * @param goodId
     * @return
     */
    @RequestMapping(value="/editGoods/{goodId}",method = RequestMethod.GET)
     public String editGoods(@PathVariable Integer goodId,Model model) {
         GoodsExtend goodsExtend
                 = this.goodsService.preUpdateGoodsService(goodId);
         model.addAttribute("goodsExtend",goodsExtend);
         return "/goods/editGoods";
     }

    /**
     * 更新物品信息
     * @param goods
     * @param model
     * @return
     */
    @RequestMapping(value="/editGoodsSubmit",method = RequestMethod.POST)
     public String editGoodsSubmit(Goods goods,Model model) {
        this.goodsService.updateGoodService(goods);
         return "/user/home";
     }


}

