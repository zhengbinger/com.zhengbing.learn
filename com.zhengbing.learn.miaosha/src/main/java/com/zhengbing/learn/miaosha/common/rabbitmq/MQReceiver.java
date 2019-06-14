package com.zhengbing.learn.miaosha.common.rabbitmq;

import com.zhengbing.learn.miaosha.common.CodeMsg;
import com.zhengbing.learn.miaosha.common.exception.GlobalException;
import com.zhengbing.learn.miaosha.common.redis.RedisService;
import com.zhengbing.learn.miaosha.entity.MiaoshaOrder;
import com.zhengbing.learn.miaosha.entity.MiaoshaUser;
import com.zhengbing.learn.miaosha.entity.Orderinfo;
import com.zhengbing.learn.miaosha.entity.vo.GoodsVO;
import com.zhengbing.learn.miaosha.service.GoodsService;
import com.zhengbing.learn.miaosha.service.MiaoshaOrderService;
import com.zhengbing.learn.miaosha.service.MiaoshaService;
import com.zhengbing.learn.miaosha.service.OrderinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2019-06-13.
 * Since Version 1.0
 */
@Service
public class MQReceiver {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderinfoService orderinfoService;

    @Autowired
    MiaoshaOrderService miaoshaOrderService;

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    RedisService redisService;

    private Logger logger = LoggerFactory.getLogger( MQReceiver.class );


    @RabbitListener(queues = MQConfig.MIAO_SHA_QUEUE)
    public void receive( String message ){
        logger.info( "received message:" +message);
        MiaoshaMessage mm = RedisService.string2Bean( message ,MiaoshaMessage.class);
        MiaoshaUser user = mm.getUser();
        long goodsId = mm.getGoodsId();

        // 判断秒杀的商品是否还有库存
       GoodsVO goods = goodsService.getGoodVOByGoodsId( goodsId );
        if ( goods.getStockCount() <= 0 ){

            throw new GlobalException( CodeMsg.MIAO_SHA_OVER );
        }

        // 判断是否已经秒杀过了
        MiaoshaOrder miaoshaOrder = miaoshaOrderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if ( null != miaoshaOrder ){
            throw new GlobalException( CodeMsg.REPEAT_MIAO_SHA );
        }
        Orderinfo order = miaoshaService.miaosha(user,goods);
    }

//    /**
//     *  Direct 模式   rabbitmq 有四种交换机模式  Exchange
//     * @param message
//     */
//    @RabbitListener(queues = MQConfig.QUEUE_NAME)
//    public void receive( String message ){
//        logger.info( "received message:" +message);
//    }
//
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
//    public void receiveTopic1( String message ){
//        logger.info( "received topic1 message:" +message);
//    }
//
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
//    public void receiveTopic2( String message ){
//        logger.info( "received topic2 message:" +message);
//    }
//
//    @RabbitListener(queues = MQConfig.HEADERS_QUEUE)
//    public void receiveHeaders( byte[] message ){
//        logger.info( "received header message:" + new String( message ) );
//    }

}
