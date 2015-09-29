package me.j360.boot.microservice.api.hystrix;

import com.netflix.hystrix.HystrixCommand;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.api.
 * User: min_xu
 * Date: 2015/9/29
 * Time: 11:05
 * 说明：
 */
public class BackendServiceCallable implements Callable<AsyncResponse>{

    public final String serviceKey;
    public final HystrixCommand<Map<String, Object>> hystrixCommand;
    public BackendServiceCallable(String serviceKey, HystrixCommand<Map<String, Object>> hystrixCommand) {
        this.serviceKey = serviceKey;
        this.hystrixCommand = hystrixCommand;
    }

    @Override
    public AsyncResponse call() throws Exception {
        return new AsyncResponse(serviceKey, hystrixCommand.execute());
    }
}
