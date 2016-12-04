package cz.codecamp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.codecamp.model.Flight;
import cz.codecamp.model.Location;
import cz.codecamp.model.User;
import cz.codecamp.repository.FlightRepository;
import cz.codecamp.repository.LocationRepository;
import cz.codecamp.repository.UserRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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

    @Autowired
    LocationRepository locationRepository;

    private final Logger log = LoggerFactory.getLogger(FlightServiceImpl.class);

    private String typeFlight = "oneway";
    private Integer limit = 100;

    @Override
    public List<Flight> saveFlightsFromJson () throws IOException {
        List<Flight> flights = new ArrayList<Flight>();
        JSONParser parser = new JSONParser();
        JSONArray data = null;
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(new FileReader("/Users/jakubbares/IdeaProjects/cc-16-letenky3/src/main/resources/data/Flights.json"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        data = (JSONArray) json.get("data");

        for (Object o : data) {
            JSONObject jflight = (JSONObject) o;

            String cityFrom = (String) jflight.get("cityFrom");
            String cityTo = (String) jflight.get("cityTo");
            Long priceLong = (Long) jflight.get("price");
            Integer price = priceLong.intValue();
            Long dTimeUTCLong = (Long) jflight.get("dTimeUTC");
            String flyDurationString = (String) jflight.get("fly_duration");
            Integer flyDuration = Integer.parseInt(flyDurationString);

            Flight flight = new Flight(cityFrom, cityTo, price, flyDuration, dTimeUTCLong);

            flightRepository.save(flight);
            flights.add(flight);
            }
        return flights;

    }

    @Override
    public List<Flight> getFlightsForUser(String emailLogin) {
        User user = userRepository.findByEmailLogin(emailLogin);
        Date dateFrom = user.getDateFrom();
        Iterable<Location> citiesTo = user.getCitiesTo();
        Location cityFrom = user.getCityFrom();
        String cityFromCode = cityFrom.getCode();
        Integer flyDurationMinutesMax = user.getFlyDurationMinutesMax();
        Double threshold = user.getPctAvgPriceMax();

        //Setting average price
        for (Location cityTo2: citiesTo) {
            String cityToCode2 = cityTo2.getCode();
            List<Flight> flightsTemp3 = flightRepository.findByCityFromAndCityTo(cityFromCode, cityToCode2);
            Integer avgPrice;
            Integer sum = 0;
            for (Flight flight : flightsTemp3){
                sum += flight.getPrice();
            }
            avgPrice = sum / flightsTemp3.size();
            System.out.println("SUM: " + sum);
            System.out.println("size: " + flightsTemp3.size());
            for (Flight flight : flightsTemp3){
                flight.setAvgPrice(avgPrice);
                flightRepository.save(flight);
            }
        }


        List<Flight> flights = new ArrayList<Flight>();
        List<Flight> flightsTemp2 = new ArrayList<Flight>();
        for (Location cityTo: citiesTo) {
            String cityToCode = cityTo.getCode();
            List<Flight> flightsTemp = flightRepository.findByCityFromAndCityTo(cityFromCode, cityToCode);
            flightsTemp2.addAll(flightsTemp);

        }
        for (Flight flight: flightsTemp2) {
            if (flight.getDepDate().getTime() >= dateFrom.getTime()){
                if (flight.getFlyDurationMinutes() < flyDurationMinutesMax){

                    if ((flight.getPrice() / flight.getAvgPrice()) <= threshold) {
                        flights.add(flight);
                    }
                }
            }
        }
        return flights;
    }


    @Override
    public List<Flight> getFlightsForUserToday(String emailLogin) {
        Date today = new Date();
        String todayString = new SimpleDateFormat("dd.MM.yyyy").format(today);
        List<Flight> flightsToday = new ArrayList<Flight>();
        List<Flight> flights = getFlightsForUser(emailLogin);
        for (Flight flight : flights){
            String dateAddedString = flight.getDateAdded().toString();
            if (todayString == dateAddedString) {
                flightsToday.add(flight);
            }
        }
        return flightsToday;


    }
//    @Override
//    public List<Flight> saveFlightsFromJson (String emailLogin) throws IOException {
//        RestTemplate restTemplate = new RestTemplate();
//        Query query = new Query();
//        User user = userRepository.findByEmailLogin(emailLogin);
//        Date dateFrom = user.getDateFrom();
//        Date dateTo = user.getDateTo();
//        Integer daysInFrom = user.getNightsInDestinationMin();
//        Integer daysInTo = user.getNightsInDestinationMax();
//        List<Location> citiesTo = user.getCitiesTo();
//        String cityFrom = user.getCityFrom().getCode();
//        List<Flight> flights = new ArrayList<Flight>();
//
//        for (Location loc : citiesTo){
//            String cityTo = loc.getCode();
//            String queryString = query.buildQuery(daysInFrom,daysInTo,cityFrom,cityTo,dateFrom, dateTo, typeFlight,limit);
//            System.out.println(queryString);
//            ResponseEntity<String> response = restTemplate.getForEntity(queryString, String.class);
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(response.getBody());
//            JsonNode data = root.get("data");
//            for (JsonNode jflight : data) {
//
//                JsonNode jflyDuration = jflight.get("fly_duration");
//                JsonNode jprice = jflight.get("conversion").get("EUR");
//                JsonNode jnightsInDest = jflight.get("nightsInDest");
//                JsonNode jdTimeStamp = jflight.get("dTimeUTC");
//
//                String flyDuration = mapper.treeToValue(jflyDuration, String.class);
//                Integer price = mapper.treeToValue(jprice, Integer.class);
//                Integer nightsInDest = mapper.treeToValue(jnightsInDest, Integer.class);
//                Integer dTimeStamp = mapper.treeToValue(jdTimeStamp, Integer.class);
//
//                Flight flight = null;
//                try {
//                    flight = new Flight(cityFrom, cityTo, price, nightsInDest, flyDuration, dTimeStamp);
//                } catch (java.text.ParseException e) {
//                    e.printStackTrace();
//                }
//                flightRepository.save(flight);
//                flights.add(flight);
//
//            }
//        }
//        return flights;
//    }
}
