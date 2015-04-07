/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciStaff;

/**
 * @author ravimishra
 *
 */
@Repository
public interface StaffRepository extends JpaRepository<CciStaff, Integer> {

}
