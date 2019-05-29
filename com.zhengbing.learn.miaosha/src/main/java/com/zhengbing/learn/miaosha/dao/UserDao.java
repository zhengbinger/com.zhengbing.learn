package com.zhengbing.learn.miaosha.dao;

import com.zhengbing.learn.miaosha.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhengbing on 2019/5/23.
 * Since Version ${VERSION}
 */
@Mapper
public interface UserDao {

    @Select( value = "select * from user where id = #{id}" )
    User getUser( @Param( "id" ) int id );

    @Insert( value = "insert into user(name) values(#{name})" )
    int insert(User user);
}
