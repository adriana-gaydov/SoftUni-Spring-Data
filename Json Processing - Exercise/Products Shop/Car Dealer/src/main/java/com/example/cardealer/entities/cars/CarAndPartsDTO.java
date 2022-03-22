package com.example.cardealer.entities.cars;

import com.example.cardealer.entities.parts.PartNamePriceDTO;

import java.util.Set;

public class CarAndPartsDTO {

    private String Make;
    private String Model;
    private Long TravelledDistance;
    private Set<PartNamePriceDTO> parts;

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Long getTravelledDistance() {
        return TravelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        TravelledDistance = travelledDistance;
    }

    public Set<PartNamePriceDTO> getParts() {
        return parts;
    }

    public void setParts(Set<PartNamePriceDTO> parts) {
        this.parts = parts;
    }
}
