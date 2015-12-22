package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffNote;

/**
 * @author sinshaw.demisse
 *
 */
@Repository
public interface FieldStaffNoteRepository extends JpaRepository<FieldStaffNote, Integer> {
   
   @Query("select n from FieldStaffNote n where n.fieldStaffNoteTopic.fieldStaffNoteTopicsId = ?1")
   List<FieldStaffNote> fieldStaffNoteByTopic(int topicId);
}
