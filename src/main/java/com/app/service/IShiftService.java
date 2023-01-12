package com.app.service;

import com.app.dto.ShiftDto;
import com.app.dto.ShiftDtoPatientDentist;
import com.app.entity.Shift;

import java.util.List;
import java.util.Optional;

public interface IShiftService {

    void saveShift(ShiftDto shiftDto);

    List<ShiftDto> findAllShift();

    Optional<ShiftDto> findShift(Long id);

    Shift updateShift(ShiftDto shiftDto);

    void saveShiftPatientDentist(ShiftDtoPatientDentist shiftDtoPatientDentist);

    void deleteShift(Long id);

}
