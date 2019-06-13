package com.zhengbing.learn.miaosha.dao;

import com.zhengbing.learn.miaosha.entity.Orderinfo;
import org.apache.ibatis.annotations.*;

/**
 * Created by zhengbing on 2019/5/30.
 * Since Version 1.0
 */
@Mapper
public interface OrderinfoDao {

    @Insert( "INSERT INTO orderinfo( user_id, goods_id, delivery_add_id, goods_name, " +
            "goods_count, goods_price, order_channel, status, create_date, pay_date) " +
            "VALUES (#{userId}, #{goodsId}, #{deliveryAddrId}, #{goodsName}, #{goodsCount}, #{goodsPrice}," +
            " #{orderChannel}, #{status}, #{createDate}, #{payDate});" )
    @SelectKey( keyColumn = "id",keyProperty = "id",resultType = long.class,before = false,statement = "select last_insert_id()")
    long insert( Orderinfo order );

    @Select( "select *  from Orderinfo where id = #{orderId}" )
    Orderinfo getById( @Param( "orderId" ) long orderId );
}
