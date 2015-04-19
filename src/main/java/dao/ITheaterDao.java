package dao;

import domain.Theater;
import org.jvnet.hk2.annotations.Contract;

import java.util.List;

@Contract
public interface ITheaterDao {

    /**
     * Get a theater by it ID.
     *
     * @param id of the theater required
     * @return Theater or null if it does not exist
     */
    Theater find(String id);

    /**
     * Get a list of theater whose reference an imdbID film ID.
     *
     * @param imdbID film ID
     * @return List of Theater or null if no theater reference imbdID given
     */
    List<Theater> findByFilmId(String imdbID);

}
