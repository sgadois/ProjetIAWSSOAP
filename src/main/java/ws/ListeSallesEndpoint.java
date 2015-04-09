package ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
import org.w3c.dom.Element;

/**
 * Created by seb on 07/04/15.
 */
@Endpoint
public class ListeSallesEndpoint {

    private static final String NAMESPACE_URI = "http://ws/theaters/schemas";

    public ListeSallesEndpoint() {
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "theatersRequest")
    @ResponsePayload
    public Element handleListeSallesRequest(@XPathParam("/theatersRequest/filmID/text()") String filmId)
            throws Exception {

        //A faire : construire la reponse xml en allant chercher les salles dans la base de données

        //Solution temporaire pour tester le code
        Element elt = XmlHelper.getRootElementFromFileInClasspath("src/test/resources/ListeSallesExemples.xml") ;
        return  elt;

    }
}
