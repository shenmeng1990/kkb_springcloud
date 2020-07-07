单机的EurekaServer：
00-eurekaserver-8000

EurekaServer集群：
00-eurekaserver-8100
00-eurekaserver-8200
00-eurekaserver-8300

consumer直连provider：
01-consumer-8080
01-provider-8081

consumer通过RestTemplate访问provider：
02-consumer-8080
02-provider-8081

consumer通过openFeign的Rest调用以及ribbon负载均衡访问provider（内置自定义的负载均衡策略）：
03-consumer-8080
03-provider-8081
03-provider-8082
03-provider-8083

Hystrix类级别的服务降级 fallbackfactory：（使用@FeignClient注解中的fallbackFactory属性）
04-consumer-hystrix-fallbackfactory-8080

Hystrix方法级别的服务降级 fallbackmethod：（方法上使用HystrixCommand注解）
04-consumer-hystrix-fallbackmethod-8080

Hystrix方法级别的服务降级方式二 fallbackfeign（使用@FeignClient注解中的fallback属性）：
04-consumer-hystrix-fallbackfeign-8080

Hystrix-dashboard 仪表盘(单机)
04-consumer-dashboard-8080
04-provider-8081

Hystrix-turbine 仪表盘(集群未分组)
00-hystrix-turbine-8888 turbine未分组服务器（同时也是Eureka的客户端）
04-consumer-turbine-8180 turbine客户端 模拟一个集群
04-consumer-turbine-8280 turbine客户端 模拟一个集群

Hystrix-turbine 仪表盘(集群分组)
00-hystrix-turbine-7777 turbine分组服务器
04-consumer-turbine-8380 turbine客户端 模拟一个集群 group1
04-consumer-turbine-8480 turbine客户端 模拟一个集群 group1
04-consumer-turbine-8580 turbine客户端 模拟一个集群 group2
04-consumer-turbine-8680 turbine客户端 模拟一个集群 group2

服务降级报警
04-consumer-fallbackalarm-8080

Zuul 服务网关
00-zuul-9000

通过服务网关屏蔽微服务名称
05-consumer-zuul-8080
05-consumer-zuul-8090

zuul实现负载均衡
05-consumer-zuul-8180
05-consumer-zuul-8280
05-consumer-zuul-8380

zuul实现服务降级
00-zuul-fallback-9000

zuulFileter实现请求过滤
00-zuul-filter-9000

zuul通过令牌桶算法实现限流
00-zuul-tokenbucket-9000

zuul实现多维限流
00-zuul-ratelimit-9000

zuul实现灰度发布的两种方式
00-zuul-graypublic-9000

spring cloud config 服务器
00-config-server-9999 连接github或gitee，拉取相关配置文件
06-config-eurekaserver eureka server作为config server的服务端，组装配置文件
06-config-provider 服务提供者 作为eureka的客户端以及configserver 的客户端
06-config-consumer 服务消费者 作为eureka的客户端以及configserver 的客户端

spring cloud config bus 实时刷新配置
06-bus-config-consumer
06-bus-config-provider

spring cloud sleuth 日志采样
07-sleuth-provider-8081
07-sleuth-provider-8080

spring cloud sleuth 通过http方式发送日志到zipkin
07-via-provider-8081
07-via-consumer-8080

spring cloud sleuth + kafka +zipkin
07-kafka-provider-8081
07-kafka-consumer-8080
zipkin启动方式： java -DKAFKA_BOOTSTRAP_SERVERS=kafkaos1:9092 –jar zipkin.jar

springcloud Stream kafka
08-stream-kafka-8080 创建发布一个主题的producer
08-stream-kafka2-8080 创建多个主题的producer，以及三种实现消费者的方式