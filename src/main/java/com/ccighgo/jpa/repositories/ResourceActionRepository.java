/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.ResourceAction;
import com.ccighgo.db.entities.Season;

/**
 * @author ravimishra
 *
 */
@Repository
public interface ResourceActionRepository extends JpaRepository<ResourceAction, Integer> {
   
   @Query("select r from ResourceAction r")
   List<ResourceAction> getAllResourceAction();

}
