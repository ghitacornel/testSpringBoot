package beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HttpCallTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int localPort;

    @Test
    void testSimple() {
        String object = restTemplate.getForObject("http://localhost:" + localPort + "/simple", String.class);
        Assertions.assertEquals("all good no delay for thread http-nio-8080-exec-1", object);
    }

    @Test
    void testSimpleWithDelay() {
        String object = restTemplate.getForObject("http://localhost:" + localPort + "/simple/delay", String.class);
        Assertions.assertEquals("all good with delay for thread http-nio-8080-exec-1", object);
    }

    @Test
    void testClientOk() {
        String object = restTemplate.getForObject("http://localhost:" + localPort + "/client/ok", String.class);
        Assertions.assertEquals("ClientResponseDto(content=ok GET dummy content from 3rd pary clientThread[#1,main,5,main]) processed by app thread http-nio-8080-exec-1", object);
    }

    @Test
    void testClientOkWithDelay() {
        String object = restTemplate.getForObject("http://localhost:" + localPort + "/client/delay", String.class);
        Assertions.assertEquals("ClientResponseDto(content=ok GET dummy content with delay from 3rd pary clientThread[#1,main,5,main]) processed by app thread http-nio-8080-exec-1", object);
    }
}
