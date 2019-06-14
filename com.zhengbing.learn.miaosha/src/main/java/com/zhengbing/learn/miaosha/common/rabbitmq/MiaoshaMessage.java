package com.zhengbing.learn.miaosha.common.rabbitmq;

import com.zhengbing.learn.miaosha.entity.MiaoshaUser;

/**
 * Created by zhengbing on 2019-06-14.
 * Since Version 1.0
 */
public class MiaoshaMessage {

    private MiaoshaUser user;
    private long goodsId;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser( MiaoshaUser user ) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId( long goodsId ) {
        this.goodsId = goodsId;
    }
}
