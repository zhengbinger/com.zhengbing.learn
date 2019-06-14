package com.zhengbing.learn.miaosha.controller;

import com.zhengbing.learn.miaosha.common.Result;
import com.zhengbing.learn.miaosha.common.rabbitmq.MQSender;
import com.zhengbing.learn.miaosha.common.redis.RedisService;
import com.zhengbing.learn.miaosha.common.redis.UserKey;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.User;
import com.zhengbing.learn.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhengbing on 2019/5/23.
 * Since Version ${VERSION}
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

    @RequestMapping("/user/info")
    @ResponseBody
    public Result<MiaoshaUser> info( Model model, MiaoshaUser user ){
        return Result.success( user );
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
        User user = userService.getById( 1 );
        return Result.success( user );
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Integer> dbTx(){
        User user = new User();
        user.setName( "china" );
        int result = userService.insert( user );
        return Result.success( result );
    }

//    @RequestMapping(value = "/mq",method = RequestMethod.GET)
//    @ResponseBody
//    public Result<Integer> mq(){
//        sender.sendTopic( "zhengbing" );
//        return Result.success( 1 );
//    }
//
//    @RequestMapping(value = "/mq/topic",method = RequestMethod.GET)
//    @ResponseBody
//    public Result<Integer> topic(){
//        sender.sendTopic( "zhengbing" );
//        return Result.success( 1 );
//    }
//
//
//    @RequestMapping(value = "/mq/fanout",method = RequestMethod.GET)
//    @ResponseBody
//    public Result<Integer> fanout(){
//        sender.sendFanout( "zhengbing" );
//        return Result.success( 1 );
//    }
//
//    @RequestMapping(value = "/mq/header",method = RequestMethod.GET)
//    @ResponseBody
//    public Result<Integer> header(){
//        sender.sendHeaders( "zhengbing" );
//        return Result.success( 1 );
//    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
//        redisService.set( "china" ,123);
//        int str = redisService.get("china" , int.class);
        User user = redisService.get( UserKey.getById, ""+1 , User.class);
//        User user = userService.getById( 1 );
        return Result.success( user );
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<User> redisSet(){
//        boolean result = redisService.set( "china1" ,"hello china");
        User user = new User();
        user.setId( 1 );
        user.setName( "zhengbing" );
        boolean result = redisService.set( UserKey.getById,""+1 ,user);
       User user1 = redisService.get(UserKey.getById,""+1 , User.class);

        return Result.success( user1 );
    }
}
