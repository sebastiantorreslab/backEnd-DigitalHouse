package com.app.controller;

import com.app.exception.GlobalHandlerException;
import com.app.dto.DentistDto;
import com.app.exception.ResourceNotFoundException;
import com.app.service.impl.DentistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private final DentistServiceImpl dentistServiceImpl;

    public DentistController(DentistServiceImpl dentistServiceImpl) {
        this.dentistServiceImpl = dentistServiceImpl;
    }

    @GetMapping("/h2")
    public String hello(){
     return "Hello world";
    }


    @PostMapping("/save")
    public ResponseEntity<DentistDto> saveDentist(@RequestBody DentistDto dentistDto)  {
       dentistServiceImpl.saveDentist(dentistDto);
       return new ResponseEntity<>(dentistDto, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DentistDto> findDentist(@PathVariable("id") Long id ) throws ResourceNotFoundException {
        DentistDto dentistDto = dentistServiceImpl.findDentist(id).orElse(null);
        if(dentistDto != null){
            return ResponseEntity.ok(dentistDto);
        }else {
            throw new ResourceNotFoundException("Dentist not founded");
        }
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<DentistDto>> findAllDentist(){
        List<DentistDto> dentistList = dentistServiceImpl.findAllDentist();
        return new ResponseEntity<>(dentistList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DentistDto>updateDentist(@PathVariable("id") Long id,  @RequestBody DentistDto dentistDto) throws ResourceNotFoundException{
        DentistDto dentistDto1 = dentistServiceImpl.findDentist(id).orElse(null);
        if(dentistDto1 != null){
            dentistDto1 = dentistServiceImpl.updateDentist(id, dentistDto);
            return ResponseEntity.ok(dentistDto1);
        }else {
            throw new ResourceNotFoundException("Dentist not found");

        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDentist(@PathVariable("id") Long id){
        dentistServiceImpl.deleteDentist(id);
        return ResponseEntity.ok("The dentist has been deleted");
    }

    @GetMapping("/name/{dentistName}")
    public ResponseEntity<List<DentistDto>> findDentistByName(@PathVariable("dentistName") String name){
        List<DentistDto> dentistDtoList = dentistServiceImpl.findDentistByName(name);
        return new ResponseEntity<>(dentistDtoList,HttpStatus.OK);
    }


}
