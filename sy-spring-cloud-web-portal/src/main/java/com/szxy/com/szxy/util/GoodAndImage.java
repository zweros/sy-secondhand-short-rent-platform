package com.szxy.com.szxy.util;

import com.szxy.pojo.Goods;
import com.szxy.pojo.Image;

/**
 * 封装物品对应的图片信息
 * @Auther:zwer
 * @Date:2019/8/27 8:35
 * @Description:com.szxy.com.szxy.util
 * @Version:1.0
 **/
public class GoodAndImage {
    private Goods goods;
    private Image image;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
