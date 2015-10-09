/**
 * 
 */
package com.ccighgo.service.components.participants;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.LookupCountry;
import com.ccighgo.db.entities.Participant;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.Season;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramOptionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.transport.participant.beans.newparticipant.NewParticipant;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ActiveParticipant;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantApplicationStatus;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantCountry;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantPlacementStatus;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantProgramOption;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantSeason;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantSubPartner;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantType;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantsActiveList;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantLead;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantsLeadList;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;

/**
 * @author ravi
 *
 */
@Component
public class ParticipantsInterfaceImpl implements ParticipantsInterface {

   @Autowired
   CommonComponentUtils componentUtils;
   @Autowired
   MessageUtils messageUtil;
   @Autowired
   ParticipantRepository participantRepository;
   @Autowired
   DepartmentProgramRepository departmentPrograms;
   @Autowired
   DepartmentProgramOptionRepository departmentProgramOptions;
   @Autowired
   PartnerRepository partnerRepository;
   @Autowired
   CountryRepository lookupCountry;
   @Autowired
   SeasonRepository seasonRepository;
   private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ParticipantsInterfaceImpl.class);

   @Override
   public ParticipantsActiveList getActiveParticipantsList(String partnerId) {
      ParticipantsActiveList participantsActiveList = new ParticipantsActiveList();
      try {
         List<Participant> participants =null;// participantRepository.findActiveParticipantByPartnerId(partnerId);
         if (participants != null) {
            for (Participant participant : participants) {
               ParticipantProgramOption participantProgramOption = new ParticipantProgramOption();
               participantProgramOption.setParticipantProgramOptionId(participant.getDepartmentProgramOption().getDepartmentProgramOptionId());
               participantProgramOption.setParticipantProgramOption(participant.getDepartmentProgramOption().getProgramOptionName());

               ParticipantType participantType = new ParticipantType();
               participantType.setParticipantTypeId(1);
               participantType.setParticipantType("Activated");

               ParticipantSeason participantSeason = new ParticipantSeason();
               participantSeason.setParticipantSeasonId(participant.getSeason().getSeasonId());
               participantSeason.setParticipantSeasonProgramId(participant.getDepartmentProgram().getDepartmentProgramId());
               participantSeason.setParticipantSeasonProgram(participant.getDepartmentProgram().getProgramName());

               ParticipantCountry participantCountry = new ParticipantCountry();
               participantCountry.setParticipantCountryId(participant.getLookupCountry().getCountryId());
               participantCountry.setParticipantCountryCode(participant.getLookupCountry().getCountryCode());
               participantCountry.setParticipantCountry(participant.getLookupCountry().getCountryName());

               ParticipantSubPartner participantSubPartner = new ParticipantSubPartner();
               //participantSubPartner.setParticipantSubPartnerId(participant.getSubPartner());
               participantSubPartner.setPartnerGoId(participant.getParticipantGoId());
               participantSubPartner.setParticipantSubPartner("Sub Partner Dude");

               ParticipantPlacementStatus participantPlacementStatus = new ParticipantPlacementStatus();
               participantPlacementStatus.setParticipantPlacementStatusId(1);
               participantPlacementStatus.setParticipantPlacementStatus("Active");

               ParticipantApplicationStatus participantApplicationStatus = new ParticipantApplicationStatus();
               participantApplicationStatus.setParticipantApplicationStatusId(1);
               participantApplicationStatus.setParticipantApplicationStatus("Accepted");

               ActiveParticipant aPart = new ActiveParticipant();
               aPart.setParticipantId(participant.getParticipantGoId() + "");
               aPart.setParticipantEmailId(participant.getEmail());
               aPart.setParticipantStartDate(DateUtils.getDateAndTime(participant.getStartDate()));
               aPart.setParticipantEndDate(DateUtils.getDateAndTime(participant.getEndDate()));
               aPart.setParticipantFirstName(participant.getFirstName());
               aPart.setParticipantlastName(participant.getLastName());
               aPart.setParticipantPicUrl("www.google.com");
               aPart.setParticipantType(participantType);
               aPart.setParticipantSeason(participantSeason);
               aPart.setParticipantCountry(participantCountry);
               aPart.setParticipantProgramOption(participantProgramOption);
               aPart.setParticipantSubPartner(participantSubPartner);
               aPart.setParticipantPlacementStatus(participantPlacementStatus);
               aPart.setParticipantApplicationStatus(participantApplicationStatus);
               aPart.setParticipantSubmittedFlightInfo(participant.getSubmittedFlightInfo() != 0);
               aPart.setParticipantGuranteed(participant.getGuaranteed() != 0);
               participantsActiveList.getParticipants().add(aPart);
            }
            participantsActiveList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (Exception e) {
         participantsActiveList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return participantsActiveList;
   }

   @Override
   public ParticipantsLeadList getLeadParticipantsList(String partnerId) {
      ParticipantsLeadList participantsLeadList = new ParticipantsLeadList();
      try {
         List<Participant> leadParticipants =null;// participantRepository.findLeadParticipantByPartnerId(partnerId);
         participantsLeadList.setParticipantCount(leadParticipants.size());
         for (Participant participant : leadParticipants) {
            com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantType participantType = new com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantType();
            participantType.setParticipantTypeId(1);
            participantType.setParticipantType("Activated");

            com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantSeason participantSeason = new com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantSeason();
            participantSeason.setParticipantSeasonId(participant.getSeason().getSeasonId());
            participantSeason.setParticipantSeasonProgramId(participant.getDepartmentProgram().getDepartmentProgramId());
            participantSeason.setParticipantSeasonProgram(participant.getDepartmentProgram().getProgramName());

            com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantProgramOption participantProgramOption = new com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantProgramOption();
            participantProgramOption.setParticipantProgramOptionId(participant.getDepartmentProgramOption().getDepartmentProgramOptionId());
            participantProgramOption.setParticipantProgramOption(participant.getDepartmentProgramOption().getProgramOptionName());

            ParticipantLead lead = new ParticipantLead();
            lead.setParticipantId(participant.getParticipantGoId() + "");
            lead.setParticipantFirstName(participant.getFirstName());
            lead.setParticipantlastName(participant.getLastName());
            lead.setParticipantEmailId(participant.getEmail());
            lead.setParticipantPicUrl("www.google.com");
            lead.setParticipantGuranteed(participant.getGuaranteed() != 0);
            lead.setParticipantType(participantType);
            lead.setParticipantSeason(participantSeason);
            lead.setParticipantProgramOption(participantProgramOption);
            participantsLeadList.getParticipants().add(lead);
         }
         participantsLeadList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         participantsLeadList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return participantsLeadList;
   }

   @Override
   public NewParticipant addNewParticipant(NewParticipant newParticipant) {
      try {
         participantRepository.saveAndFlush(getParticipantEntity(newParticipant));
         newParticipant.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         newParticipant.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return newParticipant;
   }

   private Participant getParticipantEntity(NewParticipant newParticipant) {
      Participant participant = new Participant();
      try {
         participant.setFirstName(newParticipant.getManualMehod().getParticipantFirstName());
         participant.setEmail(newParticipant.getManualMehod().getParticipantEmailId());
         participant.setLastName(newParticipant.getManualMehod().getParticipantlastName());
         DepartmentProgramOption departmentProgramOption = departmentProgramOptions.findProgramOptionsByProgramName(newParticipant.getManualMehod().getParticipantProgramOption().getParticipantProgramOption());
         participant.setDepartmentProgramOption(departmentProgramOption);
        
//         DepartmentProgram departmentProgram= departmentPrograms.findOne(newParticipant.getManualMehod().getParticipant);
//         participant.setDepartmentProgram(departmentProgram);
         participant.setEndDate(DateUtils.getDateFromString(newParticipant.getManualMehod().getParticipantEndDate()));
         participant.setGuaranteed((byte) (newParticipant.getManualMehod().isParticipantGuranteed()?1:0));
         participant.setIsLead((byte) (newParticipant.getManualMehod().isParticipantLead()?1:0));
         LookupCountry lc = lookupCountry.findOne(newParticipant.getManualMehod().getParticipantCountry().getParticipantCountryId());
         participant.setLookupCountry(lc );
         participant.setParticipantGoId(Integer.parseInt(newParticipant.getManualMehod().getParticipantId()));
//         participant.getParticipantStatusId()(newParticipant.getManualMehod().getParticipant);
         Partner partner1 = partnerRepository.findOne(newParticipant.getManualMehod().getParticipantSubPartner().getPartnerGoId());
         participant.setPartner1(partner1 );
         Partner partner2 = partnerRepository.findOne(newParticipant.getManualMehod().getParticipantSubPartner().getParticipantSubPartnerId());
         participant.setPartner2(partner2);
         Season season=seasonRepository.findOne(newParticipant.getManualMehod().getParticipantSeason().getParticipantSeasonId());
         participant.setSeason(season);
         participant.setStartDate(DateUtils.getDateFromString(newParticipant.getManualMehod().getParticipantStartDate()));
         participant.setSubmittedFlightInfo((byte) (newParticipant.getManualMehod().isParticipantSubmittedFlightInfo()?1:0));
        // participant.setSubPartner(newParticipant.getManualMehod().getParticipantSubPartner().getParticipantSubPartnerId());
         
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return participant;
   }

   private NewParticipant getParticipantEntity(Participant participant) {
      NewParticipant newParticipant = new NewParticipant();
      try {
         com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantProgramOption participantProgramOption = new com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantProgramOption();
         participantProgramOption.setParticipantProgramOptionId(participant.getDepartmentProgramOption().getDepartmentProgramOptionId());
         participantProgramOption.setParticipantProgramOption(participant.getDepartmentProgramOption().getProgramOptionName());

         com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantType participantType = new com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantType();
         participantType.setParticipantTypeId(1);
         participantType.setParticipantType("Activated");

         com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantSeason participantSeason = new com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantSeason();
         participantSeason.setParticipantSeasonId(participant.getSeason().getSeasonId());
         participantSeason.setParticipantSeasonProgramId(participant.getDepartmentProgram().getDepartmentProgramId());
         participantSeason.setParticipantSeasonProgram(participant.getDepartmentProgram().getProgramName());

         com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantCountry participantCountry = new com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantCountry();
         participantCountry.setParticipantCountryId(participant.getLookupCountry().getCountryId());
         participantCountry.setParticipantCountryCode(participant.getLookupCountry().getCountryCode());
         participantCountry.setParticipantCountry(participant.getLookupCountry().getCountryName());

         com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantSubPartner participantSubPartner = new com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantSubPartner();
        // participantSubPartner.setParticipantSubPartnerId(participant.getSubPartner());
         participantSubPartner.setPartnerGoId(participant.getParticipantGoId());
         participantSubPartner.setParticipantSubPartner("Sub Partner Dude");

         com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantPlacementStatus participantPlacementStatus = new com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantPlacementStatus();
         participantPlacementStatus.setParticipantPlacementStatusId(1);
         participantPlacementStatus.setParticipantPlacementStatus("Active");

         com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantApplicationStatus participantApplicationStatus = new com.ccighgo.service.transport.participant.beans.newparticipant.ParticipantApplicationStatus();
         participantApplicationStatus.setParticipantApplicationStatusId(1);
         participantApplicationStatus.setParticipantApplicationStatus("Accepted");

         newParticipant.getManualMehod().setParticipantId(participant.getParticipantGoId() + "");
         newParticipant.getManualMehod().setParticipantEmailId(participant.getEmail());
         newParticipant.getManualMehod().setParticipantStartDate(DateUtils.getDateAndTime(participant.getStartDate()));
         newParticipant.getManualMehod().setParticipantEndDate(DateUtils.getDateAndTime(participant.getEndDate()));
         newParticipant.getManualMehod().setParticipantFirstName(participant.getFirstName());
         newParticipant.getManualMehod().setParticipantlastName(participant.getLastName());
         newParticipant.getManualMehod().setParticipantPicUrl("www.google.com");
         newParticipant.getManualMehod().setParticipantType(participantType);
         newParticipant.getManualMehod().setParticipantSeason(participantSeason);
         newParticipant.getManualMehod().setParticipantCountry(participantCountry);
         newParticipant.getManualMehod().setParticipantProgramOption(participantProgramOption);
         newParticipant.getManualMehod().setParticipantSubPartner(participantSubPartner);
         newParticipant.getManualMehod().setParticipantPlacementStatus(participantPlacementStatus);
         newParticipant.getManualMehod().setParticipantApplicationStatus(participantApplicationStatus);
         newParticipant.getManualMehod().setParticipantSubmittedFlightInfo(participant.getSubmittedFlightInfo() != 0);
         newParticipant.getManualMehod().setParticipantGuranteed(participant.getGuaranteed() != 0);

      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return newParticipant;
   }

   @Override
   public NewParticipant editNewParticipant(Integer participantId) {
      NewParticipant editedParticipant = new NewParticipant();
      try {
         Participant participant = participantRepository.findOne(participantId);
         editedParticipant = getParticipantEntity(participant);
         editedParticipant.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         editedParticipant.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return editedParticipant;
   }

   @Override
   public NewParticipant updateParticipant(NewParticipant participant) {
      try {
         participantRepository.saveAndFlush(getParticipantEntity(participant));
         participant.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         participant.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return participant;
   }

}
