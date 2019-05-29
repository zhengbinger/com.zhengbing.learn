package com.zhengbing.learn.miaosha.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
public class MiaoshaUser implements Serializable {

    private long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private int loginCount;

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname( String nickname ) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt( String salt ) {
        this.salt = salt;
    }

    public String getHead() {
        return head;
    }

    public void setHead( String head ) {
        this.head = head;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate( Date registerDate ) {
        this.registerDate = registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate( Date lastLoginDate ) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount( int loginCount ) {
        this.loginCount = loginCount;
    }
}
