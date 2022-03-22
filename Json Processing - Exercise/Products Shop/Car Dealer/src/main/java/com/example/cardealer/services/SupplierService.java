package com.example.cardealer.services;

import com.example.cardealer.entities.suppliers.SupplierIdNamePCountDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierService {

    List<SupplierIdNamePCountDTO> getLocalSuppliers();
}
