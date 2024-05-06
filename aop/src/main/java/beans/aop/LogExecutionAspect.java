package beans.aop;

import lombok.Getter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * logs start/end of a marked method execution
 */
@Aspect
@Component
@Getter
public class LogExecutionAspect {

    private final List<String> logs = new ArrayList<>();// for test purpose

    @Around("@annotation(beans.aop.LogExecution)")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String messageStart = joinPoint.getSignature() + " started";
        System.err.println(messageStart);
        logs.add(messageStart);

        Object proceed = joinPoint.proceed();
        String messageEnd = joinPoint.getSignature() + " ended";
        System.err.println(messageEnd);
        logs.add(messageEnd);
        return proceed;
    }

}
