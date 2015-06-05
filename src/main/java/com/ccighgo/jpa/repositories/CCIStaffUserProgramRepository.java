/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUserProgram;

/**
 * @author ravi
 *
 */
@Repository
public interface CCIStaffUserProgramRepository extends JpaRepository<CCIStaffUserProgram, Integer> {

}
