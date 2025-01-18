package com.DeeksVault.SpringBoot.company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company , Long> {
    Company findByName(String title);
}
