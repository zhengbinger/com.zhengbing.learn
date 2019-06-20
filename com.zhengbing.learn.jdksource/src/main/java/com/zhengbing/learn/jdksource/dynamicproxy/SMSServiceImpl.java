package com.zhengbing.learn.jdksource.dynamicproxy;

/** Created by zhengbing on 2019-06-20. Since Version 1.0 */
public class SMSServiceImpl implements SMSService {

  public void sendMessage() {
    System.out.println("【郑冰】您正在执行重置密码操作，您的验证码为：1234，5分钟内有效，请不要将验证码转发给他人。");
  }
}
