package com.zhengbing.learn.miaosha.common.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhengbing on 2019-06-13.
 * Since Version 1.0
 */
@Configuration
public class MQConfig {

    public static final String MIAO_SHA_QUEUE = "miaosha.queue";

    public static final String QUEUE_NAME = "queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String HEADERS_QUEUE = "headers.queue";
    public static final String TOPIC_EXCHANGE = "topic.exchange";
    public static final String FANOUT_EXCHANGE = "fanout.exchange";
    public static final String HEADERS_EXCHANGE = "headers.exchange";


    @Bean
    public Queue miaoshaQueue(){
        // 第二个参数声明，队列是否要做持久化，重启队列之后可继续使用
        return new Queue( MQConfig.MIAO_SHA_QUEUE,true);
    }

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

    /**
     * topic  交换机模式
     *
     * @return
     */
    @Bean
    public Binding topicBinding(){
        return BindingBuilder.bind( topicQueue1() ).to( topicExchange() ).with( "topic.key1" );
    }

    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind( topicQueue2() ).to( topicExchange() ).with( "topic.#" );
    }

    /**
     * fanout 交换机模式
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange( FANOUT_EXCHANGE );
    }

    @Bean
    public Binding fanoutBinding1(){
        return BindingBuilder.bind( topicQueue1() ).to( fanoutExchange() );
    }

    @Bean
    public Binding fanoutBinding2(){
        return BindingBuilder.bind( topicQueue2() ).to( fanoutExchange() );
    }

    /**
     * header 交换机模式
     *
     * @return
     */

    @Bean
    public Queue headersQueue(){
        // 第二个参数声明，队列是否要做持久化，重启队列之后可继续使用
        return new Queue( MQConfig.HEADERS_QUEUE,true);
    }

    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange( HEADERS_EXCHANGE );
    }

    @Bean
    public Binding headersBinding(){

        Map<String,Object> map = new HashMap<>(  );
        map.put( "header1","value1" );
        map.put( "header2","value2" );
        return BindingBuilder.bind( headersQueue()).to( headersExchange() ).whereAll( map ).match();
    }

}
