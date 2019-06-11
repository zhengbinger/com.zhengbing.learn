package com.zhengbing.learn.miaosha.util;

import java.util.UUID;

/**
 * Created by zhengbing on 2019/5/30.
 * Since Version 1.0
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
