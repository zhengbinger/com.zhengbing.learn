package com.zhengbing.learn.miaosha.controller;

import com.zhengbing.learn.miaosha.common.Result;
import com.zhengbing.learn.miaosha.entity.vo.LoginVO;
import com.zhengbing.learn.miaosha.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * Created by zhengbing on 2019/5/28.
 * Since Version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    private Logger logger = LoggerFactory.getLogger( LoginController.class );


    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin( HttpServletResponse response,@Valid LoginVO loginVO){
        logger.info( loginVO.toString() );
        miaoshaUserService.login(response,loginVO);

        return Result.success( miaoshaUserService.login(response,loginVO) );
    }
}
