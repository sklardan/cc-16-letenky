package cz.codecamp.controllers;

import cz.codecamp.model.Location;
import cz.codecamp.model.User;
import cz.codecamp.repository.UserRepository;
import cz.codecamp.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jakubbares on 11/23/16.
 */
public class CitySelectionController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "cities2", method = RequestMethod.GET)
    public ModelAndView getCities(@RequestParam("u") String userName){
        ModelAndView modelAndView = new ModelAndView("cities");
        User user = userRepository.findByUserName(userName);

        modelAndView.addObject("user",user);
        modelAndView.addObject("locations", user.getCitiesTo());
        return modelAndView;
    }
}
