package beans.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * logs time duration of a marked method execution
 */
@Aspect
@Component
@Slf4j
public class LogExecutionTimeAspect {

    @Around("@annotation(logExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, beans.aop.LogExecutionTime logExecutionTime) throws Throwable {

        int warnTimeout = logExecutionTime.warnTimeout();

        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - start;

            // various customizations can go here
            if (executionTime >= warnTimeout) {
                log.warn(joinPoint.getSignature() + " executed in " + executionTime + "ms");
            } else {
                log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
            }

        }
    }

}
