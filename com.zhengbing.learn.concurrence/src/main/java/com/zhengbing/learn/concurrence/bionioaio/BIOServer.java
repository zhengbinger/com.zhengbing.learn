package com.zhengbing.learn.concurrence.bionioaio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BIO:阻塞式线程
 * Created by zhengbing on 2019-06-27.
 * Email mydreambing@126.com
 * Since Version 1.0
 */
public class BIOServer {

    static AtomicInteger counter = new AtomicInteger( 0 );
    static SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

    public static void main( String[] args ) {
        try {
            // 创建服务端socket
            ServerSocket server = new ServerSocket();
            // 设置服务端地址和端口
            server.bind( new InetSocketAddress( "localhost", 7777 ) );

            // 接收客户端连接
            while ( true ) {
                Socket request = server.accept();
                processWithNewThread( request );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    static void processWithNewThread( Socket socket ) {

        Runnable runnable = () -> {
            InetSocketAddress rsa = (InetSocketAddress) socket.getRemoteSocketAddress();
            System.out.println( time() + "->" + rsa.getHostName() + ":" + rsa.getPort() + "->" + Thread.currentThread().getId() + ":" + counter.incrementAndGet() );
            try {
                String result = readBytes( socket.getInputStream() );
                System.out.println( time() + "->" + result + "->" + Thread.currentThread().getId() + ":" + counter.getAndDecrement() );
                socket.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        };
        new Thread( runnable ).start();
    }

    static String readBytes( InputStream inputStream ) throws IOException {

        long start = 0;
        int total = 0;
        int count = 0;
        byte[] bytes = new byte[ 1024 ];
        // 开始读数据的时间
        long begin = System.currentTimeMillis();
        while ( ( count = inputStream.read( bytes ) ) > -1 ) {
            // 第一次独到数据的时间
            if ( start < 1 ) {
                start = System.currentTimeMillis();
            }
            total += count;
        }
        // 读完数据的时间
        long end = System.currentTimeMillis();
        return "wait=" + ( start - begin ) + "ms,read=" + ( end - start ) + "ms,total=" + total + "bs";
    }

    /**
     * 格式化当前时间
     *
     * @return
     */
    static String time() {
        return format.format( new Date() );
    }
}
