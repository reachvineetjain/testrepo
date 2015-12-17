/**
 * 
 */
package com.ccighgo.service.components.participants;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.Participant;
import com.ccighgo.db.entities.ParticipantStatus;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonCAPDetail;
import com.ccighgo.db.entities.SeasonF1Detail;
import com.ccighgo.db.entities.SeasonHSADetail;
import com.ccighgo.db.entities.SeasonIHPDetail;
import com.ccighgo.db.entities.SeasonJ1Detail;
import com.ccighgo.db.entities.SeasonLSDetail;
import com.ccighgo.db.entities.SeasonTADetail;
import com.ccighgo.db.entities.SeasonVADetail;
import com.ccighgo.db.entities.SeasonWADetail;
import com.ccighgo.db.entities.SeasonWnTSpringDetail;
import com.ccighgo.db.entities.SeasonWnTSummerDetail;
import com.ccighgo.db.entities.SeasonWnTWinterDetail;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramOptionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.jpa.repositories.ParticipantStatusRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminSeasonConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
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
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.UuidUtils;
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
   @Autowired
   GoIdSequenceRepository goIdSequenceRepository;
   @Autowired
   PartnerSeasonsRepository partnerSeasonsRepository;
   @Autowired
   PartnerProgramRepository partnerProgramRepository;
   @Autowired
   UserTypeRepository userTypeRepository;
   @Autowired
   LoginUserTypeRepository loginUserTypeRepository;
   @Autowired
   LoginRepository loginRepository;
   @Autowired
   EmailServiceImpl email;
   @Autowired
   ParticipantStatusRepository participantStatusRepository;
   @PersistenceContext
   EntityManager em;
   
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
               if (participant.getLookupCountry() != null) {
                  participantCountry.setParticipantCountryId(participant.getLookupCountry().getCountryId());
                  participantCountry.setParticipantCountryCode(participant.getLookupCountry().getCountryCode());
                  participantCountry.setParticipantCountry(participant.getLookupCountry().getCountryName());
               }
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
         if (newManualParticipant != null) {
            for (AddNewManualParticipant p : newManualParticipant.getDetails()) {
               List<Login> loginList = new ArrayList<Login>();
               GoIdSequence goIdSequence = new GoIdSequence();
               goIdSequence = goIdSequenceRepository.save(goIdSequence);
               com.ccighgo.db.entities.UserType ParticipantUserType = userTypeRepository.findOne(CCIConstants.PARTICIPANT_USER_TYPE);
               if (ParticipantUserType == null) {
                  ParticipantUserType = new com.ccighgo.db.entities.UserType();
               }
               Login login = new Login();
               login.setActive(CCIConstants.INACTIVE);
               login.setLoginName(p.getEmail());
               login.setPassword(PasswordUtil.hashKey("password"));
               login.setKeyValue(UuidUtils.nextHexUUID());
               login.setCreatedBy(p.getLoginId());
               login.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               login.setModifiedBy(p.getLoginId());
               login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               login.setGoIdSequence(goIdSequence);
               login.setEmail(p.getEmail());
               login = loginRepository.save(login);
               loginList.add(login);
               goIdSequence.setLogins(loginList);

               LoginUserType loginUserType = new LoginUserType();
               loginUserType.setActive(CCIConstants.ACTIVE);
               loginUserType.setUserType(ParticipantUserType);
               loginUserType.setCreatedBy(p.getLoginId());
               loginUserType.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               loginUserType.setModifiedBy(p.getLoginId());
               loginUserType.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               loginUserType.setDefaultUserType(CCIConstants.ACTIVE);
               loginUserType.setLogin(login);
               loginUserType = loginUserTypeRepository.save(loginUserType);

               Participant participant = new Participant();
               try {
                  ParticipantStatus participantStatus = participantStatusRepository.findOne(CCIConstants.PARTICIPANT_STATUS_PENDING_VERIFICATION);
                  participant.setParticipantStatus(participantStatus);
                  participant.setFirstName(p.getFirstName());
                  participant.setEmail(p.getEmail());
                  participant.setLastName(p.getLastName());
                  DepartmentProgramOption departmentProgramOption = departmentProgramOptions.findOne(p.getDepartmentProgramOptionId());
                  participant.setDepartmentProgramOption(departmentProgramOption);
                  DepartmentProgram departmentProgram = departmentPrograms.findOne(p.getDepartmentId());
                  participant.setDepartmentProgram(departmentProgram);
                  Season season = seasonRepository.findOne(p.getSeasonId());
                  participant.setSeason(season);
                  Partner partner = partnerRepository.findOne(p.getPartnerId());
                  participant.setPartner1(partner);
                  participant.setGuaranteed((byte) (p.isGuranteed() ? 1 : 0));
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
         // participantRepository.saveAndFlush(getParticipantEntity(participant));
         // participant.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
         // ErrorCode.DEFAULT_CODE.getValue(),
         // messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
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
         List<Season> allSeasons = seasonRepository.findAll();
         if (allSeasons != null && !allSeasons.isEmpty()) {
            for (Season s : allSeasons) {
               if (s.getSeasonF1details() != null)
                  for (SeasonF1Detail f1 : s.getSeasonF1details()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(f1.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.HSP_F1_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeasonJ1details() != null)
                  for (SeasonJ1Detail j1 : s.getSeasonJ1details()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(j1.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeasonCapdetails() != null)
                  for (SeasonCAPDetail cap : s.getSeasonCapdetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(cap.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.WP_WT_CAP_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeasonWnTsummerDetails() != null)
                  for (SeasonWnTSummerDetail summer : s.getSeasonWnTsummerDetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(summer.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.WP_WT_SUMMER_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeasonWnTwinterDetails() != null)
                  for (SeasonWnTWinterDetail winter : s.getSeasonWnTwinterDetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(winter.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.WP_WT_WINTER_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeasonWnTspringDetails() != null)
                  for (SeasonWnTSpringDetail spring : s.getSeasonWnTspringDetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(spring.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.WP_WT_SPRING_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeasonHsadetails() != null)
                  for (SeasonHSADetail hsa : s.getSeasonHsadetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(hsa.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.GHT_HS_ABRD_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeasonWadetails() != null)
                  for (SeasonWADetail wa : s.getSeasonWadetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(wa.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.GHT_WRK_ABRD_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeasonTadetails() != null)
                  for (SeasonTADetail ta : s.getSeasonTadetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(ta.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.GHT_TEACH_ABRD_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeasonLsdetails() != null)
                  for (SeasonLSDetail ls : s.getSeasonLsdetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(ls.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.GHT_LANG_SCL_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeasonVadetails() != null)
                  for (SeasonVADetail va : s.getSeasonVadetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(va.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.GHT_VOL_ABRD_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }

               if (s.getSeasonIhpdetails() != null)
                  for (SeasonIHPDetail ihp : s.getSeasonIhpdetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(ihp.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.HSP_STP_IHP_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
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
   public ProgramOptionsForParticipants getAllAvailableProgramOptions(int partnerId, int seasonId) {
      ProgramOptionsForParticipants programOptionsForParticipants = new ProgramOptionsForParticipants();
      try {
         List<PartnerSeason> partnerSeasons = partnerSeasonsRepository.findPartnerSeasonByPartnerGoIdAndSeasonId(partnerId, seasonId);

         if (partnerSeasons != null) {
            for (PartnerSeason partnerSeason : partnerSeasons) {
               DepartmentProgram departmentProgram = partnerSeason.getDepartmentProgram();
               List<DepartmentProgramOption> options = departmentProgramOptions.findProgramOptionsByDepartmentProgramId(departmentProgram.getDepartmentProgramId());
               for (DepartmentProgramOption o : options) {
                  ProgramOptionsForParticipantsDetails details = new ProgramOptionsForParticipantsDetails();
                  details.setDepartmentProgramId(departmentProgram.getDepartmentProgramId());
                  details.setDepartmentProgramOption(o.getProgramOptionName());
                  details.setProgramOptionId(o.getDepartmentProgramOptionId());
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
         List<Participant> participants = null;
         Partner partner = partnerRepository.findOne(Integer.parseInt(partnerId));
         if (partner.getIsSubPartner() == CCIConstants.TRUE_BYTE) {
            participants = participantRepository.findAddedParticipantBySubPartnerId(Integer.parseInt(partnerId));
         } else {
            participants = participantRepository.findAddedParticipantByPartnerId(Integer.parseInt(partnerId));
         }
         if (participants != null) {
            for (Participant participant : participants) {
               AddedParticipantsDetails details = new AddedParticipantsDetails();
               try {
                  GoIdSequence goIdSequence = goIdSequenceRepository.findOne(participant.getParticipantGoId());
                  Login p = loginRepository.findByGoId(goIdSequence);
                  if(p!=null)
                  details.setActive(p.getActive() != null && p.getActive() == 1);
               } catch (Exception e) {
                  ExceptionUtil.logException(e, logger);
               }
               details.setParticipantGoId(participant.getParticipantGoId() + "");
               if (participant.getParticipantStatus() != null) {
                  details.setParticipantApplicationStatus(participant.getParticipantStatus().getParticipantStatusName());
                  details.setParticipantApplicationStatusId(participant.getParticipantStatus().getParticipantStatusId());
                  details.setParticipantPlacementStatus(participant.getParticipantStatus().getActive() == 1 ? "Active" : "InActive");
               }
               if (participant.getLookupCountry() != null) {
                  details.setParticipantCountry(participant.getLookupCountry().getCountryName());
                  details.setParticipantCountryId(participant.getLookupCountry().getCountryId());
               }
               details.setParticipantEmail(participant.getEmail());
               if (participant.getEndDate() != null)
                  details.setParticipantEndDate(DateUtils.getDateAndTime(participant.getEndDate()));
               details.setParticipantFirstName(participant.getFirstName());
               if (participant.getGuaranteed() != null)
                  details.setParticipantGuranteed(participant.getGuaranteed() == 1);
               details.setParticipantlastName(participant.getLastName());
               if (participant.getPhoto() != null)
                  details.setParticipantPicUrl(participant.getPhoto());
               if (participant.getDepartmentProgramOption() != null) {
                  details.setParticipantProgramOption(participant.getDepartmentProgramOption().getProgramOptionName());
                  details.setParticipantProgramOptionId(participant.getDepartmentProgramOption().getDepartmentProgramOptionId());
               }
               details.setParticipantSeasonId(participant.getSeason().getSeasonId());
               String programName = participant.getDepartmentProgram().getProgramName();
               try {
                  details.setParticipantSeasonName(programName);
                  if (participant.getSeason().getSeasonF1details() != null && programName.equalsIgnoreCase(CCIConstants.HSP_F1)) {
                     if (participant.getSeason().getSeasonF1details() != null && !participant.getSeason().getSeasonF1details().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonF1details().get(0).getProgramName());
                  } else if (participant.getSeason().getSeasonJ1details() != null && programName.equalsIgnoreCase(CCIConstants.HSP_J1_HS)) {
                     if (participant.getSeason().getSeasonJ1details() != null && !participant.getSeason().getSeasonJ1details().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonJ1details().get(0).getProgramName());
                  } else if (participant.getSeason().getSeasonCapdetails() != null && programName.equalsIgnoreCase(CCIConstants.WP_WT_CAP)) {
                     if (participant.getSeason().getSeasonCapdetails() != null && !participant.getSeason().getSeasonCapdetails().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonCapdetails().get(0).getProgramName());
                  } else if (participant.getSeason().getSeasonWnTsummerDetails() != null && programName.equalsIgnoreCase(CCIConstants.WP_WT_SUMMER)) {
                     if (participant.getSeason().getSeasonWnTsummerDetails() != null && !participant.getSeason().getSeasonWnTsummerDetails().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonWnTsummerDetails().get(0).getProgramName());
                  } else if (participant.getSeason().getSeasonWnTwinterDetails() != null && programName.equalsIgnoreCase(CCIConstants.WP_WT_WINTER)) {
                     if (participant.getSeason().getSeasonWnTwinterDetails() != null && !participant.getSeason().getSeasonWnTwinterDetails().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonWnTwinterDetails().get(0).getProgramName());
                  } else if (participant.getSeason().getSeasonWnTspringDetails() != null && programName.equalsIgnoreCase(CCIConstants.WP_WT_SPRING)) {
                     if (participant.getSeason().getSeasonWnTspringDetails() != null && !participant.getSeason().getSeasonWnTspringDetails().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonWnTspringDetails().get(0).getProgramName());
                  } else if (participant.getSeason().getSeasonHsadetails() != null && programName.equalsIgnoreCase(CCIConstants.GHT_HS_ABRD)) {
                     if (participant.getSeason().getSeasonHsadetails() != null && !participant.getSeason().getSeasonHsadetails().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonHsadetails().get(0).getProgramName());
                  } else if (participant.getSeason().getSeasonWadetails() != null && programName.equalsIgnoreCase(CCIConstants.GHT_WRK_ABRD)) {
                     if (participant.getSeason().getSeasonWadetails() != null && !participant.getSeason().getSeasonWadetails().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonWadetails().get(0).getProgramName());

                  } else if (participant.getSeason().getSeasonTadetails() != null && programName.equalsIgnoreCase(CCIConstants.GHT_TEACH_ABRD)) {
                     if (participant.getSeason().getSeasonTadetails() != null && !participant.getSeason().getSeasonTadetails().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonTadetails().get(0).getProgramName());

                  } else if (participant.getSeason().getSeasonLsdetails() != null && programName.equalsIgnoreCase(CCIConstants.GHT_LANG_SCL)) {
                     if (participant.getSeason().getSeasonLsdetails() != null && !participant.getSeason().getSeasonLsdetails().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonLsdetails().get(0).getProgramName());

                  } else if (participant.getSeason().getSeasonVadetails() != null && programName.equalsIgnoreCase(CCIConstants.GHT_VOL_ABRD)) {
                     if (participant.getSeason().getSeasonVadetails() != null && !participant.getSeason().getSeasonVadetails().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonVadetails().get(0).getProgramName());

                  } else if (participant.getSeason().getSeasonIhpdetails() != null && programName.equalsIgnoreCase(CCIConstants.HSP_STP_IHP)) {
                     if (participant.getSeason().getSeasonIhpdetails() != null && !participant.getSeason().getSeasonIhpdetails().isEmpty())
                        details.setParticipantSeasonName(participant.getSeason().getSeasonIhpdetails().get(0).getProgramName());
                  }
               } catch (Exception e) {
                  ExceptionUtil.logException(e, logger);
               }
               if (participant.getStartDate() != null)
                  details.setParticipantStartDate(DateUtils.getDateAndTime(participant.getStartDate()));
               if (participant.getSubmittedFlightInfo() != null)
                  details.setParticipantSubmittedFlightInfo(participant.getSubmittedFlightInfo() == 1);
               if (participant.getPartner2() != null) {
                  details.setSubPartnerGoId(participant.getPartner2().getPartnerGoId());
                  details.setSubPartnerName(participant.getPartner2().getCompanyName());
               }

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
               SubPartnersForParticipantsDetails details = new SubPartnersForParticipantsDetails();
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
   public WSDefaultResponse assignSeasonToParticipant(String seasonId, String participantId, String departmentProgram) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         Participant p = participantRepository.findOne(Integer.parseInt(participantId));
         Season season = seasonRepository.findOne(Integer.parseInt(seasonId));
         DepartmentProgram departmentProgram2 = departmentPrograms.findOne(Integer.parseInt(departmentProgram));
         p.setSeason(season);
         p.setDepartmentProgram(departmentProgram2);
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

   @Override
   public WSDefaultResponse assignEmailToParticipant(String participantId, String email2) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         Participant p = participantRepository.findOne(Integer.parseInt(participantId));
         p.setEmail(email2);
         participantRepository.saveAndFlush(p);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.CHANGE_PARTICIPANT_EMAIL.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_CHANGE_PARTICIPANT_EMAIL.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PATICIPANT_EMAIL)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PATICIPANT_EMAIL));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse changeParticipantStatus(String participantId, String status) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         GoIdSequence goIdSequence = goIdSequenceRepository.findOne(Integer.parseInt(participantId));
         Login p = loginRepository.findByGoId(goIdSequence);

         if (p != null)
            if (status.equalsIgnoreCase("active"))
               p.setActive((byte) 1);
            else
               p.setActive((byte) 0);
         loginRepository.saveAndFlush(p);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.CHANGE_PARTICIPANT_STATUS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_CHANGE_PARTICIPANT_STATUS.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PATICIPANT_STATUS)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PATICIPANT_STATUS));
      }
      return wsDefaultResponse;
   }

   @Override
   public Response resetParticipantPassword(String participantGoId, HttpServletRequest request) {
      Response response = new Response();
      if (participantGoId == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(), "invalid participant id"));
         logger.error("invalid participant id");
         return response;
      } else {
         try {
            Participant paricipant = participantRepository.findOne(Integer.valueOf(participantGoId));
            if (paricipant != null) {
               Login participantLogin = loginRepository.findByCCIGoId(paricipant.getParticipantGoId());
               if (participantLogin != null) {
                  String body = "<p>Ciao! </p>" + "<p>This email was sent automatically by Greenheart Online (GO) in response to your request for a new password. </p>" + "<p>"
                        + "Your username is : " + participantLogin.getLoginName() + "</p>" + "<p>Please click on the link below to create a new password:</p> " + "<p>"
                        + formResetURL(request).concat(participantLogin.getKeyValue()) + "</p>" + "<p>If you didn't request a new password, please let us know.</p>"
                        + "<p>Thank you,</p>" + "<p>CCI Greenheart.</p>";
                  email.send(participantLogin.getEmail(), CCIConstants.RESET_PASSWORD_SUBJECT, body, true);
                  response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
                        "An email has been sent to address " + "\'" + participantLogin.getEmail() + "\'" + " for login name " + "\'" + participantLogin.getLoginName() + "\'"
                              + " with instructions to reset password"));
               } else {
                  response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                        messageUtil.getMessage(CCIConstants.NO_RECORD)));
                  logger.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
               }
            }
         } catch (CcighgoException e) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  "an error occurred while reseting password"));
            logger.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return response;
   }

   private String formResetURL(HttpServletRequest request) {
      String url = "";
      try {
         url = request.getHeader("Origin") + CCIConstants.RESET_PASSWORD_LINK;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return url;
   }

   @Override
   public SeasonsForParticipants getAllAvailableSeasons(String partnerId) {
      SeasonsForParticipants seasons = new SeasonsForParticipants();
      try {
         List<PartnerSeason> allSeasons = partnerSeasonsRepository.findPartnerSeasonByPartnerGoId(Integer.parseInt(partnerId));
         if (allSeasons != null && !allSeasons.isEmpty()) {
            for (PartnerSeason s : allSeasons) {
               if (s.getSeason().getSeasonF1details() != null)
                  for (SeasonF1Detail f1 : s.getSeason().getSeasonF1details()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(f1.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.HSP_F1_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeason().getSeasonJ1details() != null)
                  for (SeasonJ1Detail j1 : s.getSeason().getSeasonJ1details()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(j1.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeason().getSeasonCapdetails() != null)
                  for (SeasonCAPDetail cap : s.getSeason().getSeasonCapdetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(cap.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.WP_WT_CAP_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeason().getSeasonWnTsummerDetails() != null)
                  for (SeasonWnTSummerDetail summer : s.getSeason().getSeasonWnTsummerDetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(summer.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.WP_WT_SUMMER_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeason().getSeasonWnTwinterDetails() != null)
                  for (SeasonWnTWinterDetail winter : s.getSeason().getSeasonWnTwinterDetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(winter.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.WP_WT_WINTER_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeason().getSeasonWnTspringDetails() != null)
                  for (SeasonWnTSpringDetail spring : s.getSeason().getSeasonWnTspringDetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(spring.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.WP_WT_SPRING_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeason().getSeasonHsadetails() != null)
                  for (SeasonHSADetail hsa : s.getSeason().getSeasonHsadetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(hsa.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.GHT_HS_ABRD_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeason().getSeasonWadetails() != null)
                  for (SeasonWADetail wa : s.getSeason().getSeasonWadetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(wa.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.GHT_WRK_ABRD_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeason().getSeasonTadetails() != null)
                  for (SeasonTADetail ta : s.getSeason().getSeasonTadetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(ta.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.GHT_TEACH_ABRD_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeason().getSeasonLsdetails() != null)
                  for (SeasonLSDetail ls : s.getSeason().getSeasonLsdetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(ls.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.GHT_LANG_SCL_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
               if (s.getSeason().getSeasonVadetails() != null)
                  for (SeasonVADetail va : s.getSeason().getSeasonVadetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(va.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.GHT_VOL_ABRD_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }

               if (s.getSeason().getSeasonIhpdetails() != null)
                  for (SeasonIHPDetail ihp : s.getSeason().getSeasonIhpdetails()) {
                     SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
                     seasonsForParticipantDetails.setSeasonId(s.getSeason().getSeasonId());
                     seasonsForParticipantDetails.setSeasonName(ihp.getProgramName());
                     seasonsForParticipantDetails.setDepartmentProgramId(CCIConstants.HSP_STP_IHP_ID);
                     seasons.getDetails().add(seasonsForParticipantDetails);
                  }
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
   @Transactional(readOnly = true)
   public Response sendLogin(String participantGoId, HttpServletRequest request) {
      Response response = new Response();
      try {
         if (participantGoId == null || Integer.valueOf(participantGoId) == 0 || Integer.valueOf(participantGoId) < 0) {
            throw new CcighgoException("invalid Participant info, cannot send login");
         }
         Login participantLoginData = loginRepository.findByCCIGoId(Integer.parseInt(participantGoId));

         if (participantLoginData != null) {
            String body = "<p>Ciao! </p>" + "<p>This email was sent automatically by Greenheart Online (GO) in response to your request for a new password. </p>" + "<p>"
                  + "Your username is : " + participantLoginData.getLoginName() + "</p>" + "<p>Please click on the link below to create a new password:</p> " + "<p>"
                  + formResetURL(request).concat(participantLoginData.getKeyValue()) + "</p>" + "<p>If you didn't request a new password, please let us know.</p>"
                  + "<p>Thank you,</p>" + "<p>CCI Greenheart.</p>";
            email.send(participantLoginData.getEmail(), CCIConstants.RESET_PASSWORD_SUBJECT, body, true);
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
                  "An email has been sent to address " + "\'" + participantLoginData.getEmail() + "\'" + " for login name " + "\'" + participantLoginData.getLoginName() + "\'"
                        + " with instructions to reset password"));
         } else {
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            logger.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(), e.getMessage()));
         logger.error(e.getMessage());
      }
      return response;
   }

   @Override
   public SeasonsForParticipants getAllAvailableSeasons2(String partnerId) {
      SeasonsForParticipants seasons = new SeasonsForParticipants();
      try {

         @SuppressWarnings("unchecked")
         List<Object[]> result = em.createNativeQuery("call SPPartnerParticipantSeasons(:partnerId)").setParameter("partnerId", partnerId).getResultList();
         if (result != null) {
            for (Object[] dt : result) {
               SeasonsForParticipantDetails seasonsForParticipantDetails = new SeasonsForParticipantDetails();
               seasonsForParticipantDetails.setSeasonName(String.valueOf(dt[0]));
               if (dt[1] != null)
                  seasonsForParticipantDetails.setSeasonId(Integer.valueOf(String.valueOf(dt[1])));
               if (dt[2] != null)
                  seasonsForParticipantDetails.setDepartmentProgramId(Integer.valueOf(String.valueOf(dt[2])));
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
}