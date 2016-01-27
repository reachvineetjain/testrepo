package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffStatus;

/**
 * @author sinshaw.demisse
 *
 */
@Repository
public interface FieldStaffStatusRepository extends JpaRepository<FieldStaffStatus,Integer>{
 
   @Query("SELECT s FROM FieldStaffStatus s WHERE s.fieldStaffStatusName = ?1")
   FieldStaffStatus getByFieldStaffStatusName(String StatusName);
}
