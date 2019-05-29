package com.zhengbing.learn.miaosha.controller.vo;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version ${VERSION}
 */
public class LoginVO {

    private String mobile;
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
