package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//AOP Bean 등록 OR Component
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("Start : " + joinPoint.toString());
        try
        {
            return joinPoint.proceed();
        }
        finally {
            long end = System.currentTimeMillis();
            long ms = end - start;
            System.out.println("End : " + joinPoint.toString() + " " + ms + "ms");

        }

    }
}
