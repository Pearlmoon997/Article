package com.example.Article.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //AOP 클래스 선언
@Component //IoC 컨테이너가 해당 객체를 생성 및 관리
@Slf4j
public class DebuggingAspect {

    //어느 지점(메소드)에 부가기능을 주입할지 정함: CommentService#create()
    //@Pointcut("execution(* com.example.Article.Service.CommentService.create(..))") => create 메소드에 적용
    //@Pointcut("execution(* com.example.Article.Service.CommentService.*(..))") //모든 메소드에 적용
    @Pointcut("execution(* com.example.Article.API.*.*(..))") //API 패키지의 모든 메소드
    private void cut() {}

    //실행시점 설정: cut 대상이 수행되기 이전에 수행
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { //cut()의 대상 메소드
        //입력값 가져옴
        Object[] args = joinPoint.getArgs();
        //클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();
        //로깅
        for (Object obj : args) { //foreach 문
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }
    }

    //cut 의 지정된 대상 호출 성공 후 실행
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, //cut()의 대상 메소드
                                   Object returnObj){ //리턴값
        //클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();
        //반환값 로깅
        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);
    }
}
