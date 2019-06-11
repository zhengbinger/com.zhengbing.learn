package com.zhengbing.learn.miaosha.service;

import com.zhengbing.learn.miaosha.dao.MiaoshaGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
@Service
public class MiaoshaGoodsService {

    @Autowired
    MiaoshaGoodsDao miaoshaGoodsDao;

    public void reduceStock( long goodsId) {
        miaoshaGoodsDao.reduceStock(goodsId);
    }

}