package cz.codecamp.controllers;

import cz.codecamp.model.Location;
import cz.codecamp.model.User;
import cz.codecamp.repository.UserRepository;
import cz.codecamp.services.FlightService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jakubbares on 11/23/16.
 */
public class CitySelectionController {

    private UserRepository userRepository;

    @RequestMapping(value = "cities", method = RequestMethod.GET)
    public ModelAndView getCities(@PathVariable String userName){
        ModelAndView modelAndView = new ModelAndView("cities");
        User user = userRepository.findByUserName(userName);
        Map<String,String> citiesAndCountries = new HashMap<String, String>();
        for (Location loc : user.getCitiesTo()){
            String city = loc.getCity();
            String country = loc.getCountry();
            citiesAndCountries.put(city, country);
        }
        modelAndView.addObject("user",user);
        modelAndView.addObject("citiesAndCountries", citiesAndCountries);
        return modelAndView;
    }
}
