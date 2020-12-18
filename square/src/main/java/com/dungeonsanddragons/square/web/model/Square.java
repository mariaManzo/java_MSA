package com.dungeonsanddragons.square.web.model;


import javax.persistence.*;

@Entity
public class Square {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private boolean is_dead = false;
    private int attack_points;
    private int life_points;
    private String occupant_type;

    public Square(int id, boolean is_dead, int attack_points, int life_points, String occupant_type) {
        this.id = id;
        this.is_dead = is_dead;
        this.attack_points = attack_points;
        this.life_points = life_points;
        this.occupant_type = occupant_type;
    }

    public Square() {

    }

    public boolean isIs_dead() {
        return is_dead;
    }

    public void setIs_dead(boolean is_dead) {
        this.is_dead = is_dead;
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

    public String getOccupant_type() {
        return occupant_type;
    }

    public void setOccupant_type(String occupant_type) {
        this.occupant_type = occupant_type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
