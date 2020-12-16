package com.dungeonsanddragons.player.web.controller;

import com.dungeonsanddragons.player.model.Player;
import com.dungeonsanddragons.player.model.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/demo")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping(path="/add")
    public String addNewPersonnage (@RequestBody Player personnage) {
        Player p=personnage;
        playerRepository.save(p);
        return "Saved";
    }

    @GetMapping(path="/all")
    public Iterable<Player> getAllPersonnage() {

        return playerRepository.findAll();
    }

    @GetMapping(path="/all/{id}")
    public Optional<Player> getPersonnage(@PathVariable int id) {
       Optional<Player> personnage= playerRepository.findById(id);
       return personnage;
    }

    @PutMapping(path="/update")
    public String updatePersonnage(@RequestBody Player personnage) {
        playerRepository.save(personnage);
        return "Updated";
    }

    @DeleteMapping (path="/delete/{id}")
    public String deletePersonnage(@PathVariable int id) {
        playerRepository.deleteById(id);
        return "Deleted";
    }
}