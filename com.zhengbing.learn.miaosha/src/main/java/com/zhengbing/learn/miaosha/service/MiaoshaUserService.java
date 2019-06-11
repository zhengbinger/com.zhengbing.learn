package com.zhengbing.learn.miaosha.service;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.exception.GlobalException;
import com.zhengbing.learn.miaosha.common.redis.MiaoshaUserKey;
import com.zhengbing.learn.miaosha.common.redis.RedisService;
import com.zhengbing.learn.miaosha.entity.vo.LoginVO;
import com.zhengbing.learn.miaosha.dao.MiaoshaUserDao;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.util.MD5Util;
import com.zhengbing.learn.miaosha.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public static final String COOKIE_NAME_TOKEN = "token";

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById( id );
    }

    public boolean login( HttpServletResponse response, LoginVO loginVO ) {

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
        String token = UUIDUtil.uuid();

        addCookie( response,token,user );

        return true;

    }

    public MiaoshaUser getbyToken( HttpServletResponse response, String token ) {
        if ( StringUtils.isEmpty(token) ){
            return null;
        }

        MiaoshaUser user = redisService.get( MiaoshaUserKey.token,token,MiaoshaUser.class);
        if ( null != user ){
            addCookie( response,token,user );
        }
        return user;
    }

    /**
     *
     * 写cookie
     *
     * @param response
     * @param user
     */
    private void addCookie(HttpServletResponse response,String token,MiaoshaUser user){
        // 生成cookie

        redisService.set( MiaoshaUserKey.token,token,user );
        Cookie cookie = new Cookie( COOKIE_NAME_TOKEN,token );
        cookie.setMaxAge( MiaoshaUserKey.token.expireSeconds() );
        cookie.setPath( "/" );

        response.addCookie( cookie );
    }

}
