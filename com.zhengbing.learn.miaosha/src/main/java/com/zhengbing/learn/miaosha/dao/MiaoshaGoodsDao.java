package com.zhengbing.learn.miaosha.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * Created by zhengbing on 2019/5/30.
 * Since Version 1.0
 */
@Mapper
public interface MiaoshaGoodsDao {

    @Update( "update miaosha_goods set stock_count = stock_count-1 where goods_id=#{goodsId}" )
    int reduceStock( long goodsId);
}
