package com.pm.patientservice.mapper;

import com.pm.patientservice.entity.Patient;
import com.pm.patientservice.model.PatientResponse;

public class PatientMapper {
    public static PatientResponse toResponseDTO(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getDateOfBirth().toString()
        );
    }
}
