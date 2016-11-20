package cz.codecamp.services;

import cz.codecamp.Classes.Flight;
import cz.codecamp.Classes.User;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jt on 1/26/16.
 */
@Service
public class FlightServiceImpl implements FlightService {

    private Map<Integer, Flight> flightMap;

    public FlightServiceImpl() {
        loadFlights();
    }

    @Override
    public Flight getFlight(Integer id) {
        return flightMap.get(id);
    }

    @Override
    public List<Flight> listFlights() {
        return new ArrayList<>(flightMap.values());
    }

    private void loadFlights() {

        User jt = new User();
        jt.setLoginName("user");
        jt.setPassword("pass");

        flightMap = new HashMap<>();

        Flight springIntro = new Flight();
        springIntro.setId(1);
        springIntro.setCityFrom("milan_it");
        springIntro.setCityTo("prague_cz");
        springIntro.setNightsInDest(7);
        springIntro.setPrice(78);
//        springIntro.setFlyDuration("20 hours");
        springIntro.setDepTime(new Date());

        flightMap.put(1, springIntro);


    }
}
