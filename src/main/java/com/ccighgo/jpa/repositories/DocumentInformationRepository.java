/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DocumentInformation;

/**
 * @author ravi
 *
 */
@Repository
public interface DocumentInformationRepository extends JpaRepository<DocumentInformation, Integer> {

}
