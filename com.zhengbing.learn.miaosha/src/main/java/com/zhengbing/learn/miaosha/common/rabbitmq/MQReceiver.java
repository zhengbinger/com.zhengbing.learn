package com.zhengbing.learn.miaosha.common.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhengbing on 2019-06-13.
 * Since Version 1.0
 */
@Component
public class MQReceiver {

    private Logger logger = LoggerFactory.getLogger( MQReceiver.class );

    /**
     *  Direct 模式   rabbitmq 有四种交换机模式  Exchange
     * @param message
     */
    @RabbitListener(queues = MQConfig.QUEUE_NAME)
    public void receive( String message ){
        logger.info( "received message:" +message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1( String message ){
        logger.info( "received topic message:" +message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2( String message ){
        logger.info( "received message:" +message);
    }

}
