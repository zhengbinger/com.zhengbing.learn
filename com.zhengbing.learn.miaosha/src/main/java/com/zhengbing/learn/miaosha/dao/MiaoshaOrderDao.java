package com.zhengbing.learn.miaosha.dao;

import com.zhengbing.learn.miaosha.entity.MiaoshaOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhengbing on 2019/5/30.
 * Since Version 1.0
 */
@Mapper
public interface MiaoshaOrderDao {

    @Insert( "INSERT INTO miaosha_order( `user_id`, `order_id`, `goods_id`) " +
            "VALUES (#{userId}, #{orderId}, #{goodsId});" )
    long insert(MiaoshaOrder miaoshaOrder);

    @Select( "select *  from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}" )
    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId( @Param( "userId" )long userId , @Param( "goodsId" )long goodsId);
}
