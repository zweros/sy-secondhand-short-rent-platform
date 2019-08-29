package com.szxy.provider.service;

import com.szxy.pojo.Comments;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/26 14:40
 * @Description:com.szxy.provider.service
 * @Version:1.0
 **/
@RequestMapping("/goods")
public interface ProviderFeignGoodsCommentsService {

    @RequestMapping(value="/getCmtByGoodsId",method= RequestMethod.GET)
    List<Comments> findCommentsByGoodsIdService(@RequestParam("goodsId") Integer goodsId);

    @RequestMapping(value="/getCmtByUserId",method= RequestMethod.GET)
    List<Comments> findCommentsByUserIdService(@RequestParam("userId") Integer userId);

    @RequestMapping(value="/addComment",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    boolean addCommentsService(@RequestBody Comments comment);
}
