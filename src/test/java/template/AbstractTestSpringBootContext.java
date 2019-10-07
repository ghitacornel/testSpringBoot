package template;

import beans.SpringBootMain;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringBootMain.class})
public abstract class AbstractTestSpringBootContext {

    @Autowired
    protected ConfigurableApplicationContext context;

}
