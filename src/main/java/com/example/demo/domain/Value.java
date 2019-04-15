package com.example.demo.domain;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Value {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name="building_id")
    private Building building;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="sensor_id")
    private Sensor sensor;

    private Integer value;
    private Date time;

    public Value(){}

    public Integer getId() {
        return id;
    }

    public Value(Integer value, Building building, Sensor sensor, Date time) {
        this.value = value;
        this.building = building;
        this.sensor = sensor;
        this.time = time;
    }

    public String getBuildingName() {
        return building != null ? building.getName() : "<none>";
    }

    public String getSensorName() {
        return sensor != null ? sensor.getModel() : "<none>";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public String getTime() {
        return time != null ? this.time.toString(): "<none>";
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
