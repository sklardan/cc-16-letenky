package cz.codecamp.controllers;

import cz.codecamp.Classes.Flight;
import cz.codecamp.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jt on 1/20/16.
 */
@Controller
public class ProductController {

    private FlightService flightService;

    @Autowired
    public void setProductService(FlightService flightService) {
        this.flightService = flightService;
    }

    //@RequestMapping("/product")
    public String getFlight(){
        return "redirect:/index";
    }

    @RequestMapping("/flight/{id}")
    public String getFlightById(@PathVariable Integer id, Model model){

        model.addAttribute("flight", flightService.getFlight(id));

        return "flight";
    }
}
