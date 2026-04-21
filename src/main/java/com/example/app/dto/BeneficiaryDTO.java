package com.example.app.dto;

import java.time.LocalDate;

public class BeneficiaryDTO {
    private Integer id;
    private String name;
    private String relationshipType;
    private LocalDate dateOfBirth;
    private Integer policyId; // For linking to policy in response

    // Constructors
    public BeneficiaryDTO() {}

    public BeneficiaryDTO(Integer id, String name, String relationshipType, LocalDate dateOfBirth, Integer policyId) {
        this.id = id;
        this.name = name;
        this.relationshipType = relationshipType;
        this.dateOfBirth = dateOfBirth;
        this.policyId = policyId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }
}
