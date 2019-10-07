package beans.lifecycle.prototype;

import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestPrototype extends AbstractTestSpringContext {
    
    @Test
    public void testLifecycle() {

        PrototypeWithLifeCycleBean bean = context.getBean(PrototypeWithLifeCycleBean.class);
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
