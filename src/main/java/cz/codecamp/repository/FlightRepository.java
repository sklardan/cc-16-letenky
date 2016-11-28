package cz.codecamp.repository;

import cz.codecamp.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jakubbares on 11/17/16.
 */
@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

    List<Flight> findByCityFrom(String cityFrom);
    List<Flight> findByCityFromAndCityTo(String cityFrom, String cityTo);
    List<Flight> findByParameters(String cityFrom, String cityTo, Integer nightsInDest);
    List<Flight> removeByCityFrom(String cityFrom);

}