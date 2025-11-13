package com.pm.patientservice.service;

import com.pm.patientservice.model.PatientResponse;

import java.util.List;

public interface PatientService {
    List<PatientResponse> getPatients();
}
