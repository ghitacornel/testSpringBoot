package http;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestIntercepted {

    @LocalServerPort
    private int port;

    private final TestRestTemplate template = new TestRestTemplate();

    @Test
    public void testIntercepted() {
        ResponseEntity<String> entity = template.getForEntity("http://localhost:" + port + "/intercepted", String.class);
        assertThat(entity.getBody()).isEqualTo("doSomething");
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
