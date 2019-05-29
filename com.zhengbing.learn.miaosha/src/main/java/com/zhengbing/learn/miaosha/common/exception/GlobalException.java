package com.zhengbing.learn.miaosha.common.exception;

import com.zhengbing.learn.miaosha.common.CodeMsg;

/**
 * Created by zhengbing on 2019/5/30.
 * Since Version 1.0
 */
public class GlobalException extends  RuntimeException {

    private static final long serialVersionUID = 7721533835455117142L;
    private CodeMsg codeMsg;

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public GlobalException( CodeMsg codeMsg ) {
        this.codeMsg = codeMsg;
    }
}
