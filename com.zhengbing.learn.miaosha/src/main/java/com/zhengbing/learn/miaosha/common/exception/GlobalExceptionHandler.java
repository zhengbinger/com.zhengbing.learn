package com.zhengbing.learn.miaosha.common.exception;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 *
 * desc: 全局异常处理
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler( HttpServletRequest request,Exception e){
        e.printStackTrace();
        if ( e instanceof GlobalException ){
            GlobalException ex = (GlobalException) e;
            return Result.error( ex.getCodeMsg()  );
        } else if ( e instanceof BindException ){
            BindException ex = ( BindException ) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get( 0 );
            String msg = error.getDefaultMessage();
            return Result.error( CodeMsg.BIND_ERROR.fillArgs( msg ));
        } else {
            return Result.error( CodeMsg.SERVER_ERROR );
        }


    }
}
