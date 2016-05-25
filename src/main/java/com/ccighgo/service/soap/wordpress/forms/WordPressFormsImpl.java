package com.ccighgo.service.soap.wordpress.forms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.HostFamilyInquiry;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LookupCountry;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.db.entities.Salutation;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.HostFamilyInquiryRepository;
import com.ccighgo.jpa.repositories.HostFamilyParticipantRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerReviewStatusRepository;
import com.ccighgo.jpa.repositories.PartnerStatusRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.service.transport.seasons.beans.soapservice.AreaRepresentativeData;
import com.ccighgo.service.transport.seasons.beans.soapservice.HostFamilyData;
import com.ccighgo.service.transport.seasons.beans.soapservice.InternationalPartners;
import com.ccighgo.service.transport.seasons.beans.soapservice.ParticipantsInfo;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.ExceptionUtil;

public class WordPressFormsImpl implements IWordPressForms {

   public static final Logger LOGGER = Logger.getLogger(WordPressFormsImpl.class);

   @Autowired PartnerAgentInquiryRepository partnerAgentInquiryRepository;
   @Autowired LoginRepository loginRepository;
   @Autowired PartnerRepository partnerRepository;
   @Autowired GoIdSequenceRepository goIdSequenceRepository;
   @Autowired CountryRepository countryRepository;
   @Autowired PartnerProgramRepository partnerProgramRepository;
   @Autowired LookupDepartmentProgramRepository dpr;
   @Autowired SalutationRepository salutationRepository;
   @Autowired PartnerReviewStatusRepository partnerReviewStatusRepository;
   @Autowired PartnerStatusRepository partnerStatusRepository;
   @Autowired EntityManager entityManager;
   @Autowired HostFamilyInquiryRepository hostFamilyInquiryRepository;
   @Autowired HostFamilyParticipantRepository hostFamilyParticipantRepository;
   @Autowired ParticipantRepository participantRepository;
   private static final String SP_UPDATING_ADMIN_WORK_QUEUE = "CALL SPAdminWQPartnerApplicationSubmitted(?)";

   @Transactional
   @Override
   public String inquiryPartner(InternationalPartners InternationalPartners) {
      try {
         LOGGER.info("Inquiry partner Is Called !!d!");
         System.out.println("Partner Inquiry Called !! ");
         if (InternationalPartners != null) {
            List<Login> user = loginRepository.findListByEmail(InternationalPartners.getEmail());
            if (user != null&&!user.isEmpty()) {
               String message = "400:Duplicate Row (User Already Exist ):400:Duplicate Row (User Already Exist) [Login Table ]";
               System.out.println(message);
               LOGGER.info(message);
               return message;
            }
            List<PartnerAgentInquiry> pa = partnerAgentInquiryRepository.findListByEmail(InternationalPartners.getEmail());
            if (pa != null&&!pa.isEmpty()) {
               String message = "400:Duplicate Row (User Already Exist ):400:Duplicate Row (User Already Exist) [PartnerAgentInquiry Table ]";
               System.out.println(message);
               LOGGER.info(message);
               return message;
            }
            InternationalPartners.setWebsite(InternationalPartners.getWebsite().replaceAll("http://|https://|/$", ""));
            String secondFormatOfWebSite = "";
            if (InternationalPartners.getWebsite() != null) {
               if (InternationalPartners.getWebsite().toLowerCase().startsWith("www"))
                  secondFormatOfWebSite = InternationalPartners.getWebsite().replaceAll("^www\\.", "");
               else
                  secondFormatOfWebSite = "www." + InternationalPartners.getWebsite().toLowerCase();
               List<PartnerAgentInquiry> webSiteDuplicate = partnerAgentInquiryRepository.findListByWebSite(InternationalPartners.getWebsite().toLowerCase(), secondFormatOfWebSite);
               if (webSiteDuplicate != null&& !webSiteDuplicate.isEmpty()) {
                  String message = "400:Duplicate Row (WebSite Already Exist):400:Duplicate Row (WebSite Already Exist)";
                  System.out.println(message);
                  LOGGER.info(message);
                  return message;
               }
            }

            PartnerAgentInquiry legalNameDuplicate = partnerAgentInquiryRepository.findByLegalName(InternationalPartners.getLegalBusinessName());
            if (legalNameDuplicate != null) {
               String message = "400:Duplicate Row (LegalName is Already Exist):400:Duplicate Row (LegalName Already Exist)";
               System.out.println(message);
               LOGGER.info(message);
               return message;
            }
            print(InternationalPartners);
            PartnerAgentInquiry partnerAgentInquiry = new PartnerAgentInquiry();
            partnerAgentInquiry.setAdressLineOne(InternationalPartners.getAddress());
            partnerAgentInquiry.setAdressLineTwo(InternationalPartners.getAddress2());
            partnerAgentInquiry.setBusinessName(InternationalPartners.getLegalBusinessName());
            partnerAgentInquiry.setBusinessYears(InternationalPartners.getYearsInBusiness() + "");
            partnerAgentInquiry.setCity(InternationalPartners.getCity());
            partnerAgentInquiry.setEmail(InternationalPartners.getEmail());
            partnerAgentInquiry.setFirstName(InternationalPartners.getFirstName());
            if(InternationalPartners.getHearedAboutUs().contains("|"))
            	partnerAgentInquiry.setHowDidYouHearAboutCCI(InternationalPartners.getHearedAboutUs().split("\\|")[0]);
            else 
            	partnerAgentInquiry.setHowDidYouHearAboutCCI(InternationalPartners.getHearedAboutUs());
            
            partnerAgentInquiry.setLastName(InternationalPartners.getLastName());
            partnerAgentInquiry.setState(InternationalPartners.getStateOrProvince());
            partnerAgentInquiry.setCompanyName(InternationalPartners.getLegalBusinessName());
            partnerAgentInquiry.setPhone(InternationalPartners.getPhone());
            if (InternationalPartners.getDescriptionOfPrograms() != null)
               partnerAgentInquiry.setCurrentlyOfferingPrograms(InternationalPartners.getDescriptionOfPrograms());
            if (InternationalPartners.getCurrentlySendingParticipant() != null)
               partnerAgentInquiry.setCurrentlySendingParticipantToUS(InternationalPartners.getCurrentlySendingParticipant() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            if (InternationalPartners.getAmbassadorScholarship() != null)
               partnerAgentInquiry.setAmbassadorScholershipParticipants(InternationalPartners.getAmbassadorScholarship() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            if (InternationalPartners.getWebsite() != null)
               partnerAgentInquiry.setWebsite(InternationalPartners.getWebsite().replaceAll("http://|https://|/$", "").toLowerCase());

            LookupCountry lookupCountry = null;
            if (InternationalPartners.getValueOfCountry() != null)
               lookupCountry = countryRepository.findByCountryName(InternationalPartners.getValueOfCountry());
            /**
             * New partner
             */
            GoIdSequence goIdSequence = new GoIdSequence();
            goIdSequence = goIdSequenceRepository.saveAndFlush(goIdSequence);
            Partner newPartner = new Partner();
            newPartner.setPartnerGoId(goIdSequence.getGoId());
            newPartner.setEmail(InternationalPartners.getEmail());
            newPartner.setReceiveAYPMails(CCIConstants.INACTIVE);
            newPartner.setSubscribeToCCINewsletter(CCIConstants.INACTIVE);
            newPartner.setHasSubPartners(CCIConstants.INACTIVE);
            newPartner.setMultiCountrySender(CCIConstants.INACTIVE);
            newPartner.setIsSubPartner(CCIConstants.INACTIVE);
            newPartner.setPayGreenheartDirectly(CCIConstants.INACTIVE);
            newPartner.setDeliverDSForms(CCIConstants.INACTIVE);
            newPartner.setNeedPartnerReview(CCIConstants.INACTIVE);
            newPartner.setMailingAddressIsSameAsPhysicalAdress(CCIConstants.ACTIVE);
            newPartner.setParticipantMedicalReleaseRequired(CCIConstants.INACTIVE);
            newPartner.setParticipantSLEPRequired(CCIConstants.INACTIVE);
            newPartner.setParticipantTranscriptRequired(CCIConstants.INACTIVE);
            newPartner.setUnguaranteedFormRequired(CCIConstants.INACTIVE);
            newPartner.setParticipantELTISRequired(CCIConstants.INACTIVE);
            if (lookupCountry != null)
               newPartner.setLookupCountry1(lookupCountry);
            newPartner.setLookupCountry2(lookupCountry);
            newPartner.setCreatedBy(0);
            newPartner.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            newPartner.setModifiedBy(0);
            newPartner.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            newPartner = partnerRepository.saveAndFlush(newPartner);

            partnerAgentInquiry.setPartner(newPartner);
            partnerAgentInquiry.setSubmittedOn(new java.sql.Timestamp(System.currentTimeMillis()));

            /**
             * add Lookup Country
             */
            partnerAgentInquiry.setLookupCountry(lookupCountry);

            /**
             * Add partner programs
             */
            if (InternationalPartners.getPrograms() != null && !InternationalPartners.getPrograms().isEmpty()) {
               List<PartnerProgram> partnerProgramList = new ArrayList<PartnerProgram>();
               for (String it : InternationalPartners.getPrograms()) {
                  String[] v = it.split(",");
                  if (v != null) {
                     for (String pCon : v) {
                        pCon = pCon.trim();
                        PartnerProgram pp = new PartnerProgram();
                        if (pCon.equalsIgnoreCase("J-1 High School Program")) {
                           pp.setLookupDepartmentProgram(dpr.findOne(1));
                        } else if (pCon.equalsIgnoreCase("F-1 Visa Program")) {
                           pp.setLookupDepartmentProgram(dpr.findOne(2));
                        } else if (pCon.equalsIgnoreCase("Summer English Academy (ESL Summer Camp)")) {
                           // TODO changed after adding new department
                           // program
                           pp.setLookupDepartmentProgram(dpr.findOne(5));
                        } else if (pCon.equalsIgnoreCase("Short-term Group Homestay Programs")) {
                           pp.setLookupDepartmentProgram(dpr.findOne(4));
                        } else if (pCon.equalsIgnoreCase("Short-term Individual Homestay Program")) {
                           pp.setLookupDepartmentProgram(dpr.findOne(3));
                        } else if (pCon.equalsIgnoreCase("J-1 Intern/Trainee")) {
                           pp.setLookupDepartmentProgram(dpr.findOne(7));
                        } else if (pCon.equalsIgnoreCase("J-1 Summer Work Travel")) {
                           pp.setLookupDepartmentProgram(dpr.findOne(6));
                        }
                        pp.setPartner(newPartner);
                        pp.setHasApplied(CCIConstants.ACTIVE);
                        pp.setIsEligible(CCIConstants.ACTIVE);
                        partnerProgramList.add(pp);
                     }
                  }
               }
               partnerProgramRepository.save(partnerProgramList);
            }
            if (InternationalPartners.getTypeOfPrograms() != null && !InternationalPartners.getTypeOfPrograms().isEmpty()) {

               partnerAgentInquiry.setTeachAbroad(CCIConstants.INACTIVE);
               partnerAgentInquiry.setVolunteerAbroad(CCIConstants.INACTIVE);
               partnerAgentInquiry.setHighSchoolAbroad(CCIConstants.INACTIVE);
               partnerAgentInquiry.setOther(CCIConstants.INACTIVE);

               for (String it : InternationalPartners.getTypeOfPrograms()) {
                  String[] v = it.split(",");
                  if (v != null) {
                     for (String item : v) {
                        item = item.trim();
                        if (item.equalsIgnoreCase("Teach Abroad")) {
                           partnerAgentInquiry.setTeachAbroad(CCIConstants.ACTIVE);
                        } else if (item.equalsIgnoreCase("Volunteer Abroad")) {
                           partnerAgentInquiry.setVolunteerAbroad(CCIConstants.ACTIVE);
                        } else if (item.equalsIgnoreCase("High School Abroad")) {
                           partnerAgentInquiry.setHighSchoolAbroad(CCIConstants.ACTIVE);
                        } else if (item.equalsIgnoreCase("Other (please explain below)")) {
                           partnerAgentInquiry.setOther(CCIConstants.ACTIVE);
                        }
                     }
                  }
               }
            }
            if (InternationalPartners.getPrefix() != null) {
               if (InternationalPartners.getPrefix().startsWith("Mr")) {
                  Salutation s = salutationRepository.findBySalutationName("Mr");
                  partnerAgentInquiry.setSalutation(s);
               } else if (InternationalPartners.getPrefix().startsWith("Ms")) {
                  Salutation s = salutationRepository.findBySalutationName("Ms");
                  partnerAgentInquiry.setSalutation(s);
               }
            }
            /**
             * Partner Status
             * 
             */
            PartnerStatus status = partnerStatusRepository.findStatusByName("Pending");
            PartnerReviewStatus reviewStatus = new PartnerReviewStatus();
            reviewStatus.setPartner(newPartner);
            reviewStatus.setPartnerStatus1(status);
            partnerReviewStatusRepository.saveAndFlush(reviewStatus);
            partnerAgentInquiryRepository.saveAndFlush(partnerAgentInquiry);
            callTheStoredProcedure(goIdSequence.getGoId());
            String s = "200:Success:200:Success";
            System.out.println(s);
            return s;
         } else {

            String s = "500:Failed To Process Record ! Contact Admin:500:Failed To Process Record ! Contact Admin";
            System.out.println(s);
            return s;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         e.printStackTrace();
         String string = "700:Internal Error:700:" + e.getMessage();
         LOGGER.info(string);
         return string;
      }
   }

   private void callTheStoredProcedure(Integer goId) {
      try {
         Query query = entityManager.createNativeQuery(SP_UPDATING_ADMIN_WORK_QUEUE);
         query.setParameter(1, Integer.valueOf(goId));
         query.executeUpdate();
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         LOGGER.info("Error Executing the Stored Procedure !!");
      }

   }

   private void print(InternationalPartners internationalPartners) {
      System.out.println("address : " + internationalPartners.getAddress());
      System.out.println("address2 : " + internationalPartners.getAddress2());
      System.out.println("city :" + internationalPartners.getCity());
      System.out.println("country : " + internationalPartners.getValueOfCountry());
      System.out.println("Ambassdor : " + internationalPartners.getAmbassadorScholarship());
      System.out.println("description OF programs :" + internationalPartners.getDescriptionOfPrograms());
      System.out.println("Email: " + internationalPartners.getEmail());
      System.out.println("first Name: " + internationalPartners.getFirstName());
      System.out.println("heardAbout Us:" + internationalPartners.getHearedAboutUs());
      System.out.println("Last Name: " + internationalPartners.getLastName());
      System.out.println("Business Name :" + internationalPartners.getLegalBusinessName());
      System.out.println("Phone : " + internationalPartners.getPhone());
      System.out.println(" prefix :" + internationalPartners.getPrefix());
      System.out.println("State:" + internationalPartners.getStateOrProvince());
      System.out.println("webSite" + internationalPartners.getWebsite());
      System.out.println("Sending Currently : " + internationalPartners.getCurrentlySendingParticipant());

      if (internationalPartners.getTypeOfPrograms() != null && internationalPartners.getTypeOfPrograms().size() > 0)
         for (String s : internationalPartners.getTypeOfPrograms()) {
            System.out.println("Type Of program : " + s);
         }
      if (internationalPartners.getPrograms() != null && internationalPartners.getPrograms().size() > 0)
         for (String s : internationalPartners.getPrograms()) {
            System.out.println("Programs" + s);
         }
      System.out.println("Sending Currently : " + internationalPartners.getCurrentlySendingParticipant());
      System.out.println("Years  IN Business :" + internationalPartners.getYearsInBusiness());
   }

   @Override
   public String generateNewHostFamily(HostFamilyData HostFamilyData) {
      try {
         printDataCommingFromService(HostFamilyData);
         LOGGER.info("Inquiry HostFamily Is Called !!d!");
         System.out.println("Inquiry HostFamily Is Called !!!");
         if (HostFamilyData != null) {
            Login user = loginRepository.findByEmail(HostFamilyData.getEmail());
            HostFamilyInquiry pa = hostFamilyInquiryRepository.findByEmail(HostFamilyData.getEmail());
            if (user != null) {
               String message = "400:Duplicate Row (User Already Exist ):400:Duplicate Row (User Already Exist) [Login Table ]";
               System.out.println(message);
               return message;
            }
            if (pa != null) {
               String message = "400:Duplicate Row (User Already Exist ):400:Duplicate Row (User Already Exist) [HostFamilyInquiry Table ]";
               System.out.println(message);
               return message;
            }

            pa = new HostFamilyInquiry();
            pa.setAddress(HostFamilyData.getAddress());
            pa.setCciComments(HostFamilyData.getComments());
            pa.setCurrentCity(HostFamilyData.getCity());
            // TODO pa.setCurrentState(HostFamilyData.getState());
            pa.setFirstName(HostFamilyData.getFirstName());
            pa.setEmailAddress(HostFamilyData.getEmail());
            pa.setLastName(HostFamilyData.getLastName());
            pa.setZipCode(HostFamilyData.getPostalCode());
            pa.setPreferredPhoneNumber(HostFamilyData.getPreferredPhone());
            pa.setOptionalPhoneNumber(HostFamilyData.getOptionalPhone());

            GoIdSequence goIdSequence = new GoIdSequence();
            goIdSequence = goIdSequenceRepository.save(goIdSequence);
            System.out.println("GoID : " + goIdSequence.getGoId());
            pa.setHostFamilyInquiryId(goIdSequence.getGoId());
            hostFamilyInquiryRepository.saveAndFlush(pa);

         }
         return "200:Success:200:Success";
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         String string = "700:Internal Error:700:" + e.getMessage();
         LOGGER.info(string);
         return string;
      }
   }

   private void printDataCommingFromService(HostFamilyData HostFamilyData) {
      LOGGER.info("Generate New Host Family");

      System.out.println("Generate New Host Family");

      if (HostFamilyData != null) {
         System.out.println("FName :" + HostFamilyData.getFirstName());
         System.out.println("LName :" + HostFamilyData.getLastName());
         System.out.println("Email :" + HostFamilyData.getEmail());
         System.out.println("City :" + HostFamilyData.getCity());
         System.out.println("State : " + HostFamilyData.getState());
         System.out.println("PostalCode : " + HostFamilyData.getPostalCode());
         System.out.println("PreferredPhone: " + HostFamilyData.getPreferredPhone());
         System.out.println("OptionalPhone : " + HostFamilyData.getOptionalPhone());
         System.out.println("Email : " + HostFamilyData.getEmail());
         System.out.println("Students : " + HostFamilyData.getStudents());
         System.out.println("Comments : " + HostFamilyData.getComments());

      }
   }

   @Override
   public String generateNewAreaRepresentative(AreaRepresentativeData AreaRepresentativeData) {
      try {
         LOGGER.info("Generate New Area Representative");
         System.out.println("Generate New Area Representative");

         if (AreaRepresentativeData != null) {
            System.out.println("FName :" + AreaRepresentativeData.getFirstName());
            System.out.println("LName :" + AreaRepresentativeData.getLastName());
            System.out.println("Email :" + AreaRepresentativeData.getEmail());
            System.out.println("City :" + AreaRepresentativeData.getCity());
            System.out.println("State : " + AreaRepresentativeData.getState());
            System.out.println("Postal Code : " + AreaRepresentativeData.getPostalCode());
            System.out.println("Home Phone : " + AreaRepresentativeData.getHomePhone());
            System.out.println("workPhone : " + AreaRepresentativeData.getWorkPhone());
            System.out.println("comments : " + AreaRepresentativeData.getComments());
            System.out.println("HoomeAddress : " + AreaRepresentativeData.getHomeAddress());
            System.out.println("interestedINlocalCoForSumm : " + AreaRepresentativeData.getInterestedInLocalCoordinatorForSummer());
            System.out.println("INterestedInLocalCoForYear : " + AreaRepresentativeData.getInterestedInLocalCoordinatorForYear());
            System.out.println("current Community Volunteer: " + AreaRepresentativeData.isCurrentCommunityVolunteer());
            System.out.println("currentCommunityVolunteer Details : " + AreaRepresentativeData.getCurrentCommunityVolunteerDetails());
            System.out.println("FieldStaffWIlling to host: " + AreaRepresentativeData.getFieldStaffWillingToHost());

         }
         if (AreaRepresentativeData.getEmail().equalsIgnoreCase("success@gmail.com")) {
            String string = "200:Success:200:Success";
            System.out.println(string);
            return string;
         } else if (AreaRepresentativeData.getEmail().equalsIgnoreCase("duplicate@gmail.com")) {
            String string = "400:Duplicate Row:400:Duplicate Row";
            System.out.println(string);
            return string;
         } else if (AreaRepresentativeData.getEmail().equalsIgnoreCase("failed@gmail.com")) {
            String string = "500:Failed To Process Record ! Contact Admin:500:Failed To Process Record ! Contact Admin";
            System.out.println(string);
            return string;
         } else {
            String string = "300:Missing Information:300:Missing Information";
            System.out.println(string);
            return string;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         String string = "700:Internal Error:700:" + e.getMessage();
         System.out.println(string);
         return string;
      }
   }

   @Override
   public Boolean isEmailExist(String Email) {
      try {
         System.out.println("IsEmailExist is Called !!! ");
         System.out.println("Email : " + Email);
         Login user = loginRepository.findByEmail(Email.trim());
         
         if (user != null) {
        	 System.out.println( Email + " : exist");
            return true;
         }
      } catch (Exception e) {
    	  System.out.println( Email + " : not exist");
         ExceptionUtil.logException(e, LOGGER);
         return false;
      }
      System.out.println( Email + " : not exist");
      return false;
   }

   @Override
   public Boolean isLegalNameExist(String LegalName) {
      try {
         System.out.println("IsLegalNameExist is Called !!! ");
         System.out.println("Legal Name:" + LegalName);
         PartnerAgentInquiry legalNameDuplicate = partnerAgentInquiryRepository.findByLegalName(LegalName.trim());
         if (legalNameDuplicate != null) {
            System.out.println(LegalName + ": exist");
            return true;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         System.out.println(LegalName + ": not exist");
         return false;
      }
      System.out.println(LegalName + ": not exist");
      return false;
   }

   @Override
   public Boolean isWebSiteExist(String WebSite) {
      try {
         System.out.println("IsWebSiteExist is Called !!! ");
         WebSite = WebSite.replaceAll("http://|https://|/$", "");
         String secondFormatOfWebSite = "";
         if (WebSite.toLowerCase().startsWith("www"))
            secondFormatOfWebSite = WebSite.replaceAll("^www\\.", "");
         else
            secondFormatOfWebSite = "www." + WebSite.toLowerCase();
         PartnerAgentInquiry webSiteDuplicate = partnerAgentInquiryRepository.findByWebSite(WebSite.toLowerCase().trim(), secondFormatOfWebSite.toLowerCase().trim());
         if (webSiteDuplicate != null) {
        	 System.out.println(WebSite + ": exist");
            return true;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         System.out.println(WebSite + ": not exist");
         return false;
      }
      System.out.println(WebSite + ": not exist");
      return false;
   }

   public static void main(String[] args) {
      System.out.println("www.google.com".replaceAll("^www\\.", ""));
   }

   @Override
   public List<ParticipantsInfo> participantList() {
      List<ParticipantsInfo> participantInfo = new ArrayList<ParticipantsInfo>();
      try {
         for (int i = 0; i < 20; i++) {
            ParticipantsInfo p = new ParticipantsInfo();
            p.setAge(25 + i);
            p.setBiography("Konrad enjoys fishing. He says he can fish for hours without even catching anything. He likes how relaxing it is to sit and wait for a bite. His favorite sport is tennis. He takes lessons every week, and plays in the Summer with his Father. Konrad also likes to play golf and read science fiction books like The Magician’s Guild or The Maze Runner. Konrad is very friendly, mature, and reliable. His dream is to study in the U.S. Konrad does have a severe allergy to cats, and a medium allergy to dogs."
                  + (i + 1));
            p.setGender(i % 2 == 0 ? "male" : "female");
            p.setID(34 + i);
            p.setInterests("Exercise/Sports, Reading/Writing" + (i + 1));
            p.setName("Konrad" + (i + 1));
            p.setNationality(i % 2 == 0 ? "en" : "fr");
            p.setProgramStart("Fall 2016");
            participantInfo.add(p);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return participantInfo;
   }
}
