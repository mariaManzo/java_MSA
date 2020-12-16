package com.dungeonsanddragons.square.web.dao;

import com.dungeonsanddragons.square.web.model.Square;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SquareRepository extends JpaRepository<Square, Integer> {
    List<Square> findAll();
    Square findById(int id);
}
