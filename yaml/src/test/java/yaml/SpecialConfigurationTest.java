package yaml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yaml.configurations.MySpecialConfiguration;

@SpringBootTest
public class SpecialConfigurationTest {

    @Autowired
    MySpecialConfiguration specialConfiguration;

    @Test
    public void testSpecialConfiguration() {
        Assertions.assertEquals(Utils.readFile("specialConfiguration.txt"), specialConfiguration.toString());
    }
}
