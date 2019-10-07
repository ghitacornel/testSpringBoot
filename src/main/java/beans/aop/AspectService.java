package beans.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component// an aspect is a container managed bean
@Aspect// an aspect can be a simple class with a special marker
public class AspectService {

    // PLAY WITH DIFFERENT FLAVORS OF AOP OFFERED BY SPRING

    @Before(value = "execution(* beans.aop.beans.*.*(..))")
    void aspectExecutedBeforeExecutingTargetedMethods() {
        System.out.println("before executing method call BEFORE");
    }

    @After(value = "execution(* beans.aop.beans.*.*(..))")
    void aspectExecutedAfterExecutingTargetedMethods() {
        System.out.println("after executing method call AFTER");
    }

    @AfterReturning(pointcut = "execution(* beans.aop.beans.*.*(..))", returning = "valueReturnedByTargetedMethod")
    public void aspectExecutedAfterExecutingTargetedMethodsReturnAValues(Object valueReturnedByTargetedMethod) {
        System.out.println("returned object is " + valueReturnedByTargetedMethod);
    }

    @AfterThrowing(pointcut = "execution(* beans.aop.beans.*.*(..))", throwing = "exceptionThrownByTargetedMethod")
    public void aspectExecutedWhenTargetedMethodsThrowExceptions(Exception exceptionThrownByTargetedMethod) {
        System.out.println("exception thrown is " + exceptionThrownByTargetedMethod);
    }

}
