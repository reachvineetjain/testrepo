/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.LookupUSState;

/**
 * @author ravimishra
 *
 */
@Repository
public interface StateRepository extends JpaRepository<LookupUSState, Integer> {

   @Query("SELECT s FROM LookupUSState s WHERE s.stateName = ?1")
   LookupUSState getStateByName(String state);

}
