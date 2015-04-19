package ugmontSOAP.ws;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import ugmontSOAP.Main;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

/**
 * Created by seb on 09/04/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("application-context.xml")
public class ListeSallesEndpointTestIntegration {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;
    private HttpServer server;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
        server = Main.startServer();
    }

    @Test
    public void listeSallesEndpoint() throws Exception {
        Source requestPayload = new StreamSource(new ClassPathResource("ListeSallesRequest.xml").getInputStream() );
        Source responsePayload = new StreamSource(new ClassPathResource("ListeSallesResponseReference.xml").getInputStream());

        mockClient.sendRequest(withPayload(requestPayload)).
                andExpect(payload(responsePayload));
    }
}
