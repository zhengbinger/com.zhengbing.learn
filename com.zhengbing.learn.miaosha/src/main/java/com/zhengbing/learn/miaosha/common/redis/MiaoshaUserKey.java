package com.zhengbing.learn.miaosha.common.redis;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version ${VERSION}
 */
public class MiaoshaUserKey extends BasePrefix {

    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public MiaoshaUserKey( String prefix ) {
        super( prefix );
    }

    public MiaoshaUserKey( int expireSeconds,String prefix ) {
        super( expireSeconds,prefix );
    }

    public static MiaoshaUserKey getById = new MiaoshaUserKey("id");
    public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE,"token");

}
