package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciDepartmentProgram;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentProgramRepository extends JpaRepository<CciDepartmentProgram, Integer> {

}
