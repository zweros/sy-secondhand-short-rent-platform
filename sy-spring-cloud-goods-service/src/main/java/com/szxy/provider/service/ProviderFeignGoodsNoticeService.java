package com.szxy.provider.service;

import com.szxy.pojo.Notice;
import com.szxy.pojo.NoticeGrid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/29 16:37
 * @Description:com.szxy.provider.service
 * @Version:1.0
 **/
@RequestMapping("/goods")
public interface ProviderFeignGoodsNoticeService {

    /**
     * 查找所有求购信息
     * @return
     */
    @RequestMapping(value="findAllNotice",method = RequestMethod.GET)
    public List<Notice> findAllNotice();

    /**
     * 添加求购信息
     * @param notice
     */
    @RequestMapping(value="addNotice",method = RequestMethod.POST)
    public void addNotice(@RequestBody Notice notice);

    /**
     * 显示求购信息分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="findNoticeByPagination",method = RequestMethod.POST)
    NoticeGrid findNoticeByPaginationService(@RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize") Integer pageSize);
}
