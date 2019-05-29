package com.zhengbing.learn.miaosha.entity;

import java.io.Serializable;

/**
 * Created by zhengbing on 2019/5/23.
 * Since Version ${VERSION}
 */
public class User implements Serializable {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
