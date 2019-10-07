package beans.properties.xml;

import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestInjection extends AbstractTestSpringContext {

    @Test
    public void testInjectionOfPropertiesFromXML() {
        BeanWithPropertiesInjectedFromXML bean = context.getBean(BeanWithPropertiesInjectedFromXML.class);
        Assert.assertNotNull(bean);
        Assert.assertEquals("customConstructorProvidedValue", bean.getConstructorProperty());
        Assert.assertEquals(2, bean.getSimpleProperty());
    }
}
