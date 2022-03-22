package com.example.productshopxml.services;

import javax.xml.bind.JAXBException;

public interface XMLExportService {

    void exportProductsInRange() throws JAXBException;
    void exportProductsSoldWithBuyer() throws JAXBException;
    void exportCategoriesWithCount() throws JAXBException;
    void exportUsersWithProductsSoldWithCount() throws JAXBException;
}