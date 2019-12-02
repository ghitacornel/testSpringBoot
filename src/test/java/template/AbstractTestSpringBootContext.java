package template;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
public abstract class AbstractTestSpringBootContext {

    @Autowired
    protected ConfigurableApplicationContext context;

    @Autowired
    private Flyway flyway;

    @Before
    public void before() {
        flyway.migrate();
    }

}
