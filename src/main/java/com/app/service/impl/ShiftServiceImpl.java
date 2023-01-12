package com.app.service.impl;

import com.app.dto.DentistDto;
import com.app.dto.PatientDto;
import com.app.dto.ShiftDto;
import com.app.dto.ShiftDtoPatientDentist;
import com.app.entity.Address;
import com.app.entity.Dentist;
import com.app.entity.Patient;
import com.app.entity.Shift;
import com.app.repository.AddressRepository;
import com.app.repository.DentistRepository;
import com.app.repository.PatientRepository;
import com.app.repository.ShiftRepository;
import com.app.service.IShiftService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftServiceImpl implements IShiftService {
    private static final Logger LOGGER = Logger.getLogger(ShiftServiceImpl.class);
    @Autowired
    private final ShiftRepository shiftRepository;
    @Autowired
    private final DentistRepository dentistRepository;

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    ObjectMapper mapper;

    public ShiftServiceImpl(ShiftRepository shiftRepository, DentistRepository dentistRepository, PatientRepository patientRepository, AddressRepository addressRepository) {
        this.shiftRepository = shiftRepository;
        this.dentistRepository = dentistRepository;
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
    }


    //This assignment requires having a dentist a patient must be created before create the shift
    @Override
    public void saveShift(ShiftDto shiftDto) {

        Optional<Patient> patient =  patientRepository.findById(shiftDto.getPatient_id());
        Optional<Dentist> dentist = dentistRepository.findById(shiftDto.getDentist_id());

        if(shiftDto != null && patient.isPresent() && dentist.isPresent()){

            Shift shift = mapper.convertValue(shiftDto,Shift.class);

            shift.setPatient(patient.get());
            shift.setDentist(dentist.get());
            shiftRepository.save(shift);
        }else{
            LOGGER.error("Exception creating shift");
        }
    }

    @Override
    public List<ShiftDto> findAllShift() {
        List<Shift> shiftList = shiftRepository.findAll();
        List<ShiftDto> shiftDtoList = new ArrayList<>();
        ShiftDto shiftDto = null;
        if(!shiftList.isEmpty()){
            for(Shift shift: shiftList){
                shiftDto = mapper.convertValue(shift,ShiftDto.class);
                shiftDtoList.add(shiftDto);
            }
        }else{
            LOGGER.error("The list of shift is empty");
        }
        return shiftDtoList;
    }

    // todo: tener en cuenta las implicaciones del update con las relaciones entre las tablas

    @Override
    public Optional<ShiftDto> findShift(Long id) {
        Optional<Shift> shift = shiftRepository.findById(id);
        ShiftDto shiftDto = null;
        if (shift.isPresent()) {
            shiftDto = mapper.convertValue(shift, ShiftDto.class);
        }
        return Optional.ofNullable(shiftDto);
    }

    @Override
    public Shift updateShift(ShiftDto shiftDto) {
    Shift shift = shiftRepository.findById(shiftDto.getId()).orElse(null);
    if(shift != null){
        shift = mapper.convertValue(shiftDto,Shift.class);
        shiftRepository.save(shift);
    }else{
        LOGGER.error("Exception");
    }

        return shift;
    }

    //This method create a new shift also creating a dentist and a patient related to this shift. Its not advisable because one patient can have several shift but only one address
    @Override
    public void saveShiftPatientDentist(ShiftDtoPatientDentist shiftDtoPatientDentist) {
        PatientDto patientDto = shiftDtoPatientDentist.getPatientDto();
        DentistDto dentistDto = shiftDtoPatientDentist.getDentistDto();
        Optional<Address> address = addressRepository.findById(patientDto.getAddress_id());

        if(shiftDtoPatientDentist != null && patientDto != null && dentistDto != null && address.isPresent()){
            Patient patient = mapper.convertValue(patientDto, Patient.class);
            Dentist dentist = mapper.convertValue(dentistDto, Dentist.class);
            // tener en cuenta que aquí no se puede asignar la misma dirección a un paciente - este método es funcional solo para pacientes nuevos
            patient.setAddress(address.get());
            patientRepository.save(patient);
            dentistRepository.save(dentist);

            Shift shift = mapper.convertValue(shiftDtoPatientDentist, Shift.class);
            shift.setPatient(patient);
            shift.setDentist(dentist);
            shiftRepository.save(shift);

        }else{
            LOGGER.error("Exception creating objects envolved in a Shift");
        }

    }

    @Override
    public void deleteShift(Long id) {
        shiftRepository.deleteById(id);
        LOGGER.info("a shift was deleted");
    }
}
