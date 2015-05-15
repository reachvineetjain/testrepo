/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Ccistaffuser;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersRepository extends JpaRepository<Ccistaffuser, Integer> {

}
