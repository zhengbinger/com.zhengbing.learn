package com.zhengbing.learn.miaosha.entity.vo;

import com.zhengbing.learn.miaosha.entity.Goods;

import java.util.Date;

/**
 * Created by zhengbing on 2019/5/30.
 * Since Version 1。0
 */
public class GoodsVO extends Goods {

    private double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

    public double getMiaoshaPrice() {
        return miaoshaPrice;
    }

    public void setMiaoshaPrice( double miaoshaPrice ) {
        this.miaoshaPrice = miaoshaPrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount( Integer stockCount ) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate( Date startDate ) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate( Date endDate ) {
        this.endDate = endDate;
    }
}
