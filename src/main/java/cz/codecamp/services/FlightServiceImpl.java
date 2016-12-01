package cz.codecamp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.codecamp.model.Flight;
import cz.codecamp.model.Location;
import cz.codecamp.model.User;
import cz.codecamp.repository.FlightRepository;
import cz.codecamp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

/**
 * Created by jbares on 1/26/16.
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(FlightServiceImpl.class);

    private String typeFlight = "return";
    private Integer limit = 100;

    @Override
    public void saveFlightsFromJson (String emailLogin) throws JsonProcessingException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        Query query = new Query();
        User user = userRepository.findByEmailLogin(emailLogin);
        Date dateFrom = user.getDateFrom();
        Date dateTo = user.getDateTo();
        Integer daysInFrom = user.getNightsInDestinationMin();
        Integer daysInTo = user.getNightsInDestinationMax();
        List<Location> citiesTo = user.getCitiesTo();
        String cityFrom = user.getCityFrom().getCode();

        for (Location loc : citiesTo){
            String cityTo = loc.getCode();
            String queryString = query.buildQuery(daysInFrom,daysInTo,cityFrom,cityTo, typeFlight,limit);
            ResponseEntity<String> response = restTemplate.getForEntity(queryString, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode data = root.get("data");
            for (JsonNode jflight : data) {

                JsonNode jflyDuration = jflight.get("fly_duration");
                JsonNode jprice = jflight.get("conversion").get("EUR");
                JsonNode jnightsInDest = jflight.get("nightsInDest");
                JsonNode jdTimeStamp = jflight.get("dTimeUTC");

                String flyDuration = mapper.treeToValue(jflyDuration, String.class);
                Integer price = mapper.treeToValue(jprice, Integer.class);
                Integer nightsInDest = mapper.treeToValue(jnightsInDest, Integer.class);
                Integer dTimeStamp = mapper.treeToValue(jdTimeStamp, Integer.class);

                Flight flight = null;
                try {
                    flight = new Flight(cityFrom, cityTo, price, nightsInDest, flyDuration, dTimeStamp);
                    log.info(flight.getFlyDurationMinutes().toString());
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                flightRepository.save(flight);
            }
        }
    }

    @Override
    public List<Flight> getFlightsForUser(String emailLogin) {
        User user = userRepository.findByEmailLogin(emailLogin);
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
