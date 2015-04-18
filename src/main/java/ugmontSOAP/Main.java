package ugmontSOAP;

import dao.ITheaterDao;
import dao.TheaterDaoH2Db;
import database.Database;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Created by seb on 18/04/15.
 */
public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in ugmontSOAP package
        final ResourceConfig rc = new ResourceConfig()
                .packages("ugmontSOAP")
                .register(DeclarativeLinkingFeature.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind((Database) Database.getInstance()).to(Database.class);
                        bind(TheaterDaoH2Db.class).to(ITheaterDao.class);
                    }
                });

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * ugmontSOAP.Main method.
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }
}
