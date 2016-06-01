/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;

/**
 * @author ravimishra
 *
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

   @Query("SELECT l FROM Login l where l.loginName = ?1")
   public Login findByLoginName(String loginName);

   @Query("SELECT l FROM Login l where l.loginId = ?1")
   public Login findByLoginId(Integer loginId);

   @Query("SELECT l FROM Login l where l.goIdSequence = ?1")
   public Login findByGoId(GoIdSequence goIdSequence);

   @Query("SELECT l FROM Login l where l.goIdSequence.goId = ?1")
   public Login findByCCIGoId(Integer goId);

   @Query("SELECT l FROM Login l where l.email = ?1")
   public Login findByEmail(String email);

   @Query("SELECT l FROM Login l where l.keyValue = ?1")
   public Login findByKeyValue(String keyValue);

   @Query("SELECT l FROM Login l where l.goIdSequence = ?1")
   public List<Login> findAllByGoId(GoIdSequence goIdSequence);

   @Query("SELECT l FROM Login l where l.goIdSequence.goId = ?1")
   public List<Login> findAllByGoId(Integer goId);
   
   @Query("SELECT l FROM Login l where l.email = ?1")
   public List<Login> findListByEmail(String email);
}
