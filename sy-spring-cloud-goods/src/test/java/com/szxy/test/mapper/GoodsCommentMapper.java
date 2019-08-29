package com.szxy.test.mapper;

import com.szxy.GoodsApp;
import com.szxy.mapper.GoodsCommentsMapper;
import com.szxy.pojo.Comments;
import com.szxy.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther:zwer
 * @Date:2019/8/26 19:09
 * @Description:com.szxy.test.mapper
 * @Version:1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={GoodsApp.class})
public class GoodsCommentMapper {

    @Autowired
    private GoodsCommentsMapper goodsCommentsMapper;

    @Test
    public void testInsertComments(){
        Comments cmt = new Comments();
         cmt.setGoodsId(111);
        User user = new User(); user.setId(8);
        cmt.setUser(user);
        cmt.setContent("哈哈哈哈哈啊哈哈");
        cmt.setCreateAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int i = this.goodsCommentsMapper.insertCommentsMapper(cmt);
        System.out.println(i);
    }


}
