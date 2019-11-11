package com.szxy.service;

import com.szxy.pojo.Notice;
import com.szxy.pojo.NoticeGrid;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/29 16:36
 * @Description:com.szxy.service
 * @Version:1.0
 **/
@FeignClient(name = "sy-spring-clound-zuul-gateway")
public interface GoodsNoticeService {

    /**
     * 查找所有求购信息
     *
     * @return
     */
    @RequestMapping(value = "/goods/findAllNotice", method = RequestMethod.GET)
    public List<Notice> findAllNotice();

    /**
     * 添加求购信息
     *
     * @param notice
     */
    @RequestMapping(value = "/goods/addNotice", method = RequestMethod.POST)
    public void addNotice(@RequestBody Notice notice);

    /**
     * 显示求购信息分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/goods/findNoticeByPagination", method = RequestMethod.POST)
    NoticeGrid findNoticeByPaginationService(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

}
