/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.LookupGender;

/**
 * @author ravi
 *
 */
@Repository
public interface GenderRepository extends JpaRepository<LookupGender, Integer> {

}
