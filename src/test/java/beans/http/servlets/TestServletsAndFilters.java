package beans.http.servlets;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.xmlunit.matchers.CompareMatcher;
import template.AbstractTestSpringBootContext;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestServletsAndFilters extends AbstractTestSpringBootContext {

    @LocalServerPort
    private int port;

    private final TestRestTemplate template = new TestRestTemplate();

    @Test
    public void testCustomServlet() {
        ResponseEntity<String> entity = template.getForEntity("http://localhost:" + port + "/customServletURL", String.class);
        assertThat(entity.getBody(), CompareMatcher.isIdenticalTo("<html><body>Hello World! GET with filter attribute value filterAddedValue custom servlet</body></html>")
                .ignoreComments()
                .ignoreWhitespace());
    }

    @Test
    public void testCustomServletGET() {
        ResponseEntity<String> entity = template.getForEntity("http://localhost:" + port + "/customServletURLGET?customParameterName=aaa", String.class);
        assertThat(entity.getBody(), CompareMatcher.isIdenticalTo("<html><body>Hello World! beans.http.servlets.CustomServletGET with parameter 'customParameterName' = aaa custom servlet</body></html>")
                .ignoreComments()
                .ignoreWhitespace());
    }

    @Test
    public void testCustomServletPOST() {
        ResponseEntity<String> entity = template.postForEntity("http://localhost:" + port + "/customServletURLPOST", null, String.class);
        assertThat(entity.getBody(), CompareMatcher.isIdenticalTo("<html><body>Hello World! beans.http.servlets.CustomServletPOST with attribute 'customAttributeName' = gicu custom servlet</body></html>")
                .ignoreComments()
                .ignoreWhitespace());
    }

}
