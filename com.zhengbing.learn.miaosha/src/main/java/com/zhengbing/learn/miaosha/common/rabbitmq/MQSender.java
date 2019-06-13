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

    public void send( Object message ){
        String msg = RedisService.bean2String( message );
        logger.info( "send message:"+msg );
        amqpTemplate.convertAndSend(MQConfig.QUEUE_NAME, msg );
    }

    public void sendTopic( Object message ){
        String msg = RedisService.bean2String( message );
        logger.info( "send topic message:"+msg );
        amqpTemplate.convertAndSend(MQConfig.TOPIC_QUEUE1,"topic_key1", msg +"1");
        amqpTemplate.convertAndSend(MQConfig.TOPIC_QUEUE2,"topic_key2", msg +"2");
    }
}
