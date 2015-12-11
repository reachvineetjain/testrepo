package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffDocument;

/**
 * @author sinshaw.demisse
 *
 */

@Repository
public interface FieldStaffDocumentRepository extends JpaRepository<FieldStaffDocument, Integer> {

   @Query("select fd from FieldStaffDocument fd where fd.fieldStaff.fieldStaffGoId = ?1")
   List<FieldStaffDocument> getFieldStaffDocumentsByFieldStaffGoId(int fieldStaffGoId);
}
