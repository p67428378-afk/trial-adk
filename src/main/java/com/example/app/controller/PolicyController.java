package com.example.app.controller;

import com.example.app.dto.PolicyDTO;
import com.example.app.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping("/")
    public ResponseEntity<PolicyDTO> createPolicy(@RequestBody PolicyDTO policyDTO) {
        // A placeholder policy_holder_id is used here. In a real application,
        // this would come from the authenticated user.
        PolicyDTO createdPolicy = policyService.createPolicy(policyDTO, 1);
        return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PolicyDTO>> getAllPolicies(
            @RequestParam(defaultValue = "0") int skip,
            @RequestParam(defaultValue = "100") int limit) {
        List<PolicyDTO> policies = policyService.getPolicies(skip, limit);
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<PolicyDTO> getPolicyById(@PathVariable Integer policyId) {
        PolicyDTO policy = policyService.getPolicyById(policyId);
        if (policy == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(policy, HttpStatus.OK);
    }

    @PutMapping("/{policyId}")
    public ResponseEntity<PolicyDTO> updatePolicy(@PathVariable Integer policyId, @RequestBody PolicyDTO policyDTO) {
        try {
            PolicyDTO updatedPolicy = policyService.updatePolicy(policyId, policyDTO);
            return new ResponseEntity<>(updatedPolicy, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Integer policyId) {
        try {
            policyService.deletePolicy(policyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
