package com.app.repository;

import com.app.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {


   @Query("from Dentist d where d.name like %:name%")
   List<Dentist> findDentistByName(String name);


}
