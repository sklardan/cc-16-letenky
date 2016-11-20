package cz.codecamp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.codecamp.Classes.Flight;
import cz.codecamp.Database.FlightRepository;
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


@SpringBootApplication
public class LetenkyApplication {

	private static final Logger log = LoggerFactory.getLogger(LetenkyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LetenkyApplication.class);
	}

	@Bean
	public CommandLineRunner demo(FlightRepository repository) {
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
				log.info(flight.getFlyDurationMin().toString());
				repository.save(flight);

				// fetch all customers
				log.info("Flights found with findAll():");
				log.info("-------------------------------");
				for (Flight flight1 : repository.findAll()) {
					log.info(flight1.toString());
				}
				log.info("");

				// fetch an individual customer by ID
				Flight flight2 = repository.findOne(1L);
				log.info("Flight found with findOne(1L):");
				log.info("--------------------------------");
				log.info(flight2.toString());
				log.info("");

				// fetch customers by last name
				log.info("Customer found with findByCityFrom(Brno):");
				log.info("--------------------------------------------");
				for (Flight brno : repository.findByCityFromAndCityTo("Brno","Nis")) {
					log.info(brno.toString());
				}
				log.info("");
			};
		};
	}
}
