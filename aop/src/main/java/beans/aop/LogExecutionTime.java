package beans.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Logs execution time of a method
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {

    /**
     * Log execution time as info by default
     * Log execution time as warn if specified timeout is exceeded
     *
     * @return the timeout warning threshold
     */
    int warnTimeout() default 500;

}
