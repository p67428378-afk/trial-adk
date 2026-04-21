package com.example.app.dto;

import java.util.List;

public class PolicyHolderDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private List<PolicyDTO> policies;

    // Constructors
    public PolicyHolderDTO() {}

    public PolicyHolderDTO(Integer id, String firstName, String lastName, String email, List<PolicyDTO> policies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.policies = policies;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PolicyDTO> getPolicies() {
        return policies;
    }

    public void setPolicies(List<PolicyDTO> policies) {
        this.policies = policies;
    }
}
