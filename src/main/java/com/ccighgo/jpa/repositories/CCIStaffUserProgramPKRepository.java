/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUserProgram;
import com.ccighgo.db.entities.CCIStaffUserProgramPK;

/**
 * @author ravi
 *
 */
@Repository
public interface CCIStaffUserProgramPKRepository extends JpaRepository<CCIStaffUserProgram, CCIStaffUserProgramPK> {

}
