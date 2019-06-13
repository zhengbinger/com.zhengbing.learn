package com.zhengbing.learn.miaosha.entity.vo;

import com.zhengbing.learn.miaosha.entity.MiaoshaUser;

/**
 * Created by zhengbing on 2019-06-12.
 * Since Version 1.0
 */
public class GoodsDetailVO {

    private GoodsVO goods;
    private MiaoshaUser miaoshaUser;
    private int remainSeconds;
    private int miaoshaStatus;

    public GoodsVO getGoods() {
        return goods;
    }

    public void setGoods( GoodsVO goods ) {
        this.goods = goods;
    }

    public MiaoshaUser getMiaoshaUser() {
        return miaoshaUser;
    }

    public void setMiaoshaUser( MiaoshaUser miaoshaUser ) {
        this.miaoshaUser = miaoshaUser;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds( int remainSeconds ) {
        this.remainSeconds = remainSeconds;
    }

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus( int miaoshaStatus ) {
        this.miaoshaStatus = miaoshaStatus;
    }
}
