package beans.configurations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import template.AbstractTestSpringBootContext;
import template.Utils;

public class SpecialConfigurationTest extends AbstractTestSpringBootContext {

    @Autowired
    MySpecialConfiguration specialConfiguration;

    @Test
    public void testSpecialConfiguration() {
        Assertions.assertEquals(Utils.readFile(SpecialConfigurationTest.class, "specialConfiguration.txt"), specialConfiguration.toString());
    }
}
