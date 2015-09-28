package me.j360.boot.microservice.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created with springbootweb -> me.j360.springboot.jar.
 * User: min_xu
 * Date: 2015/7/28
 * Time: 15:28
 * 说明：
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class J360Configuration{

}