package com.example.app.dto;

import java.time.LocalDate;
import java.util.List;

public class PolicyDTO {
    private Integer id;
    private String policyNumber;
    private String coverageType;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private Float premiumAmount;
    private String status;
    private Integer policyHolderId;
    private List<BeneficiaryDTO> beneficiaries;

    // Constructors
    public PolicyDTO() {}

    public PolicyDTO(Integer id, String policyNumber, String coverageType, LocalDate effectiveDate, LocalDate expirationDate, Float premiumAmount, String status, Integer policyHolderId, List<BeneficiaryDTO> beneficiaries) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.coverageType = coverageType;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.premiumAmount = premiumAmount;
        this.status = status;
        this.policyHolderId = policyHolderId;
        this.beneficiaries = beneficiaries;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Float getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(Float premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPolicyHolderId() {
        return policyHolderId;
    }

    public void setPolicyHolderId(Integer policyHolderId) {
        this.policyHolderId = policyHolderId;
    }

    public List<BeneficiaryDTO> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<BeneficiaryDTO> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }
}
