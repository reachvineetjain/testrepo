package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Salutation;

/**
 * @author rajan
 *
 */
@Repository
public interface SalutationRepository extends JpaRepository<Salutation, Integer> {

   Salutation findBySalutationName(String salutation);

}
