package com.szxy.service;

import com.szxy.pojo.Comments;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/26 14:34
 * @Description:com.szxy.service
 * @Version:1.0
 **/
public interface  ProviderGoodsCommentsService {

    List<Comments> findCommentsByGoodsIdService(Integer goodsId);

    List<Comments> findCommentsByUserIdService(Integer userId);

    boolean addCommentsService(Comments comment);

}
