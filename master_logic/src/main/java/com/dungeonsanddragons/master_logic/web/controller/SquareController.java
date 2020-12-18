package com.dungeonsanddragons.master_logic.web.controller;

import com.dungeonsanddragons.master_logic.web.model.Square;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class SquareController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resourceUrlSquares}")
    private String resourceUrlSquares;

    public Square getSquare(int square_id) {
        return restTemplate.getForObject(resourceUrlSquares + square_id, Square.class);
    }
}
