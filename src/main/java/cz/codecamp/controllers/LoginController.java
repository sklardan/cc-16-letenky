package cz.codecamp.controllers;

import cz.codecamp.commands.LoginCommand;
import cz.codecamp.repository.FlightRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * Created by jt on 2/2/16.
 */
@Controller
public class LoginController {

    private FlightRepository flightRepository;

    @RequestMapping({"/", "signin"})
    public String showLoginForm(Model model){

        model.addAttribute("loginCommand", new LoginCommand());

        return "signin";
    }

    @RequestMapping("logout")
    public String yourLoggedOut(){
        return "logout";
    }

    @RequestMapping("registration")
    public String register(){
        return "registration";
    }


}
