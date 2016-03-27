package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Salutation;

/**
 * @author rajan
 *
 */
@Repository
public interface SalutationRepository extends JpaRepository<Salutation, Integer> {

   @Query("SELECT p FROM Salutation p WHERE p.salutationName = ?1")
   public Salutation findBySalutationName(String salutation);

}
