package com.ccighgo.service.soap.wordpress.forms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.service.transport.seasons.beans.soapservice.AreaRepresentativeData;
import com.ccighgo.service.transport.seasons.beans.soapservice.HostFamilyData;
import com.ccighgo.service.transport.seasons.beans.soapservice.InternationalPartners;
import com.ccighgo.utils.ExceptionUtil;

public class WordPressFormsImpl implements IWordPressForms {

   public static final Logger LOGGER = Logger.getLogger(WordPressFormsImpl.class);

   @Autowired
   PartnerAgentInquiryRepository partnerAgentInquiryRepository;
   @Autowired
   LoginRepository loginRepository;
   @Autowired
   PartnerRepository partnerRepository;

   @Override
   public String InquiryPartner(InternationalPartners InternationalPartners) {
      try {
         LOGGER.info("Inquiry partner Is Called !!d!");
         System.out.println("Inquiry partner Is Called !!!");
         if (InternationalPartners != null) {
            Login user = loginRepository.findByEmail(InternationalPartners.getEmail());
            if (user != null) {
               String message = "400:Duplicate Row (User Already Exist ):400:Duplicate Row (User Already Exist)";
               System.out.println(message);
               return message;
            }
            String secondFormatOfWebSite = "";
            if (InternationalPartners.getWebsite() != null) {
               if (InternationalPartners.getWebsite().toLowerCase().startsWith("www"))
                  secondFormatOfWebSite = InternationalPartners.getWebsite().replaceAll("^www\\.", "");
               else
                  secondFormatOfWebSite = "www." + InternationalPartners.getWebsite();
               PartnerAgentInquiry webSiteDuplicate = partnerAgentInquiryRepository.findByWebSite(InternationalPartners.getWebsite(), secondFormatOfWebSite);
               if (webSiteDuplicate != null) {
                  String message = "400:Duplicate Row (WebSite Already Exist):400:Duplicate Row (WebSite Already Exist)";
                  System.out.println(message);
                  return message;
               }
            }

            PartnerAgentInquiry legalNameDuplicate = partnerAgentInquiryRepository.findByLegalName(InternationalPartners.getLegalBusinessName());
            if (legalNameDuplicate != null) {
               String message = "400:Duplicate Row (LegalName is Already Exist):400:Duplicate Row (LegalName Already Exist)";
               System.out.println(message);
               return message;
            }
            print (InternationalPartners);
            PartnerAgentInquiry partnerAgentInquiry = new PartnerAgentInquiry();
            partnerAgentInquiry.setAdressLineOne(InternationalPartners.getAddress());
            partnerAgentInquiry.setAdressLineTwo(InternationalPartners.getAddress2());
            partnerAgentInquiry.setBusinessName(InternationalPartners.getLegalBusinessName());
            partnerAgentInquiry.setBusinessYears(InternationalPartners.getYearsInBusiness() + "");
            partnerAgentInquiry.setCity(InternationalPartners.getCity());
            partnerAgentInquiry.setEmail(InternationalPartners.getEmail());
            partnerAgentInquiry.setFirstName(InternationalPartners.getFirstName());
            partnerAgentInquiry.setHowDidYouHearAboutCCI(InternationalPartners.getHearedAboutUs());
            partnerAgentInquiry.setLastName(InternationalPartners.getLastName());
            partnerAgentInquiry.setState(InternationalPartners.getStateOrProvince());
            partnerAgentInquiry.setCompanyName(InternationalPartners.getLegalBusinessName());
            if(InternationalPartners.getWebsite()!=null)
            partnerAgentInquiry.setWebsite(InternationalPartners.getWebsite().replaceAll("http://|https://|/$", "").toLowerCase());
            // partnerAgentInquiry.setPartnerAgentInquiriesId(new Random().nextInt());

            // GoIdSequence goIdSequence = new GoIdSequence();
            // goIdSequence = goIdSequenceRepository.save(goIdSequence);
            // partnerAgentInquiry.setpartn
            // partnerAgentInquiry.setPartnerAgentInquiriesId(new Random().nextInt());
            partnerAgentInquiryRepository.saveAndFlush(partnerAgentInquiry);
            String s = "200:Success:200:Success";
            System.out.println(s);
            return s;
         } else {
            // if (InternationalPartners.getEmail().equalsIgnoreCase("success@gmail.com")) {
            // String string = "200:Success:300:Missing Information";
            // System.out.println(string);
            // return string;
            // } else if (InternationalPartners.getEmail().equalsIgnoreCase("duplicate@gmail.com")) {
            // String string = "400:Duplicate Row:400:Duplicate Row";
            // System.out.println(string);
            // return string;
            // } else if (InternationalPartners.getEmail().equalsIgnoreCase("failed@gmail.com")) {
            // String string =
            // "500:Failed To Process Record ! Contact Admin:500:Failed To Process Record ! Contact Admin";
            // System.out.println(string);
            // return string;
            // } else {
            // String string = "300:Missing Information:300:Missing Information";
            // System.out.println(string);
            // return string;
            // }
            String s = "500:Failed To Process Record ! Contact Admin:500:Failed To Process Record ! Contact Admin";
            System.out.println(s);
            return s;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         String string = "700:Internal Error:700:" + e.getMessage();
         System.out.println(string);
         return string;
      }
   }

   private void print(InternationalPartners internationalPartners) {
      System.out.println("address : "+internationalPartners.getAddress());
      System.out.println("address2 : "+internationalPartners.getAddress2());
      System.out.println("city :"+internationalPartners.getCity());
      System.out.println("country"+internationalPartners.getCountry());
      System.out.println("description OF programs :"+internationalPartners.getDescriptionOfPrograms());
      System.out.println("Email: "+internationalPartners.getEmail());
      System.out.println("first Name: "+internationalPartners.getFirstName());
      System.out.println("heardAbout Us:"+internationalPartners.getHearedAboutUs());
      System.out.println("Last Name: "+internationalPartners.getLastName());
      System.out.println("Business Name :"+internationalPartners.getLegalBusinessName());
      System.out.println("Phone : "+internationalPartners.getPhone());
      System.out.println(" prefix :"+internationalPartners.getPrefix());
      System.out.println("State:"+internationalPartners.getStateOrProvince());
      System.out.println("webSite"+internationalPartners.getWebsite());
      System.out.println("Sending Currently : "+internationalPartners.getCurrentlySendingParticipant());

      if (internationalPartners.getTypeOfPrograms() != null && internationalPartners.getTypeOfPrograms().size() > 0)
         for (String s : internationalPartners.getTypeOfPrograms()) {
            System.out.println("Type Of program : "+s);
         }
      if (internationalPartners.getPrograms() != null && internationalPartners.getPrograms().size() > 0)
         for (String s : internationalPartners.getPrograms()) {
            System.out.println("Programs"+s);
         }
      System.out.println("Sending Currently : "+internationalPartners.getCurrentlySendingParticipant());
      System.out.println("Years  IN Business :"+internationalPartners.getYearsInBusiness());
   }

   @Override
   public String GenerateNewHostFamily(HostFamilyData HostFamilyData) {
      try {
         LOGGER.info("Generate New Host Family");

         System.out.println("Generate New Host Family");

         if (HostFamilyData != null) {
            System.out.println("FName :" + HostFamilyData.getFirstName());
            System.out.println("LName :" + HostFamilyData.getLastName());
            System.out.println("Email :" + HostFamilyData.getEmail());
            System.out.println("City :" + HostFamilyData.getCity());
            System.out.println("State : " + HostFamilyData.getState());
         }
         if (HostFamilyData.getEmail().equalsIgnoreCase("success@gmail.com")) {
            String string = "200:Success";
            System.out.println(string);
            return string;
         } else if (HostFamilyData.getEmail().equalsIgnoreCase("duplicate@gmail.com")) {
            String string = "400:Duplicate Row";
            System.out.println(string);
            return string;
         } else if (HostFamilyData.getEmail().equalsIgnoreCase("failed@gmail.com")) {
            String string = "500:Failed To Process Record ! Contact Admin";
            System.out.println(string);
            return string;
         } else {
            String string = "300:Missing Information";
            System.out.println(string);
            return string;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         String string = "700:Internal Error";
         System.out.println(string);
         return string;
      }

   }

   @Override
   public String GenerateNewAreaRepresentative(AreaRepresentativeData AreaRepresentativeData) {
      try {
         LOGGER.info("Generate New Area Representative");
         System.out.println("Generate New Area Representative");

         if (AreaRepresentativeData != null) {
            System.out.println("FName :" + AreaRepresentativeData.getFirstName());
            System.out.println("LName :" + AreaRepresentativeData.getLastName());
            System.out.println("Email :" + AreaRepresentativeData.getEmail());
            System.out.println("City :" + AreaRepresentativeData.getCity());
            System.out.println("State : " + AreaRepresentativeData.getState());
         }
         if (AreaRepresentativeData.getEmail().equalsIgnoreCase("success@gmail.com")) {
            String string = "200:Success";
            System.out.println(string);
            return string;
         } else if (AreaRepresentativeData.getEmail().equalsIgnoreCase("duplicate@gmail.com")) {
            String string = "400:Duplicate Row";
            System.out.println(string);
            return string;
         } else if (AreaRepresentativeData.getEmail().equalsIgnoreCase("failed@gmail.com")) {
            String string = "500:Failed To Process Record ! Contact Admin";
            System.out.println(string);
            return string;
         } else {
            String string = "300:Missing Information";
            System.out.println(string);
            return string;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         String string = "700:Internal Error";
         System.out.println(string);
         return string;
      }
   }

   @Override
   public Boolean IsEmailExist(String Email) {
      try {
         System.out.println("IsEmailExist is Called !!! ");
         System.out.println("Email : " + Email);
         Login user = loginRepository.findByEmail(Email.trim());
         System.out.println(user);
         if (user != null) {
            return true;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return false;
      }
      return false;
   }

   @Override
   public Boolean IsLegalNameExist(String LegalName) {
      try {
         System.out.println("IsLegalNameExist is Called !!! ");
         System.out.println("Legal Name:" + LegalName);
         PartnerAgentInquiry legalNameDuplicate = partnerAgentInquiryRepository.findByLegalName(LegalName.trim());
         if (legalNameDuplicate != null) {
            System.out.println("TRUE");
            return true;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         System.out.println("FALSE");
         return false;
      }
      System.out.println("FALSE");
      return false;
   }

   @Override
   public Boolean IsWebSiteExist(String WebSite) {
      try {
         System.out.println("IsWebSiteExist is Called !!! ");
         WebSite = WebSite.replaceAll("http://|https://|/$", "");
         String secondFormatOfWebSite = "";
         if (WebSite.toLowerCase().startsWith("www"))
            secondFormatOfWebSite = WebSite.replaceAll("^www\\.", "");
         else
            secondFormatOfWebSite = "www." + WebSite;
         PartnerAgentInquiry webSiteDuplicate = partnerAgentInquiryRepository.findByWebSite(WebSite.toLowerCase().trim(), secondFormatOfWebSite.toLowerCase().trim());
         if (webSiteDuplicate != null) {
            System.out.println("TRUE");
            return true;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         System.out.println("FALSE");
         return false;
      }
      System.out.println("FALSE");
      return false;
   }

   public static void main(String[] args) {
      System.out.println("www.google.com".replaceAll("^www\\.", ""));
   }
}
