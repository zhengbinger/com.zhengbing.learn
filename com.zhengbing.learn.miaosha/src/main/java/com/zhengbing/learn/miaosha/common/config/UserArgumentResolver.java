package com.zhengbing.learn.miaosha.common.config;

import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhengbing on 2019/5/30.
 * Since Version ${VERSION}
 */

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter( MethodParameter methodParameter ) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == MiaoshaUser.class;
    }

    @Nullable
    @Override
    public Object resolveArgument( MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory ) throws Exception {
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        String paramToken = request.getParameter( MiaoshaUserService.COOKIE_NAME_TOKEN );
        String cookieToken = getCookieValue(request, MiaoshaUserService.COOKIE_NAME_TOKEN);

        if( null == paramToken && null == cookieToken ){
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;

         return miaoshaUserService.getbyToken( response,token );
    }

    /**
     *
     * 获取 cookie 值
     *
     * @param request
     * @param cookieName
     * @return
     */
    private String getCookieValue( HttpServletRequest request, String cookieName ) {

        Cookie[] cookies = request.getCookies();
        // 压测之后查处的bug进行修改
        if( null == cookies || cookies.length == 0){
            return null;
        }
        for ( Cookie cookie:cookies ){
            if ( cookie.getName().equals( cookieName ) ){
                return cookie.getValue();
            }
        }
        return null;
    }
}
