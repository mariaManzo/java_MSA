package com.accueil.accueil.web.controller;

import com.accueil.accueil.model.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resourceUrlMaster}")
    private String resourceUrlMaster;


    @GetMapping(value = { "/launchDices" })
    public String launchDices(Model model) {
        int dices = restTemplate.exchange(resourceUrlMaster + "round", int);
//        PersonnageController personnageController = new PersonnageController();
//        personnageController.squareList();
        model.addAttribute('dices', dices);
        return "redirect:/Squares";
    }
}
