package com.szxy.service.impl;

import com.szxy.pojo.*;
import com.szxy.service.*;
import com.szxy.utils.FtpUtil;
import com.szxy.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 服务消费者   假的接口实现类
 */

@Service
public class GoodsServiceImpl {

    @Value("${REDIS_HOMEGOODS_SHOW_KEY}")
    private  String REDIS_HOMEGOODS_SHOW_KEY ;
    @Value("${DEFAULT_CATELOG_NAME}")
    private String DEFAULT_CATELOG_NAME;
    @Value("${FTP_HOST}")
    private String FTP_HOST;
    @Value("${FTP_PROT}")
    private int FTP_PROT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;
    @Value("${FTP_FILEPATH}")
    private String FTP_FILEPATH;
    @Value("${IMG_URL_SUFFIX}")
    private String IMG_URL_SUFFIX;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsImageService goodsImageService;
    @Autowired
    private GoodsCatelogService goodsCatelogService;
    @Autowired
    private GoodsCommentsService goodsCommentsService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;



    /**
     * 查看所有物品分类的物品信息
     * 并每个类别显示前 6 条
     * 将其放入 Redis 缓存中
     * @return
     */
    public   List< Map<Goods, Image>>  findAllCatelogGoods() {
        List< Map<Goods, Image>>  list = null;
        /*//更换序列器
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        // TODO: 2019/8/25   Redis 缓存有些问题  前台页面报错
        Object obj = this.redisTemplate.opsForList().leftPop((REDIS_HOMEGOODS_SHOW_KEY));
        System.out.println(obj);
        if(obj != null){
            System.out.println((List< Map<Goods, Image>>)obj);
            list = (List< Map<Goods, Image>>)obj;
            return list;
        }*/
        //创建 List 集合，存放物品信息
       list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            //查询所有分类物品，按发布时间倒序排序，每个类别显示 6 个
            List<Goods> goods = this.goodsService.findByCatelogGoodsIdService(i == 0 ? null : i, 6);
            Map<Goods, Image> goodAndImgMap = new HashMap<>();
            for (Goods good : goods) {
                Image img = this.goodsImageService.findGoodsImageByGoodIdService(good.getId());
                goodAndImgMap.put(good, img);
            }
            list.add(goodAndImgMap);
        }
      /*  //更换序列器
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        //将数据放入 Redis 缓存中
        this.redisTemplate.opsForList().leftPush(REDIS_HOMEGOODS_SHOW_KEY,list);*/
        return list;
    }

    /**
     * 查询物品详细信息
     * @param id  物品 ID
     * @return
     */
    public  void  findDetailGoods(Integer id, Model model){
        Goods goods = this.goodsService.findByGoodIdService(id);
        if(goods != null){
            Image img = this.goodsImageService.findGoodsImageByGoodIdService(goods.getId());
            User seller = this.userService.findSellerInfoByIdService(goods.getUserId());
            Catelog catelog = this.goodsCatelogService.findCatelogByIdService(goods.getCatelogId());
            model.addAttribute("goods",goods);
            model.addAttribute("img",img);
            model.addAttribute("seller",seller);
            model.addAttribute("catelog",catelog);
        }
    }

    /**
     * 按物品分类查询所有物品信息，按发布时间倒序排序
     * 并将物品信息和物品图片放入 Map 集合中
     * @param cid
     * @return
     */
    public  Map<Goods, Image> findAllGoodsByCid(Integer cid) {
        // TODO: 2019/8/25   考虑后期分页
        List<Goods> goods = null;
        if(cid != null && cid == 0){
            goods = this.goodsService.findByCatelogGoodsIdService(null,null);
        }else{
            goods = this.goodsService.findByCatelogGoodsIdService(cid,null);
        }
        Map<Goods, Image> goodAndImgMap = new HashMap<>();
        for (Goods good : goods) {
            Image img = this.goodsImageService.findGoodsImageByGoodIdService(good.getId());
            goodAndImgMap.put(good, img);
        }
        return goodAndImgMap;
    }

    /**
     * 查询单个分类目录信息
     * @param cid
     * @return
     */
    public Catelog findCatelogInfoById(Integer cid){
        Catelog catelog = null;
        if(cid != null && cid != 0){
            catelog = this.goodsCatelogService.findCatelogByIdService(cid);
        }else{
            catelog = new Catelog();
            try {
                //从配置中心读取是以  ISO-8859-1 编码读取，中文需要转为 utf-8 编码，否则会出现乱码
                catelog.setName(new String(this.DEFAULT_CATELOG_NAME.getBytes("ISO-8859-1"),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return catelog;
    }

    /**
     * 根据物品 ID 获取评论
     * @param goodId
     * @return
     */
    public List<Comments>  getCmtByGoodsIdService(Integer goodId){
        List<Comments> comments =
                this.goodsCommentsService.findCommentsByGoodsIdService(goodId);
        if(comments != null && comments.size() > 0){
            for (Comments comment : comments) {
                if(comment.getUser() != null){
                    // 查找评论人的用户信息
                    User user = this.userService.findSellerInfoByIdService(comment.getUser().getId());
                    comment.getUser().setUsername(user.getUsername());
                }
            }
        }
        return  comments;
    }


    /**
     * 获取所有物品目录
     */
    public List<Catelog> getAllCatelog(){
        List<Catelog> catelogs = this.goodsCatelogService.findAllCatelog();
        return catelogs;
    }

    /**
     * 添加评论信息
     * @param comment
     * @param req
     * @return
     */
    public Map<String, String> addCommentsService(Comments comment, HttpServletRequest req) {
        Map<String,String> map = new HashMap<>();
        comment.setCreateAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Cookie[] cookies = req.getCookies();
        //从 cookie  获取之前用户成功后的令牌 token
        //再利用 token 从 Redis 缓存查找用户信息
        if(cookies != null  && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    // 判断
                    // 更换序列化器
                    this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
                    Object userJson =  this.redisTemplate.opsForValue().get(cookie.getValue());
                    if(userJson != null ){
                        comment.setUser((User)userJson);
                    }else{
                       /* User user = (User) req.getSession().getAttribute("cur_user");
                        comment.setUser(user);*/
                         map.put("msg","false");
                         return  map;
                    }
                }
            }
        }
        boolean flag = this.goodsCommentsService.addCommentsService(comment);
        if(flag){
            map.put("msg","true");
        }else{
            map.put("msg","false");
        }
        return map;
    }

    /**
     * 添加物品和物品图片
     * @param goods
     * @param myfile
     */
    public void addGoodsAndImg(Goods goods, MultipartFile myfile,HttpServletRequest req) {
        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        goods.setStartTime(dateStr);
        goods.setPolishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        goods.setEndTime(dateStr);
        goods.setStatus(1);
        //获取用户 ID
        Cookie[] cookies = req.getCookies();
        //从 cookie  获取之前用户成功后的令牌 token
        //再利用 token 从 Redis 缓存查找用户信息
        if(cookies != null  && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    // 更换序列化器
                    this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
                    User user = (User) this.redisTemplate.opsForValue().get(cookie.getValue());
                    if(user != null){
                        goods.setUserId(user.getId());
                    }
                }
            }
        }
        //生成物品 ID 编号
        Integer goodId = IDUtils.genItemId();
        goods.setId(goodId);
        System.out.println(goods);
        //保存物品信息
        this.goodsService.addGoodsService(goods);
        //上传图片
        InputStream input = null;
        try {
            // 获取上传图片的原始名称
            String originalFilename = myfile.getOriginalFilename();
            if(originalFilename != null && !originalFilename.equals("")) {
                // 获取上传图片的文件后缀，包括 . 分隔符
                String suffix = originalFilename.substring(originalFilename.indexOf("."));
                // 生成新的文件名
                String filename = UUID.randomUUID().toString() + suffix;
                input = myfile.getInputStream();
                boolean flag = FtpUtil.uploadFile(this.FTP_HOST, this.FTP_PROT, this.FTP_USERNAME, this.FTP_PASSWORD, this.FTP_BASEPATH, this.FTP_FILEPATH, filename, input);
                if (flag) {
                    //图片访问地址
                    String imgUrl = IMG_URL_SUFFIX + filename;
                    Image image = new Image();
                    image.setImgUrl(imgUrl);
                    image.setGoodsId(goodId);
                    System.out.println(imgUrl);
                    System.out.println(image);
                    //保存图片
                    this.goodsImageService.addGoodsImageService(image);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(input != null){
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 搜索物品
     * @param str
     * @return
     */
    public List<GoodsExtend> searchGoodsService(String str) {
        List<GoodsExtend> goodsExtendList = new ArrayList<>();
        List<Goods> goods = this.goodsService.searchGoodsService(str);
        for (Goods good : goods) {
            GoodsExtend goodsExtend = new GoodsExtend();
            Image img =
                    this.goodsImageService.findGoodsImageByGoodIdService(good.getId());
            List<Image> images = new ArrayList<>();
            images.add(img);
            goodsExtend.setGoods(good);
            goodsExtend.setImages(images);
            //添加到集合中
            goodsExtendList.add(goodsExtend);
        }
        return goodsExtendList;
    }

    /**
     * 查询用户已发布的物品
     * @param req
     * @return
     */
    public List<GoodsExtend> findUserPublishedAllGoods(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        //从 cookie  获取之前用户成功后的令牌 token
        //再利用 token 从 Redis 缓存查找用户信息
        if(cookies != null  && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    // 更换序列化器
                    this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
                    Object userJson = this.redisTemplate.opsForValue().get(cookie.getValue());
                    if(userJson != null){
                        User user = (User)userJson;
                        List<Goods>  goods = this.goodsService.findUserPublishedAllGoodsService(user.getId());
                        List<GoodsExtend> goodsExtendList = new ArrayList<>();
                        for (Goods good : goods) {
                            GoodsExtend goodsExtend = new GoodsExtend();
                            Image img =
                                    this.goodsImageService.findGoodsImageByGoodIdService(good.getId());
                            List<Image> images = new ArrayList<>();
                            images.add(img);
                            goodsExtend.setGoods(good);
                            goodsExtend.setImages(images);
                            //添加到集合中
                            goodsExtendList.add(goodsExtend);
                        }
                        return goodsExtendList;
                    }
                }
            }
        }
        return null;
    }
}
