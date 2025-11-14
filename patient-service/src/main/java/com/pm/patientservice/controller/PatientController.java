package com.pm.patientservice.controller;

import com.pm.patientservice.model.PatientRequest;
import com.pm.patientservice.model.PatientResponse;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API to manage patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get all Patients")
    public ResponseEntity<List<PatientResponse>> getPatients() {
        List<PatientResponse> patients = patientService.getPatients();
        return ResponseEntity.ok(patients);
    }

    @PostMapping
    @Operation(summary = "Register a new Patient")
    public ResponseEntity<PatientResponse> registerPatient(@Valid @RequestBody PatientRequest patientRequest) {
        PatientResponse patientResponse = patientService.registerPatient(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a patient details")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable long id,
                                                         @Valid @RequestBody PatientRequest patientRequest) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient")
    public ResponseEntity<Void> deletePatient(@PathVariable long id) {
        return ResponseEntity.noContent().build();
    }
}
