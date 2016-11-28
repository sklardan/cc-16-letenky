package cz.codecamp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.codecamp.model.Flight;
import org.springframework.expression.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Created by jt on 1/26/16.
 */
public interface FlightService {

    List<Flight> getFlightsForUserAndParameters(String loginName);

    void saveFlightsFromJson(String loginName) throws JsonProcessingException, IOException;
}
