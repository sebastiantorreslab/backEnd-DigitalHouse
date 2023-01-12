package com.app.service.impl;


import com.app.dto.AddressDto;
import com.app.dto.PatientDto;
import com.app.dto.PatientDtoAddress;
import com.app.entity.Address;
import com.app.entity.Patient;
import com.app.repository.AddressRepository;
import com.app.repository.PatientRepository;
import com.app.service.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PatientServiceImpl implements IPatientService {

    private static final Logger LOGGER = Logger.getLogger(PatientServiceImpl.class);
    @Autowired
    private final PatientRepository patientRepository;
    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    ObjectMapper mapper;

    public PatientServiceImpl(PatientRepository patientRepository, AddressRepository addressRepository) {
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    public void savePatient(PatientDto patientDto) {
        Optional<Address> address = addressRepository.findById(patientDto.getAddress_id());
        if(patientDto!=null && address.isPresent()){
           Patient patient = mapper.convertValue(patientDto,Patient.class);
           patient.setAddress(address.get());
            patientRepository.save(patient);
        }else{
            throw new RuntimeException();
        }
    }

    public void savePatientAddress(PatientDtoAddress patientDtoAddress){
        if(patientDtoAddress != null){
            AddressDto addressDto = patientDtoAddress.getAddressDto();
            Address address = mapper.convertValue(addressDto,Address.class);
            addressRepository.save(address);
            Patient patient = mapper.convertValue(patientDtoAddress, Patient.class);
            patient.setAddress(address);
            patientRepository.save(patient);
        }
    };

    @Override
    public List<PatientDto> findAllPatient() {

        List<Patient> patientsList = patientRepository.findAll();
        List<PatientDto> patientsDtoList = new ArrayList<>();

        for(Patient patient : patientsList) {
            PatientDto patientDto = mapper.convertValue(patient,PatientDto.class);
            patientsDtoList.add(patientDto);
        }

        return patientsDtoList;
    }

    @Override
    public Optional<PatientDto> findPatient(Long id) {
        PatientDto patientDto = null;
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient != null){
            patientDto = mapper.convertValue(patient,PatientDto.class);
        }
        return Optional.ofNullable(patientDto);
    }

    @Override
    public ResponseEntity updatePatient(@NotNull PatientDto patientDto) {

        if(patientDto.getId() != null){
            Patient patient = mapper.convertValue(patientDto,Patient.class);
            patientRepository.save(patient);
        }else{
            ResponseEntity.status(404);
        }
        return ResponseEntity.ok("Patient " + patientDto.getId() + " Updated");
        }


    @Override
    public ResponseEntity deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient != null){
            patientRepository.deleteById(id);
        }else{
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok("Patient "+ id +" "+ patient.getName() +" "+ patient.getLastName() + " deleted");

    };
}

