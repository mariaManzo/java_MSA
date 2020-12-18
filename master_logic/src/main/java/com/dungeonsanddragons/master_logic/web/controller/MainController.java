package com.dungeonsanddragons.master_logic.web.controller;

import com.dungeonsanddragons.master_logic.web.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private final PlayerController playerController = new PlayerController();

    @RequestMapping(value = {"/round"}, method = RequestMethod.GET)
    public Integer round(@RequestParam int player_id) {
//        lance les dés
        Integer dices = this.launchDice();
//        avance la position du personnage (case_courante_id) avec le résultat des dés
        this.step_forward(player_id, dices);
        return dices;
    }

    public Integer launchDice() {
        return 1 + (int) (Math.random() * 12);
    }

    public void step_forward(int player_id, Integer dices) {
//        Recupere perso
        Player player = playerController.getPlayer(player_id);
//        Modifie attribut case_courante_id += dices
        int new_position=player.getCurrent_square_id()+dices;
        if(new_position<=64){
            player.setCurrent_square_id(new_position);
            //        update (modifie et recupere perso)
            playerController.updatePlayer(player);
        }
    }
}
