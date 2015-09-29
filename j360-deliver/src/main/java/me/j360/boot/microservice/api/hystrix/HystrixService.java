package me.j360.boot.microservice.api.hystrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.api.
 * User: min_xu
 * Date: 2015/9/29
 * Time: 11:06
 * 说明：
 */
public class HystrixService {
    private static final int TIMEOUT = 60000;

    public static Map<String, Map<String, Object>> doBackendAsyncServiceCall(List<Callable<AsyncResponse>> callables) {
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
}
