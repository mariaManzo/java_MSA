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
    public Player round(@RequestParam int player_id) {
//        lance les dés
        int dices = this.launchDice();
//        avance la position du personnage (case_courante_id) avec le résultat des dés
        return this.step_forward(player_id, dices);
    }

    public int launchDice() {
        return 1 + (int) (Math.random() * 12);
    }

    public Player step_forward(int player_id, int dices) {
//        Recupere perso
        Player player = playerController.getPlayer(player_id);
//        Modifie attribut case_courante_id += dices
        player.setCurrent_square_id(player.getCurrent_square_id() + dices);
//        update (modifie et recupere perso)
        Player player2 = playerController.updatePlayer(player);
        return playerController.updatePlayer(player);
    }
}
