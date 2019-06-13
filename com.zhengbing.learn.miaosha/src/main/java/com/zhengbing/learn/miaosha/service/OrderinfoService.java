package com.zhengbing.learn.miaosha.service;

import com.zhengbing.learn.miaosha.dao.OrderinfoDao;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.Orderinfo;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
@Service
public class OrderinfoService {

    @Autowired
    OrderinfoDao orderinfoDao;

    @Autowired
    MiaoshaOrderService miaoshaOrderService;

    /**
     * 生成订单
     *
     * @param user
     * @param goodsVO
     * @return
     */
    public Orderinfo createOrder( MiaoshaUser user, GoodsVO goodsVO ) {

        Orderinfo order = new Orderinfo();
        order.setCreateDate( new Date(  ) );
        order.setGoodsId( goodsVO.getId() );
        order.setDeliveryAddrId( 0L );
        order.setGoodsName( goodsVO.getGoodsName() );
        order.setGoodsPrice( goodsVO.getMiaoshaPrice() );
        order.setGoodsCount( 1 );
        order.setOrderChannel( 1 );
        order.setStatus( 0 );
        order.setUserId( user.getId() );
        long orderId = orderinfoDao.insert( order );
        return order;
    }

    public Orderinfo getOrderById( long orderId ) {
        return orderinfoDao.getById( orderId );
    }
}