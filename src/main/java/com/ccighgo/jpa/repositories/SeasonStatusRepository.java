package com.ccighgo.jpa.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ccighgo.db.entities.SeasonStatus;
/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonStatusRepository extends JpaRepository<SeasonStatus,Integer> {

}
