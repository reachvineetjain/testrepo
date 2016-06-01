/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginHistory;

/**
 * @author ravimishra
 *
 */
@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Integer> {
   
   @Query("SELECT MAX(loggedOn) FROM LoginHistory l where l.login.loginId = ?1")
   public Timestamp findLastLogin(Integer loginId);

}
