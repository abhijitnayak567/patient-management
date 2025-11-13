package com.pm.patientservice.model;

public record PatientResponse(
        long id,
        String name,
        String email,
        String address,
        String dateOfBirth) {
}
