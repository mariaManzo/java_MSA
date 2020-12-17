package com.dungeonsanddragons.player.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected  int id;
    protected String name;
    protected String type;
    protected String weapon;
    protected String potion;
    protected int attack_points;
    protected int life_points;
    protected int current_square_id;

    public Player (){

    }
    public Player(int id, String name, String type, String weapon, String potion, int attack_points, int life_points, int current_square_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weapon = weapon;
        this.potion = potion;
        this.attack_points = attack_points;
        this.life_points = life_points;
        this.current_square_id=current_square_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getPotion() {
        return potion;
    }

    public void setPotion(String potion) {
        this.potion = potion;
    }

    public int getAttack_points() {
        return attack_points;
    }

    public void setAttack_points(int attack_points) {
        this.attack_points = attack_points;
    }

    public int getLife_points() {
        return life_points;
    }

    public void setLife_points(int life_points) {
        this.life_points = life_points;
    }

    public int getCurrent_square_id() {
        return current_square_id;
    }

    public void setCurrent_square_id(int current_square_id) {
        this.current_square_id = current_square_id;
    }
}
