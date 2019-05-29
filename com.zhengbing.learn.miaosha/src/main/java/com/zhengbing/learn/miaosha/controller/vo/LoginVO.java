package com.zhengbing.learn.miaosha.controller.vo;

import com.zhengbing.learn.miaosha.common.validator.IsMobile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version ${VERSION}
 */
public class LoginVO {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile( String mobile ) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
