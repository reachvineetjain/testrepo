package com.ccighgo.service.component.partner.generic;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonNote;
import com.ccighgo.db.entities.PartnerSeasonNoteTopic;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.PartnerCodes;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonNoteRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
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

   @Autowired CommonComponentUtils componentUtils;

   @Autowired PartnerRepository partnerRepository;

   @Autowired PartnerNoteRepository partnerNoteRepository;

   @Autowired PartnerNoteTopicRepository partnerNoteTopicRepository;

   @Autowired MessageUtils messageUtil;

   @Autowired PartnerUserRepository partnerUserRepository;
   @Autowired LoginRepository loginRepository;
   @Autowired ReusedFunctions reusedFunctions;
   @Autowired PartnerSeasonNoteTopicRepository partnerSeasonNoteTopicRepository;
   @Autowired PartnerSeasonNoteRepository partnerSeasonNoteRepository;
   @Autowired PartnerSeasonsRepository partnerSeasonsRepository;

   @Override
   public WSDefaultResponse addNote(ScreenNote note) {
      try {
         if (note != null)
            LOGGER.info("noteId: " + note.getNoteId() + " noteValue: " + note.getNoteValue() + "_public: " + note.isPublic() + " createdOn: " + note.getCreatedOn() + " loginId: "
                  + note.getLoginId() + " topicId: " + note.getLoginId() + " partnerId: " + note.getPartnerId());
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         PartnerNote noteEntity = new PartnerNote();
         noteEntity.setCreatedBy(note.getLoginId());
         if (note.getCreatedOn() == null) {
            note.setCreatedOn(DateUtils.getDateAndTime2(new Date()));
         }
         noteEntity.setCreatedOn(new java.sql.Timestamp(DateUtils.getMysqlDateFromStringFormatwithSlash(note.getCreatedOn()).getTime()));
         noteEntity.setModifiedOn(new java.sql.Timestamp(DateUtils.getMysqlDateFromStringFormatwithSlash(note.getCreatedOn()).getTime()));
         noteEntity.setModifiedBy(note.getLoginId());

         Partner partner = partnerRepository.findOne(note.getPartnerId());
         noteEntity.setPartner(partner);
         noteEntity.setPartnerNote(note.getNoteValue());

         PartnerNoteTopic partnerNoteTopic = partnerNoteTopicRepository.findOne(note.getTopicId());
         noteEntity.setPartnerNoteTopic(partnerNoteTopic);

         partnerNoteRepository.saveAndFlush(noteEntity);
         UserInformationOfCreatedBy userInformationOfCreatedBy = reusedFunctions.getPartnerCreatedByInformation(note.getLoginId());
         if (userInformationOfCreatedBy != null) {
            NoteUserCreator noteCreator = new NoteUserCreator();
            noteCreator.setPhotoUrl(userInformationOfCreatedBy.getPhotoUrl());
            noteCreator.setRole(userInformationOfCreatedBy.getRole());
            noteCreator.setUserName(userInformationOfCreatedBy.getUserName());
            wsDefaultResponse.setCreatedBy(noteCreator);
         }

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_TO_CREATE_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_CREATE_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_CREATE_GENERIC_NOTE));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse deleteNote(DeleteNote deleteNote) {
      try {
         if (deleteNote != null)
            LOGGER.info("noteId: " + deleteNote.getNoteId());
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         partnerNoteRepository.delete(deleteNote.getNoteId());
         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_TO_DELETE_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_NOTE));
      }
      return responce;
   }

   @Override
   public Topics viewTopics(int partnerId) {
      try {
         LOGGER.info("partnerId: " + partnerId);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      Topics topicsList = new Topics();
      try {
         List<PartnerNoteTopic> partnerTopics = partnerNoteTopicRepository.findAllPartnerNoteTopicByPartnerId(Integer.valueOf(partnerId));
         if (partnerTopics == null) {
            topicsList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_TO_VIEW_NOTE.getValue(),
                  messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE)));
            LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE));
            return topicsList;
         }
         if (partnerTopics != null) {
            for (PartnerNoteTopic partnerTopic : partnerTopics) {
               Topic tpc = new Topic();
               tpc.setLoginId(partnerTopic.getCreatedBy());
               tpc.setGoId(partnerTopic.getPartner().getGoIdSequence().getGoId());
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
      try {
         if (topic != null)
            LOGGER.info("loginId: " + topic.getLoginId() + "isTopicPublic: " + topic.isIsTopicPublic() + "goId: " + topic.getGoId() + "partnerNoteTopicId: "
                  + topic.getPartnerNoteTopicId() + "partnerNoteTopicName: " + topic.getPartnerNoteTopicName() + "embassyVisaInfo: " + topic.isEmbassyVisaInfo() + "f1: "
                  + topic.isF1() + "ght: " + topic.isGht() + "intern: " + topic.isIntern() + "isPublic: " + topic.isIsPublic() + "j1: " + topic.isMeetingVisit()
                  + " meetingVisit: " + topic.isMeetingVisit() + "seasonInfo: " + topic.isSeasonInfo() + "stInbound: " + topic.isStInbound() + "trainee: " + topic.isTrainee()
                  + "wt: " + topic.isWT() + " createdBy: " + topic.getCreatedBy());
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
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
         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.UPDATE_TOPIC_TAG.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_UPDATE_TOPIC_TAG)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_UPDATE_TOPIC_TAG));
      }
      return responce;
   }

   @Override
   public WSDefaultResponse createTopic(Topic topic) {
      try {
         if (topic != null)
            LOGGER.info("loginId: " + topic.getLoginId() + "isTopicPublic: " + topic.isIsTopicPublic() + "goId: " + topic.getGoId() + "partnerNoteTopicId: "
                  + topic.getPartnerNoteTopicId() + "partnerNoteTopicName: " + topic.getPartnerNoteTopicName() + "embassyVisaInfo: " + topic.isEmbassyVisaInfo() + "f1: "
                  + topic.isF1() + "ght: " + topic.isGht() + "intern: " + topic.isIntern() + "isPublic: " + topic.isIsPublic() + "j1: " + topic.isMeetingVisit()
                  + " meetingVisit: " + topic.isMeetingVisit() + "seasonInfo: " + topic.isSeasonInfo() + "stInbound: " + topic.isStInbound() + "trainee: " + topic.isTrainee()
                  + "wt: " + topic.isWT() + " createdBy: " + topic.getCreatedBy());
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         PartnerNoteTopic topicData = new PartnerNoteTopic();
         Partner partner = partnerRepository.findOne(topic.getGoId());
         topicData.setPartner(partner);
         topicData.setPartnerNoteTopicName(topic.getPartnerNoteTopicName());
         topicData.setIsPublic(topic.isIsPublic() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         topicData.setIsVisibleToPartner(CCIConstants.TRUE_BYTE);
         topicData.setCreatedBy(topic.getLoginId());
         topicData.setModifiedBy(topic.getLoginId());
         topicData.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);

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
         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_CREATE_TOPIC.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_CREATE_TOPIC)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_CREATE_TOPIC));
      }
      return responce;
   }

   @Override
   public WSDefaultResponse addPartnerSeasonNote(com.ccighgo.service.transport.partner.beans.generic.partnerseason.notes.ScreenNote note) {
      try {
         if (note != null)
            LOGGER.info("noteId: " + note.getNoteId() + " noteValue: " + note.getNoteValue() + "_public: " + note.isPublic() + " createdOn: " + note.getCreatedOn() + " loginId: "
                  + note.getLoginId() + " topicId: " + note.getLoginId() + " partnerSeasonId: " + note.getPartnerSeasonId());
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         PartnerSeasonNote noteEntity = new PartnerSeasonNote();
         noteEntity.setCreatedBy(note.getLoginId());
         if (note.getCreatedOn() == null) {
            note.setCreatedOn(DateUtils.getDateAndTime2(new Date()));
         }
         noteEntity.setCreatedOn(new java.sql.Timestamp(DateUtils.getMysqlDateFromStringFormatwithSlash(note.getCreatedOn()).getTime()));
         noteEntity.setModifiedOn(new java.sql.Timestamp(DateUtils.getMysqlDateFromStringFormatwithSlash(note.getCreatedOn()).getTime()));
         noteEntity.setModifiedBy(note.getLoginId());

         noteEntity.setPartnerSeason(partnerSeasonsRepository.findOne(note.getPartnerSeasonId()));
         noteEntity.setPartnerNote(note.getNoteValue());

         PartnerSeasonNoteTopic partnerNoteTopic = partnerSeasonNoteTopicRepository.findOne(note.getTopicId());
         noteEntity.setPartnerSeasonNoteTopic(partnerNoteTopic);

         partnerSeasonNoteRepository.saveAndFlush(noteEntity);
         UserInformationOfCreatedBy userInformationOfCreatedBy = reusedFunctions.getPartnerCreatedByInformation(note.getLoginId());
         if (userInformationOfCreatedBy != null) {
            NoteUserCreator noteCreator = new NoteUserCreator();
            noteCreator.setPhotoUrl(userInformationOfCreatedBy.getPhotoUrl());
            noteCreator.setRole(userInformationOfCreatedBy.getRole());
            noteCreator.setUserName(userInformationOfCreatedBy.getUserName());
            wsDefaultResponse.setCreatedBy(noteCreator);
         }

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_TO_CREATE_PARTNER_SEASON_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_CREATE_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_CREATE_GENERIC_NOTE));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse deletePartnerSeasonNote(DeleteNote deleteNote) {
      try {
         if (deleteNote != null)
            LOGGER.info("noteId: " + deleteNote.getNoteId());
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         partnerSeasonNoteRepository.delete(deleteNote.getNoteId());
         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_TO_DELETE_PARTNER_SEASON_NOTE.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_NOTE)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_NOTE));
      }
      return responce;
   }

   @Override
   public com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.Topics viewPartnerSeasonTopics(int partnerSeasonId) {
      try {
         LOGGER.info("partnerSeasonId: " + partnerSeasonId);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.Topics topicsList = new com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.Topics();
      try {
         List<PartnerSeasonNoteTopic> partnerTopics = partnerSeasonNoteTopicRepository.findAllPartnerSeasonNoteTopicByPartnerSeasonId(Integer.valueOf(partnerSeasonId));
         if (partnerTopics == null) {
            topicsList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_TO_VIEW_PARTNER_SEASON_NOTE.getValue(),
                  messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE)));
            LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_NOTE));
            return topicsList;
         }
         if (partnerTopics != null) {
            for (PartnerSeasonNoteTopic partnerTopic : partnerTopics) {
               com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.Topic tpc = new com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.Topic();
               tpc.setLoginId(partnerTopic.getCreatedBy());
//               tpc.setGoId(partnerTopic.getPartner().getGoIdSequence().getGoId());
               tpc.setPartnerSeasonNoteTopicName(partnerTopic.getPartnerSeasonNoteTopicName());
               tpc.setPartnerSeasonNoteTopicId(partnerTopic.getPartnerSeasonNoteTopicId());
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
               tpc.setCreatedOn(DateUtils.getDateAndTime(partnerTopic.getCreatedOn()));
               List<PartnerSeasonNote> partnerSeasonNotes = partnerTopic.getPartnerSeasonNotes();
               if (partnerSeasonNotes != null) {
                  for (PartnerSeasonNote partnerSeasonNote : partnerSeasonNotes) {
                     com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.SubPartnerScreeningNotes note = new com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.SubPartnerScreeningNotes();
                     UserInformationOfCreatedBy userInformationOfCreatedBy = reusedFunctions.getPartnerCreatedByInformation(partnerSeasonNote.getCreatedBy());
                     if (userInformationOfCreatedBy != null) {
                        com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.NoteUserCreator noteCreator = new com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.NoteUserCreator();
                        noteCreator.setPhotoUrl(userInformationOfCreatedBy.getPhotoUrl());
                        noteCreator.setRole(userInformationOfCreatedBy.getRole());
                        noteCreator.setUserName(userInformationOfCreatedBy.getUserName());
                        note.setCreatedBy(noteCreator);
                        note.setUserId(userInformationOfCreatedBy.getUserId());
                     }
                     note.setNoteId(partnerSeasonNote.getPartnerSeasonNotesId());
                     note.setCreatedOn(DateUtils.getDateAndTime(partnerSeasonNote.getCreatedOn()));
                     note.setNoteValue(partnerSeasonNote.getPartnerNote());
                     note.setTopicId(tpc.getPartnerSeasonNoteTopicId());
                     note.setPartnerSeasonId(Integer.valueOf(partnerSeasonId));
                     tpc.getPartnerSeasonNotes().add(note);
                  }
               }
               if (partnerTopic.getCreatedBy() != null) {
                  UserInformationOfCreatedBy userInformationOfCreatedBy = reusedFunctions.getPartnerCreatedByInformation(partnerTopic.getCreatedBy());
                  if (userInformationOfCreatedBy != null) {
                     com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.TopicUserCreator topicCreator = new com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.TopicUserCreator();
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
   public WSDefaultResponse tagPartnerSeasonTopic(com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.Topic topic) {
      try {
         if (topic != null)
            LOGGER.info("loginId: " + topic.getLoginId() + "isTopicPublic: " + topic.isIsTopicPublic()  + "embassyVisaInfo: " + topic.isEmbassyVisaInfo() + "f1: "
                  + topic.isF1() + "ght: " + topic.isGht() + "intern: " + topic.isIntern() + "isPublic: " + topic.isIsPublic() + "j1: " + topic.isMeetingVisit()
                  + " meetingVisit: " + topic.isMeetingVisit() + "seasonInfo: " + topic.isSeasonInfo() + "stInbound: " + topic.isStInbound() + "trainee: " + topic.isTrainee()
                  + "wt: " + topic.isWT() + " createdBy: " + topic.getCreatedBy());
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         PartnerSeasonNoteTopic topicData = partnerSeasonNoteTopicRepository.findOne(topic.getPartnerSeasonNoteTopicId());
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

         partnerSeasonNoteTopicRepository.saveAndFlush(topicData);
         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.UPDATE_TOPIC_PARTNER_SEASON_TAG.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_UPDATE_TOPIC_TAG)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_UPDATE_TOPIC_TAG));
      }
      return responce;
   }

   @Override
   public WSDefaultResponse createPartnerSeasonTopic(com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.Topic topic) {
      try {
         if (topic != null)
            LOGGER.info("loginId: " + topic.getLoginId() + "isTopicPublic: " + topic.isIsTopicPublic() + "partnerNoteTopicId: "
                  + topic.getPartnerSeasonNoteTopicId() + "partnerNoteTopicName: " + topic.getPartnerSeasonNoteTopicName() + "embassyVisaInfo: " + topic.isEmbassyVisaInfo() + "f1: "
                  + topic.isF1() + "ght: " + topic.isGht() + "intern: " + topic.isIntern() + "isPublic: " + topic.isIsPublic() + "j1: " + topic.isMeetingVisit()
                  + " meetingVisit: " + topic.isMeetingVisit() + "seasonInfo: " + topic.isSeasonInfo() + "stInbound: " + topic.isStInbound() + "trainee: " + topic.isTrainee()
                  + "wt: " + topic.isWT() + " createdBy: " + topic.getCreatedBy());
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         PartnerSeasonNoteTopic topicData = new PartnerSeasonNoteTopic();
         PartnerSeason partner = partnerSeasonsRepository.findOne(topic.getPartnerSeasonId());
         topicData.setPartnerSeason(partner);
         topicData.setPartnerSeasonNoteTopicName(topic.getPartnerSeasonNoteTopicName());
         topicData.setIsPublic(topic.isIsPublic() ? CCIConstants.TRUE_BYTE : CCIConstants.FALSE_BYTE);
         topicData.setIsVisibleToPartner(CCIConstants.TRUE_BYTE);
         topicData.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         topicData.setCreatedBy(topic.getLoginId());
         topicData.setModifiedBy(topic.getLoginId());
         topicData.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);

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

         PartnerSeasonNoteTopic result = partnerSeasonNoteTopicRepository.saveAndFlush(topicData);
         responce.setTopicOfNotesId(result.getPartnerSeasonNoteTopicId());
         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_CREATE_PARTNER_SEASON_TOPIC.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_CREATE_TOPIC)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_CREATE_TOPIC));
      }
      return responce;
   }

}
