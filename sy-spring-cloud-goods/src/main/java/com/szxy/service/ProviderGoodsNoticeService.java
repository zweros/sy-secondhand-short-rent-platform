package com.szxy.service;

import com.szxy.pojo.Notice;
import com.szxy.pojo.NoticeGrid;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/29 16:43
 * @Description:com.szxy.service
 * @Version:1.0
 **/
public interface ProviderGoodsNoticeService {

    /**
     * 查找所有求购信息
     * @return
     */
    List<Notice> findAllNoticeService();

    /**
     * 添加用户求购信息
     * @param notice
     */
    void addNoticeService(Notice notice);

    /**
     * 求购信息，分页查询
     * @param pageNum    当前页，从 第 1页开始
     * @param pageSize   每页显示的记录数
     * @return
     */
    NoticeGrid findNoticeByPaginationService(Integer pageNum, Integer pageSize);
}
