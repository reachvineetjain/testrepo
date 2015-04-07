/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciProgram;

/**
 * @author ravimishra
 *
 */
@Repository
public interface ProgramRepository extends JpaRepository<CciProgram, Integer> {

}
