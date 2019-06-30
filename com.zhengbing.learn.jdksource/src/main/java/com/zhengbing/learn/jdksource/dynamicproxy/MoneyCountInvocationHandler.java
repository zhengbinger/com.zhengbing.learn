package com.zhengbing.learn.jdksource.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** Created by zhengbing on 2019-06-20. Since Version 2.0 */
public class MoneyCountInvocationHandler implements InvocationHandler {

  /** 被代理的目标 */
  private final Object object;

  private Double moneyCount;

  public MoneyCountInvocationHandler(Object object) {
    this.object = object;
    this.moneyCount = 0.0;
  }

  /**
   * 包装业务处理
   *
   * @param proxy
   * @param method
   * @param args
   * @return
   * @throws Throwable
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Object result = method.invoke(object, args);
    moneyCount += 0.05;
    System.out.println("发送短信成功，共花了：" + moneyCount + "元");
    return result;
  }
}
