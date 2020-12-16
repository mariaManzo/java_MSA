package com.dungeonsanddragons.square.web.model;


import javax.persistence.*;
import java.util.Optional;

@Entity
public class Square {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private boolean is_visited = false;
    private boolean is_dead = false;
    private int attack_points;
    private int life_points;
    private String occupant_type;
    private boolean current_square = false;

    public Square(int id, boolean is_visited, boolean is_dead, int attack_points, int life_points, String occupant_type, boolean current_square) {
        this.id = id;
        this.is_visited = is_visited;
        this.is_dead = is_dead;
        this.attack_points = attack_points;
        this.life_points = life_points;
        this.occupant_type = occupant_type;
        this.current_square = current_square;
    }

    public Square() {

    }

    public boolean isIs_visited() {
        return is_visited;
    }

    public void setIs_visited(boolean is_visited) {
        this.is_visited = is_visited;
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

    public boolean getCurrent_square() {
        return current_square;
    }

    public void setCurrent_square(boolean current_square) {
        this.current_square = current_square;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
