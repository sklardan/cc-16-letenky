package cz.codecamp.services;

import cz.codecamp.model.Flight;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.Transport;
import java.util.List;

/**
 * Created by jakubbares on 11/23/16.
 */
public interface EmailService {

    @Scheduled(fixedRate = 86400000) //24 hours cycle
    public void sendEmailToUsers();

}
