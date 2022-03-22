package com.example.cardealer.entities.suppliers;

import java.util.Objects;

public class SupplierIdNamePCountDTO {

    private Long Id;
    private String Name;
    private int partsCount;

    public SupplierIdNamePCountDTO(Long id, String name, int partsCount) {
        Id = id;
        Name = name;
        this.partsCount = partsCount;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierIdNamePCountDTO that = (SupplierIdNamePCountDTO) o;
        return Id.equals(that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
