package com.app.service;

import com.app.dto.AddressDto;
import com.app.dto.PatientDto;
import com.app.entity.Patient;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface IAddressService {

    List<AddressDto> findAllAddress();
    void saveAddress(AddressDto addressDto);

    Optional<AddressDto> findAddressById(Long id);

    void updateAddress(AddressDto addressDto);

    void deleteAddress(Long id);





}
