package beans.factories;

import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestFactoriesPrototypeLifeCycle extends AbstractTestSpringContext {

    @Test
    public void testLifecyclePrototype() {

        ConfigurationFactoryProductPrototype bean = context.getBean(ConfigurationFactoryProductPrototype.class);
        Assert.assertNotNull(bean);
        Assert.assertTrue(bean.isPostConstructCalled);
        Assert.assertFalse(bean.isPreDestroyCalled);

        // destroy the container
        context.close();

        Assert.assertTrue(bean.isPostConstructCalled);
        /**
         * even after the container is destroyed the pre destroy event is not triggered<br>
         * This behavior occurs because this bean is no longer maintained by the container once produced and delivered
         */
        Assert.assertFalse(bean.isPreDestroyCalled);

    }

}
