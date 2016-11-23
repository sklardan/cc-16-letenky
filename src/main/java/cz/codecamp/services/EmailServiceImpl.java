package cz.codecamp.services;

import cz.codecamp.classes.Flight;
import cz.codecamp.database.FlightRepository;
import cz.codecamp.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jakubbares on 11/23/16.
 */
public class EmailServiceImpl {

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FlightService flightService;


//    public String sendEmail(){
//        List<Flight> flights = flightService.getFlightsForUserAndParameters(loginName, userRepository, flightRepository);
//
//    }

}
