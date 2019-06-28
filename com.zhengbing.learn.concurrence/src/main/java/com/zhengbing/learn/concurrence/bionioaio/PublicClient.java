package com.zhengbing.learn.concurrence.bionioaio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

/**
 * Created by zhengbing on 2019-06-27.
 * Email mydreambing@126.com
 * Since Version 1.0
 */
public class PublicClient {

    public static void main( String[] args ) {

        try {
            // 创建20个连接去连接服务端，每个连接使用一个线程发送数据
            for ( int i = 0; i < 20; i++ ) {
                Socket socket = new Socket();
                socket.connect( new InetSocketAddress( "localhost", 7777 ) );
                processWithNewThread( socket, i );
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }

    static void processWithNewThread( Socket socket, int n ) {
        Runnable runnable = () -> {

            try {
                //睡眠随机的5-10秒，模拟数据尚未就绪
                Thread.sleep( ( ( new Random().nextInt( 6 ) + 5 ) * 1000 ) );
                //睡眠随机的5-10秒，模拟数据尚未就绪
                socket.getOutputStream().write( prepareBytes() );
                //睡眠1秒，让服务器端把数据读完
                Thread.sleep( 1000 );
                socket.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        };
        new Thread( runnable ).start();
    }

    static byte[] prepareBytes() {
        byte[] bytes = new byte[ 1024 * 1024 * 1 ];
        for ( int i = 0; i < bytes.length; i++ ) {
            bytes[ i ] = 1;
        }
        return bytes;
    }
}
