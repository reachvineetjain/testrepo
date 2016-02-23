package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyUpdateLog;

@Repository
public interface HostFamilyUpdateLogRepository extends JpaRepository<HostFamilyUpdateLog, Integer>{

   @Query("SELECT f FROM HostFamilyUpdateLog f WHERE f.hostFamily.hostFamilyGoId = ?1")
   List<HostFamilyUpdateLog> getHostFamilyUpdateLogByHsGoId(int fsGoId);
}
