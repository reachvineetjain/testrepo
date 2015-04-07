package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciDepartmentalFunction;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentalFunctionRepository extends JpaRepository<CciDepartmentalFunction, Integer> {

}
