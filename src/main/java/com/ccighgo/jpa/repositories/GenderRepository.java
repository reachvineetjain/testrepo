/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.LookupGender;

/**
 * @author ravi
 *
 */
@Repository
public interface GenderRepository extends JpaRepository<LookupGender, Integer> {

	@Query("SELECT f FROM LookupGender f WHERE f.genderName =?1")
	LookupGender findByName(String genderName);

}
