package com.ccighgo.service.components.generic.notes.fieldstaffs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffNoteTopic;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffNoteRepository;
import com.ccighgo.jpa.repositories.FieldStaffNoteTopicRepository;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.GenericMessageConstants;
import com.ccighgo.service.components.generic.document.GenericDocumentsInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffNote;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffTopic;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffTopics;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.UploadedByUser;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.reuse.function.ReusedFunctions;
import com.ccighgo.utils.reuse.function.pojo.UserInformationOfCreatedBy;

@Component
public class FieldStaffGenericNoteImpl implements FieldStaffGenericNoteInterface {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(GenericDocumentsInterface.class);;

   @Autowired
   CommonComponentUtils componentUtils;

   @Autowired
   MessageUtils messageUtil;

   @Autowired
   FieldStaffNoteTopicRepository fieldStaffNoteTopicRepository;

   @Autowired
   FieldStaffRepository fieldStaffRepository;

   @Autowired
   FieldStaffNoteRepository fieldStaffNoteRepository;

   @Autowired
   ReusedFunctions reusedFunctions;

   @Override
   public Response addNotes(FieldStaffNote note) {
      Response response = new Response();
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
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.CREATE_FIELD_STAFF_NOTE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CREATE_FIELD_STAFF_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_CREATE_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_CREATE_GENERIC_NOTE));
      }
      return response;
   }

   @Override
   public FieldStaffTopics viewTopics(int fieldStaffGoId) {

      FieldStaffTopics fieldStaffTopics = new FieldStaffTopics();
      try
      {
      List<FieldStaffNoteTopic> topics = fieldStaffNoteTopicRepository.listTopicsByFieldStaffId(fieldStaffGoId);
      if (topics == null || topics.size()<=0) {
         fieldStaffTopics.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.VIEW_FIELD_STAFF_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE)));
         return fieldStaffTopics;
      }
      for (FieldStaffNoteTopic t : topics) {
         FieldStaffTopic topic = new FieldStaffTopic();
         topic.setFieldStaffNoteTopicId(t.getFieldStaffNoteTopicsId());
         topic.setFieldStaffNoteTopicName(t.getFieldStaffNoteTopicName());
         topic.setFieldStaffGoId(t.getFieldStaff().getFieldStaffGoId());
         topic.setPublic(t.getIsPublic() == CCIConstants.ACTIVE);
         topic.setTitle(t.getTitle());
         UserInformationOfCreatedBy userInformation = reusedFunctions.getPartnerCreatedByInformation(t.getCreatedBy());
         if (userInformation != null) {
            UploadedByUser user = new UploadedByUser();
            user.setPhotoUrl(userInformation.getPhotoUrl());
            user.setRole(userInformation.getRole());
            user.setUserName(userInformation.getUserName());
            topic.setUploadBy(user);
         }
         topic.setCreatedOn(DateUtils.getDateAndTime(t.getCreatedOn()));
         List<com.ccighgo.db.entities.FieldStaffNote> notes = t.getFieldStaffNotes();
         if (notes != null) {
            for (com.ccighgo.db.entities.FieldStaffNote n : notes) {
               FieldStaffNote note = new FieldStaffNote();
               note.setFieldStaffNoteId(n.getFieldStaffNoteId());
               note.setFieldStaffGoId(n.getFieldStaff().getFieldStaffGoId());
               note.setFieldStaffNoteTopicId(n.getFieldStaffNoteTopic().getFieldStaffNoteTopicsId());;
               note.setNoteValue(n.getFieldStaffNote());
               note.setRead(n.getHasRead() == CCIConstants.ACTIVE);
               note.setCreatedOn(DateUtils.getDateAndTime(n.getCreatedOn()));
               UserInformationOfCreatedBy userInfo = reusedFunctions.getPartnerCreatedByInformation(n.getCreatedBy());
               if (userInfo != null) {
                  UploadedByUser user = new UploadedByUser();
                  user.setPhotoUrl(userInfo.getPhotoUrl());
                  user.setRole(userInfo.getRole());
                  user.setUserName(userInfo.getUserName());
                  note.setUploadBy(user);
               }
               topic.getFieldStaffNotes().add(note);
            }
         }
         fieldStaffTopics.getTopics().add(topic);
      }
      fieldStaffTopics.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.VIEW_FIELD_STAFF_NOTE.getValue(),
            messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      }catch(Exception e)
      {
         fieldStaffTopics.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.VIEW_FIELD_STAFF_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE));
         
      }
      return fieldStaffTopics;
   }

   @Override
   public Response updateNotes(FieldStaffNote note) {
      Response respons = new Response();
      try {
         com.ccighgo.db.entities.FieldStaffNote fieldStaffNote = fieldStaffNoteRepository.findOne(note.getFieldStaffNoteId());
         if (fieldStaffNote != null) {
            FieldStaffNoteTopic topic = fieldStaffNoteTopicRepository.findOne(note.getFieldStaffNoteTopicId());
            FieldStaff fieldStaff = fieldStaffRepository.findOne(note.getFieldStaffGoId());
            fieldStaffNote.setFieldStaffNoteTopic(topic);
            fieldStaffNote.setFieldStaff(fieldStaff);
            fieldStaffNote.setFieldStaffNote(note.getNoteValue());
            // TODO
            // fieldStaffNote.setHasRead(CCIConstants.INACTIVE);
            fieldStaffNote.setModifiedBy(note.getLoginId());
            fieldStaffNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            fieldStaffNoteRepository.saveAndFlush(fieldStaffNote);
            respons.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UPDATE_FIELD_STAFF_NOTE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (Exception e) {
         respons.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_FIELD_STAFF_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDEAT_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDEAT_GENERIC_NOTE));
      }
      return respons;
   }

   @Override
   public Response removeNote(int noteId) {
      Response response = new Response();
      try
      {
      fieldStaffNoteRepository.delete(noteId);
      fieldStaffNoteRepository.flush();
      response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DELETE_FIELD_STAFF_NOTE.getValue(),
            messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      }catch(Exception e)
      {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_FIELD_STAFF_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_NOTE));
      }
      return response;
   }

}
