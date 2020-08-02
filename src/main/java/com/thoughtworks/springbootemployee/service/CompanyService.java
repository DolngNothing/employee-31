package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {


    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<CompanyResponse> findAllCompanies() {
        return companyRepository.findAll().stream().map(CompanyMapper::map).collect(Collectors.toList());
    }

    public CompanyResponse findCompanyByID(Integer companyID) {
        return CompanyMapper.map(this.companyRepository.findById(companyID).orElse(null));
    }

    public List<EmployeeResponse> findEmployeesByCompanyID(Integer companyID) {
        return this.companyRepository.findById(companyID).orElse(null).getEmployees().stream().map(EmployeeMapper::map).collect(Collectors.toList());
    }

    public Page<CompanyResponse> findCompaniesByPageAndPageSize(int page, int pageSize) {
        return new PageImpl<>(this.companyRepository.findAll(PageRequest.of(page,pageSize)).stream().map(CompanyMapper::map).collect(Collectors.toList()));
    }

    public CompanyResponse addCompany(CompanyRequest companyRequest) {
        Company map = CompanyMapper.map(companyRequest);
        Company save = this.companyRepository.save(map);
        return CompanyMapper.map(save);
    }

    public CompanyResponse updateCompany(int companyID, CompanyRequest newCompany) {
        Company company = this.companyRepository.findById(companyID).orElse(null);
        if(newCompany!=null){
            if(newCompany.getName()!=null){
                company.setName(newCompany.getName());
            }
            this.companyRepository.save(company);
        }

        return CompanyMapper.map(company);
    }

    public void deleteCompanyByID(int companyID) {
//        Company company = this.companyRepository.findById(companyID).orElse(null);
//        List<Employee> employees = company != null ? company.getEmployees() : null;
        this.companyRepository.deleteById(companyID);
//        if (employees != null) {
//            this.employeeRepository.deleteAll(employees);
//        }
    }
}
