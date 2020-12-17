package com.dungeonsanddragons.master_logic.web.controller;

import com.dungeonsanddragons.master_logic.MasterLogicApplication;
import com.dungeonsanddragons.master_logic.web.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;


@RestController
public class MainController {

    @Autowired
    private final PlayerController playerController = new PlayerController();


    private Logger logger= MasterLogicApplication.logger(this);


    @RequestMapping(value = {"/round"}, method = RequestMethod.GET)
    public Player round(@RequestParam int player_id) {
//        lance les dés
        int dices = this.launchDice();
//        avance la position du personnage (case_courante_id) avec le résultat des dés
        //return new Player(player_id);
        return this.step_forward(player_id, dices);
    }

    public int launchDice() {
        int dices =  1 + (int) (Math.random() * 12);
        logger.info(String.valueOf(dices));
        //System.out.println(dices);
        return dices;
//        return 1 + (int) (Math.random() * 12);
    }

    public Player step_forward(int player_id, int dices) {
//        Recupere perso
        Player player = playerController.getPlayer(player_id);
        System.out.println(player);
//        Modifie attribut case_courante_id += dices
        player.setCurrent_square_id(player.getCurrent_square_id() + dices);
//        update (modifie et recupere perso)
        Player player2 = playerController.updatePlayer(player);
//        System.out.println(player2);
        logger.info(String.valueOf(player2));
//        return player2;
//        return playerController.updatePlayer(player);
        return player2;
    }
}
