package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciDepartments;
/**
 * 
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentsRepository extends JpaRepository<CciDepartments, Integer> {

}
