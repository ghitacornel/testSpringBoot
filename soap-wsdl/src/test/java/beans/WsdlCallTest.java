package beans;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class WsdlCallTest {

    @LocalServerPort
    int port;

    @Autowired
    MockMvc mvc;

    @Test
    @Disabled("FUCK SOAP")
    public void testWsdlCall() throws Exception {

        String request = Utils.readFile("WsdlRequest.xml");
        String response = Utils.readFile("WsdlResponse.xml");

        mvc.perform(post("http://localhost:" + port + "/services/Hello/")
                        .contentType(MediaType.APPLICATION_XML)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

    }
}