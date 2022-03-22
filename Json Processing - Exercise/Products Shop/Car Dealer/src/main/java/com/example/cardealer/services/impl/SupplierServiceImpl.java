package com.example.cardealer.services.impl;

import com.example.cardealer.entities.suppliers.SupplierIdNamePCountDTO;
import com.example.cardealer.repositories.SupplierRepository;
import com.example.cardealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierIdNamePCountDTO> getLocalSuppliers() {
        return this.supplierRepository.findByIsImporterFalse();
    }
}
