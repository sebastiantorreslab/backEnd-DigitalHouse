package com.app.service;

import com.app.exception.GlobalHandlerException;
import com.app.dto.DentistDto;
import com.app.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;


public interface IDentistService {

    void saveDentist(DentistDto dentistDto)  throws ResourceNotFoundException;

    List<DentistDto> findAllDentist();
    Optional<DentistDto> findDentist(Long id) throws ResourceNotFoundException;

    DentistDto updateDentist(Long id, DentistDto dentistDto);

     void deleteDentist(Long id);



}
