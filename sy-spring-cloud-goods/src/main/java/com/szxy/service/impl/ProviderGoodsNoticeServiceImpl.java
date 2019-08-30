package com.szxy.service.impl;

import com.szxy.mapper.GoodsNoticeMapper;
import com.szxy.pojo.Notice;
import com.szxy.pojo.NoticeGrid;
import com.szxy.service.ProviderGoodsNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/29 16:45
 * @Description:com.szxy.service.impl
 * @Version:1.0
 **/
@Service
public class ProviderGoodsNoticeServiceImpl implements ProviderGoodsNoticeService {

    @Autowired
    private GoodsNoticeMapper goodsNoticeMapper;

    @Override
    public NoticeGrid findNoticeByPaginationService(Integer pageNum, Integer pageSize) {
        NoticeGrid noticeGrid = new NoticeGrid();
        Integer startPos = (pageNum-1)*pageSize;
        Integer endPos =  pageSize;
        List<Notice> noticeList = this.goodsNoticeMapper.selNoticeByPagination(startPos,endPos);
        noticeGrid.setRows(noticeList);
        noticeGrid.setCurrent(pageNum);
        noticeGrid.setRowCount(pageSize);
        noticeGrid.setTotal(this.goodsNoticeMapper.selAllNoticeCount());
        return noticeGrid;
    }

    @Override
    public List<Notice> findAllNoticeService() {
        return this.goodsNoticeMapper.selAllNoticeMapper();
    }

    @Override
    public void addNoticeService(Notice notice) {
        this.goodsNoticeMapper.addNoticeMapper(notice);
    }
}
