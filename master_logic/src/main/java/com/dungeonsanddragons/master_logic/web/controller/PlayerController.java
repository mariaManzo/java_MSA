package com.dungeonsanddragons.master_logic.web.controller;

import com.dungeonsanddragons.master_logic.MasterLogicApplication;
import com.dungeonsanddragons.master_logic.web.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

@RestController
public class PlayerController {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger= MasterLogicApplication.logger(this);

    @Value("${resourceUrlListPerso}")
    private String resourceUrlListPerso;

    public Player getPlayer(int id) {
        return restTemplate.getForObject(resourceUrlListPerso + id, Player.class);
    }

    public Player updatePlayer(Player player) {
        Player player_updated= restTemplate.patchForObject(resourceUrlListPerso + "update", player, Player.class);
        logger.info(String.valueOf(player_updated));
        return player_updated;
    }
}
