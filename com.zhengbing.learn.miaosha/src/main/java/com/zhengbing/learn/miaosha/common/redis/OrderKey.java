package com.zhengbing.learn.miaosha.common.redis;

import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version ${VERSION}
 */
public class OrderKey extends BasePrefix {

    public OrderKey( int expireSeconds, String prefix ) {
        super( expireSeconds, prefix );
    }
}
