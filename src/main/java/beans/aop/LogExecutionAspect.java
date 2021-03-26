package beans.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionAspect {

    @Around("@within(beans.aop.LogExecution)")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        System.err.println(joinPoint.getSignature() + " started");
        Object proceed = joinPoint.proceed();
        System.err.println(joinPoint.getSignature() + " ended");
        return proceed;
    }

}
