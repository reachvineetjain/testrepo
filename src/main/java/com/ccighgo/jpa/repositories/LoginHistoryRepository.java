/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.LoginHistory;

/**
 * @author ravimishra
 *
 */
@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Integer> {

}
