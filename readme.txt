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