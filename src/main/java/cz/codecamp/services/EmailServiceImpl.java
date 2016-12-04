package cz.codecamp.services;

import cz.codecamp.model.Flight;
import cz.codecamp.model.User;
import cz.codecamp.repository.FlightRepository;
import cz.codecamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
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

    @Autowired
    private JavaMailSender mailSender;


    @Scheduled(fixedRate = 86400000) //24 hours cycle
    public void sendEmailToUsers() throws MessagingException, IOException {
        Iterable<User> users = userRepository.findAll();
        for (User user : users){
            String emailLogin = user.getEmailLogin();
            flightService.saveFlightsFromJson();
            List<Flight> flights = flightService.getFlightsForUserToday(emailLogin);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            String htmlMsg = "<h3>Newly found flights for you </h3>\n" +
                    "<tr>\n" +
                    "                <th><h6>City From</h6></th>\n" +
                    "                <th><h6>City To</h6></th>\n" +
                    "                <th><h6>Price</h6></th>\n" +
                    "                <th><h6>Fly duration</h6></th>\n" +
                    "                <th><h6>Date</h6></th>\n" +
                    "                <th><h6>Nights in destination</h6></th>\n" +
                    "            </tr>\n" +
                    "            <tr th:each=\"flight : ${flights}\">\n" +
                    "                <td th:text=\"${flight.getCityFrom()}\">CityFrom</td>\n" +
                    "                <td th:text=\"${flight.getCityTo()}\">CityTo</td>\n" +
                    "                <td th:text=\"${flight.getPrice()}\">Price</td>\n" +
                    "                <td th:text=\"${flight.getFlightDurationMinutes()/60}\">FlightDurationMinutes</td>\n" +
                    "                <td th:text=\"${flight.getDepTime()}\">Price</td>\n" +
                    "                <td th:text=\"${flight.getNightsInDest()}\">FlightDurationMinutes</td>\n" +
                    "            </tr>";
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(emailLogin);
            helper.setSubject("Your flights found today");
            helper.setFrom("kubres@gmail.com");
            mailSender.send(mimeMessage);
        }

    }

}
