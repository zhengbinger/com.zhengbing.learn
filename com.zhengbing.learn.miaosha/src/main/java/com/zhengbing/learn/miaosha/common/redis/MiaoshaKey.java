package com.zhengbing.learn.miaosha.common.redis;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version 1.0
 */
public class MiaoshaKey extends BasePrefix {


    public MiaoshaKey( String prefix ) {
        super( prefix );
    }

    public MiaoshaKey( int expireSeconds, String prefix ) {
        super( expireSeconds, prefix );
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey("goods_over");
    public static MiaoshaKey getMiaoshaPath = new MiaoshaKey( 60,"miaosha_path" );
    public static MiaoshaKey getMiaoshaVerifyCode = new MiaoshaKey( 300,"verify_code" );

}
