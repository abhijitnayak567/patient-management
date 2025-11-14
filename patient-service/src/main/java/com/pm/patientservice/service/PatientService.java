package com.pm.patientservice.service;

import com.pm.patientservice.model.PatientRequest;
import com.pm.patientservice.model.PatientResponse;

import java.util.List;
import java.util.Map;

public interface PatientService {
    List<PatientResponse> getPatients();
    PatientResponse registerPatient(PatientRequest patientRequest);
    PatientResponse updatePatient(long id, PatientRequest patientRequest);
    void deletePatient(long id);
}
