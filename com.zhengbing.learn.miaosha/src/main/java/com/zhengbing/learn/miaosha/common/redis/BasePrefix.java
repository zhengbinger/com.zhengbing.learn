package com.zhengbing.learn.miaosha.common.redis;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version 1.0
 */
public abstract class BasePrefix implements KeyPrefix {

    private String prefix;

    private int expireSeconds;

    public BasePrefix( String prefix ){
        this(0,prefix);
    }

    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() { // default  0  means forever
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className +":"+ prefix;
    }
}
