package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffUpdateLog;

@Repository
public interface FieldStaffUpdateLogRepository extends JpaRepository<FieldStaffUpdateLog, Integer> {

   @Query("SELECT f FROM FieldStaffUpdateLog f WHERE f.fieldStaff.fieldStaffGoId = ?1")
   List<FieldStaffUpdateLog> getFieldStaffUpdateLogByFsGoId(int fsGoId);

}
