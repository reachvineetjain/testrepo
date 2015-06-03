/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUser;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersRepository extends JpaRepository<CCIStaffUser, Integer> {

}
