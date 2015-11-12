package com.ccighgo.service.component.partner.generic;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.GenericMessageConstants;
import com.ccighgo.service.components.partner.subpartner.SubPartnerInterfaceImpl;
import com.ccighgo.service.transport.partner.beans.generic.deletenote.DeleteNote;
import com.ccighgo.service.transport.partner.beans.generic.notes.ScreenNote;
import com.ccighgo.service.transport.partner.beans.generic.topic.NoteUserCreator;
import com.ccighgo.service.transport.partner.beans.generic.topic.SubPartnerScreeningNotes;
import com.ccighgo.service.transport.partner.beans.generic.topic.Topic;
import com.ccighgo.service.transport.partner.beans.generic.topic.TopicUserCreator;
import com.ccighgo.service.transport.partner.beans.generic.topic.Topics;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;
import com.ccighgo.utils.reuse.function.ReusedFunctions;
import com.ccighgo.utils.reuse.function.pojo.UserInformationOfCreatedBy;

@Component
public class PartnerGenericNote implements PartnerGenericNoteInterface {

   private static final Logger LOGGER = Logger.getLogger(SubPartnerInterfaceImpl.class);

   @Autowired
   CommonComponentUtils componentUtils;

   @Autowired
   PartnerRepository partnerRepository;

   @Autowired
   PartnerNoteRepository partnerNoteRepository;

   @Autowired
   PartnerNoteTopicRepository partnerNoteTopicRepository;

   @Autowired
   MessageUtils messageUtil;

   @Autowired
   PartnerUserRepository partnerUserRepository;
   @Autowired
   LoginRepository loginRepository;
   @Autowired
   ReusedFunctions reusedFunctions;

   @Override
   public WSDefaultResponse addNote(ScreenNote note) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         PartnerNote noteEntity = new PartnerNote();
         noteEntity.setCreatedBy(note.getLoginId());
         noteEntity.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         noteEntity.setModifiedBy(note.getLoginId());

         noteEntity.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         Partner partner = partnerRepository.findOne(note.getPartnerId());
         noteEntity.setPartner(partner);
         noteEntity.setPartnerNote(note.getNoteValue());

         PartnerNoteTopic partnerNoteTopic = partnerNoteTopicRepository.findOne(note.getTopicId());
         noteEntity.setPartnerNoteTopic(partnerNoteTopic);

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NOTE_CREATED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

         partnerNoteRepository.saveAndFlush(noteEntity);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_CREATE_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_CREATE_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_CREATE_GENERIC_NOTE));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse deleteNote(DeleteNote deleteNote) {
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         partnerNoteRepository.delete(deleteNote.getNoteId());
         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NOTE_DELETED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_CREATE_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE));
      }
      return responce;
   }

   @Override
   public Topics viewTopics(int partnerId) {
      Topics topicsList = new Topics();
      try {
         List<PartnerNoteTopic> partnerTopics = partnerNoteTopicRepository.findAllPartnerNoteTopicByPartnerId(Integer.valueOf(partnerId));
         if (partnerTopics == null) {
            topicsList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_VIEW_NOTE.getValue(),
                  messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE)));
            LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE));
            return topicsList;
         }
         if (partnerTopics != null) {
            for (PartnerNoteTopic partnerTopic : partnerTopics) {
               Topic tpc = new Topic();
               tpc.setPartnerNoteTopicName(partnerTopic.getPartnerNoteTopicName());
               tpc.setPartnerNoteTopicId(partnerTopic.getPartnerNoteTopicId());
               tpc.setCompetitorInfo(partnerTopic.getCompetitorInfo() == CCIConstants.ACTIVE ? true : false);
               tpc.setEmbassyVisaInfo(partnerTopic.getEmbassy_VisaInfo() == CCIConstants.ACTIVE ? true : false);
               tpc.setIsPublic(partnerTopic.getIsPublic() == CCIConstants.ACTIVE ? true : false);
               tpc.setWT(partnerTopic.getW_t() == CCIConstants.ACTIVE ? true : false);
               tpc.setJ1(partnerTopic.getJ1() == CCIConstants.ACTIVE ? true : false);
               tpc.setGht(partnerTopic.getGht() == CCIConstants.ACTIVE ? true : false);
               tpc.setStInbound(partnerTopic.getStInbound() == CCIConstants.ACTIVE ? true : false);
               tpc.setMeetingVisit(partnerTopic.getMeeting_visit() == CCIConstants.ACTIVE ? true : false);
               tpc.setSeasonInfo(partnerTopic.getSeasonInfo() == CCIConstants.ACTIVE ? true : false);
               tpc.setF1(partnerTopic.getF1() == CCIConstants.ACTIVE ? true : false);
               tpc.setIntern(partnerTopic.getIntern() == CCIConstants.ACTIVE ? true : false);
               tpc.setTrainee(partnerTopic.getTrainee() == CCIConstants.ACTIVE ? true : false);
               List<PartnerNote> partnerNotes = partnerTopic.getPartnerNotes();
               if (partnerNotes != null) {
                  for (PartnerNote partnerNote : partnerNotes) {
                     SubPartnerScreeningNotes note = new SubPartnerScreeningNotes();
                     UserInformationOfCreatedBy userInformationOfCreatedBy = reusedFunctions.getPartnerCreatedByInformation(partnerNote.getCreatedBy());
                     if (userInformationOfCreatedBy != null) {
                        NoteUserCreator noteCreator = new NoteUserCreator();
                        noteCreator.setPhotoUrl(userInformationOfCreatedBy.getPhotoUrl());
                        noteCreator.setRole(userInformationOfCreatedBy.getRole());
                        noteCreator.setUserName(userInformationOfCreatedBy.getUserName());
                        note.setCreatedBy(noteCreator);
                        note.setUserId(userInformationOfCreatedBy.getUserId());
                     }
                     note.setNoteId(partnerNote.getPartnerNotesId());
                     note.setCreatedOn(DateUtils.getDateAndTime(partnerNote.getCreatedOn()));
                     note.setNoteValue(partnerNote.getPartnerNote());
                     note.setTopicId(tpc.getPartnerNoteTopicId());
                     note.setPartnerId(Integer.valueOf(partnerId));
                     tpc.getPartnerNotes().add(note);
                  }
               }
               if (partnerTopic.getCreatedBy() != null) {
                  UserInformationOfCreatedBy userInformationOfCreatedBy = reusedFunctions.getPartnerCreatedByInformation(partnerTopic.getCreatedBy());
                  if (userInformationOfCreatedBy != null) {
                     TopicUserCreator topicCreator = new TopicUserCreator();
                     topicCreator.setPhotoUrl(userInformationOfCreatedBy.getPhotoUrl());
                     topicCreator.setRole(userInformationOfCreatedBy.getRole());
                     topicCreator.setUserName(userInformationOfCreatedBy.getUserName());
                     tpc.setCreatedBy(topicCreator);
                  }
               }
               topicsList.getTopics().add(tpc);
            }
         }
         topicsList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NOTE_VIEW.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         topicsList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_VIEW_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_NOTE));
      }
      return topicsList;
   }

   @Override
   public WSDefaultResponse tagTopic(Topic topic) {
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         PartnerNoteTopic topicData = partnerNoteTopicRepository.findOne(topic.getPartnerNoteTopicId());
         if (topic.isCompetitorInfo() != null) {
            topicData.setCompetitorInfo(topic.isCompetitorInfo() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isEmbassyVisaInfo() != null) {
            topicData.setEmbassy_VisaInfo(topic.isEmbassyVisaInfo() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isF1() != null) {
            topicData.setF1(topic.isF1() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isGht() != null) {
            topicData.setGht(topic.isGht() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isIntern() != null) {
            topicData.setIntern(topic.isIntern() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isIsPublic() != null) {
            topicData.setIsPublic(topic.isIsPublic() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isJ1() != null) {
            topicData.setJ1(topic.isJ1() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isMeetingVisit() != null) {
            topicData.setMeeting_visit(topic.isMeetingVisit() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isSeasonInfo() != null) {
            topicData.setSeasonInfo(topic.isSeasonInfo() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isStInbound() != null) {
            topicData.setStInbound(topic.isStInbound() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isTrainee() != null) {
            topicData.setTrainee(topic.isTrainee() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isWT() != null) {
            topicData.setW_t(topic.isWT() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }

         partnerNoteTopicRepository.saveAndFlush(topicData);
         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UPDATE_TOPIC_TAG.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_TOPIC_TAG.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_UPDATE_TOPIC_TAG)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_UPDATE_TOPIC_TAG));
      }
      return responce;
   }
   @Override
   public WSDefaultResponse createTopic(Topic topic) {
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         PartnerNoteTopic topicData = new PartnerNoteTopic();
         Partner partner = partnerRepository.findOne(topic.getGoId());
         topicData.setPartner(partner );
         topicData.setPartnerNoteTopicName(topic.getPartnerNoteTopicName());
         topicData.setCreatedBy(topic.getLoginId());
         if (topic.isCompetitorInfo() != null) {
            topicData.setCompetitorInfo(topic.isCompetitorInfo() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isEmbassyVisaInfo() != null) {
            topicData.setEmbassy_VisaInfo(topic.isEmbassyVisaInfo() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isF1() != null) {
            topicData.setF1(topic.isF1() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isGht() != null) {
            topicData.setGht(topic.isGht() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isIntern() != null) {
            topicData.setIntern(topic.isIntern() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isIsPublic() != null) {
            topicData.setIsPublic(topic.isIsPublic() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isJ1() != null) {
            topicData.setJ1(topic.isJ1() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isMeetingVisit() != null) {
            topicData.setMeeting_visit(topic.isMeetingVisit() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isSeasonInfo() != null) {
            topicData.setSeasonInfo(topic.isSeasonInfo() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isStInbound() != null) {
            topicData.setStInbound(topic.isStInbound() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isTrainee() != null) {
            topicData.setTrainee(topic.isTrainee() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }
         if (topic.isWT() != null) {
            topicData.setW_t(topic.isWT() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         }

         PartnerNoteTopic result = partnerNoteTopicRepository.saveAndFlush(topicData);
         responce.setTopicOfNotesId(result.getPartnerNoteTopicId());
         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.CREATE_TOPIC.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CREATE_TOPIC.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_CREATE_TOPIC)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_CREATE_TOPIC));
      }
      return responce;
   }

}
