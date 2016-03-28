/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerWorkQueueType;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerWorkQueueTypeRepository extends JpaRepository<PartnerWorkQueueType, Integer> {

   @Query("SELECT p FROM PartnerWorkQueueType p WHERE p.lookupDepartmentProgram.lookupDepartmentProgramId = ?1")
   public List<PartnerWorkQueueType> getPartnerWorkQueueTypesByDepartmentProgramId(Integer departmentProgramId);

}
