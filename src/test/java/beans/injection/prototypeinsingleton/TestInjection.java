package beans.injection.prototypeinsingleton;

import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestInjection extends AbstractTestSpringContext {

    @Test
    public void testInjectionOfPrototypeInSingleton() {

        SpecialCaseSingleton bean1 = context.getBean(SpecialCaseSingleton.class);
        Assert.assertNotNull(bean1);
        Assert.assertNotNull(bean1.getPrototype());

        SpecialCaseSingleton bean2 = context.getBean(SpecialCaseSingleton.class);
        Assert.assertNotNull(bean2);
        Assert.assertNotNull(bean2.getPrototype());

        Assert.assertSame(bean1, bean2);

        // observe only 1 prototype is injected and only once in this singleton
        Assert.assertSame(bean1.getPrototype(), bean2.getPrototype());

    }

}
