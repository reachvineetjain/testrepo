/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonDepartmentDocument;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonDepartmentDocumentRepository extends JpaRepository<SeasonDepartmentDocument, Integer> {

}
