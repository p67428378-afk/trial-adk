package com.example.app.repository;

import com.example.app.model.PolicyHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Integer> {
}
