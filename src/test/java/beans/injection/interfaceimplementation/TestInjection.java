package beans.injection.interfaceimplementation;

import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestInjection extends AbstractTestSpringContext {

    @Test
    public void testInjection() {
        IITarget target = context.getBean(IITarget.class);

        Assert.assertNotNull(target);
        Assert.assertNotNull(target.directSource);
        Assert.assertNotNull(target.sourceInterface);

        System.out.println(target.directSource.getClass());
        System.out.println(target.sourceInterface.getClass());

        Assert.assertNotEquals(DirectSource.class, target.directSource.getClass());
        Assert.assertTrue(DirectSource.class.isAssignableFrom(target.directSource.getClass()));

        Assert.assertNotEquals(Source.class, target.sourceInterface.getClass());
        Assert.assertNotEquals(SourceInterface.class, target.sourceInterface.getClass());
        Assert.assertTrue(Source.class.isAssignableFrom(target.sourceInterface.getClass()));
        Assert.assertTrue(SourceInterface.class.isAssignableFrom(target.sourceInterface.getClass()));

    }

}
