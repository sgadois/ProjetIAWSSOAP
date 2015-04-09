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

        // parse le XML de ReleveNotesRequest pour extraire les informations de
        // l'année scolaire, du niveau et du semestre  et creer les objets ad-hoc


        // invoque le service "releveNoteService" pour récupérer les objets recherchés
        //

        // Transforme en élément XML ad-hoc pour le retour
        // Ici, on prend le parti de renvoyer un fichier XML statique. Il faudrait traiter la
        // liste des évaluations avec une API XML pour fournir l'élément réponse de manière
        // dynamique

        Element elt = XmlHelper.getRootElementFromFileInClasspath("src/test/resources/ListeSallesExemples.xml") ;
        return  elt;

    }
}
