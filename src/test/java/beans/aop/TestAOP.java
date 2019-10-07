package beans.aop;

import beans.aop.beans.BeanAffectedByAspect;
import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestAOP extends AbstractTestSpringContext {

    @Test
    public void test_AOP_OK() {
        BeanAffectedByAspect bean = context.getBean(BeanAffectedByAspect.class);
        Assert.assertNotNull(bean);
        System.out.println(bean.doIt());

        // CHECK THE CONSOLE

    }

    @Test(expected = RuntimeException.class)
    public void test_AOP_FAIL() {
        BeanAffectedByAspect bean = context.getBean(BeanAffectedByAspect.class);
        Assert.assertNotNull(bean);
        System.out.println(bean.fail());

        // CHECK THE CONSOLE

    }
}
