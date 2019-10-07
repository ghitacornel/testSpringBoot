package beans.lifecycle.singleton;

import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestSingleton extends AbstractTestSpringContext {

    @Test
    public void testLifecycle() {

        SingletonWithLifeCycleBean bean = context.getBean(SingletonWithLifeCycleBean.class);
        Assert.assertNotNull(bean);
        Assert.assertTrue(bean.isPostConstructCalled);
        Assert.assertFalse(bean.isPreDestroyCalled);

        // destroy the container
        context.close();

        Assert.assertTrue(bean.isPostConstructCalled);
        Assert.assertTrue(bean.isPreDestroyCalled);

    }

}
