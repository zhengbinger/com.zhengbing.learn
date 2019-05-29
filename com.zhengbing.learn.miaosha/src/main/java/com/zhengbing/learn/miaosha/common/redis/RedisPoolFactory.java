package com.zhengbing.learn.miaosha.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version ${VERSION}
 */
@Service
public class RedisPoolFactory {

    @Autowired
    RedisConfig redisConfig;

    @Bean
    public JedisPool jedisFactory(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle( redisConfig.getPoolMaxIdle());
        config.setMaxTotal(  redisConfig.getPoolMaxTotal());
        config.setMaxWaitMillis(  redisConfig.getPoolMaxWait() *1000);
        JedisPool pool = new JedisPool( config,redisConfig.getHost(),redisConfig.getPort(),redisConfig.getTimeout(),redisConfig.getPassword(),0 );
        return pool;
    }


}
