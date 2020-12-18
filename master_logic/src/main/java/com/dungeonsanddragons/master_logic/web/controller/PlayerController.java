package com.dungeonsanddragons.master_logic.web.controller;

import com.dungeonsanddragons.master_logic.web.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PlayerController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resourceUrlListPerso}")
    private String resourceUrlListPerso;

    public Player getPlayer(int id) {
        return restTemplate.getForObject(resourceUrlListPerso + id, Player.class);
    }

    public void updatePlayer(Player player) {
        restTemplate.patchForObject(resourceUrlListPerso + "update", player, Player.class);
    }
}
