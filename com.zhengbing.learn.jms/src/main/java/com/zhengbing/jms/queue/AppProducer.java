package com.zhengbing.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by zhengbing on 2019/5/17.
 * Since Version 1.0
 */
public class AppProducer {

    public static final String URL="tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME="queue_test";

    public static void main( String[] args ) throws JMSException {

        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( URL );

        // 2. 创建连接
        Connection connection = connectionFactory.createConnection();

        // 3. 启动连接
        connection.start();

        // 4. 创建会话 session
        Session  session = connection.createSession( false ,1);
    }
}
