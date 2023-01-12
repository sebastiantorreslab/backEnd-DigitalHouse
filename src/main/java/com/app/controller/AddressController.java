package com.app.controller;

import com.app.dto.AddressDto;
import com.app.dto.PatientDto;
import com.app.entity.Address;
import com.app.exception.ResourceNotFoundException;
import com.app.service.impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressServiceImpl addressServiceImpl;



    @GetMapping("/Hello")
    public String hello(){
        return "Hello world";
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<AddressDto>> findAllAddress(){
        List<AddressDto> addressDtoList = addressServiceImpl.findAllAddress();
        return new ResponseEntity<>(addressDtoList, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable("id") Long id){
        AddressDto addressDto = addressServiceImpl.findAddressById(id).orElse(null);
        return new ResponseEntity<>(addressDto,HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity saveAddress(@RequestBody AddressDto addressDto){
        addressServiceImpl.saveAddress(addressDto);
        return ResponseEntity.ok("Address created in database");
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity updateAddress(@RequestBody AddressDto addressDto) throws ResourceNotFoundException {
        Long id = addressDto.getId();
        if(id != null){
            addressServiceImpl.updateAddress(addressDto);
        }else{
            throw new ResourceNotFoundException("Not founded");
        }
        return ResponseEntity.ok("Address updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>  deleteAddress(@PathVariable("id") Long id){
            addressServiceImpl.deleteAddress(id);
            return new ResponseEntity<>("Address deleted",HttpStatus.OK);
    }


}
