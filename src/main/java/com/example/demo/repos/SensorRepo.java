package com.example.demo.repos;

import com.example.demo.domain.Sensor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SensorRepo extends CrudRepository <Sensor,Integer> {
        List<Sensor> findByName(String name);
}
