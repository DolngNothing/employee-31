package com.thoughtworks.springbootemployee.dto;

import java.util.Objects;

public class EmployeeResponse {
    private Integer id;
    private Integer companyId;
    private Integer age;
    private String gender;
    private String name;
    private Integer salary;


    public EmployeeResponse(Integer id, Integer companyId, Integer age, String gender, String name, Integer salary) {
        this.id = id;
        this.companyId = companyId;
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.salary = salary;
    }

    public EmployeeResponse(Integer id, Integer age, String gender, String name, Integer salary) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.salary = salary;
    }

    public EmployeeResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeResponse that = (EmployeeResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(companyId, that.companyId) &&
                Objects.equals(age, that.age) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(name, that.name) &&
                Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyId, age, gender, name, salary);
    }
}
