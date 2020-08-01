package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.beans.BeanUtils;

public class CompanyMapper {
    public static Company map(CompanyRequest companyRequest){
        Company company=new Company();
        BeanUtils.copyProperties(companyRequest,company);
        return company;
    }

    public static CompanyResponse map(Company company){
        CompanyResponse companyResponse=new CompanyResponse();
        BeanUtils.copyProperties(company,companyResponse);
        return companyResponse;
    }
}
