package com.zhengbing.learn.miaosha.service;

import com.zhengbing.learn.miaosha.dao.MiaoshaOrderDao;
import com.zhengbing.learn.miaosha.entity.MiaoshaOrder;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
@Service
public class MiaoshaOrderService {

    @Autowired
    MiaoshaOrderDao miaoshaOrderDao;

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId( long userId, long goodsId ) {
        return miaoshaOrderDao.getMiaoshaOrderByUserIdGoodsId( userId, goodsId );
    }

    public long insert(MiaoshaOrder miaoshaOrder) {
        return miaoshaOrderDao.insert( miaoshaOrder );
    }

    public long createMiaoshaOrder( MiaoshaUser user, GoodsVO goodsVO, long orderId){
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId( goodsVO.getId() );
        miaoshaOrder.setOrderId( orderId );
        miaoshaOrder.setUserId( user.getId() );

        return miaoshaOrderDao.insert(miaoshaOrder);
    }
}
