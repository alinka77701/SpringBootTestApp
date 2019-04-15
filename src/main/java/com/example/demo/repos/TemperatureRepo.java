package com.example.demo.repos;


import com.example.demo.domain.Value;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TemperatureRepo extends CrudRepository <Value,Integer> {
    List<Value> findByBuilding_idAndSensor_id(Integer building_id, Integer sensor_id);
    List<Value> findByBuilding_idAndSensor_idAndTimeAfterAndTimeBefore(Integer building_id, Integer sensor_id, Date from, Date to);
    List<Value> findByBuilding_idAndTimeAfterAndTimeBefore(Integer building_id, Date from, Date to);
    List<Value> findBySensor_idAndTimeAfterAndTimeBefore(Integer sensor_id, Date from, Date to);
    List<Value> findByBuilding_id(Integer building_id);
    List<Value> findBySensor_id(Integer sensor_id);

    @Query(value = "SELECT new ValueProjection(building.name, AVG(value.value)) FROM value JOIN building ON value.building_id=building.id GROUP BY building.name;", nativeQuery = true)
    List<ValueProjection>  findAverage();
}
