package com.szxy.test.mapper;

import com.szxy.GoodsApplication;
import com.szxy.pojo.Notice;
import com.szxy.pojo.NoticeGrid;
import com.szxy.pojo.User;
import com.szxy.service.ProviderGoodsNoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/29 17:08
 * @Description:com.szxy.test.mapper
 * @Version:1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={GoodsApplication.class})
public class GoodsNoticeServiceTest {

    @Autowired
    private ProviderGoodsNoticeService providerGoodsNoticeService;

    @Test
    public void selAll(){
        List<Notice> noticeList = this.providerGoodsNoticeService.findAllNoticeService();
        for (Notice notice : noticeList) {
            System.out.println(notice);
        }
    }

    @Test
    public void test2(){
        Notice notice = new Notice();
        notice.setContext("测试");
        notice.setCreateAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        User user = new User();
        user.setId(31);
        notice.setUser(user);
        this.providerGoodsNoticeService.addNoticeService(notice);
    }

    @Test
    public void test3(){
        NoticeGrid noticeGrid = this.providerGoodsNoticeService.findNoticeByPaginationService(4, 5);
        List<Notice> noticeList = noticeGrid.getRows();
        System.out.println(noticeList);
        for (Notice notice : noticeList) {
            System.out.println(notice);
        }
        System.out.println("总记录数"+noticeGrid.getTotal());
    }

}
