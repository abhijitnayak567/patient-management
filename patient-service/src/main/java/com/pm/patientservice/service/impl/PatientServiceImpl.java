package com.pm.patientservice.service.impl;

import com.pm.patientservice.entity.Patient;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.PatientResponse;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientResponse> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
