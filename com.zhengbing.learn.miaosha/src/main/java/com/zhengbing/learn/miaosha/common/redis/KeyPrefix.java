package com.zhengbing.learn.miaosha.common.redis;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version ${VERSION}
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
