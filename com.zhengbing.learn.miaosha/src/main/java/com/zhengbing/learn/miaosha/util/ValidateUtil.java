package com.zhengbing.learn.miaosha.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
public class ValidateUtil {

    private static final Pattern MOBILE_PATTERN = Pattern.compile( "1\\d{10}" );

    /**
     *
     * validate mobile number format
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile){
        if ( StringUtils.isEmpty(mobile) ){
            return false;
        }
        Matcher matcherer = MOBILE_PATTERN.matcher( mobile );

        return matcherer.matches();
    }

    public static void main( String[] args ) {
        System.out.println( isMobile( "15918111111" ) );
        System.out.println(isMobile( "1591871123" ));
    }
}
