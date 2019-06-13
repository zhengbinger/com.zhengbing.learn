# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)


## command line jmeter  test

1. window上录好jmx
2. sh  jmeter.sh  -n -t  XXX.jmx -l  result.jtl
3. 把result.jtl 导入jmeter

## Redis 压测（逐个命令进行测试）
    
* 100个并发  100000个请求  
   > redis-benchmark -h 127.0.0.1  -p 6379 -c 100 -n 100000
* 存储大小为100的字节包
   > redis-benchmark -h 127.0.0.1  -p 6379 -q -d 100
* 指定某些命令进行测试
   > redis-benchmark -h 127.0.0.1  -p 6379  -t set.lpush -n 100000 -q
## springboot 打war包
1. 添加spring-boot-starter-tomcat的provided依赖
        
        <dependency>
	        <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-tomcat</artifactId>
		    <scope>provided</scope>
	    </dependency>
	    
2. 添加maven-war-plugin 插件
       
       <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
                <configuration>
                <failOnMissingWebXml>false</failOnMissingWebXml>
            </configuration>
        </plugin>
        
3. 修改pom 属性  <packaging>war</packaging>
4. 修改 XXXApplication 启动类
    1. 继承 SpringBootServletInitializer
    2. 重写方法 configure
    
            @Override
	        protacted SpringApplicationBuilder  configure(SpringBootApplicationBuilder builder){
	            return builder.sources(XXXApplication.class);
	        }
	          
## 页面优化技术
1. 页面缓存+URL缓存+对象缓存
    1. 页面缓存
        
        > 1. 取缓存
        > 2. 手动渲染
        > 3. 结果输出，存缓存
    2. URL缓存
    3. 对象缓存
        > 缓存使用频繁且变更不频繁的用户对象信息   
          如果有涉及到更新，需要同步更新缓存的对象

2. 页面静态化，前后端分离  
    1. 常用技术 VUE AngularJS
    2. 优点，利用浏览器缓存
3. 静态资源化
    1. js/css压缩，减少流量使用
    2. 多个js/css组合，减少文件连接数   
        1. tengine 淘宝提供的技术处理压缩
        2. webpack  专门用来打包前端资源
        3. CDN就近访问  内容分发网络，（静态资源缓存）  
         根据用户位置，获取距离最近的镜像节点获取内容，  
         解决网络拥堵，提高页面响应效率
### 问题处理
1. 超卖的问题
    1. 数据库加唯一索引：防止同一用户购买同一商品
    2. SQL加库存判断，防止库存数量变成负数         

## 接口优化

1. Redis预减库存，减少数据库访问
2. 内存标记减少Redis访问
3. 请求先入队缓冲，异步下单，增强用户体验
4. RabbitMQ安装与springboot集成
5. Nginx水平扩展
6. 压测
7. 数据库分库分表，阿里mycat中间件
#### 优化思路：减少数据库的访问
1. 系统初始化，预先把商品的库存加载到Redis
2. 收到请求，redis预减库存，如遇库存不足，直接返回
3. 请求进入消息队列，立即返回排队中，可以缓冲并发
4. 请求出队列，生成订单，减少库存
5. 客户端轮询，是否秒杀成功

##### springboot 集成RabbitMQ
1. 添加依赖  spring-boot-starter-amqp
2. 创建发送者
3. 创建接收者

#### RabbitMQ 四种交换机模式

    发送者发送消息并不是直接发送到队列里，而是先发送到交换机，再通过交换机发送给消息队列
1. Direct
2. Topic