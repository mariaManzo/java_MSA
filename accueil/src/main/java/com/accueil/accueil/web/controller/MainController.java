package com.accueil.accueil.web.controller;

import com.accueil.accueil.model.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PersonnageController personnageController;

    @Value("${resourceUrlMaster}")
    private String resourceUrlMaster;
    @Value("${uriPlayer}")
    private String uriPlayer;

    @RequestMapping(value = { "/launchDices" },method = RequestMethod.GET)

    public String launchDices(Model model) {
        Personnage[] personnages = restTemplate.getForObject(uriPlayer+"all", Personnage[].class);
        int player_id=personnages[personnages.length-1].getId();
        ResponseEntity<Integer> responseDices = restTemplate.getForEntity(resourceUrlMaster + "round/?player_id="+player_id, Integer.class);
        int dices=responseDices.getBody();
        String responseVue=  personnageController.squareList(model, dices);
        return responseVue;
    }
}
