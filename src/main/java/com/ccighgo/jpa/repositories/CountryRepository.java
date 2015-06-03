/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Country;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
