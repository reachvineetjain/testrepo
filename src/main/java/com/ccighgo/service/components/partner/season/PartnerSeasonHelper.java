package com.ccighgo.service.components.partner.season;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Creator;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Note;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Topic;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

@Component
public class PartnerSeasonHelper {
   
   @Autowired LoginRepository loginRepository;
   @Autowired PartnerNoteTopicRepository partnerNoteTopicRepository;
   
   /**
    * Method returns note topics and notes associated with j1 program
    * 
    * @param partnerGoId
    * @return
    */
   public com.ccighgo.service.transport.partner.beans.partnerseasondetail.NoteTopics getJ1Notes(String partnerGoId) {
      com.ccighgo.service.transport.partner.beans.partnerseasondetail.NoteTopics partnerSeasonNotes = null;
      List<PartnerNoteTopic> partnerNoteTopicsList = partnerNoteTopicRepository.findByPartnerGoId(Integer.valueOf(partnerGoId));
      if (partnerNoteTopicsList != null) {
         partnerSeasonNotes = new com.ccighgo.service.transport.partner.beans.partnerseasondetail.NoteTopics();
         partnerSeasonNotes.setTopicCount(partnerNoteTopicsList.size());
         List<Topic> topicList = new ArrayList<Topic>();
         for (PartnerNoteTopic pnt : partnerNoteTopicsList) {
            Topic topic = new Topic();
            topic.setTopicId(pnt.getPartnerNoteTopicId());
            topic.setTopicTitle(pnt.getPartnerNoteTopicName());
            List<Note> notesList = null;
            List<PartnerNote> partNoteList = pnt.getPartnerNotes();
            if (partNoteList != null) {
               notesList = new ArrayList<Note>();
               for (PartnerNote pn : partNoteList) {
                  Note note = new Note();
                  note.setNoteId(pn.getPartnerNotesId());
                  note.setTopicId(pnt.getPartnerNoteTopicId());
                  note.setNote(pn.getPartnerNote());
                  Creator noteCreator = new Creator();
                  Login login = loginRepository.findOne(pn.getCreatedBy());
                  if (login != null && login.getActive() == CCIConstants.ACTIVE) {
                     for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
                        if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                           noteCreator.setCreatedBy(login.getGoIdSequence().getCcistaffUser().getFirstName() + " " + login.getGoIdSequence().getCcistaffUser().getLastName());
                           noteCreator.setCreatedByPicUrl(login.getGoIdSequence().getCcistaffUser().getPhoto());
                           noteCreator.setDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                        }
                        if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                           Partner partner = login.getGoIdSequence().getPartner();
                           if (partner != null) {
                              List<PartnerUser> partnerUserslist = partner.getPartnerUsers();
                              if (partnerUserslist != null) {
                                 for (PartnerUser pu : partnerUserslist) {
                                    if (pu.getLogin().getLoginId() == login.getLoginId()) {
                                       noteCreator.setCreatedBy(pu.getFirstName() + " " + pu.getLastName());
                                       noteCreator.setCreatedByPicUrl(pu.getPhoto());
                                       noteCreator.setDesignation(pu.getTitle());
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
                  note.setCreator(noteCreator);
                  note.setTimestamp(DateUtils.getTimestamp(pn.getCreatedOn()));
                  notesList.add(note);
               }
            }
            topic.getNotes().addAll(notesList);
            topicList.add(topic);
         }
      }
      return partnerSeasonNotes;
   }
   
   /**
    * @param partnerGoId
    * @return
    */
   public com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.NoteTopics getF1ProgramNotes(String partnerGoId) {
      com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.NoteTopics partnerSeasonNotes = null;
      List<PartnerNoteTopic> partnerNoteTopicsList = partnerNoteTopicRepository.findByPartnerGoId(Integer.valueOf(partnerGoId));
      if (partnerNoteTopicsList != null) {
         partnerSeasonNotes = new com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.NoteTopics();
         partnerSeasonNotes.setTopicCount(partnerNoteTopicsList.size());
         List<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Topic> topicList = new ArrayList<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Topic>();
         for (PartnerNoteTopic pnt : partnerNoteTopicsList) {
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Topic topic = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Topic();
            topic.setTopicId(pnt.getPartnerNoteTopicId());
            topic.setTopicTitle(pnt.getPartnerNoteTopicName());
            List<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Note> notesList = null;
            List<PartnerNote> partNoteList = pnt.getPartnerNotes();
            if (partNoteList != null) {
               notesList = new ArrayList<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Note>();
               for (PartnerNote pn : partNoteList) {
                  com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Note note = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Note();
                  note.setNoteId(pn.getPartnerNotesId());
                  note.setTopicId(pnt.getPartnerNoteTopicId());
                  note.setNote(pn.getPartnerNote());
                  com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Creator noteCreator = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Creator();
                  Login login = loginRepository.findOne(pn.getCreatedBy());
                  if (login != null && login.getActive() == CCIConstants.ACTIVE) {
                     for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
                        if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                           noteCreator.setCreatedBy(login.getGoIdSequence().getCcistaffUser().getFirstName() + " " + login.getGoIdSequence().getCcistaffUser().getLastName());
                           noteCreator.setCreatedByPicUrl(login.getGoIdSequence().getCcistaffUser().getPhoto());
                           noteCreator.setDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                        }
                        if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                           Partner partner = login.getGoIdSequence().getPartner();
                           if (partner != null) {
                              List<PartnerUser> partnerUserslist = partner.getPartnerUsers();
                              if (partnerUserslist != null) {
                                 for (PartnerUser pu : partnerUserslist) {
                                    if (pu.getLogin().getLoginId() == login.getLoginId()) {
                                       noteCreator.setCreatedBy(pu.getFirstName() + " " + pu.getLastName());
                                       noteCreator.setCreatedByPicUrl(pu.getPhoto());
                                       noteCreator.setDesignation(pu.getTitle());
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
                  note.setCreator(noteCreator);
                  note.setTimestamp(DateUtils.getTimestamp(pn.getCreatedOn()));
                  notesList.add(note);
               }
            }
            topic.getNotes().addAll(notesList);
            topicList.add(topic);
         }
      }
      return partnerSeasonNotes;
   }

   /**
    * @param partnerGoId
    * @return
    */
   public com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.NoteTopics getIHPProgramNotes(String partnerGoId) {
      com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.NoteTopics partnerSeasonNotes = null;
      List<PartnerNoteTopic> partnerNoteTopicsList = partnerNoteTopicRepository.findByPartnerGoId(Integer.valueOf(partnerGoId));
      if (partnerNoteTopicsList != null) {
         partnerSeasonNotes = new com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.NoteTopics();
         partnerSeasonNotes.setTopicCount(partnerNoteTopicsList.size());
         List<com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Topic> topicList = new ArrayList<com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Topic>();
         for (PartnerNoteTopic pnt : partnerNoteTopicsList) {
            com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Topic topic = new com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Topic();
            topic.setTopicId(pnt.getPartnerNoteTopicId());
            topic.setTopicTitle(pnt.getPartnerNoteTopicName());
            List<com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Note> notesList = null;
            List<PartnerNote> partNoteList = pnt.getPartnerNotes();
            if (partNoteList != null) {
               notesList = new ArrayList<com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Note>();
               for (PartnerNote pn : partNoteList) {
                  com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Note note = new com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Note();
                  note.setNoteId(pn.getPartnerNotesId());
                  note.setTopicId(pnt.getPartnerNoteTopicId());
                  note.setNote(pn.getPartnerNote());
                  com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Creator noteCreator = new com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Creator();
                  Login login = loginRepository.findOne(pn.getCreatedBy());
                  if (login != null && login.getActive() == CCIConstants.ACTIVE) {
                     for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
                        if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                           noteCreator.setCreatedBy(login.getGoIdSequence().getCcistaffUser().getFirstName() + " " + login.getGoIdSequence().getCcistaffUser().getLastName());
                           noteCreator.setCreatedByPicUrl(login.getGoIdSequence().getCcistaffUser().getPhoto());
                           noteCreator.setDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                        }
                        if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                           Partner partner = login.getGoIdSequence().getPartner();
                           if (partner != null) {
                              List<PartnerUser> partnerUserslist = partner.getPartnerUsers();
                              if (partnerUserslist != null) {
                                 for (PartnerUser pu : partnerUserslist) {
                                    if (pu.getLogin().getLoginId() == login.getLoginId()) {
                                       noteCreator.setCreatedBy(pu.getFirstName() + " " + pu.getLastName());
                                       noteCreator.setCreatedByPicUrl(pu.getPhoto());
                                       noteCreator.setDesignation(pu.getTitle());
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
                  note.setCreator(noteCreator);
                  note.setTimestamp(DateUtils.getTimestamp(pn.getCreatedOn()));
                  notesList.add(note);
               }
            }
            topic.getNotes().addAll(notesList);
            topicList.add(topic);
         }
      }
      return partnerSeasonNotes;
   }



}
