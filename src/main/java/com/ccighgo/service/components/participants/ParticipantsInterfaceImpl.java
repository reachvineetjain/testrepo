/**
 * 
 */
package com.ccighgo.service.components.participants;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Participant;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.db.entities.Season;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramOptionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.components.partner.season.PartnerSeasonInterface;
import com.ccighgo.service.transport.participant.beans.addedParticipantList.AddedParticipantsDetails;
import com.ccighgo.service.transport.participant.beans.addedParticipantList.AddedParticipantsList;
import com.ccighgo.service.transport.participant.beans.availableprogramOptionsforparticipant.ProgramOptionsForParticipants;
import com.ccighgo.service.transport.participant.beans.availableprogramOptionsforparticipant.ProgramOptionsForParticipantsDetails;
import com.ccighgo.service.transport.participant.beans.availableseasonsforparticipant.SeasonsForParticipantDetails;
import com.ccighgo.service.transport.participant.beans.availableseasonsforparticipant.SeasonsForParticipants;
import com.ccighgo.service.transport.participant.beans.availablesubpartnerforparticipant.SubPartnersForParticipants;
import com.ccighgo.service.transport.participant.beans.availablesubpartnerforparticipant.SubPartnersForParticipantsDetails;
import com.ccighgo.service.transport.participant.beans.newmanualparticipant.AddNewManualParticipant;
import com.ccighgo.service.transport.participant.beans.newmanualparticipant.NewManualParticipant;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ActiveParticipant;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantsActiveList;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantLead;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantsLeadList;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;

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
   @Autowired GoIdSequenceRepository goIdSequenceRepository;
   @Autowired PartnerSeasonsRepository partnerSeasonsRepository;
   @Autowired PartnerProgramRepository partnerProgramRepository;

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
   public NewManualParticipant addNewParticipant(NewManualParticipant newManualParticipant) {
      try {
         if(newManualParticipant!=null){
            for (AddNewManualParticipant p : newManualParticipant.getDetails()) {
               Participant participant = new Participant();
               try {
                  participant.setFirstName(p.getFirstName());
                  participant.setEmail(p.getEmail());
                  participant.setLastName(p.getLastName());
                  DepartmentProgramOption departmentProgramOption = departmentProgramOptions.findOne(p.getDepartmentProgramOptionId());
                  participant.setDepartmentProgramOption(departmentProgramOption);
                  DepartmentProgram departmentProgram = departmentPrograms.findOne(p.getDepartmentId());
                  participant.setDepartmentProgram(departmentProgram);
                  Season season=seasonRepository.findOne(p.getSeasonId());
                  participant.setSeason(season);
                
                  participant.setGuaranteed((byte) (p.isGuranteed() ? 1 : 0));
                  GoIdSequence goIdSequence = new GoIdSequence();
                  goIdSequence = goIdSequenceRepository.save(goIdSequence);
                  participant.setParticipantGoId(goIdSequence.getGoId());
                  participantRepository.saveAndFlush(participant);
                  p.setAdded(true);
               } catch (Exception e) {
                  ExceptionUtil.logException(e, logger);
               }
            }
         }
         newManualParticipant.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         newManualParticipant.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return newManualParticipant;
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
   public SeasonsForParticipants getAllAvailableSeasons(int partnerId) {
      SeasonsForParticipants seasons = new SeasonsForParticipants();
      try {
          List<PartnerSeason> partnerSeasons = partnerSeasonsRepository.findPartnerSeasonByPartnerGoId(partnerId);
          if (partnerSeasons != null) {
            for (PartnerSeason partnerSeason : partnerSeasons) {
               SeasonsForParticipantDetails seasonsForParticipantDetails =new SeasonsForParticipantDetails();
               seasonsForParticipantDetails.setSeasonId(partnerSeason.getSeason().getSeasonId());
               seasonsForParticipantDetails.setSeasonName(partnerSeason.getSeason().getSeasonFullName());
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
   public ProgramOptionsForParticipants getAllAvailableProgramOptions(int partnerId,int seasonId) {
      ProgramOptionsForParticipants programOptionsForParticipants = new ProgramOptionsForParticipants();
      try {
         List<PartnerSeason> partnerSeasons= partnerSeasonsRepository.findPartnerSeasonByPartnerGoIdAndSeasonId(partnerId,seasonId);
         
         if (partnerSeasons != null) {
            for (PartnerSeason partnerSeason : partnerSeasons) {
               for (DepartmentProgramOption options : partnerSeason.getDepartmentProgram().getDepartmentProgramOptions()) {
                  ProgramOptionsForParticipantsDetails details= new ProgramOptionsForParticipantsDetails();
                  details.setDepartmentProgramId(partnerSeason.getDepartmentProgram().getDepartmentProgramId());
                  details.setDepartmentProgramOption(options.getProgramOptionName());
                  details.setProgramOptionId(options.getDepartmentProgramOptionId());
                  programOptionsForParticipants.getDetails().add(details);                  
               }
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

   @Override
   public AddedParticipantsList getAddedParticipant(String partnerId) {
      AddedParticipantsList addedParticipants = new AddedParticipantsList();
      try {
         // TODO
         List<Participant> participants =  participantRepository.findAddedParticipantByPartnerId(Integer.parseInt(partnerId));
         if (participants != null) {
            for (Participant participant : participants) {
               AddedParticipantsDetails details=new AddedParticipantsDetails();
               // details.setActive(participant.get);
               // active came from login table
               // username came from login table
               // details.setActive();
               details.setParticipantGoId(participant.getParticipantGoId()+"");
               details.setParticipantApplicationStatus(participant.getParticipantStatus().getParticipantStatusName());
               details.setParticipantApplicationStatusId(participant.getParticipantStatus().getParticipantStatusId());
               details.setParticipantCountry(participant.getLookupCountry().getCountryName());
               details.setParticipantCountryId(participant.getLookupCountry().getCountryId());
               details.setParticipantEmail(participant.getEmail());
               details.setParticipantEndDate(DateUtils.getDateAndTime(participant.getEndDate()));
               details.setParticipantFirstName(participant.getFirstName());
               details.setParticipantGuranteed(participant.getGuaranteed()==1);
               details.setParticipantlastName(participant.getLastName());
               details.setParticipantPicUrl(participant.getPhoto());
               details.setParticipantPlacementStatus(participant.getParticipantStatus().getActive()==1?"Active":"InActive");
               details.setParticipantProgramOption(participant.getDepartmentProgramOption().getProgramOptionName());
               details.setParticipantProgramOptionId(participant.getDepartmentProgramOption().getDepartmentProgramOptionId());
               details.setParticipantSeasonId(participant.getSeason().getSeasonId());
               details.setParticipantSeasonName(participant.getSeason().getSeasonName());
               details.setParticipantStartDate(DateUtils.getDateAndTime(participant.getStartDate()));
               details.setParticipantSubmittedFlightInfo(participant.getSubmittedFlightInfo()==1);
               addedParticipants.getParticipants().add(details);
            }
            addedParticipants.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (Exception e) {
         addedParticipants.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return addedParticipants;
   }

   @Override
   public SubPartnersForParticipants getAllAvailableSubPartners(int partnerId) {
      SubPartnersForParticipants subPartners = new SubPartnersForParticipants();
      try {
         List<Partner> allPartners = partnerRepository.findByIsSubPartnerAndParentId(partnerId);
         if (allPartners != null) {
            for (Partner p : allPartners) {
               SubPartnersForParticipantsDetails details =new SubPartnersForParticipantsDetails();
               details.setSubPartnerId(p.getPartnerGoId());
               details.setSubPartnerName(p.getCompanyName());
               subPartners.getDetails().add(details);
            }
         }
         subPartners.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         subPartners.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         ExceptionUtil.logException(e, logger);
      }
      return subPartners;
   }

   @Override
   public WSDefaultResponse assignSeasonToParticipant(String seasonId, String participantId) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         Participant p = participantRepository.findOne(Integer.parseInt(participantId));
         Season season =seasonRepository.findOne(Integer.parseInt(seasonId));
         p.setSeason(season);
         participantRepository.saveAndFlush(p);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.CHANGE_PARTICIPANT_SEASON.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_CHANGE_PARTICIPANT_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PATICIPANT_SEASON)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PATICIPANT_SEASON));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse assignSubpartnerToParticipant(String subpartnerId, String participantId) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         Participant p = participantRepository.findOne(Integer.parseInt(participantId));
         Partner subPartner = partnerRepository.findOne(Integer.parseInt(subpartnerId));
         p.setPartner2(subPartner);
         participantRepository.saveAndFlush(p);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.CHANGE_PARTICIPANT_SUBPARTNER.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_CHANGE_PARTICIPANT_SUBPARTNER.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PATICIPANT_SUBPARTNER)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PATICIPANT_SUBPARTNER));
      }
      return wsDefaultResponse;
   }

}
