package com.app.controller;

import com.app.dto.AddressDto;
import com.app.dto.ShiftDto;
import com.app.dto.ShiftDtoPatientDentist;
import com.app.service.impl.ShiftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shift")
public class ShiftController {

    @Autowired
    private final ShiftServiceImpl shiftServiceImpl;

    public ShiftController(ShiftServiceImpl shiftServiceImpl) {
        this.shiftServiceImpl = shiftServiceImpl;
    }


    @GetMapping("/Hello")
    public String hello(){
        return "Hello world";
    }

    @PostMapping("/save")
    public ResponseEntity saveShift(@RequestBody ShiftDto shiftDto){
        shiftServiceImpl.saveShift(shiftDto);
        return ResponseEntity.ok("Shift created in database");
    }

    @GetMapping("/findAllShift")
    public ResponseEntity<List<ShiftDto>> findAllShift(){
        List<ShiftDto> shiftDtoList = shiftServiceImpl.findAllShift();
        return new ResponseEntity<>(shiftDtoList, HttpStatus.OK);
    }

    @GetMapping("/findShiftById/{id}")
    public ResponseEntity<?> findShiftById(@PathVariable("id") Long id){
        ShiftDto shiftDto = shiftServiceImpl.findShift(id).orElse(null);
        return new ResponseEntity<>(shiftDto,HttpStatus.OK);
    }

    @PostMapping("/saveShiftPD")
    public ResponseEntity saveShiftPatientDentist(@RequestBody ShiftDtoPatientDentist shiftDtoPatientDentist){
        shiftServiceImpl.saveShiftPatientDentist(shiftDtoPatientDentist);
        return ResponseEntity.ok("Shift created in database with the patient and dentist at the same time");
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity updateShift(@RequestBody ShiftDto shiftDto){
        Long id = shiftDto.getId();
        if(id != null){
            shiftServiceImpl.updateShift(shiftDto);
        }else{
            return ResponseEntity.ok("Shift not founded");
        }
        return ResponseEntity.ok("Shift updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>  deleteShift(@PathVariable("id") Long id){
        shiftServiceImpl.deleteShift(id);
        return new ResponseEntity<>("Shift deleted",HttpStatus.OK);
    }





}
