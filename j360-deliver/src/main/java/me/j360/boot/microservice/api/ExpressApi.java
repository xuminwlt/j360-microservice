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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.api.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 16:20
 * 说明：
 */

@Service
public class ExpressApi {

    private static final String GROUP = "express";
    private static final int TIMEOUT = 60000;

    private final ExpressService expressService;

    @Autowired
    public ExpressApi(ExpressService expressService){
        this.expressService = expressService;
    }

    public Express getExperss(Long expressId){
        try {
            String json = new ObjectMapper().writeValueAsString(getExpress(expressId).get("express"));
            return new ObjectMapper().readValue(json,Express.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Map<String, Object>> getExpress(Long expressId) {
        List<Callable<AsyncResponse>> callables = new ArrayList<Callable<AsyncResponse>>();
        callables.add(new BackendServiceCallable("express", getExpressMap(expressId)));
        return HystrixService.doBackendAsyncServiceCall(callables);
    }

    @Cacheable
    private HystrixCommand<Map<String, Object>> getExpressMap(final Long expressId) {
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
                return expressService.getMap(expressId);
            }
            @Override
            protected Map getFallback() {
                return new HashMap<String, Object>();
            }
        };
    }

}
