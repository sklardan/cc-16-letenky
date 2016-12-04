package cz.codecamp.controllers;

import cz.codecamp.model.Location;
import cz.codecamp.model.User;
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
 * Created by jakubbares on 1/20/16.
 */
@Controller
public class FlightsShowController {

    @Autowired
    FlightService flightService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationRepository locationRepository;

    @RequestMapping(value = "flights", method = RequestMethod.GET)
    public ModelAndView getFlights(@PathVariable String userName){
        ModelAndView modelAndView = new ModelAndView("flights");
        User user = userRepository.findByUserName(userName);
        String emailLogin = user.getEmailLogin();
        modelAndView.addObject("user", user);
        modelAndView.addObject("flights", flightService.getFlightsForUser(emailLogin));
        return modelAndView;
    }

    @RequestMapping(value = "flights2", method = RequestMethod.GET)
    public ModelAndView logout(){
        ModelAndView modelAndView = new ModelAndView("logout");
        return modelAndView;
    }

    @RequestMapping(value = "settings1", method = RequestMethod.GET)
    public ModelAndView getSettings(@RequestParam("u") String userName){
        ModelAndView modelAndView = new ModelAndView("settings");
        User user = userRepository.findByUserName(userName);
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value = "cities1", method = RequestMethod.GET)
    public ModelAndView getCities(@RequestParam("u") String userName){
        ModelAndView modelAndView = new ModelAndView("cities");
        User user = userRepository.findByUserName(userName);
        modelAndView.addObject("user",user);
        modelAndView.addObject("locations", user.getCitiesTo());
        return modelAndView;
    }



    @GetMapping("/addcity2")
    public String addCity2(Model model){
        model.addAttribute("city", new String());
        return "cities";
    }

    @PostMapping("/addcity2")
    public String citySubmit2(@ModelAttribute String city) {
        Location location = locationRepository.findByCity(city);
        User user = userRepository.findByUserName("kubres");
        List<Location> citiesTo = user.getCitiesTo();
        citiesTo.add(location);
        user.setCitiesTo(citiesTo);
        userRepository.save(user);
        return "result2";
    }



}







