package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    List<CompanyResponse> getCompanies() {
        return this.companyService.findAllCompanies();
    }

    @GetMapping("/{id}")
    CompanyResponse getCompanyById(@PathVariable("id") Integer id) {
        return this.companyService.findCompanyByID(id);
    }

    @GetMapping("/{id}/employees")
    List<EmployeeResponse> getEmployeesByCompanyId(@PathVariable("id") Integer id) {
        return this.companyService.findEmployeesByCompanyID(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    Page<CompanyResponse> getCompaniesByPageAndSize(int page, int pageSize) {
        return this.companyService.findCompaniesByPageAndPageSize(--page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CompanyResponse addCompany(@RequestBody CompanyRequest companyRequest) {
        return this.companyService.addCompany(companyRequest);
    }

    @PutMapping("/{id}")
    CompanyResponse updateCompany(@RequestBody CompanyRequest newCompany, @PathVariable("id") Integer id) {
        return this.companyService.updateCompany(id,newCompany);
    }

    @DeleteMapping("/{id}")
    void deleteCompany(@PathVariable("id") Integer id) {
        this.companyService.deleteCompanyByID(id);
    }
}
