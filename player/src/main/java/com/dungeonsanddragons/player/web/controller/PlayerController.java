package com.dungeonsanddragons.player.web.controller;

import com.dungeonsanddragons.player.model.Player;
import com.dungeonsanddragons.player.model.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping(path="/add", consumes = "application/json", produces="application/json")
    public Player addNewPersonnage (@RequestBody Player personnage) {
        Player p=personnage;
//        if(p.getType()=="Guerrier") {
//            p.setWeapon("");
//            p.setPotion("");
//            p.setAttack_points(5);
//            p.setLife_points(5);
//        } else {
//            p.setWeapon("");
//            p.setPotion("");
//            p.setAttack_points(8);
//            p.setLife_points(4);
//        }
        return playerRepository.save(p);
        //return "Saved";
    }

    @GetMapping(path="/all")
    public Iterable<Player> getAllPersonnage() {

        return playerRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public Optional<Player> getPersonnage(@PathVariable int id) {
       Optional<Player> personnage= playerRepository.findById(id);
       return personnage;
    }

    @PatchMapping(path="/update", produces="application/json")
    public Player updatePersonnage(@RequestBody Player personnage) {
        return playerRepository.save(personnage);
    }

    @DeleteMapping (path="/delete/{id}")
    public String deletePersonnage(@PathVariable int id) {
        playerRepository.deleteById(id);
        return "Deleted";
    }
}
