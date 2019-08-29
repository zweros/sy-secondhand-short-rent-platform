package com.szxy.goods;

import com.szxy.WebPortalApp;
import com.szxy.pojo.Catelog;
import com.szxy.pojo.Goods;
import com.szxy.pojo.Image;
import com.szxy.service.impl.GoodsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Auther:zwer
 * @Date:2019/8/25 21:44
 * @Description:com.szxy.goods
 * @Version:1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={WebPortalApp.class})
public class CatelogGoodsTest {

    @Autowired
    private GoodsServiceImpl goodsService;

    @Test
    public void testGood(){
        Catelog cate = this.goodsService.findCatelogInfoById(1);
        System.out.println(cate);
    }

    @Test
    public void testGoodsCatelog(){
        Map<Goods, Image> map = this.goodsService.findAllGoodsByCid(7);
        Set<Goods> set = map.keySet();
        Iterator<Goods> iterator  = set.iterator();
        while(iterator.hasNext()){
            Goods good = iterator.next();
            Image img = map.get(good);
            System.out.println(good+" "+img);
        }

    }

}
