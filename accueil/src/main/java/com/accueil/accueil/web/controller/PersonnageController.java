package com.accueil.accueil.web.controller;

import com.accueil.accueil.form.PersonnageForm;
import com.accueil.accueil.model.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@Controller
public class PersonnageController
{
    @Autowired
    private RestTemplate restTemplate;

    // Membres
    //private static ArrayList<Personnage> listPersonnages = new ArrayList<Personnage>();


    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${uriPlayer}")
    private String uriPlayer;

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

            return "board";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";


    }


}

