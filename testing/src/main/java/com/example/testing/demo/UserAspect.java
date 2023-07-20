package com.example.testing.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class UserAspect {
    @Before(value = "execution(* com.example.testing.controller.SampleController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
       log.info("Request to"+joinPoint.getSignature()+"Started at"+new Date());
    }
    @After(value = "execution(* com.example.testing.controller.SampleController.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        log.info("Request to"+joinPoint.getSignature()+"Ended at"+new Date());
    }
    @Before(value = "execution(* com.example.testing.demo.UserService.*(..))")
    public void beforeAdviceService(JoinPoint joinPoint){
        log.info("Request to"+joinPoint.getSignature()+"Started at"+new Date());
    }
    @After(value = "execution(* com.example.testing.demo.UserService.*(..))")
    public void afterAdviceService(JoinPoint joinPoint){
        log.info("Request to"+joinPoint.getSignature()+"Ended at"+new Date());
    }
}
