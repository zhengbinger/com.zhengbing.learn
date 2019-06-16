package com.zhengbing.learn.miaosha.service;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.exception.GlobalException;
import com.zhengbing.learn.miaosha.common.redis.MiaoshaKey;
import com.zhengbing.learn.miaosha.common.redis.RedisService;
import com.zhengbing.learn.miaosha.entity.MiaoshaOrder;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.Orderinfo;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import com.zhengbing.learn.miaosha.util.MD5Util;
import com.zhengbing.learn.miaosha.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by zhengbing on 2019/5/31.
 * Since Version 1.0
 */
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    MiaoshaGoodsService miaoshaGoodsService;

    @Autowired
    OrderinfoService orderinfoService;

    @Autowired
    MiaoshaOrderService miaoshaOrderService;

    @Autowired
    RedisService redisService;

    @Transactional
    public Orderinfo miaosha( MiaoshaUser user, GoodsVO goodsVO){

        // 减少秒杀库存
        boolean ret = miaoshaGoodsService.reduceStock( goodsVO.getId() );

        if ( ret ){
            // 生成订单（ 1.生成基本订单 ）
            Orderinfo order =  orderinfoService.createOrder( user, goodsVO );
            if ( null == order){
                return null;
            }

            // 生成秒杀订单（ 2.生成秒杀订单 )
            MiaoshaOrder miaoshaOrder = miaoshaOrderService.createMiaoshaOrder(user, goodsVO,order.getId());
            if ( null == miaoshaOrder ){
                return null;
            }
            return order;
        }
        setGoodsOver(goodsVO.getId());
        return null;
    }

    private void setGoodsOver( Long goodsId ) {
        redisService.set( MiaoshaKey.isGoodsOver,""+goodsId,true);
    }

    /**
     * 获取秒杀结果
     *
     * @param userId
     * @param goodsId
     * @return
     */
    public long getMiaoshaResult( long userId, long goodsId ) {
        MiaoshaOrder order = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId( userId,goodsId );
        if ( null != order ){
            return order.getId();
        }else {
            if ( getGoodsOver( goodsId ) ){
                throw new GlobalException( CodeMsg.MIAO_SHA_OVER );
            }else {
                return 0;
            }
        }

    }

    /**
     * 获取秒杀是否结束的缓存
     * @param goodsId
     * @return
     */
    private boolean getGoodsOver( long goodsId ) {
        return redisService.exists( MiaoshaKey.isGoodsOver,""+goodsId);
    }

    /**
     * 创建秒杀url参数 并存放缓存
     *
     * @param user
     * @param goodsId
     * @return
     */
    public String createMiaoshaPath( MiaoshaUser user, long goodsId ) {
        String sign = MD5Util.md5( UUIDUtil.uuid() +Math.random()*100000);

        redisService.set( MiaoshaKey.getMiaoshaPath,user.getId()+"_"+goodsId ,sign);
        return sign;
    }

    /**
     * 检查匹配秒杀URL参数的正确性
     *
     * @param user
     * @param goodsId
     * @param path
     * @return
     */
    public boolean checkMiaoshaPath( MiaoshaUser user, long goodsId, String path ) {
        if ( null == user || goodsId <= 0 ){
            return false;
        }
        String sign = redisService.get( MiaoshaKey.getMiaoshaPath,user.getId()+"_"+goodsId,String.class );
        return sign.equals( path );

    }

    /**
     * 生成验证码图片
     *
     * @param user
     * @param goodsId
     * @return
     */
    public BufferedImage createVerifyCode( MiaoshaUser user, long goodsId ) {
        if ( null == user || goodsId <= 0 ){
            return null;
        }
        int width = 100;
        int height =32;
        // create image
        BufferedImage image = new BufferedImage( width,height,BufferedImage.TYPE_INT_RGB  );
        // create graphics
        Graphics graphics = image.createGraphics();

        // 设置背景颜色
        graphics.setColor( new Color( 0xDCDCDC ) );
        graphics.fillRect( 0,0,width,height );

        // 设置边框颜色
        graphics.setColor( Color.black );
        graphics.drawRect( 0,0,width-1,height-1 );

        // 生成干扰点
        Random random = new Random(  );
        for ( int i = 0; i < 50; i++ ){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            graphics.drawOval( x,y,0,0 );
        }

        String verify = ganerateVerifyCode( random );

        graphics.setColor( new Color( 0,100,0 ) );
        graphics.setFont( new Font( "Candara",Font.BOLD,24 ) );
        graphics.drawString( verify,8,24 );
        graphics.dispose();

        // 把验证码存到redis中
        int rnd = calc(verify);
        redisService.set( MiaoshaKey.getMiaoshaVerifyCode,user.getId()+","+goodsId,rnd );

        return image;


    }

    private static final char[] ops = new char[]{'+','-','*'};

    /**
     * 生成验证码字符串
     * @param random
     * @return
     */
    private String ganerateVerifyCode( Random random ) {
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(10);
        char op1 = ops[random.nextInt(3)];
        char op2 = ops[random.nextInt(3)];

        String exp = "" + num1 + op1 + num2 + op2 + num3;
        return exp;
    }

    /**
     * 计算表达式的值
     *
     * @param exp
     * @return
     */
    private int calc( String exp ){

        try{
            ScriptEngineManager manager = new ScriptEngineManager(  );
            ScriptEngine se = manager.getEngineByName( "JavaScript" );
            return (Integer)se.eval( exp );

        }catch( Exception e ){
            e.printStackTrace();
            return 0;
        }
    }
}
