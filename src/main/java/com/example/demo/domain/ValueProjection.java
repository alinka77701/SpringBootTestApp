package com.example.demo.domain;

public class ValueProjection {
    private String nameBuilding;
    private Integer averageValue;

    public ValueProjection(){}

    public ValueProjection(String nameBuilding, Integer averageValue) {
        this.nameBuilding = nameBuilding;
        this.averageValue = averageValue;
    }

    public String getNameBuilding() {
        return nameBuilding;
    }

    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public Integer getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(Integer averageValue) {
        this.averageValue = averageValue;
    }

}
