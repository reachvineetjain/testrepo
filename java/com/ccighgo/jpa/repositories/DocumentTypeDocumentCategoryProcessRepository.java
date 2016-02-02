/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DocumentTypeDocumentCategoryProcess;

/**
 * @author ravi
 *
 */
@Repository
public interface DocumentTypeDocumentCategoryProcessRepository extends JpaRepository<DocumentTypeDocumentCategoryProcess, Integer> {
   
   @Query("SELECT d FROM DocumentTypeDocumentCategoryProcess d WHERE d.documentType.documentTypeName = ?1")
   public DocumentTypeDocumentCategoryProcess findByDocumentType(String docType);

}
