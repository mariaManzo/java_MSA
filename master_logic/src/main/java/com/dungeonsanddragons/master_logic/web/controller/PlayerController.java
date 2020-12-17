package com.dungeonsanddragons.master_logic.web.controller;

import com.dungeonsanddragons.master_logic.web.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class PlayerController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resourceUrlListPerso}")
    private String resourceUrlListPerso;

    public Player getPlayer(int id) {
        return restTemplate.getForObject(resourceUrlListPerso + id, Player.class);
    }

    public Player updatePlayer(Player player) {
        return restTemplate.postForObject(resourceUrlListPerso + "update", player, Player.class);
    }
}
