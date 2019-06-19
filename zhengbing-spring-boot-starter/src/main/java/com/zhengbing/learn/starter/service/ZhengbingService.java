package com.zhengbing.learn.starter.service;

import com.zhengbing.learn.starter.ZhengbingProperties;

/** Created by zhengbing on 2019-06-19. Since Version 1.0 */
public class ZhengbingService {

  private ZhengbingProperties zhengbingProperties;

  public ZhengbingService() {}

  public ZhengbingService(ZhengbingProperties zhengbingProperties) {
    this.zhengbingProperties = zhengbingProperties;
  }

  public void sayHello() {
    System.out.println(
        "大叫好，我叫："
            + zhengbingProperties.getName()
            + "，今年："
            + zhengbingProperties.getAge()
            + "岁，性别："
            + zhengbingProperties.getSex());
  }
}
