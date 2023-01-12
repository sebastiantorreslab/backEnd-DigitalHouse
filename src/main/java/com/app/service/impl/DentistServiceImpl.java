package com.app.service.impl;

import com.app.exception.GlobalHandlerException;
import com.app.dto.DentistDto;
import com.app.entity.Dentist;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.DentistRepository;
import com.app.service.IDentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistServiceImpl  implements IDentistService{

    private static final Logger LOGGER = Logger.getLogger(DentistServiceImpl.class);

    @Autowired
    private final DentistRepository dentistRepository;

    @Autowired
    ObjectMapper mapper;

    public DentistServiceImpl(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public void saveDentist(DentistDto dentistDto)  {
        if(dentistDto != null){
            Dentist dentist = mapper.convertValue(dentistDto,Dentist.class);
            dentistRepository.save(dentist);
        }else{
            LOGGER.error("Exception");
        }

    }

    @Override
    public List<DentistDto> findAllDentist() {
        List<Dentist> dentistList = dentistRepository.findAll();
        List<DentistDto> dentistDtoList = new ArrayList<>();
        DentistDto dentistDto = null;

        for(Dentist dentist:dentistList){
            dentistDto = mapper.convertValue(dentist,DentistDto.class);
            dentistDtoList.add(dentistDto);
        }
        return dentistDtoList;
    }

    @Override
    public Optional<DentistDto> findDentist(Long id) throws ResourceNotFoundException{
        Optional<Dentist> dentist = dentistRepository.findById(id);
        DentistDto dentistDto = null;
        if(dentist.isPresent()){
          dentistDto = mapper.convertValue(dentist,DentistDto.class);
        }else{
            throw new ResourceNotFoundException("Dentist not founded");
        }
        return Optional.ofNullable(dentistDto);
    }

    @Override
    public DentistDto updateDentist(Long id, DentistDto dentistDto) {
         Optional<Dentist> dentist = dentistRepository.findById(id);
         Dentist dentist1 = null;
        if(dentist.isPresent()){
            dentist1 = dentist.get();
            dentist1.setLastName(dentistDto.getLastName());
            dentist1.setName(dentistDto.getName());
            dentist1.setLicense(dentistDto.getLicense());
            dentistRepository.save(dentist1);
            DentistDto dentistDto1 = mapper.convertValue(dentist1,DentistDto.class);
            return dentistDto1;
        }else {
            return null;
        }

    }

    @Override
    public void deleteDentist(Long id) {
        dentistRepository.deleteById(id);
        LOGGER.info("ok dentist deleted");

    }



    public List<DentistDto> findDentistByName(String name){
       List<Dentist> dentistList = dentistRepository.findDentistByName(name);
       List<DentistDto> dentistListDto = new ArrayList<>();

       for(Dentist dentist: dentistList){
           if(dentist != null){
               DentistDto dentistDto = mapper.convertValue(dentist,DentistDto.class);
               dentistListDto.add(dentistDto);
           }else{
               return null;
           }

       }
       return dentistListDto;
       }



}












