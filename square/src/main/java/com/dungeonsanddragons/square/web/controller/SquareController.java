package com.dungeonsanddragons.square.web.controller;

import com.dungeonsanddragons.square.web.dao.SquareRepository;
import com.dungeonsanddragons.square.web.model.Square;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="API for CRUD operations on Square resource")
@RestController
public class SquareController {

    @Autowired
    private SquareRepository squareRepository;

    @ApiOperation(value="Display full square list in json format.")
    @RequestMapping(value = "/Squares", method = RequestMethod.GET)
    public List<Square> displaySquareList() {
        return squareRepository.findAll();
    }

    @ApiOperation(value="Retrieve and display in json format one square based on his id.")
    @RequestMapping(value = "/Squares/{id}", method = RequestMethod.GET)
    public Square displaySquare(@PathVariable int id) {
        return squareRepository.findById(id);
    }

//    @ApiOperation(value="Edit a square with new json data if id already exist.")
//    @PutMapping(value = "/Squares")
//    public void updateSquare(@RequestBody Square newSquare) {
//        squareRepository.save(newSquare);
//    }
}
