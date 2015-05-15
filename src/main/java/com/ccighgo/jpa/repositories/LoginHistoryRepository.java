/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.History;

/**
 * @author ravimishra
 *
 */
@Repository
public interface LoginHistoryRepository extends JpaRepository<History, Integer> {

}
