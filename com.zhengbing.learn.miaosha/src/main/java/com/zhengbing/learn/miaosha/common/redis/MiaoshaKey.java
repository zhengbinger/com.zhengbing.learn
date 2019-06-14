package com.zhengbing.learn.miaosha.common.redis;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version 1.0
 */
public class MiaoshaKey extends BasePrefix {

    public MiaoshaKey( String prefix ) {
        super( prefix );
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey("goods_over");

}
