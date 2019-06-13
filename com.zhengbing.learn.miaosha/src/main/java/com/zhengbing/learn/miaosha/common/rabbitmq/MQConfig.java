package com.zhengbing.learn.miaosha.common.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Created by zhengbing on 2019-06-13.
 * Since Version 1.0
 */
@Configuration
public class MQConfig {

    public static final String QUEUE_NAME = "queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topic.exchange";

    @Bean
    public Queue queue(){
        // 第二个参数声明，队列是否要做持久化，重启队列之后可继续使用
        return new Queue( MQConfig.QUEUE_NAME,true);
    }

    @Bean
    public Queue topicQueue1(){
        // 第二个参数声明，队列是否要做持久化，重启队列之后可继续使用
        return new Queue( MQConfig.TOPIC_QUEUE1,true);
    }
    @Bean
    public Queue topicQueue2(){
        // 第二个参数声明，队列是否要做持久化，重启队列之后可继续使用
        return new Queue( MQConfig.TOPIC_QUEUE2,true);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange( TOPIC_EXCHANGE );
    }

    @Bean
    public Binding topicBinding(){
        return BindingBuilder.bind( topicQueue1() ).to( topicExchange() ).with( "topic_key1" );
    }

    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind( topicQueue2() ).to( topicExchange() ).with( "topic_#" );
    }


}
