package me.j360.boot.microservice.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.hystrix.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 22:34
 * 说明：
 */
public class DeliverCommand  extends HystrixCommand {

    public DeliverCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
    }

    @Override
    protected String run() {
        return "Hello World";
    }

    @Override
    protected String getFallback() {
        return "Hello Fallback";
    }
}

