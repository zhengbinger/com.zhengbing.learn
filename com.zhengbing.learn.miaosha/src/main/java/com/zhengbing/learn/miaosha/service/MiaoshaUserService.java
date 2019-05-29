package com.zhengbing.learn.miaosha.service;

import com.sun.tools.javac.jvm.Code;
import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.exception.GlobalException;
import com.zhengbing.learn.miaosha.controller.vo.LoginVO;
import com.zhengbing.learn.miaosha.dao.MiaoshaUserDao;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById( id );
    }

    public CodeMsg login( LoginVO loginVO ) {

        if( null == loginVO ){
            throw new GlobalException( CodeMsg.SERVER_ERROR );
        }
        String mobile = loginVO.getMobile();
        String formPass =  loginVO.getPassword();

        // 验证手机号是否存在
        MiaoshaUser user = getById( Long.parseLong( mobile ) );
        if ( null == user ){
            throw new GlobalException( CodeMsg.MOBILE_NOT_EXIST );
        }

        // 验证密码
        String dbPass = user.getPassword();
        String saltDb = user.getSalt();

        String calcPass = MD5Util.formPassDBPass( formPass,saltDb );

        if ( !calcPass.equals( dbPass ) ){
            throw new GlobalException( CodeMsg.PASSWORD_ERROR );
        }

        return CodeMsg.SUCCESS;

    }
}
