package com.app.service;

import com.app.dto.PatientDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IPatientService {


    void savePatient(PatientDto patientDto);

    List<PatientDto> findAllPatient();
    Optional<PatientDto> findPatient(Long id);

    ResponseEntity updatePatient(PatientDto patientDto);

    ResponseEntity deletePatient(Long id);


}
