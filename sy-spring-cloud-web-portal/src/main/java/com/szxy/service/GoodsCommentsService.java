package com.szxy.service;

import com.szxy.pojo.Comments;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/26 14:54
 * @Description:com.szxy.service
 * @Version:1.0
 **/
@FeignClient(name="sy-spring-clound-zuul-gateway")
public interface GoodsCommentsService  {

    @RequestMapping(value="/goods/getCmtByGoodsId",method= RequestMethod.GET)
    List<Comments> findCommentsByGoodsIdService(@RequestParam("goodsId") Integer goodsId);

    @RequestMapping(value="/goods/getCmtByUserId",method= RequestMethod.GET)
    List<Comments> findCommentsByUserIdService(@RequestParam("userId") Integer userId);

    @RequestMapping(value="/goods/addComment",method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    boolean addCommentsService(@RequestBody Comments comment);

}
