package beans.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * logs time duration of a marked method execution
 */
@Aspect
@Component
public class LogExecutionTimeAspect {

    @Around("@annotation(logExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, beans.aop.LogExecutionTime logExecutionTime) throws Throwable {

        int warnTimeout = logExecutionTime.warnTimeout();

        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - start;

            if (executionTime >= warnTimeout) {
                System.err.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
            } else {
                System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
            }

        }
    }

}
