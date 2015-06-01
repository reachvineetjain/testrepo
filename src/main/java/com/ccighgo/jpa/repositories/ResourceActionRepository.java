/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.ResourceActions;

/**
 * @author ravimishra
 *
 */
@Repository
public interface ResourceActionRepository extends JpaRepository<ResourceActions, Integer> {

}
