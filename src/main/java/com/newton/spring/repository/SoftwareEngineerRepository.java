package com.newton.spring.repository;

import com.newton.spring.entities.SoftwareEngineerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareEngineerRepository
        extends JpaRepository<SoftwareEngineerEntity, Integer> {
}
