package cz.codecamp.controllers;

import cz.codecamp.model.Location;
import cz.codecamp.model.User;
import cz.codecamp.repository.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jakubbares on 11/23/16.
 */
public class SettingsController {

    private UserRepository userRepository;

    @RequestMapping(value = "settings/{userName}", method = RequestMethod.POST)
    public ModelAndView getSettings(@PathVariable String userName){
        ModelAndView modelAndView = new ModelAndView("nastaveni-hledani");
        User user = userRepository.findByUserName(userName);
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
