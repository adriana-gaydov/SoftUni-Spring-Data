package com.example.demo;

import com.example.demo.entities.DTO.EmployeeDTO;
import com.example.demo.entities.DTO.ManagerDTO;
import com.example.demo.entities.Employee;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class DemoMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Employee manager = new Employee("Mr.", "Someone", BigDecimal.TEN, LocalDate.of(2003, 10, 6), "asdsad", true);
        Employee employee1 = new Employee("Mr.", "asdas", BigDecimal.TEN, LocalDate.of(2003, 10, 6), "asdsad", true, manager);
        Employee employee2 = new Employee("Ms.", "SARA", BigDecimal.TEN, LocalDate.of(2003, 10, 6), "asdsad", true, manager);

        manager.addEmployee(employee1);
        manager.addEmployee(employee2);

        ModelMapper modelMapper = new ModelMapper();
        EmployeeDTO employeeDTO = modelMapper.map(employee1, EmployeeDTO.class);
        EmployeeDTO employeeDTO2 = modelMapper.map(employee2, EmployeeDTO.class);
        ManagerDTO managerDTO = modelMapper.map(manager, ManagerDTO.class);

        System.out.println(employeeDTO);
        System.out.println(employeeDTO2);
        System.out.println(managerDTO);
    }
}
