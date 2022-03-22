package com.example.demo.entities.DTO;

import com.example.demo.entities.Employee;

import java.util.Set;

public class ManagerDTO {

    private String firstName;
    private String lastName;
    private Set<EmployeeDTO> employees;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "ManagerDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employees=" + employees +
                '}';
    }
}
