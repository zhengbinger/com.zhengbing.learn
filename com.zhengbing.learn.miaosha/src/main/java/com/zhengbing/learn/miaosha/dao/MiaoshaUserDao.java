package com.zhengbing.learn.miaosha.dao;

import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version 1.0
 */
@Mapper
public interface MiaoshaUserDao {

    @Select( "select *  from miaosha_user where id = #{id}" )
     MiaoshaUser getById( @Param( "id" ) long id);

    @Update( "update miaosha_user set password = #{password} whre id = #{id}" )
    int update( MiaoshaUser updateUser );
}
