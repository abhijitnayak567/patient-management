package com.pm.patientservice.mapper;

import com.pm.patientservice.entity.Patient;
import com.pm.patientservice.model.PatientRequest;
import com.pm.patientservice.model.PatientResponse;

import java.time.LocalDate;

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

    public static Patient toPatient(PatientRequest request) {
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setAddress(request.getAddress());
        patient.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));

        return patient;
    }

    public static Patient toPatient(PatientRequest request, Patient patient) {
        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setAddress(request.getAddress());
        patient.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));

        return patient;
    }
}
