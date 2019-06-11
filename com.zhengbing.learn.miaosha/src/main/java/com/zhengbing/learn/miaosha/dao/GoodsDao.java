package com.zhengbing.learn.miaosha.dao;

import com.zhengbing.learn.miaosha.entity.Goods;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zhengbing on 2019/5/30.
 * Since Version 1.0
 */
@Mapper
public interface GoodsDao {

    @Select( "select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from goods g left join miaosha_goods mg on mg.goods_id = g.id " )
    List<GoodsVO> listGoodsVO();

    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from goods g left join miaosha_goods mg on mg.goods_id = g.id where goods_id = #{goodsId}" )
    GoodsVO getGoodVOByGoodsId( @Param( "goodsId" ) long goodsId );

    @Update( "update goods set goods_stock=goods_stock-1 where id=#{id}" )
    int reduceStock( Goods goods );
}
