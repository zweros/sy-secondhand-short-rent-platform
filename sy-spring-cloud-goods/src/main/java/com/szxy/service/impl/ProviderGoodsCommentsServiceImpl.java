package com.szxy.service.impl;

import com.szxy.mapper.GoodsCommentsMapper;
import com.szxy.pojo.Comments;
import com.szxy.service.ProviderGoodsCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/26 14:36
 * @Description:com.szxy.service.impl
 * @Version:1.0
 **/
@Service
public class ProviderGoodsCommentsServiceImpl implements ProviderGoodsCommentsService {

    @Autowired
    private GoodsCommentsMapper goodsCommentsMapper;

    @Override
    public List<Comments> findCommentsByGoodsIdService(Integer goodsId) {
        return this.goodsCommentsMapper.selCommentsByGoodsId(goodsId);
    }

    @Override
    public List<Comments> findCommentsByUserIdService(Integer userId) {
        return this.goodsCommentsMapper.selCommentsByUserId(userId);
    }

    @Override
    public boolean addCommentsService(Comments comment) {
        int flag =  this.goodsCommentsMapper.insertCommentsMapper(comment);
        if(flag > 0){
            return true;
        }
        return false;
    }
}
