package com.zhengbing.learn.miaosha.entity.vo;

import com.zhengbing.learn.miaosha.entity.Orderinfo;

/**
 * Created by zhengbing on 2019-06-12.
 * Since Version 1.0
 */
public class OrderinfoVO {

    private GoodsVO goods;
    private Orderinfo order;

    public GoodsVO getGoods() {
        return goods;
    }

    public void setGoods( GoodsVO goods ) {
        this.goods = goods;
    }

    public Orderinfo getOrder() {
        return order;
    }

    public void setOrder( Orderinfo order ) {
        this.order = order;
    }
}
