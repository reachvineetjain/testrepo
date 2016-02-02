/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AddendumDocumentInformation;

/**
 * @author ravi
 *
 */
@Repository
public interface AddendumDocumentInformationRepository extends JpaRepository<AddendumDocumentInformation, Integer> {

}
