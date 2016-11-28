package cz.codecamp.services;

import cz.codecamp.model.Flight;
import cz.codecamp.repository.FlightRepository;
import cz.codecamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
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

    @Scheduled(fixedRate = 86400000) //24 hours cycle
    public List<Flight> sendEmailToUser(String emailLogin) {
        List<Flight> flights = flightService.getFlightsForUserAndParameters(emailLogin);
        return flights;
    }

}
