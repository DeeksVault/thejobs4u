package com.DeeksVault.SpringBoot.user;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String currentCompany;

    private Double currentPackage;

    @ElementCollection
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private List<String> skills;

    @Column(length = 300)
    private String description;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public Double getCurrentPackage() {
        return currentPackage;
    }

    public void setCurrentPackage(Double currentPackage) {
        this.currentPackage = currentPackage;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 300) {
            throw new IllegalArgumentException("Description cannot exceed 300 characters.");
        }
        this.description = description;
    }
}

