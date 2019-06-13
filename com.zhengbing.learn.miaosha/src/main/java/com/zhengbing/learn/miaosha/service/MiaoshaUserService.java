package com.zhengbing.learn.miaosha.service;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.exception.GlobalException;
import com.zhengbing.learn.miaosha.common.redis.MiaoshaUserKey;
import com.zhengbing.learn.miaosha.common.redis.RedisService;
import com.zhengbing.learn.miaosha.dao.MiaoshaUserDao;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.vo.LoginVO;
import com.zhengbing.learn.miaosha.util.MD5Util;
import com.zhengbing.learn.miaosha.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 根据用户id获取用户信息
     * （加入对象缓存处理）
     *
     * @param id
     * @return
     */
    public MiaoshaUser getById(long id){
        // 取缓存
        MiaoshaUser user = redisService.get( MiaoshaUserKey.getById,":"+id,MiaoshaUser.class );
        if ( null != user ){
            return user;
        }
        // 从数据库取数据
        user = miaoshaUserDao.getById( id );
        if ( null != user ){
            redisService.set( MiaoshaUserKey.getById,":"+id,user );
        }
        return user;
    }

    /**
     * 修改用户密码
     *
     * @param token
     * @param id
     * @param password
     * @return
     */
    public boolean updatePassword(String token,long id,String password){
        MiaoshaUser user = getById( id );
        if ( null == user ){
            throw  new  GlobalException( CodeMsg.MOBILE_NOT_EXIST );
        }

        MiaoshaUser updateUser = new MiaoshaUser();
        updateUser.setId( id );
        user.setPassword( MD5Util.formPassDBPass( password,user.getSalt() ) );
        int result = miaoshaUserDao.update( updateUser );

        if ( result > 0  ) {
            // 更新缓存
            redisService.set( MiaoshaUserKey.token, token, updateUser );
            redisService.delete( MiaoshaUserKey.getById, "" + id );
            return true;
        }
        return false;

    }

    /**
     * 用户登录
     *
     * @param response
     * @param loginVO
     * @return
     */
    public String login( HttpServletResponse response, LoginVO loginVO ) {

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

        // 设置cookie 页面端存储 用户相关信息和token 后续需要传输token 验证用户
        // 设置中已经将对象缓存到redis中
        addCookie( response,token,user );

        return token;

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
