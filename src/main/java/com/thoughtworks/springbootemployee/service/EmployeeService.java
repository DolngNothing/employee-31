package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse update(Integer employeeID, EmployeeRequest updatedEmployee) throws IllegalOperationException, NoSuchDataException {
        Employee employee = employeeRepository.findById(employeeID).orElse(null);
        if (employee == null) {
            throw new NoSuchDataException();
        }
        if (!employee.getId().equals(updatedEmployee.getId())) {
            throw new IllegalOperationException();
        }
        BeanUtils.copyProperties(updatedEmployee, employee);

        return EmployeeMapper.map(this.employeeRepository.save(employee));
    }

    public List<EmployeeResponse> findAll() {
        return this.employeeRepository.findAll().stream().map(EmployeeMapper::map).collect(Collectors.toList());
    }

    public Page<EmployeeResponse> findEmployeesByPageAndPageSize(int page, int pageSize) throws IllegalOperationException {
        if(page<0||pageSize<1) throw new IllegalOperationException();
        return new PageImpl<>(this.employeeRepository.findAll(PageRequest.of(page, pageSize)).stream().map(EmployeeMapper::map).collect(Collectors.toList()));
}

    public List<EmployeeResponse> findEmployeesByGender(String gender) {
        List<EmployeeResponse> genderEmployees = this.employeeRepository.findAllByGender(gender).stream().map(EmployeeMapper::map).collect(Collectors.toList());

        return genderEmployees;
    }

    public EmployeeResponse findEmployeeByID(Integer employeeID) throws NoSuchDataException {
        Employee foundEmployee = this.employeeRepository.findById(employeeID).orElse(null);
        if (foundEmployee == null) {
            throw new NoSuchDataException();
        }
        return EmployeeMapper.map(foundEmployee);
    }

    public EmployeeResponse addEmployee(EmployeeRequest employee) {
        return EmployeeMapper.map(this.employeeRepository.save(EmployeeMapper.map(employee)));
    }

    public void deleteEmployeeByID(Integer employeeID) {
        this.employeeRepository.deleteById(employeeID);
    }
}
