package com.szxy.goods;

import com.szxy.WebPortalApp;
import com.szxy.pojo.Comments;
import com.szxy.service.impl.GoodsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/26 14:58
 * @Description:com.szxy.goods
 * @Version:1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={WebPortalApp.class})
public class CommentsTest {

    @Autowired
    private GoodsServiceImpl goodsService;


    @Test
    public void getCmtByGoodsIdService(){
        List<Comments> comments = this.goodsService.getCmtByGoodsIdService(90);
        for (Comments comment : comments) {
            System.out.println(comment);
        }
    }

}
