package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// Aspect 어노테이션을 사용해줘야 AOP임을 명시할 수 있다.
@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 원하는 적용 대상을 지정한다(hellospring 패키지 전체)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis(); // 시작 시간
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis(); // 종료 시간
            long timeMs = finish - start; // 총 시간
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
