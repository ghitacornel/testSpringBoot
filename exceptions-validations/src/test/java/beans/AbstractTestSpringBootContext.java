package beans;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)// NO MOCKS
@AutoConfigureMockMvc
public abstract class AbstractTestSpringBootContext {
}
