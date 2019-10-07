package beans.properties.config;

import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestInjection extends AbstractTestSpringContext {

    @Test
    public void testInjectionOfPropertiesFromPropertyFiles() {
        BeanWithPropertiesInjectedFromPropertyFiles bean = context.getBean(BeanWithPropertiesInjectedFromPropertyFiles.class);
        Assert.assertNotNull(bean);
        Assert.assertEquals("bla bla bla", bean.getCustomValue());
        Assert.assertEquals("tra la la", bean.getCustomSecondValue());
    }

}
