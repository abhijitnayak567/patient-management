package com.pm.patientservice.repository;

import com.pm.patientservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdIsNot(String email, long id);
}
