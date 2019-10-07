package beans.aop.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanAffectedByAspect {

    public String doIt() {
        return this + " executed";
    }

    public String fail() {
        throw new RuntimeException("fail");
    }
}
