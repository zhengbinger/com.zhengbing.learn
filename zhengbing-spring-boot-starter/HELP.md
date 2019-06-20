# 自定义starter
## 创建maven项目（ maven-archetype-quickstart ）
命名规则：注意artifactId的命名规则，Spring官方Starter通常命名为spring-boot-starter-{name}
        如 spring-boot-starter-web， Spring官方建议非官方Starter命名应遵循{name}-spring-boot-starter
        的格式, 如mybatis-spring-boot-starter。这里创建的项目的artifactId为zhengbing-spring-boot-starte
## 引入必要的依赖
    修改pom文件中的<packaging>jar</packaging>
    starter需要使用到Spring boot的自动配置功能，所以需要引入自动配置相关的依赖
   
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-autoconfigure</artifactId>
      <version>2.0.0.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-configuration-processor</artifactId>
      <version>2.0.0.RELEASE</version>
      <optional>true</optional>
    </dependency>
## 定义自己的Properties类
    @ConfigurationProperties(prefix = "spring.zhengbing")
    public class ZhengbingProperties {

      private String name;
      private int age;
      private char sex;
        ...
     }

## 定义自己的核心服务类
    业务处理逻辑

## 定义核心配置类
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

## 创建META-INF/spring.factories文件
    写入自己的核心配置类
    org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.zhengbing.learn.starter.config.ZhengbingAutoConfiguration

## 打包生成即可
    mvn clean install

## 引用的工程，添加自定义starter的依赖
		<dependency>
			<groupId>com.zhengbing.learn</groupId>
			<artifactId>zhengbing-spring-boot-starter</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>

## 引用的工程加入自定义starter的配置
      zhengbing:
        name: zhengbing
        sex: M

## 测试
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

## 输出结果：
    大叫好，我叫：zhengbing，今年：0岁，性别：M 
