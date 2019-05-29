package com.zhengbing.learn.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version 1.0
 *
 * 两次md5，一次由前端制定取salt内容拼接之后进行md5加密之后传送到后端
 *         一次在后端拿到前端传输的密码秘文之后再进行一次md5之后再入库
 */
public class MD5Util {

    public static final String salt = "1a2b3c4d";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassFormPass(String inputPass){
        String src = salt.charAt( 0 ) + salt.charAt( 2 ) + inputPass +salt.charAt( 5 ) +salt.charAt( 4 );
        return md5( src );
    }

    public static String formPassDBPass(String formPass, String salt){
        String src = salt.charAt( 0 ) + salt.charAt( 2 ) + formPass +salt.charAt( 5 ) +salt.charAt( 4 );
        return md5( src );
    }

    public static String inputPass2DbPass(String pass,String salt){
        String formPass = inputPassFormPass( pass );
        String DbPass = formPassDBPass( formPass,salt );
        return DbPass;
    }

    public static void main( String[] args ) {
        System.out.println(inputPassFormPass( "123456" ));
        System.out.println(formPassDBPass( "123456","1a2b3c4d" ));
        System.out.printf( inputPass2DbPass( "123456","1a2b3c4d"  ) );
    }
}
