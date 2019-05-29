package com.zhengbing.learn.miaosha.controller;

import com.sun.tools.javac.jvm.Code;
import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.Result;
import com.zhengbing.learn.miaosha.controller.vo.LoginVO;
import com.zhengbing.learn.miaosha.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by zhengbing on 2019/5/28.
 * Since Version ${VERSION}
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger( LoginController.class );


    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin( LoginVO loginVO){
        logger.info( loginVO.toString() );
        if ( StringUtils.isEmpty(loginVO.getMobile()) ){
            return Result.error( CodeMsg.MOBILE_EMPTY );
        }
        if ( StringUtils.isEmpty(loginVO.getPassword()) ){
            return Result.error( CodeMsg.PASSWORD_EMPTY );
        }
        if (! ValidateUtil.isMobile( loginVO.getMobile() ) ){
            return Result.error( CodeMsg.MOBILE_ERROR );
        }
        return null;
    }
}
