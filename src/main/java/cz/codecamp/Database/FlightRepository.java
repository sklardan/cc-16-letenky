package cz.codecamp.Database;

import cz.codecamp.Classes.Flight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jakubbares on 11/17/16.
 */
public interface FlightRepository extends CrudRepository<Flight, Long> {

    List<Flight> findByCityFrom(String cityFrom);
    List<Flight> findByCityFromAndCityTo(String cityFrom, String cityTo);
    List<Flight> findByParameters(String cityFrom, String cityTo, Integer nightsInDest);
    List<Flight> removeByCityFrom(String cityFrom);

}