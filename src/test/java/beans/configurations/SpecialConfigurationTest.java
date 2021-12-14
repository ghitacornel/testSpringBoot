package beans.configurations;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import template.AbstractTestSpringBootContext;

public class SpecialConfigurationTest extends AbstractTestSpringBootContext {

    @Autowired
    MySpecialConfiguration specialConfiguration;

    @Test
    public void testSpecialConfiguration() {
        Assert.assertEquals("MySpecialConfiguration{name='test-YAML', environment='testing', enabled=false, servers=[www.abc.test.com, www.xyz.test.com], props={a=1, b=2, c=3}}", specialConfiguration.toString());
    }
}
