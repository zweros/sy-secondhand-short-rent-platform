package com.szxy.service.impl;

import com.netflix.discovery.converters.Auto;
import com.szxy.pojo.Catelog;
import com.szxy.pojo.Goods;
import com.szxy.pojo.Image;
import com.szxy.pojo.User;
import com.szxy.service.GoodsCatelogService;
import com.szxy.service.GoodsImageService;
import com.szxy.service.GoodsService;
import com.szxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务消费者   假的接口实现类
 */

@Service
public class GoodsServiceImpl {

    @Value("${REDIS_HOMEGOODS_SHOW_KEY}")
    private  String REDIS_HOMEGOODS_SHOW_KEY ;
    @Value("${DEFAULT_CATELOG_NAME}")
    private String DEFAULT_CATELOG_NAME;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsImageService goodsImageService;
    @Autowired
    private GoodsCatelogService goodsCatelogService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 查看所有物品分类的物品信息
     * 并每个类别显示前 6 条
     * 将其放入 Redis 缓存中
     * @return
     */
    public   List< Map<Goods, Image>>  findAllCatelogGoods() {
        List< Map<Goods, Image>>  list = null;
        // TODO: 2019/8/25   Redis 缓存有些问题  前台页面报错
        /*
        list= (List<Map<Goods, Image>>)
                this.redisTemplate.opsForList().leftPop((REDIS_HOMEGOODS_SHOW_KEY));
        if(list != null){
            return list;
        }*/
        //创建 List 集合，存放物品信息
       list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            //查询所有分类物品，按发布时间倒序排序，每个类别显示 6 个
            List<Goods> goods = this.goodsService.findByCatelogGoodsIdService(i == 0 ? null : i, 6);
            Map<Goods, Image> goodAndImgMap = new HashMap<>();
            for (Goods good : goods) {
                Image img = this.goodsImageService.findGoodsImageByGoodIdService(good.getId());
                goodAndImgMap.put(good, img);
            }
            list.add(goodAndImgMap);
        }
        /*
        //更换序列器
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        //将数据放入 Redis 缓存中
        this.redisTemplate.opsForList().leftPush(REDIS_HOMEGOODS_SHOW_KEY,list);
        */
        return list;
    }

    /**
     * 查询物品详细信息
     * @param id  物品 ID
     * @return
     */
    public  Map<String,Object> findDetailGoods(Integer id){
        Goods goods = this.goodsService.findByGoodIdService(id);
        Image img = this.goodsImageService.findGoodsImageByGoodIdService(goods.getId());
        User seller = this.userService.findSellerInfoByIdService(goods.getUserId());
        Catelog catelog = this.goodsCatelogService.findCatelogByIdService(id);
        Map<String, Object> map = new HashMap<>();
        map.put("goods",goods);
        map.put("img",img);
        map.put("seller",seller);
        map.put("catelog",catelog);
        return map;
    }

    /**
     * 按物品分类查询所有物品信息，按发布时间倒序排序
     * 并将物品信息和物品图片放入 Map 集合中
     * @param cid
     * @return
     */
    public  Map<Goods, Image> findAllGoodsByCid(Integer cid) {
        // TODO: 2019/8/25   考虑后期分页
        List<Goods> goods = null;
        if(cid != null && cid == 0){
            goods = this.goodsService.findByCatelogGoodsIdService(null,null);
        }else{
            goods = this.goodsService.findByCatelogGoodsIdService(cid,null);
        }
        Map<Goods, Image> goodAndImgMap = new HashMap<>();
        for (Goods good : goods) {
            Image img = this.goodsImageService.findGoodsImageByGoodIdService(good.getId());
            goodAndImgMap.put(good, img);
        }
        return goodAndImgMap;
    }

    /**
     * 查询物品分类目录信息
     * @param cid
     * @return
     */
    public Catelog findCatelogInfoById(Integer cid){
        Catelog catelog = null;
        if(cid != null && cid != 0){
            catelog = this.goodsCatelogService.findCatelogByIdService(cid);
        }else{
            catelog = new Catelog();
            try {
                //从配置中心读取是以  ISO-8859-1 编码读取，中文需要转为 utf-8 编码，否则会出现乱码
                catelog.setName(new String(this.DEFAULT_CATELOG_NAME.getBytes("ISO-8859-1"),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return catelog;
    }

}
