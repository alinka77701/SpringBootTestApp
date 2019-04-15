package com.example.demo.repos;

import com.example.demo.domain.Building;
import com.example.demo.domain.Sensor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuildingRepo extends CrudRepository <Building,Integer> {
        List<Building> findByName(String name);
}
