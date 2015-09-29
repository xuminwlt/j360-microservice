package me.j360.boot.microservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import me.j360.boot.microservice.api.hystrix.AsyncResponse;
import me.j360.boot.microservice.api.hystrix.BackendServiceCallable;
import me.j360.boot.microservice.api.hystrix.HystrixService;
import me.j360.boot.microservice.domain.Express;
import me.j360.boot.microservice.service.ExpressService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.api.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 16:20
 * 说明：
 */

@Aspect
@Service
public class HystrixAdvice {

    private static final String GROUP = "express";
    private static final int TIMEOUT = 60000;
    private static final Logger logger = LoggerFactory
            .getLogger(HystrixAdvice.class);

    /*
    * 定义object需要转化为Object对象，此处省略
    @Around("execution(* me.j360.boot.microservice.service.ExpressService.findOne(..))")
    public Object hystrixCommand(final ProceedingJoinPoint pjp){
        logger.info("log Around method: "
                + pjp.getTarget().getClass().getName() + "."
                + pjp.getSignature().getName());

        List<Callable<AsyncResponse>> callables = new ArrayList<Callable<AsyncResponse>>();
        callables.add(new BackendServiceCallable("express", getExpressMap(pjp)));

        Map<String, Map<String, Object>> map = HystrixService.doBackendAsyncServiceCall(callables);
        try {
            String json = new ObjectMapper().writeValueAsString(map.get("express"));
            return new ObjectMapper().readValue(json,Express.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/


    @Around("execution(* me.j360.boot.microservice.service.ExpressService.getMap(..))")
    public Map<String, Object> hystrixCommandMap(final ProceedingJoinPoint pjp){
        logger.info("log Around method: "
                + pjp.getTarget().getClass().getName() + "."
                + pjp.getSignature().getName());

        List<Callable<AsyncResponse>> callables = new ArrayList<Callable<AsyncResponse>>();
        callables.add(new BackendServiceCallable("express", getExpressMap(pjp)));
        Map<String, Map<String, Object>> map = HystrixService.doBackendAsyncServiceCall(callables);
        return  map.get("express");
    }


    @Cacheable
    private HystrixCommand<Map<String, Object>> getExpressMap(final ProceedingJoinPoint pjp) {
        return new HystrixCommand<Map<String, Object>>(
                HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(GROUP))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("getExpressMap"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationThreadTimeoutInMilliseconds(TIMEOUT)
                        )
        ) {
            @Override
            protected Map<String, Object> run() throws Exception {
                try {
                    return (Map<String, Object>) pjp.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                return new HashMap<String, Object>();
            }
            @Override
            protected Map getFallback() {
                return new HashMap<String, Object>();
            }
        };
    }

}
