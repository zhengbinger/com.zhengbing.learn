package com.zhengbing.learn.miaosha.common.rabbitmq;

import com.zhengbing.learn.miaosha.common.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhengbing on 2019-06-13.
 * Since Version 1.0
 */
@Component
public class MQSender {

    private Logger logger = LoggerFactory.getLogger( MQSender.class );

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMiaoshaMessage(MiaoshaMessage mm){
        String msg = RedisService.bean2String( mm );
        logger.info( "send message:"+msg );
        amqpTemplate.convertAndSend(MQConfig.MIAO_SHA_QUEUE, msg );
    }

//    public void send( Object message ){
//        String msg = RedisService.bean2String( message );
//        logger.info( "send message:"+msg );
//        amqpTemplate.convertAndSend(MQConfig.QUEUE_NAME, msg );
//    }
//
//    public void sendTopic( Object message ){
//        String msg = RedisService.bean2String( message );
//        logger.info( "send topic message:"+msg );
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key1", msg +"1");
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key2", msg +"2");
//    }
//
//    public void sendFanout( Object message ){
//        String msg = RedisService.bean2String( message );
//        logger.info( "send fanout message:"+msg );
//        amqpTemplate.convertAndSend( MQConfig.FANOUT_EXCHANGE,"", msg +"1");
//    }
//
//    public void sendHeaders( Object message ){
//        String msg = RedisService.bean2String( message );
//
//        logger.info( "send headers message:"+msg );
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setHeader( "header1","value1" );
//        messageProperties.setHeader( "header2","value2" );
//        Message obj = new Message( msg.getBytes(),messageProperties);
//
//        amqpTemplate.convertAndSend( MQConfig.HEADERS_EXCHANGE,"", obj);
//    }

}
