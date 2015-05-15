/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Resourceaction;

/**
 * @author ravimishra
 *
 */
@Repository
public interface ResourceActionRepository extends JpaRepository<Resourceaction, Integer> {

}
