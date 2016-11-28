package cz.codecamp.controllers;

import cz.codecamp.repository.FlightRepository;
import cz.codecamp.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }


    @RequestMapping(value = "letenky/{emailLogin}", method = RequestMethod.GET)
    public ModelAndView getFlights(){
        ModelAndView modelAndView = new ModelAndView("prehled-letenek");
        modelAndView.addObject("serverTime", new Date());
//        modelAndView.addObject("letenky", flightService.getFlightsForUserAndParameters(emailLogin));
        return modelAndView;
    }


    @RequestMapping(value = "message", method = RequestMethod.GET)
    public String messages(Model model) {
        model.addAttribute("messages", flightRepository.findAll());
        return "message/list";
    }

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    public ModelAndView messages() {
        ModelAndView mav = new ModelAndView("message/list");
        mav.addObject("messages", flightRepository.findAll());
        return mav;
    }


    @RequestMapping("/product")
    public String getFlight(){
        return "redirect:/index";
    }


    @ModelAttribute("planets")
    public List<String> populatePlanets() {
        return Arrays.asList(new String[] {
                "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"
        });
    }

}
