package com.DeeksVault.SpringBoot.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    void createCompany(Company company);
    Company getCompanyById(Long id);

    boolean updateCompany(Long id ,Company company);

    boolean deleteCompany(Long id);
}
