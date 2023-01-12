package com.app.controller;

import com.app.dto.PatientDto;
import com.app.dto.PatientDtoAddress;
import com.app.service.impl.AddressServiceImpl;
import com.app.service.impl.DentistServiceImpl;
import com.app.service.impl.PatientServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private static final Logger LOGGER = Logger.getLogger(DentistServiceImpl.class);

    @Autowired
    private final PatientServiceImpl patientServiceImpl;
    @Autowired
    private final AddressServiceImpl addressServiceImpl;

    public PatientController(PatientServiceImpl patientServiceImpl, AddressServiceImpl addressServiceImpl) {
        this.patientServiceImpl = patientServiceImpl;
        this.addressServiceImpl = addressServiceImpl;
    }


    @GetMapping("/hello")
    public String hello(){
        return "Hello patient";

    }

    @PostMapping("/save")
    public ResponseEntity savePatient(@RequestBody PatientDto patientDto){
       patientServiceImpl.savePatient(patientDto);
        return ResponseEntity.ok("The patient has been created");

    }

    @PostMapping("/savePatient&Address")
    public ResponseEntity savePatient(@RequestBody PatientDtoAddress patientDtoAddress){
       patientServiceImpl.savePatientAddress(patientDtoAddress);
        return ResponseEntity.ok("Patient created with addres in the requestBody");
    }

    @GetMapping("/find/{id}")
    public PatientDto findPatient(@PathVariable("id") Long id){
        return patientServiceImpl.findPatient(id).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>  deletePatient(@PathVariable("id") Long id){
        return patientServiceImpl.deletePatient(id);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<PatientDto>> findAllPatients(){
        List<PatientDto> patientDtoList = patientServiceImpl.findAllPatient();
        return new ResponseEntity<>(patientDtoList, HttpStatus.OK);
    }

    @PutMapping("/update")
    public  ResponseEntity updatePatient(@RequestBody PatientDto patientDto){
       Long id = patientDto.getId();
        if(id != null){
            patientServiceImpl.updatePatient(patientDto);
        }else{
            return ResponseEntity.ok("Patient not founded");
        }
        return ResponseEntity.ok("patient updated");
    }




}
