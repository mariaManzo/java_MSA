package com.accueil.accueil.web.controller;

import com.accueil.accueil.form.PersonnageForm;
import com.accueil.accueil.model.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;



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
        String nom = personnageForm.getNom();
        String type = personnageForm.getType();

        if (nom != null && nom.length() > 0 //
                && type != null && type.length() > 0) {

            Personnage personnage = new Personnage(id, nom, type);
            restTemplate.postForObject("http://localhost:8081/Personnages", personnage, Personnage.class);

            return "redirect:/start";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }



    /*
    //Personnages en post qui sert à ajouter un personnage
    @PostMapping(value = "Personnage/list")
    public void creerPersonnage( @RequestBody Personnage personnage)
    {
        listPersonnages.add(personnage);
    }

    @PutMapping(value = "Personnage/modifier/{id}")
    public void modifierPersonnage( @RequestBody Personnage personnage, @PathVariable int id)
    {
        listPersonnages.set(id, personnage);
    }

    @DeleteMapping(value= "Personnage/supprimer/{id}")
    public void supprimerPersonnage(@PathVariable int id)
    {
        listPersonnages.remove(id);
    }*/

}

