/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminWorkQueueType;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminWorkQueueTypeRepository extends JpaRepository<AdminWorkQueueType, Integer> {
   @Query("select d from AdminWorkQueueType d where d.lookupDepartmentProgram.lookupDepartmentProgramId = ?1 ")
   List<AdminWorkQueueType> findTypesByDepartmentProgramId(int departmentProgramId);

   @Query("select d from AdminWorkQueueType d where d.roleType =?1")
   List<AdminWorkQueueType> findTypesByPartnerRole(String roleType);

}
