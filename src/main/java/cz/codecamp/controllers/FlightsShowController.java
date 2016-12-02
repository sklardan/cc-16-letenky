package cz.codecamp.controllers;

import cz.codecamp.model.User;
import cz.codecamp.repository.FlightRepository;
import cz.codecamp.repository.UserRepository;
import cz.codecamp.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by jakubbares on 1/20/16.
 */
@Controller
public class FlightsShowController {

    private FlightService flightService;
    private UserRepository userRepository;

    @RequestMapping(value = "flights/{userName}", method = RequestMethod.GET)
    public ModelAndView getFlights(@PathVariable String userName){
        ModelAndView modelAndView = new ModelAndView("flights");
        User user = userRepository.findByUserName(userName);
        String emailLogin = user.getEmailLogin();
        modelAndView.addObject("user", user);
        modelAndView.addObject("flights", flightService.getFlightsForUser(emailLogin));
        return modelAndView;
    }

}
