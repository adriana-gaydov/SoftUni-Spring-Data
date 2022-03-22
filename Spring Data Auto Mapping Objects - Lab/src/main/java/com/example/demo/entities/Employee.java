package com.example.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Employee {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate birthday;
    private String address;
    private boolean isOnHoliday;
    private Employee manager;
    private Set<Employee> employees;

    public Employee() {
    }

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate birthday, String address, boolean isOnHoliday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
        this.isOnHoliday = isOnHoliday;
        this.employees = new HashSet<>();
    }

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate birthday, String address, boolean isOnHoliday, Employee manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
        this.isOnHoliday = isOnHoliday;
        this.manager = manager;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOnHoliday() {
        return isOnHoliday;
    }

    public void setOnHoliday(boolean onHoliday) {
        isOnHoliday = onHoliday;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }


}
