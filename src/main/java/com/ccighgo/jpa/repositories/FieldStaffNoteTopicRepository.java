package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffNoteTopic;

/**
 * @author sinshaw.demisse
 *
 */
@Repository
public interface FieldStaffNoteTopicRepository extends JpaRepository<FieldStaffNoteTopic, Integer>{
  
   @Query("select t from FieldStaffNoteTopic t where t.fieldStaff.fieldStaffGoId = ?1")
   List<FieldStaffNoteTopic> listTopicsByFieldStaffId(int fieldStaffGoId);

}
