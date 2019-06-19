package com.zhengbing.learn.starter.config;

import com.zhengbing.learn.starter.ZhengbingProperties;
import com.zhengbing.learn.starter.service.ZhengbingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Created by zhengbing on 2019-06-19. Since Version 1.0 */
@Configuration
@EnableConfigurationProperties(ZhengbingProperties.class)
@ConditionalOnClass(ZhengbingService.class)
@ConditionalOnProperty(prefix = "spring.zhengbing", value = "enable", matchIfMissing = true)
public class ZhengbingAutoConfiguration {

  @Autowired private ZhengbingProperties zhengbingProperties;

  /**
   * 在容器中注入ZhengbingService
   *
   * @return
   */
  @Bean
  @ConditionalOnMissingBean(ZhengbingService.class) // 当容器中没有指定Bean的情况下，自动配置ZhengbingService类
  public ZhengbingService zhengbingService() {
    ZhengbingService zhengbingService = new ZhengbingService(zhengbingProperties);
    return zhengbingService;
  }
}
