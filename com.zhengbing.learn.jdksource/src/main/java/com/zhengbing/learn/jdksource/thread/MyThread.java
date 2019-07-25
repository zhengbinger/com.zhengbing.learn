package com.zhengbing.learn.jdksource.thread;

/**
 * Thread类本质上是实现了Runnable接口的一个实例， 代表一个线程的实例。启动线程的唯一方法就是通过Thread类的start()实例方法。 start()方法是一个native方法，
 * 它将启动一个新线程，并执行run()方法。这种方式实现多线程很简单， 通过自己的类直接extend Thread，并复写run()方法，就可以启动新线程并执行自己定义的run()方法。
 *
 * Created by zhengbing on 2019-06-26.
 * Email mydreambing@126.com
 * Since Version 1.0
 */
public class MyThread extends Thread {

  @Override
  public void run() {
    super.run();
    System.out.println("MyThread run...");
  }

  public static void main(String[] args) {
    MyThread thread = new MyThread();
    MyThread thread2 = new MyThread();
    thread.run();
    thread2.run();
  }
}
