package com.zhengbing.learn.miaosha.common.redis;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version 1.0
 */
public class GoodsKey extends BasePrefix {

    public GoodsKey( String prefix ) {
        super( prefix );
    }
    public GoodsKey( int expireSeconds,String prefix ) {
        super( expireSeconds,prefix );
    }

    public static GoodsKey getGoodsList = new GoodsKey(60,"goods_list");
    public static GoodsKey getGoodsDetail = new GoodsKey(60,"goods_details");
}
