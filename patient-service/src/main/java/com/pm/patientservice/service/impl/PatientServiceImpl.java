package com.pm.patientservice.service.impl;

import com.pm.patientservice.entity.Patient;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.PatientRequest;
import com.pm.patientservice.model.PatientResponse;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return patients.stream().map(PatientMapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public PatientResponse registerPatient(PatientRequest patientRequest) {
        if (patientRepository.existsByEmail(patientRequest.getEmail()))
            throw new EmailAlreadyExistsException(
                    "Patient with email - " + patientRequest.getEmail() + " already exists");

        Patient patient = PatientMapper.toPatient(patientRequest);
        patient.setRegisteredDate(LocalDate.now());
        patientRepository.save(patient);
        return PatientMapper.toResponseDTO(patient);
    }

    @Override
    public PatientResponse updatePatient(long id, PatientRequest patientRequest) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new PatientNotFoundException("Patient not found with id - " + id));
        if (patientRepository.existsByEmailAndIdIsNot(patientRequest.getEmail(), id))
            throw new EmailAlreadyExistsException(
                    "Patient with email - " + patientRequest.getEmail() + " already exists");
        PatientMapper.toPatient(patientRequest, patient);
        patientRepository.save(patient);
        return PatientMapper.toResponseDTO(patient);
    }

    @Override
    public void deletePatient(long id) {
        patientRepository.deleteById(id);
    }
}
