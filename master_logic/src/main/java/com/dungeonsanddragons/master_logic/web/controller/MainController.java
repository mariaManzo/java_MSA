package com.dungeonsanddragons.master_logic.web.controller;

import com.dungeonsanddragons.master_logic.web.model.Player;
import com.dungeonsanddragons.master_logic.web.model.Square;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
public class MainController {

    String[] ennemis = {"Gobelin", "Dragon", "Sorcier"};
    String[] armes = {"Massue", "Epee", "Eclair", "Fire", };
    String[] potions = {"StandardPotion", "BigPotion"};

    @Autowired
    private final PlayerController playerController = new PlayerController();

    @Autowired
    private final SquareController squareController = new SquareController();

    @RequestMapping(value = {"/round"}, method = RequestMethod.GET)
    public Integer round(@RequestParam int player_id) {
//        lance les dés
        Integer dices = this.launchDice();
//        avance la position du personnage (case_courante_id) avec le résultat des dés
        this.step_forward(player_id, dices);

        //interact with square occupant
        this.interact(player_id);
        //
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

    public void interact(int player_id){
        //récupérer player
        Player player = playerController.getPlayer(player_id);
        //récupérer case
        Square square = squareController.getSquare(player.getCurrent_square_id());
        String occupant = square.getOccupant_type();

        if (Arrays.asList(ennemis).contains(occupant)) {
            this.fight(player, square);
        } else if (Arrays.asList(armes).contains(occupant)) {
            player.setWeapon(occupant);
            player.setAttack_points(player.getAttack_points() + square.getAttack_points());
        } else if (Arrays.asList(potions).contains(occupant)) {
            player.setPotion(occupant);
            player.setLife_points(player.getLife_points() + square.getLife_points());
        }
        playerController.updatePlayer(player);
    }

    public void fight(Player player, Square square) {
        int force_ennemi = square.getAttack_points();
        int vie_ennemi = square.getLife_points();
        int force_player = player.getAttack_points();
        int vie_player = player.getLife_points();

        if (vie_ennemi - force_player <= 0) {
            square.setLife_points(0);
        } else {
            square.setLife_points(vie_ennemi - force_player);
        }
        if (vie_player - force_ennemi <= 0){
            player.setLife_points(0);
        } else  {
            player.setLife_points(vie_player - force_ennemi);
        }
    }
}
