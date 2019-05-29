package com.zhengbing.learn.miaosha.common;

/**
 * Created by zhengbing on 2019/5/22.
 * Since Version 1.0
 */
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    /**
     * success construct
     * @param data
     */
    private Result(  T data ) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    /**
     * error construct
     * @param cm
     */
    private Result(  CodeMsg cm) {
        if ( null == cm ){
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();

    }

    /**
     *  success method
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success( T data){
        return new Result<T>( data );
    }

    /**
     * error method
     * @param cm
     * @param <T>
     * @return
     */
    public static <T> Result<T> error( CodeMsg cm){
        return new Result<T>( cm );
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

    public T getData() {
        return data;
    }

//    public void setData( T data ) {
//        this.data = data;
//    }
}



