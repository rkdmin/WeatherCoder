package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    // 특정 어노테이션을 대상 지정
    @Pointcut("@annotation(com.example.firstproject.annotation.RunningTime)")
    private void enableRunningTime(){}

    // 기본 패키지의 모든 메소드
    @Pointcut("execution(* com.example.firstproject..*.*(..))")// 하위 메소드 전체지정!
    private void cut(){}

    // 실행 시점 설정 : 두 조건을 모두 만족하는 대상을 전후로 부가 기능을 삽입
    @Around("cut() && enableRunningTime()")// 대상 메소드(하위메소드전체)와 우리가 만든 RunningTime 어노테이션
    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {// Around는 JoinPoint가 아니라 ProceedingJoinPoint
        // 메소드 수행 전, 측정 시작
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 메소드를 수행
        Object returningObj = joinPoint.proceed();
        String methodName = joinPoint.getSignature().getName();
        // 메소드 수행 후, 측정 종료 및 로깅
        stopWatch.stop();
        log.info("{}의 총 수행 시간 => {} sec", methodName, stopWatch.getTotalTimeSeconds());
    }
}
