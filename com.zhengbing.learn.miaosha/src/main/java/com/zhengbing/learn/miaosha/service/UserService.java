package com.zhengbing.learn.miaosha.service;

import com.zhengbing.learn.miaosha.dao.UserDao;
import com.zhengbing.learn.miaosha.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2019/5/23.
 * Since Version ${VERSION}
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById( int id){
        return userDao.getUser( id );
    }


    public int insert( User user ) {
        return userDao.insert(user);
    }
}
