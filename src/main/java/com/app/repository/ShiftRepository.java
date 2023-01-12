package com.app.repository;

import com.app.entity.Patient;
import com.app.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift,Long> {
}
