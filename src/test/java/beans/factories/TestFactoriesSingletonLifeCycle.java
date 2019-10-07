package beans.factories;

import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestFactoriesSingletonLifeCycle extends AbstractTestSpringContext {

    @Test
    public void testLifecycleSingleton() {

        ConfigurationFactoryProductSingleton bean = context.getBean(ConfigurationFactoryProductSingleton.class);
        Assert.assertNotNull(bean);
        Assert.assertTrue(bean.isPostConstructCalled);
        Assert.assertFalse(bean.isPreDestroyCalled);

        // destroy the container
        context.close();

        Assert.assertTrue(bean.isPostConstructCalled);
        Assert.assertTrue(bean.isPreDestroyCalled);

    }
}
