package cz.codecamp.services;

import cz.codecamp.classes.Flight;
import cz.codecamp.classes.Location;
import cz.codecamp.classes.User;
import cz.codecamp.database.FlightRepository;
import cz.codecamp.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jt on 1/26/16.
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    UserRepository userRepository;

    private Map<Integer, Flight> flightMap;

//    public FlightServiceImpl() {
//        getFlightsForUserAndParameters(loginName);
//    }

    @Override
    public Flight getFlight(Integer id) {
        return flightMap.get(id);
    }

    @Override
    public List<Flight> listFlights() {
        return new ArrayList<Flight>(flightMap.values());
    }

    @Override
    public List<Flight> getFlightsForUserAndParameters(String loginName) {
        User user = userRepository.findByLoginName(loginName);
        Date dateFrom = user.getDateFrom();
        Integer nightsInDestMin = user.getNightsInDestinationMin();
        Integer nightsInDestMax = user.getNightsInDestinationMax();
        List<Location> citiesTo = user.getCitiesTo();
        Location cityFrom = user.getCityFrom();
        String cityFromCode = cityFrom.getCode();
        Long flyDurationMinutesMax = user.getFlyDurationMinutesMax();
        Float threshold = user.getPctAvgPriceMax();

        List<Flight> flights = new ArrayList<Flight>();
        List<Flight> flightsTemp2 = new ArrayList<Flight>();
        for (Location cityTo: citiesTo) {
            String cityToCode = cityTo.getCode();
            for (Integer nights = nightsInDestMin; nights <= nightsInDestMax; nights++ ) {
                List<Flight> flightsTemp = flightRepository.findByParameters(cityFromCode, cityToCode, nights);
                flightsTemp2.addAll(flightsTemp);
            }
        }
        for (Flight flight: flightsTemp2) {
            if (flight.getDepTime().getTime() >= dateFrom.getTime()){
                if (flight.getFlyDurationMinutes() < flyDurationMinutesMax){
//                    if ((flight.getPrice() / flight.getAvgPrice()) <= threshold) {
                        flights.add(flight);
//                    }
                }
            }
        }
        return flights;
    }
}
