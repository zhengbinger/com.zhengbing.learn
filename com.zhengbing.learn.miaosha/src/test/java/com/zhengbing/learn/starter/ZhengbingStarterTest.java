package com.zhengbing.learn.starter;

import com.zhengbing.learn.starter.service.ZhengbingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** Created by zhengbing on 2019-06-19. Since Version 1.0 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZhengbingStarterTest {

  @Autowired ZhengbingService zhengbingService;

  @Test
  public void testStarter() {
    zhengbingService.sayHello();
  }
}
