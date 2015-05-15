/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Usstate;

/**
 * @author ravimishra
 *
 */
@Repository
public interface StateRepository extends JpaRepository<Usstate, Integer> {

}
