package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
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


    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository=employeeRepository;

    }


    public List<CompanyResponse> findAllCompanies() throws NoSuchDataException {
        List<Company> all = companyRepository.findAll();
        if(all==null) throw new NoSuchDataException();
        return all.stream().map(CompanyMapper::map).collect(Collectors.toList());
    }

    public CompanyResponse findCompanyByID(Integer companyID) throws NoSuchDataException {
        Company company = this.companyRepository.findById(companyID).orElse(null);
        if(company==null) throw new NoSuchDataException();

        return CompanyMapper.map(company);
    }

    public List<EmployeeResponse> findEmployeesByCompanyID(Integer companyID) throws NoSuchDataException {
        Company company = this.companyRepository.findById(companyID).orElse(null);
        if(company==null) throw new NoSuchDataException();
        List<Employee> employees = company.getEmployees();
        if(employees==null) throw new NoSuchDataException();
        return employees.stream().map(EmployeeMapper::map).collect(Collectors.toList());
    }

    public Page<CompanyResponse> findCompaniesByPageAndPageSize(int page, int pageSize) throws IllegalOperationException {
        if(page<0||pageSize<1) throw new IllegalOperationException();
        return new PageImpl<>(this.companyRepository.findAll(PageRequest.of(page,pageSize)).stream().map(CompanyMapper::map).collect(Collectors.toList()));
    }

    public CompanyResponse addCompany(CompanyRequest companyRequest) {
        Company map = CompanyMapper.map(companyRequest);
        Company save = this.companyRepository.save(map);
        return CompanyMapper.map(save);
    }

    public CompanyResponse updateCompany(int companyID, CompanyRequest newCompany) throws IllegalOperationException {
        Company company = this.companyRepository.findById(companyID).orElse(null);
        if(company.getId()!=newCompany.getId()) throw new IllegalOperationException();
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
