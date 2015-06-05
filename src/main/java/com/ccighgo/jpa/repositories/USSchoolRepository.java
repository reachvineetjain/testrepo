package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.USSchool;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface USSchoolRepository extends JpaRepository<USSchool, Integer> {

}
