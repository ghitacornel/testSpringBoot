package beans.properties.environment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestInjection extends AbstractTestSpringContext {

    @Before
    public void setUp() {
        System.setProperty("environmentProperty", "bbb");
        System.setProperty("anotherEnvironmentProperty", "yyy");
    }

    @Test
    public void testInjectionOfEnvironment() {
        BeanWithPropertiesInjectedFromEnvironment bean = context.getBean(BeanWithPropertiesInjectedFromEnvironment.class);
        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.getEnvironment());
        Assert.assertEquals("yyy", bean.getEnvironment().getProperty("anotherEnvironmentProperty"));
        Assert.assertEquals("bbb", bean.getEnvironment().getProperty("environmentProperty"));
    }
}
