package com.accueil.accueil.web.controller;

import com.accueil.accueil.form.PersonnageForm;
import com.accueil.accueil.model.Personnage;
import com.accueil.accueil.model.Square;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Controller
public class PersonnageController
{
    @Autowired
    private RestTemplate restTemplate;

    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${uriPlayer}")
    private String uriPlayer;

    @Value("${uriSquare}")
    private String uriSquare;

    @Value("${error.message}")
    private String errorMessage;

    // Methodes

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }


    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        PersonnageForm personnageForm = new PersonnageForm();
        model.addAttribute("personForm", personnageForm);

        return "addPerson";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String savePersonnage(Model model, //
                                 @ModelAttribute("personForm") PersonnageForm personnageForm) {

        int id = personnageForm.getId();
        String name = personnageForm.getName();
        String type = personnageForm.getType();

        if (name != null && name.length() > 0 //
                && type != null && type.length() > 0) {

            restTemplate.postForObject(uriPlayer+"add", personnageForm, Personnage.class);
            return "redirect:/Squares";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }

    @GetMapping(value = { "/Squares" })
    public String squareList(Model model, Integer dices) {
        Square[] squares = restTemplate.getForObject(uriSquare, Square[].class);
        model.addAttribute("squares", squares);
        Personnage[] personnages = restTemplate.getForObject(uriPlayer+"all", Personnage[].class);
        int player_id=personnages[personnages.length-1].getId();
        Personnage personnage = restTemplate.getForObject(uriPlayer+player_id, Personnage.class);
       if(dices!=null) {
           model.addAttribute("dices", dices);
       }
        model.addAttribute("personnage", personnage);
        return "board";
    }
}

