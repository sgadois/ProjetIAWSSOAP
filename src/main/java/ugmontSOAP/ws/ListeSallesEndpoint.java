package ugmontSOAP.ws;

import dao.ITheaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;
import org.w3c.dom.Element;

/**
 * Created by seb on 07/04/15.
 */
@Endpoint
public class ListeSallesEndpoint {

    private static final String NAMESPACE_URI = "http://ugmontSOAP/theaters/schemas";

    @Autowired
    private ITheaterDao dao;

    public ListeSallesEndpoint() {
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "theatersRequest")
    @Namespace(prefix = "rn", uri = NAMESPACE_URI) // nom de l'élément racine
    @ResponsePayload
    public Element handleListeSallesRequest(@XPathParam("/rn:theatersRequest/rn:filmID/text()") String filmID)
            throws Exception {

        //A faire : construire la reponse xml en allant chercher les salles dans la base de données
        //List<Theater> t = dao.findByFilmId("tt0138902");
        //System.out.println(t.get(0).getCity());
        System.out.println("filmID : " + filmID);



        //Solution temporaire pour tester le code
        Element elt = XmlHelper.getRootElementFromFileInClasspath("ListeSallesExemple.xml");
        return elt;

    }
}
