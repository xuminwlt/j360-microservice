j360-microservice
==============

j360系列之spring-boot微服务架构和docker部署
##介绍##
- j360-order底层服务提供接口 使用restAPI提供服务
- j360-deliver提供UI操作界面，调用底层j360-order提供服务

## 构建 ##
- Java 1.7+(官方建议1.8)
- Maven 3.2+

- Spring 4.1.3
- Spring-boot 1.3.0.BUILD-SNAPSHOT
- j360 1.0.0.BUILD-SNAPSHOT


##j360-order##
- 直接使用Spring data jpa，省点力气写dao ：）

###两种rest风格###
- @RepositoryRestResource(collectionResourceRel = "expresss", path = "expresses")
- +spring.data.rest.base-path=/api
    - http://localhost:8080/api/expresses/1
    - 通过dao层接口映射到api接口，类似于ws，可以查看所有的接口列表及类型
- @RestController+@RequestMapping("/expresses")
    - http://localhost:8080/expresses/1
    - 常规rest，推荐
- 也可以使用hateoas来实现超媒体的rest效果
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-hateoas</artifactId>
</dependency>
```

##j360-deliver##
- RestTemplate作为restAPI调用工具，需要定义@Bean
- ExpressApiService使用netflix.hystrix确保分布式系统的持久性

###测试##
- 单元测试
   - ExperssServiceTest
      - 测试api接口调用实现
      - 测试Hystrix在远程调用中的封装


- MockMvc测试
    - 测试用户界面调用接口
    
    
    
##j360-profile##
- hateoas演示案例