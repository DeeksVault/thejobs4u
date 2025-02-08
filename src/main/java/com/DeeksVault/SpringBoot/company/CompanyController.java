package com.DeeksVault.SpringBoot.company;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    CompanyService companyService;
    CompanyRepository companyRepository;

    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies() , HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        if(companyRepository.findByName(company.getName())!=null){
            return new ResponseEntity<>("company already present" , HttpStatus.BAD_REQUEST);
        }
        companyService.createCompany(company);
        return new ResponseEntity<>("Successfully added the copmany" , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        if(companyService.getCompanyById(id)!=null){
            return new ResponseEntity<>(companyService.getCompanyById(id) , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id , @RequestBody Company updatedCompany){
        if(companyService.updateCompany(id ,updatedCompany)){
            return  new ResponseEntity<>(updatedCompany , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        if(companyService.deleteCompany(id)){
            return  new ResponseEntity<>("Successfully deleted company" , HttpStatus.OK);
        }
        return  new ResponseEntity<>("Unable to find the requested id" , HttpStatus.NOT_FOUND);
    }
}
