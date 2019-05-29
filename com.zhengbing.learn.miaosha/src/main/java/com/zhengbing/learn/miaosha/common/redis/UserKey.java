package com.zhengbing.learn.miaosha.common.redis;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version ${VERSION}
 */
public class UserKey extends BasePrefix {

    public UserKey( String prefix ) {
        super( prefix );
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

}
