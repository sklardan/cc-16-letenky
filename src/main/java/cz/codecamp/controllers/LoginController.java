package cz.codecamp.controllers;

import cz.codecamp.commands.LoginCommand;
import cz.codecamp.model.Flight;
import cz.codecamp.model.Location;
import cz.codecamp.model.User;
import cz.codecamp.repository.FlightRepository;
import cz.codecamp.repository.LocationRepository;
import cz.codecamp.repository.UserRepository;
import cz.codecamp.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by jt on 2/2/16.
 */
@Controller
public class LoginController {
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    FlightService flightService;

    @RequestMapping({"/", "signin"})
    public String showLoginForm(Model model){
        model.addAttribute("loginCommand", new LoginCommand());
        return "signin";
    }

    @RequestMapping("/logout")
    public String yourLoggedOut(){
        return "logout";
    }

    @RequestMapping("registration")
    public String register(){
        return "registration";
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ModelAndView testFlights(){
        ModelAndView modelAndView = new ModelAndView("flights");
        User user = userRepository.findByUserName("kubres");
        String emailLogin = user.getEmailLogin();
        modelAndView.addObject("user", user);
        List<Flight> flights = flightService.getFlightsForUser(emailLogin);
        modelAndView.addObject("flights",flights );
        return modelAndView;
    }

    @GetMapping("testset")
    public ModelAndView getSettings(){
        ModelAndView modelAndView = new ModelAndView("settings");
        User user = userRepository.findByUserName("kubres");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @PostMapping("testset")
    public String postSettings(@ModelAttribute User user){
        userRepository.save(user);
        return "result2";
    }

    @GetMapping("/addcity")
    public String addCity(Model model){
        model.addAttribute("location", new Location());
        return "addcity";
    }

    @PostMapping("/addcity")
    public String citySubmit(@ModelAttribute Location location) {
        locationRepository.save(location);
        User user = userRepository.findByUserName("kubres");
        user.setNightsInDestinationMin(10);
        user.setNightsInDestinationMax(12);
        List<Location> citiesTo = user.getCitiesTo();
        citiesTo.add(location);
        user.setCitiesTo(citiesTo);
        System.out.println(citiesTo);
//        userRepository.save(user);
        return "result3";
    }


}
