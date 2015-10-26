/**
 * 
 */
package com.ccighgo.service.components.participants;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.Participant;
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
import com.ccighgo.service.transport.participant.beans.availableprogramOptionsforparticipant.ProgramOptionsForParticipants;
import com.ccighgo.service.transport.participant.beans.availableprogramOptionsforparticipant.ProgramOptionsForParticipantsDetails;
import com.ccighgo.service.transport.participant.beans.availableseasonsforparticipant.SeasonsForParticipantDetails;
import com.ccighgo.service.transport.participant.beans.availableseasonsforparticipant.SeasonsForParticipants;
import com.ccighgo.service.transport.participant.beans.newmanualparticipant.AddNewManualParticipant;
import com.ccighgo.service.transport.participant.beans.newmanualparticipant.NewManualParticipant;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ActiveParticipant;
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
         // TODO
         List<Participant> participants = new ArrayList<Participant>();// participantRepository.findActiveParticipantByPartnerId(partnerId);
         if (participants != null) {
            for (Participant participant : participants) {
               com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantProgramOption participantProgramOption = new com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantProgramOption();
               participantProgramOption.setParticipantProgramOptionId(participant.getDepartmentProgramOption().getDepartmentProgramOptionId());
               participantProgramOption.setParticipantProgramOption(participant.getDepartmentProgramOption().getProgramOptionName());

               com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantType participantType = new com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantType();
               participantType.setParticipantTypeId(1);
               participantType.setParticipantType("Activated");

               com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantSeason participantSeason = new com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantSeason();
               participantSeason.setParticipantSeasonId(participant.getSeason().getSeasonId());
               participantSeason.setParticipantSeasonProgramId(participant.getDepartmentProgram().getDepartmentProgramId());
               participantSeason.setParticipantSeasonProgram(participant.getDepartmentProgram().getProgramName());

               com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantCountry participantCountry = new com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantCountry();
               participantCountry.setParticipantCountryId(participant.getLookupCountry().getCountryId());
               participantCountry.setParticipantCountryCode(participant.getLookupCountry().getCountryCode());
               participantCountry.setParticipantCountry(participant.getLookupCountry().getCountryName());

               com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantSubPartner participantSubPartner = new com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantSubPartner();
               // participantSubPartner.setParticipantSubPartnerId(participant.getSubPartner());
               participantSubPartner.setPartnerGoId(participant.getParticipantGoId());
               participantSubPartner.setParticipantSubPartner("Sub Partner Dude");

               com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantPlacementStatus participantPlacementStatus = new com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantPlacementStatus();
               participantPlacementStatus.setParticipantPlacementStatusId(1);
               participantPlacementStatus.setParticipantPlacementStatus("Active");

               com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantApplicationStatus participantApplicationStatus = new com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantApplicationStatus();
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
         List<Participant> leadParticipants = null;// participantRepository.findLeadParticipantByPartnerId(partnerId);
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
   public NewManualParticipant addNewParticipant(NewManualParticipant NewManualParticipant) {
      try {
         if(NewManualParticipant!=null){
            for (AddNewManualParticipant p : NewManualParticipant.getDetails()) {
               Participant participant = new Participant();
               try {
                  participant.setFirstName(p.getFirstName());
                  participant.setEmail(p.getEmail());
                  participant.setLastName(p.getLastName());
                  DepartmentProgramOption departmentProgramOption = departmentProgramOptions.findOne(p.getDepartmentProgramOptionId());
                  participant.setDepartmentProgramOption(departmentProgramOption);
                  Season season=seasonRepository.findOne(p.getSeasonId());
                  participant.setSeason(season);
                
                  participant.setGuaranteed((byte) (p.isGuranteed() ? 1 : 0));
                  participantRepository.saveAndFlush(participant);
                  p.setAdded(true);
               } catch (Exception e) {
                  ExceptionUtil.logException(e, logger);
               }
            }
         }
         NewManualParticipant.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         NewManualParticipant.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return NewManualParticipant;
   }
 

   private NewManualParticipant getParticipantEntity(Participant participant) {
      NewManualParticipant NewManualParticipant = new NewManualParticipant();
      try {

      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return NewManualParticipant;
   }

   @Override
   public NewManualParticipant editNewParticipant(Integer participantId) {
      NewManualParticipant editedParticipant = new NewManualParticipant();
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
   public NewManualParticipant updateParticipant(NewManualParticipant participant) {
      try {
//         participantRepository.saveAndFlush(getParticipantEntity(participant));
//         participant.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
//               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         participant.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return participant;
   }

   @Override
   public SeasonsForParticipants getAllAvailableSeasons() {
      SeasonsForParticipants seasons = new SeasonsForParticipants();
      try {
         List<Season> allSeasons = seasonRepository.getAllSeasons();
         if (allSeasons != null) {
            for (Season season : allSeasons) {
               SeasonsForParticipantDetails seasonsForParticipantDetails =new SeasonsForParticipantDetails();
               seasonsForParticipantDetails.setSeasonId(season.getSeasonId());
               seasonsForParticipantDetails.setSeasonName(season.getSeasonFullName());
               seasons.getDetails().add(seasonsForParticipantDetails);
            }
         }
         seasons.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         seasons.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return seasons;
   }

   @Override
   public ProgramOptionsForParticipants getAllAvailableProgramOptions() {
      ProgramOptionsForParticipants programOptionsForParticipants = new ProgramOptionsForParticipants();
      try {
         List<DepartmentProgramOption> allProgramOptions = departmentProgramOptions.findAll();
         if (allProgramOptions != null) {
            for (DepartmentProgramOption departmentProgramOption : allProgramOptions) {
               ProgramOptionsForParticipantsDetails details= new ProgramOptionsForParticipantsDetails();
               details.setDepartmentProgramId(departmentProgramOption.getDepartmentProgram().getDepartmentProgramId());
               details.setDepartmentProgramOption(departmentProgramOption.getProgramOptionName());
               details.setProgramOptionId(departmentProgramOption.getDepartmentProgramOptionId());
               programOptionsForParticipants.getDetails().add(details);
            }
         }
         programOptionsForParticipants.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         programOptionsForParticipants.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return programOptionsForParticipants;
   }

}
