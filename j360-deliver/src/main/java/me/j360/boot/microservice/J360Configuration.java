package me.j360.boot.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created with springbootweb -> me.j360.springboot.jar.
 * User: min_xu
 * Date: 2015/7/28
 * Time: 15:28
 * 说明：
 */

@SpringBootApplication
public class J360Configuration extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory
            .getLogger(J360Configuration.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(J360Configuration.class);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}