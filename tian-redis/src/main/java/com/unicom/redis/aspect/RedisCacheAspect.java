package com.unicom.redis.aspect;

import com.unicom.redis.annotation.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author by ctf
 * @Classsname RedisCacheAspect
 * @Description TODO
 * @Date 2020/5/29 0:16
 **/
@Aspect
@Component
@Slf4j
public class RedisCacheAspect {
    //@Pointcut("@annotation(com.unicom.redis.annotation.CacheException)")
    @Pointcut("execution(public  * com.unicom.redis.config.*Service.*(..))")
    public void redisCache(){}
    @Around("redisCache()")
    public Object around(ProceedingJoinPoint joinPoint){
        Signature signature=joinPoint.getSignature();
        MethodSignature methodSignature= (MethodSignature) signature;
        Method method=methodSignature.getMethod();
        Object result=null;
     //   CacheException cacheException=method.getAnnotation(CacheException.class);
            try {
               result=joinPoint.proceed();
            } catch (Throwable throwable) {
                log.error("redis缓存 出错 {},  描述  {}", throwable.getMessage(),"???");
                Class<?> clazz=method.getReturnType();
                if (clazz==boolean.class) {
                    return false;
                }
                //throwable.printStackTrace();

        }

        return result;
    }
}
