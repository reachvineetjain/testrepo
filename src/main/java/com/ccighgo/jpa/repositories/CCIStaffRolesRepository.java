/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Ccistaffrole;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffRolesRepository extends JpaRepository<Ccistaffrole, Integer> {

}
