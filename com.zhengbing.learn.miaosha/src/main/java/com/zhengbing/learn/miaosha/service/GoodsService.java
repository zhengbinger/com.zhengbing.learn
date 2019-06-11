package com.zhengbing.learn.miaosha.service;

import com.zhengbing.learn.miaosha.dao.GoodsDao;
import com.zhengbing.learn.miaosha.entity.Goods;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVO> listGoodsVO(){
        return goodsDao.listGoodsVO();
    }


    public GoodsVO getGoodVOByGoodsId( long goodsId ) {
        return goodsDao.getGoodVOByGoodsId( goodsId );
    }

    public void reduceStock( GoodsVO goodsVO) {
        Goods goods = new Goods();
        goods.setId( goods.getId() );
        goodsDao.reduceStock(goods);
    }
}
