/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DocumentType;

/**
 * @author ravi
 *
 */
@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {

}
