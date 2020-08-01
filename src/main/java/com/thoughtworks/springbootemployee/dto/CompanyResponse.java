package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public class CompanyResponse {
    private Integer id;
    private String name;
    private List<Employee> employees;
    private Integer employeeNumber;

    public CompanyResponse() {
    }

    public CompanyResponse(Integer id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        this.employeeNumber=(employees==null?null:employees.size());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
