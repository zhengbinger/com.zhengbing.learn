package com.zhengbing.learn.miaosha.service;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.exception.GlobalException;
import com.zhengbing.learn.miaosha.common.redis.MiaoshaKey;
import com.zhengbing.learn.miaosha.common.redis.RedisService;
import com.zhengbing.learn.miaosha.entity.MiaoshaOrder;
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

    @Autowired
    RedisService redisService;

    @Transactional
    public Orderinfo miaosha( MiaoshaUser user, GoodsVO goodsVO){

        // 减少秒杀库存
        boolean ret = miaoshaGoodsService.reduceStock( goodsVO.getId() );

        if ( ret ){
            // 生成订单（ 1.生成基本订单 ）
            Orderinfo order =  orderinfoService.createOrder( user, goodsVO );
            if ( null == order){
                return null;
            }

            // 生成秒杀订单（ 2.生成秒杀订单 )
            MiaoshaOrder miaoshaOrder = miaoshaOrderService.createMiaoshaOrder(user, goodsVO,order.getId());
            if ( null == miaoshaOrder ){
                return null;
            }
            return order;
        }
        setGoodsOver(goodsVO.getId());
        return null;
    }

    private void setGoodsOver( Long goodsId ) {
        redisService.set( MiaoshaKey.isGoodsOver,""+goodsId,true);
    }

    /**
     * 获取秒杀结果
     *
     * @param userId
     * @param goodsId
     * @return
     */
    public long getMiaoshaResult( long userId, long goodsId ) {
        MiaoshaOrder order = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId( userId,goodsId );
        if ( null != order ){
            return order.getId();
        }else {
            if ( getGoodsOver( goodsId ) ){
                throw new GlobalException( CodeMsg.MIAO_SHA_OVER );
            }else {
                return 0;
            }
        }

    }

    private boolean getGoodsOver( long goodsId ) {
        return redisService.exists( MiaoshaKey.isGoodsOver,""+goodsId);
    }
}
