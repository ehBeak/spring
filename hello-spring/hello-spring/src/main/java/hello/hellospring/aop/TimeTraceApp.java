package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect // AOP는 aspect라고 어노테이션 설정
@Component// -> bean등록
public class TimeTraceApp {

    @Around("execution(* hello.hellospring..*(..))") // 공통 관심 사항을 어디에 적용할 지
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); // 어떤 메소드 호출하는지

        try {
            Object result = joinPoint.proceed(); // 다음 메서드로 진행
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }


    }
}
