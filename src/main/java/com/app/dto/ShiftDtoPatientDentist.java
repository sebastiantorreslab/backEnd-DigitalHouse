package com.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftDtoPatientDentist {

        private Long id;
        private String shiftDate;
        private String shiftTime;
        private DentistDto dentistDto;
        private PatientDto patientDto;

    public ShiftDtoPatientDentist() {
    }


    public String getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(String shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public DentistDto getDentistDto() {
        return dentistDto;
    }

    public void setDentistDto(DentistDto dentistDto) {
        this.dentistDto = dentistDto;
    }

    public PatientDto getPatientDto() {
        return patientDto;
    }

    public void setPatientDto(PatientDto patientDto) {
        this.patientDto = patientDto;
    }
}


