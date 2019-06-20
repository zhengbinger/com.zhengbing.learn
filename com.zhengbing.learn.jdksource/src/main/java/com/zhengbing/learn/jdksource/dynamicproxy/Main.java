package com.zhengbing.learn.jdksource.dynamicproxy;

import java.lang.reflect.Proxy;

/** 基于 jdk 实现动态代理 Created by zhengbing on 2019-06-20. Since Version 1.0 */
public class Main {

  public static void main(String[] args) {
    // 1.0  基础版本实现
    SMSService smsService = new SMSServiceImpl();

    // 2.0 动态代理实现
    smsService =
        (SMSService)
            Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[] {SMSService.class},
                new MoneyCountInvocationHandler(smsService));
    smsService.sendMessage();
    smsService.sendMessage();
  }
}
