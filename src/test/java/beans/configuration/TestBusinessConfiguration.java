package beans.configuration;

import beans.properties.BusinessConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import template.AbstractTestSpringBootContext;

public class TestBusinessConfiguration extends AbstractTestSpringBootContext {

    @Autowired
    BusinessConfiguration businessConfiguration;

    @Test
    public void testBusinessConfiguration() {
        Assert.assertEquals("my custom value", businessConfiguration.getSetting());
    }

}
