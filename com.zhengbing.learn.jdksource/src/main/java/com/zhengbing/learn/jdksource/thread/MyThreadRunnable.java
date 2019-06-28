package com.zhengbing.learn.jdksource.thread;

/** Created by zhengbing on 2019-06-26. Email mydreambing@126.com Since Version 1.0 */
public class MyThreadRunnable implements Runnable {
  public void run() {
    System.out.println("MyThreadRunnable run。。。");
  }

  public static void main(String[] args) {
    MyThreadRunnable runnable = new MyThreadRunnable();
    Thread thread = new Thread(runnable);
    thread.start();
    runnable.run();
  }
}
