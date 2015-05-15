/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.UserType;

/**
 * @author ravimishra
 *
 */
@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

}
