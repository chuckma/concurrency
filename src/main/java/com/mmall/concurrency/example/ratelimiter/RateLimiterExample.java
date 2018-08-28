package com.mmall.concurrency.example.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Author: lucasma
 *
 *
 * Guava 限流类  单机限流
 */
@Slf4j
public class RateLimiterExample {

    // 定义每秒可以处理的请求次数为 5 次
    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 100; i++) {
            // 等200 毫秒去获取令牌
            if (rateLimiter.tryAcquire(200,TimeUnit.MILLISECONDS)) {
                test(i);
            }
        }
    }


    private static void test(int i) {
        log.info("{}",i);
    }
}
