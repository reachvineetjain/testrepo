package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffType;

@Repository
public interface FieldStaffTypeRepository extends JpaRepository<FieldStaffType, Integer> {

   @Query("SELECT ft FROM FieldStaffType ft WHERE ft.fieldStaffTypeCode = ?1")
   FieldStaffType findByFieldStaffTypeCode(String type);

}
