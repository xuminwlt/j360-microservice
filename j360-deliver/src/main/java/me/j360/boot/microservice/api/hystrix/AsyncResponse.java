package me.j360.boot.microservice.api.hystrix;

import java.util.Map;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.
 * User: min_xu
 * Date: 2015/9/29
 * Time: 11:04
 * 说明：
 */
public class AsyncResponse {

    public final String serviceKey;
    public final Map<String, Object> response;

    AsyncResponse(String serviceKey, Map<String, Object> response) {
        this.serviceKey = serviceKey;
        this.response = response;
    }
}