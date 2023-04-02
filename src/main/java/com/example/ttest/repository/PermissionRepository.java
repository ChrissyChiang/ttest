package com.example.ttest.repository;

import com.example.ttest.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
    PermissionEntity findByAuth(String auth);
}
