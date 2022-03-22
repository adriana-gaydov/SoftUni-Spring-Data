package com.example.cardealer.repositories;

import com.example.cardealer.entities.suppliers.Supplier;
import com.example.cardealer.entities.suppliers.SupplierIdNamePCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT new com.example.cardealer.entities.suppliers.SupplierIdNamePCountDTO(" +
            "s.id, s.name, size(p)) FROM Supplier s JOIN s.parts p WHERE s.isImporter = false")
    List<SupplierIdNamePCountDTO> findByIsImporterFalse();
}
