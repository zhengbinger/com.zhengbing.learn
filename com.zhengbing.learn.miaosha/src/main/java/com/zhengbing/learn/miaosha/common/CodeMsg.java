package com.zhengbing.learn.miaosha.common;

/**
 * Created by zhengbing on 2019/5/22.
 * Since Version 1.0
 */
public class CodeMsg {

        private int code;
        private String msg;

        /** 通用错误码 **/
        public static CodeMsg SUCCESS = new CodeMsg(0,"success");
        public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"system error"  );

        /** 登陆模块 5002xx **/
        public static CodeMsg MOBILE_EMPTY = new CodeMsg(500201,"mobile can not empty"  );
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500202,"mobile can not empty"  );
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500203,"mobile format errors"  );


        private CodeMsg( int code, String msg ) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

//    public void setCode( int code ) {
//        this.code = code;
//    }

        public String getMsg() {
            return msg;
        }

//    public void setMsg( String msg ) {
//        this.msg = msg;
//    }
    }
