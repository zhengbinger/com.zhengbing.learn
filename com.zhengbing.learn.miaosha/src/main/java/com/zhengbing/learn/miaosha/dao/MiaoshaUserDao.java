package com.zhengbing.learn.miaosha.dao;

import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhengbing on 2019/5/29.
 * Since Version ${VERSION}
 */
@Mapper
public interface MiaoshaUserDao {

    @Select( "select *  from miaosha_user where id = #{id}" )
     MiaoshaUser getById( @Param( "id" ) long id);

}
