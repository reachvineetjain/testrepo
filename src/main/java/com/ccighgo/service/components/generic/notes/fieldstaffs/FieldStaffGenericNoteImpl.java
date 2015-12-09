package com.ccighgo.service.components.generic.notes.fieldstaffs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffNoteTopic;
import com.ccighgo.jpa.repositories.FieldStaffNoteRepository;
import com.ccighgo.jpa.repositories.FieldStaffNoteTopicRepository;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffNote;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffTopics;
import com.ccighgo.utils.CCIConstants;

@Component
public class FieldStaffGenericNoteImpl implements FieldStaffGenericNoteInterface {

   @Autowired
   FieldStaffNoteTopicRepository fieldStaffNoteTopicRepository;

   @Autowired
   FieldStaffRepository fieldStaffRepository;

   @Autowired
   FieldStaffNoteRepository fieldStaffNoteRepository;

   @Override
   public Response addNotes(FieldStaffNote note) {
      Response respons = new Response();
      try {
         com.ccighgo.db.entities.FieldStaffNote fieldStaffNote = new com.ccighgo.db.entities.FieldStaffNote();
         FieldStaffNoteTopic topic = fieldStaffNoteTopicRepository.findOne(note.getFieldStaffNoteTopicId());
         FieldStaff fieldStaff = fieldStaffRepository.findOne(note.getFieldStaffGoId());
         fieldStaffNote.setFieldStaffNoteTopic(topic);
         fieldStaffNote.setFieldStaff(fieldStaff);
         fieldStaffNote.setFieldStaffNote(note.getNoteValue());
         fieldStaffNote.setHasRead(CCIConstants.INACTIVE);
         fieldStaffNote.setCreatedBy(note.getLoginId());
         fieldStaffNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         fieldStaffNote.setModifiedBy(note.getLoginId());
         fieldStaffNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         fieldStaffNoteRepository.saveAndFlush(fieldStaffNote);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return respons;
   }

   @Override
   public FieldStaffTopics viewTopics(int fieldStaffGoId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Response updateNotes(FieldStaffNote note) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Response removeNote(int noteId) {
      Response response = new Response();
      fieldStaffNoteRepository.delete(noteId);
      return response;
   }

}
