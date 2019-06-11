package com.zhengbing.learn.miaosha.service;

import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.Orderinfo;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhengbing on 2019/5/31.
 * Since Version 1.0
 */
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    MiaoshaGoodsService miaoshaGoodsService;

    @Autowired
    OrderinfoService orderinfoService;

    @Autowired
    MiaoshaOrderService miaoshaOrderService;

    @Transactional
    public Orderinfo miaosha( MiaoshaUser user, GoodsVO goodsVO){

        // 减少秒杀库存
        miaoshaGoodsService.reduceStock( goodsVO.getId() );

        // 生成订单（ 1.生成基本订单 ）
        Orderinfo order =  orderinfoService.createOrder( user, goodsVO );

        // 生成订单（ 2.生成秒杀订单 )
        miaoshaOrderService.createMiaoshaOrder(user, goodsVO,order.getId());

        return order;
    }
}
