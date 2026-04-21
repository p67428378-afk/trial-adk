package com.example.app.service;

import com.example.app.dto.BeneficiaryDTO;
import com.example.app.dto.PolicyDTO;
import com.example.app.model.Beneficiary;
import com.example.app.model.Policy;
import com.example.app.model.PolicyHolder;
import com.example.app.repository.BeneficiaryRepository;
import com.example.app.repository.PolicyHolderRepository;
import com.example.app.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private PolicyHolderRepository policyHolderRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    public List<PolicyDTO> getPolicies(int skip, int limit) {
        return policyRepository.findAll(PageRequest.of(skip / limit, limit)).getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PolicyDTO getPolicyById(Integer policyId) {
        Optional<Policy> policyOptional = policyRepository.findById(policyId);
        return policyOptional.map(this::convertToDto).orElse(null);
    }

    @Transactional
    public PolicyDTO createPolicy(PolicyDTO policyDTO, Integer policyHolderId) {
        PolicyHolder policyHolder = policyHolderRepository.findById(policyHolderId)
                .orElseThrow(() -> new RuntimeException("PolicyHolder not found"));

        Policy policy = new Policy();
        policy.setPolicyNumber(policyDTO.getPolicyNumber());
        policy.setCoverageType(policyDTO.getCoverageType());
        policy.setEffectiveDate(policyDTO.getEffectiveDate());
        policy.setExpirationDate(policyDTO.getExpirationDate());
        policy.setPremiumAmount(policyDTO.getPremiumAmount());
        policy.setStatus(policyDTO.getStatus() != null ? policyDTO.getStatus() : "Active");
        policy.setPolicyHolder(policyHolder);

        Policy savedPolicy = policyRepository.save(policy);

        if (policyDTO.getBeneficiaries() != null) {
            List<Beneficiary> beneficiaries = policyDTO.getBeneficiaries().stream()
                    .map(beneficiaryDTO -> {
                        Beneficiary beneficiary = new Beneficiary();
                        beneficiary.setName(beneficiaryDTO.getName());
                        beneficiary.setRelationshipType(beneficiaryDTO.getRelationshipType());
                        beneficiary.setDateOfBirth(beneficiaryDTO.getDateOfBirth());
                        beneficiary.setPolicy(savedPolicy);
                        return beneficiary;
                    })
                    .collect(Collectors.toList());
            beneficiaryRepository.saveAll(beneficiaries);
            savedPolicy.setBeneficiaries(beneficiaries);
        }

        return convertToDto(savedPolicy);
    }

    @Transactional
    public PolicyDTO updatePolicy(Integer policyId, PolicyDTO policyDTO) {
        Policy existingPolicy = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        existingPolicy.setPolicyNumber(policyDTO.getPolicyNumber());
        existingPolicy.setCoverageType(policyDTO.getCoverageType());
        existingPolicy.setEffectiveDate(policyDTO.getEffectiveDate());
        existingPolicy.setExpirationDate(policyDTO.getExpirationDate());
        existingPolicy.setPremiumAmount(policyDTO.getPremiumAmount());
        existingPolicy.setStatus(policyDTO.getStatus());

        Policy updatedPolicy = policyRepository.save(existingPolicy);

        // Handle beneficiaries update (this is a simplified approach, a full implementation would manage adds/removes/updates)
        if (policyDTO.getBeneficiaries() != null) {
            // For simplicity, we'll clear existing and add new ones.
            // In a real app, you'd compare and update.
            beneficiaryRepository.deleteAll(existingPolicy.getBeneficiaries());
            existingPolicy.getBeneficiaries().clear();

            List<Beneficiary> beneficiaries = policyDTO.getBeneficiaries().stream()
                    .map(beneficiaryDTO -> {
                        Beneficiary beneficiary = new Beneficiary();
                        beneficiary.setName(beneficiaryDTO.getName());
                        beneficiary.setRelationshipType(beneficiaryDTO.getRelationshipType());
                        beneficiary.setDateOfBirth(beneficiaryDTO.getDateOfBirth());
                        beneficiary.setPolicy(updatedPolicy);
                        return beneficiary;
                    })
                    .collect(Collectors.toList());
            beneficiaryRepository.saveAll(beneficiaries);
            updatedPolicy.setBeneficiaries(beneficiaries);
        }

        return convertToDto(updatedPolicy);
    }

    public void deletePolicy(Integer policyId) {
        policyRepository.deleteById(policyId);
    }

    private PolicyDTO convertToDto(Policy policy) {
        PolicyDTO policyDTO = new PolicyDTO();
        policyDTO.setId(policy.getId());
        policyDTO.setPolicyNumber(policy.getPolicyNumber());
        policyDTO.setCoverageType(policy.getCoverageType());
        policyDTO.setEffectiveDate(policy.getEffectiveDate());
        policyDTO.setExpirationDate(policy.getExpirationDate());
        policyDTO.setPremiumAmount(policy.getPremiumAmount());
        policyDTO.setStatus(policy.getStatus());
        policyDTO.setPolicyHolderId(policy.getPolicyHolder() != null ? policy.getPolicyHolder().getId() : null);

        if (policy.getBeneficiaries() != null) {
            policyDTO.setBeneficiaries(policy.getBeneficiaries().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList()));
        }
        return policyDTO;
    }

    private BeneficiaryDTO convertToDto(Beneficiary beneficiary) {
        BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO();
        beneficiaryDTO.setId(beneficiary.getId());
        beneficiaryDTO.setName(beneficiary.getName());
        beneficiaryDTO.setRelationshipType(beneficiary.getRelationshipType());
        beneficiaryDTO.setDateOfBirth(beneficiary.getDateOfBirth());
        beneficiaryDTO.setPolicyId(beneficiary.getPolicy() != null ? beneficiary.getPolicy().getId() : null);
        return beneficiaryDTO;
    }
}
