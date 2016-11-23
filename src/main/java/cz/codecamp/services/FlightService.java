package cz.codecamp.services;

import cz.codecamp.classes.Flight;

import java.util.List;

/**
 * Created by jt on 1/26/16.
 */
public interface FlightService {

    Flight getFlight(Integer id);

    List<Flight> listFlights();

    List<Flight> getFlightsForUserAndParameters(String loginName);
}
