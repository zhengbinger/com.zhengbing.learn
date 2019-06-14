package com.zhengbing.learn.miaosha.common.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by zhengbing on 2019/5/28.
 * Since Version ${VERSION}
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * get data
     *
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix,String key,Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            String str = jedis.get( realKey );
            T t = string2Bean(str,clazz );
            return t;
        }finally {
            return2Pool(jedis);
        }
    }

    /**
     * set data
     *
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix,String key,T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = bean2String(value);
            String realKey = prefix.getPrefix()+key;
            int expireSeconds = prefix.expireSeconds();
            if(expireSeconds <= 0 ){
                jedis.set( realKey ,str);
            }else {
                jedis.setex( realKey,expireSeconds,str);
            }
            return true;
        }finally {
            return2Pool(jedis);
        }
    }

    /**
     *
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.exists( realKey );
        }finally {
            return2Pool(jedis);
        }
    }

    /**
     *
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.incr( realKey );
        }finally {
            return2Pool(jedis);
        }
    }

    /**
     *
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.decr( realKey );
        }finally {
            return2Pool(jedis);
        }
    }

    /**
     * 删除
     *
     * @param prefix
     * @param key
     * @return
     */
    public boolean delete(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            long ret = jedis.decr( realKey );
            return ret>0;
        }finally {
            return2Pool(jedis);
        }
    }

    /**
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String bean2String( T value ) {
        if( null == value){
            return "";
        }
        Class<?> clazz = value.getClass();
        if ( clazz == Integer.class || clazz == int.class ){
            return "" + value;
        }else if ( clazz == String.class ){
            return (String) value;
        }else if ( clazz == Long.class || clazz == long.class){
            return "" + value;
        } else{
            return JSON.toJSONString( value );
        }
    }

    /**
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T string2Bean( String str ,Class<T> clazz) {
        if( null == str || str.length() <=0 || null == clazz){
            return null;
        }

        if ( clazz == Integer.class || clazz == int.class ){
            return (T) Integer.valueOf( str );
        }else if ( clazz == String.class ){
            return (T) str;
        }else if ( clazz == Long.class ||clazz == long.class ){
            return (T) Long.valueOf( str );
        }else{
            return JSON.toJavaObject( JSON.parseObject( str ),clazz );
        }

    }

    /**
     * return connection to pool
     *
     * @param jedis
     */
    private void return2Pool( Jedis jedis ) {
        if ( null != jedis ){
            jedis.close();
        }
    }

}
