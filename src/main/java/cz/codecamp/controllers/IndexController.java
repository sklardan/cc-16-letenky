package cz.codecamp.controllers;

import cz.codecamp.Classes.Flight;
import cz.codecamp.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jt on 1/20/16.
 */
@Controller
public class IndexController {

    private FlightService flightService;

    @Autowired
    public void setFlightService(FlightService productService) {
        this.flightService = flightService;
    }

    @RequestMapping({"/", "index"})
    public String getIndex(Model model){

        model.addAttribute("flights", flightService.listFlights());

        return "index";
    }

    @RequestMapping("secured")
    public String secured(){
        return "secured";
    }
}
