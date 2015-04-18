package ugmontSOAP.ws;

import dao.ITheaterDao;
import domain.Theater;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.List;

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
    @Namespace(prefix = "rn", uri = NAMESPACE_URI)
    @ResponsePayload
    public Element handleListeSallesRequest(@XPathParam("/rn:theatersRequest/rn:filmID/text()") String filmID)
            throws Exception {


        //Récupère la liste des salles associées au filmID
        List<Theater> t = dao.findByFilmId("tt0138902");

        //On crée la racine XML;
        org.jdom2.Namespace namespace = org.jdom2.Namespace.getNamespace("http://ugmontSOAP/theaters/schemas");
        Element racine = new Element("theaters", namespace);
        org.jdom2.Namespace XSI = org.jdom2.Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        racine.addNamespaceDeclaration(XSI);
        racine.setAttribute("schemaLocation", "http://ugmontSOAP/theaters/schemas ListeSalles.xsd", XSI);
        racine.setAttribute("filmID", filmID);

        for (Theater theater : t) {
            Element e = new Element("theater", namespace);
            e.setAttribute(new Attribute("id", theater.getId()));
            racine.addContent(e);

            Element city = new Element("city", namespace);
            city.setText(theater.getCity());
            e.addContent(city);

            Element name = new Element("name", namespace);
            name.setText(theater.getName());
            e.addContent(name);

            Element region = new Element("region", namespace);
            region.setText(theater.getRegion());
            e.addContent(region);

            Element zipcode = new Element("zipcode", namespace);
            zipcode.setText(theater.getZipcode());
            e.addContent(zipcode);

        }
        return racine;
    }
}
