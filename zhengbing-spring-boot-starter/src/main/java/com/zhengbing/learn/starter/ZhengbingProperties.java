package com.zhengbing.learn.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Created by zhengbing on 2019-06-19. Since Version 1.0 */
@ConfigurationProperties(prefix = "spring.zhengbing")
public class ZhengbingProperties {

  private String name;
  private int age;
  private char sex;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public char getSex() {
    return sex;
  }

  public void setSex(char sex) {
    this.sex = sex;
  }
}
