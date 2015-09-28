package me.j360.boot.microservice.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.cache.annotation.Cacheable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.service.
 * User: min_xu
 * Date: 2015/9/27
 * Time: 21:32
 * ËµÃ÷£º
 */
public class ExpressService {
    private static final String GROUP = "express";
    private static final int TIMEOUT = 60000;

    private static Map<String, Map<String, Object>> doBackendAsyncServiceCall(List<Callable<AsyncResponse>> callables) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        try {
            List<Future<AsyncResponse>> futures = executorService.invokeAll(callables);
            executorService.shutdown();
            executorService.awaitTermination(TIMEOUT, TimeUnit.MILLISECONDS);
            Map<String, Map<String, Object>> result = new HashMap<>();
            for (Future<AsyncResponse> future : futures) {
                AsyncResponse response = future.get();
                result.put(response.serviceKey, response.response);
            }
            return result;
        } catch (InterruptedException|ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @Cacheable
    private HystrixCommand<Map<String, Object>> getProductDetails(String productId) {
        return new HystrixCommand<Map<String, Object>>(
                HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(GROUP))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("getProductDetails"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationThreadTimeoutInMilliseconds(TIMEOUT)
                        )
        ) {
            @Override
            protected Map<String, Object> run() throws Exception {
                //return productDetailService.getDetails(productId);
                return null;
            }
            @Override
            protected Map getFallback() {
                return new HashMap<>();
            }
        };
    }
    /*private HystrixCommand<Map<String, Object>> getProductPricing(String productId) {
        // ... snip, see getProductDetails() ...
    }
    private HystrixCommand<Map<String, Object>> getProductRatings(String productId) {
        // ... snip, see getProductDetails() ...
    }
    private HystrixCommand<Map<String, Object>> getProductReviews(String productId) {
        // ... snip, see getProductDetails() ...
    }*/
    private static class AsyncResponse {
        private final String serviceKey;
        private final Map<String, Object> response;
        AsyncResponse(String serviceKey, Map<String, Object> response) {
            this.serviceKey = serviceKey;
            this.response = response;
        }
    }
    private static class BackendServiceCallable implements Callable<AsyncResponse> {
        private final String serviceKey;
        private final HystrixCommand<Map<String, Object>> hystrixCommand;
        public BackendServiceCallable(String serviceKey, HystrixCommand<Map<String, Object>> hystrixCommand) {
            this.serviceKey = serviceKey;
            this.hystrixCommand = hystrixCommand;
        }
        @Override
        public AsyncResponse call() throws Exception {
            return new AsyncResponse(serviceKey, hystrixCommand.execute());
        }
    }
}
