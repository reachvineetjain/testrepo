package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaff;

@Repository
public interface FieldStaffRepository extends JpaRepository<FieldStaff, Integer> {

   @Query("select fs from FieldStaff fs where fs.fieldStaffType.fieldStaffTypeId = ?1")
   List<FieldStaff> findAllERDStaff(Integer fieldstafftypecodeErd);

   @Query("select fs from FieldStaff fs where fs.fieldStaffType.fieldStaffTypeId = ?1")
   List<FieldStaff> findAllRDStaff(Integer fieldstafftypecoderd);

   @Query("select fs from FieldStaff fs where fs.fieldStaffType.fieldStaffTypeId in (2,3,4)")
   List<FieldStaff> findAllStaffRatherERD();

   @Query("select fs from FieldStaff fs where fs.fieldStaffType.fieldStaffTypeCode = ?1")
   List<FieldStaff> findAllByFieldStaffType(String fieldStaffTypeCode);
}
