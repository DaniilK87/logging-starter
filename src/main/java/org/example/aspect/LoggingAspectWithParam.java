package org.example.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LoggingAspectWithParam {
    private final LoggingProperties properties;

    @Pointcut("@annotation(org.example.aspect.LoggingMethodWithParam)") // method
    public void loggingMethodsPointcut() {}

    @Pointcut("@within(org.example.aspect.LoggingMethodWithParam)") // class
    public void loggingTypePointcut() {}

    @Around(value = "loggingMethodsPointcut() || loggingTypePointcut()")
    public Object loggingMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        Object type = pjp.getArgs()[0].getClass();
        String args = Arrays.toString(pjp.getArgs());
        log.atLevel(properties.getLevel()).log("Before -> TimesheetService#{},{},{}", methodName,type,args);
        try {
            return pjp.proceed();
        } finally {
            log.atLevel(properties.getLevel()).log("After -> TimesheetService#{},{},{}", methodName,type,args);
        }
    }

//    @Before(value = "execution(* org.example.spring_framework.homework3.timesheet.TimesheetService.getById(..))")
//    public void beforeTimesheetServiceFindById(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//        Object type = jp.getArgs()[0].getClass();
//        String args = Arrays.toString(jp.getArgs());
//        log.info("Before -> TimesheetService.{},{},{}", methodName,type,args);
//    }

//    @After(value = "execution(* org.example.spring_framework.homework3.timesheet.TimesheetService.delete(..))")
//    public void afterTimesheetServiceDelete(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//        Object type = jp.getArgs()[0].getClass();
//        String args = Arrays.toString(jp.getArgs());
//        log.info("After -> TimesheetService.{},{},{}", methodName,type,args);
//    }
}
