package com.app.service.impl;

import com.app.entity.Patient;
import com.app.repository.PatientRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(PatientServiceImplTest.class);

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImplTest(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Test
    void savePatient() {
        Patient patient = new Patient();
        patient.setName("Sebastián");
        patient.setLastName("Torres");
        patientRepository.save(patient);

        assertTrue(patientRepository.save(patient)!=null);

        LOGGER.info("Aplied Test");

    }

    @Test
    void findPatient() {
        Patient patient = new Patient();
        patient.setName("Luka");
        patient.setLastName("Modric");
        patientRepository.save(patient);
       Patient patient1 = patientRepository.findById(patient.getId()).orElse(null);
       assertTrue(patient1.getName() == "Luka");

    }
}