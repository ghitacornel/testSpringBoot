package beans.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * logs time duration of a marked method execution
 */
@Aspect
@Component
public class LogExecutionTimeAspect {

    @Around("@annotation(beans.aop.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - start;
            System.err.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        }
    }

}
