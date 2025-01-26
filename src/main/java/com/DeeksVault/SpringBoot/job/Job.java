package com.DeeksVault.SpringBoot.job;


import com.DeeksVault.SpringBoot.company.Company;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id")
    private Company company;

    private LocalDateTime createdDate;

    public Job() {
    }

    public Job(Long id, String title, String description, String location, String maxSalary, String minSalary , LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.createdDate = createdDate;
    }



    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
