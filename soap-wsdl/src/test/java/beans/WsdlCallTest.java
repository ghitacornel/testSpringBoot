package beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@WebServiceServerTest
public class WsdlCallTest {

    @Autowired
    MockWebServiceClient client;

    @Test
    public void testWsdlCall() {

        String request = Utils.readFile("WsdlRequest.xml");
        String response = Utils.readFile("WsdlResponse.xml");

        client.sendRequest(withPayload(new StringSource(request)))
                .andExpect(noFault())
                .andExpect(payload(new StringSource(response)));

    }
}

