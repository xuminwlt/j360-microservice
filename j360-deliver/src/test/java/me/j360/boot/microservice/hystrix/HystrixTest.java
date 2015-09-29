package me.j360.boot.microservice.hystrix;

import java.util.concurrent.ExecutionException;

/**
 * Created with j360-microservice -> me.j360.boot.microservice.hystrix.
 * User: min_xu
 * Date: 2015/9/28
 * Time: 22:35
 * 说明：
 */
public class HystrixTest {

    public static void main(String[] args){
        //String s = (String) new DeliverCommand().execute();

        java.util.concurrent.Future future = new DeliverCommand().queue();
        try {
            String s = (String) future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
