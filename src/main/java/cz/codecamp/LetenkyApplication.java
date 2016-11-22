package cz.codecamp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.codecamp.Classes.Flight;
import cz.codecamp.Classes.Location;
import cz.codecamp.Classes.User;
import cz.codecamp.Database.FlightRepository;
import cz.codecamp.Database.UserRepository;
import cz.codecamp.KiwiAPI.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class LetenkyApplication {

	private static final Logger log = LoggerFactory.getLogger(LetenkyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LetenkyApplication.class);
	}

	@Bean
	public CommandLineRunner demo(FlightRepository flightRepository, UserRepository userRepository) {
		return (args) -> {

			RestTemplate restTemplate = new RestTemplate();
			String query = Query.buildQuery();
			ResponseEntity<String> response = restTemplate.getForEntity(query, String.class);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(response.getBody());
			JsonNode data = root.get("data");
			for (JsonNode jflight : data) {

				JsonNode jflyDuration = jflight.get("fly_duration");
				JsonNode jprice = jflight.get("conversion").get("EUR");
				JsonNode jnightsInDest = jflight.get("nightsInDest");
				JsonNode jcityTo = jflight.get("cityTo");
				JsonNode jcityFrom = jflight.get("cityFrom");
				JsonNode jdTimeStamp = jflight.get("dTimeUTC");

				String flyDuration = mapper.treeToValue(jflyDuration, String.class);
				Integer price = mapper.treeToValue(jprice, Integer.class);
				Integer nightsInDest = mapper.treeToValue(jnightsInDest, Integer.class);
				String cityTo = mapper.treeToValue(jcityTo, String.class);
				String cityFrom = mapper.treeToValue(jcityFrom, String.class);
				Integer dTimeStamp = mapper.treeToValue(jdTimeStamp, Integer.class);

				Flight flight = new Flight(cityFrom, cityTo, price, nightsInDest, flyDuration, dTimeStamp);
				log.info(flight.getFlyDurationMinutes().toString());
				flightRepository.save(flight);


				log.info("Flights found with findAll():");
				log.info("-------------------------------");
				for (Flight flight1 : flightRepository.findAll()) {
					log.info(flight1.toString());
				}
				log.info("");


				Flight flight2 = flightRepository.findOne(1L);
				log.info("Flight found with findOne(1L):");
				log.info("--------------------------------");
				log.info(flight2.toString());
				log.info("");


				log.info("Flight found with findByCityFromAndCityTo(Brno):");
				log.info("--------------------------------------------");
				for (Flight brno : flightRepository.findByCityFromAndCityTo("Brno", "Nis")) {
					log.info(brno.toString());
				}
				log.info("");

				log.info("Flights fitting criteria for a user:");
				log.info("--------------------------------------------");
			}
		};
	};

	public List<Flight> getFlightsForUserAndParameters(String loginName, UserRepository userRepository, FlightRepository flightRepository) {
		User tomas = userRepository.findByLoginName(loginName);
		log.info(tomas.toString());
		Date dateFrom = tomas.getDateFrom();
		Integer nightsInDestMin = tomas.getNightsInDestinationMin();
		Integer nightsInDestMax = tomas.getNightsInDestinationMax();
		List<Location> citiesTo = tomas.getCitiesTo();
		Location cityFrom = tomas.getCityFrom();
		String cityFromCode = cityFrom.getCode();
		Long flyDurationMinutesMax = tomas.getFlyDurationMinutesMax();

		List<Flight> flights = new ArrayList();
		List<Flight> flightsTemp2 = new ArrayList();
		for (Location cityTo: citiesTo) {
			String cityToCode = cityTo.getCode();
			for (Integer nights = nightsInDestMin; nights <= nightsInDestMax; nights++ ) {
				List<Flight> flightsTemp = flightRepository.findByParameters(cityFromCode, cityToCode, nights);
				flightsTemp2.addAll(flightsTemp);
			}
		}
		for (Flight flight: flightsTemp2) {
			if (flight.getDepTime() >= dateFrom){
				if (flight.getFlyDurationMinutes() < flyDurationMinutesMax){
					flights.add(flight);
				}
			}
		}


		return flights;
	}
}
