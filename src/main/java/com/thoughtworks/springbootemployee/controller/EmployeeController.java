package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService ;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService=employeeService;
    }


    @GetMapping()
    public List<EmployeeResponse> getAllEmployees() {
        return this.employeeService.findAll();
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<EmployeeResponse> getAllEmployeesByPageAndSize(Integer page, Integer pageSize) throws IllegalOperationException {
        return this.employeeService.findEmployeesByPageAndPageSize(--page,pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<EmployeeResponse> getAllEmployeesByGender(String gender) throws NoSuchDataException {
        return this.employeeService.findEmployeesByGender(gender);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable("id") Integer id) throws NoSuchDataException {
        return this.employeeService.findEmployeeByID(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EmployeeResponse addEmployee(@RequestBody EmployeeRequest employee) {
        return this.employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    EmployeeResponse updateEmployee(@RequestBody EmployeeRequest newEmployee, @PathVariable("id") Integer id) throws NoSuchDataException, IllegalOperationException {
        return this.employeeService.update(id, newEmployee);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable("id") Integer id) {
        this.employeeService.deleteEmployeeByID(id);
    }

}
