/**
 * 
 */
package com.ccighgo.service.components.partner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.db.entities.PartnerAgentProgram;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerReviewStatusRepository;
import com.ccighgo.service.components.participants.ParticipantsInterfaceImpl;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.PartnerAdminDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDashboardSections;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDetails;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerPrograms;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.PartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.PartnerWorkQueueType;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;


/**
 * @author ravi
 *
 */
@Component
public class PartnerServiceImpl implements PartnerService {

   private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PartnerServiceImpl.class);

   @Autowired
   PartnerAgentInquiryRepository  partnerAgentInquiryRepository;
   
   @Autowired
   PartnerReviewStatusRepository partnerReviewStatusRepository;
   
   @Override
   public PartnerDetails getPartnerDetails(String userId) {
      PartnerDetails partnerDetails = new PartnerDetails();
      partnerDetails.setPartnerFirstName("Super");
      partnerDetails.setPartnerLastName("Man");
      partnerDetails.setPartnerAddress("Planet Krypton");
      partnerDetails.setPartnerCountry("Doesn't matter");
      partnerDetails.setPartnerCompany("Save the world");
      partnerDetails.setPartnerEmail("superman@planetkrypton.com");
      partnerDetails.setPartnerId(007);
      partnerDetails.setPartnerLogo("Logo stolen by batman");
      List<PartnerPrograms> partnerPrograms = new ArrayList<PartnerPrograms>();
      PartnerPrograms pp = new PartnerPrograms();
      pp.setPartnerDepartmentId("1");
      pp.setPartnerDepartmentName("Save the world");
      pp.setPartnerProgramId("1");
      pp.setPartnerProgramName("Help Joker");
      PartnerPrograms pp1 = new PartnerPrograms();
      pp1.setPartnerDepartmentId("1");
      pp1.setPartnerDepartmentName("Save the world");
      pp1.setPartnerProgramId("1");
      pp1.setPartnerProgramName("Help Joker");
      partnerPrograms.add(pp);
      partnerPrograms.add(pp1);
      List<PartnerDashboardSections> dashboardSections = new ArrayList<PartnerDashboardSections>();
      return partnerDetails;
   }
 

   @Override
   public PartnerAdminDashboard getDashboard() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public WSDefaultResponse changePartnerApplicationStatus() {
      // TODO Auto-generated method stub
      return null;
   }


   @Override
   public WSDefaultResponse updatePartnerApplicationFollowUpDate() {
      // TODO Auto-generated method stub
      return null;
   }


   @Override
   public PartnerRecruitmentAdmin getAgentRecruitmentData() {
      // TODO Auto-generated method stub
      return null;
   }


   @Override
   public PartnerWorkQueueType getWorkQueueType() {
      // TODO Auto-generated method stub
      return null;
   }


   @Override
   public PartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int partnerAgentGoId) {
      PartnerWorkQueueSubmittedApplications pwqa = new PartnerWorkQueueSubmittedApplications();
      try {
        List<PartnerAgentInquiry> result = partnerAgentInquiryRepository.findPartnerByPartnerId(partnerAgentGoId);
        if(result!=null){
           for (PartnerAgentInquiry partnerAgentInquiry : result) {
            PartnerWorkQueueSubmittedApplicationsDetail pd = new  PartnerWorkQueueSubmittedApplicationsDetail();
            pd.setCompanyName(partnerAgentInquiry.getCompanyName());
            pd.setCountry(partnerAgentInquiry.getLookupCountry().getCountryName());
            pd.setEmail(partnerAgentInquiry.getEmail());
            pd.setFirstName(partnerAgentInquiry.getFirstName());
            pd.setFlagUrl(partnerAgentInquiry.getCountryFlag());
            pd.setFollowUpDate(DateUtils.getDateAndTime(partnerAgentInquiry.getFollowUpDate()));
            pd.setLastName(partnerAgentInquiry.getLastName());
            pd.setPhone(partnerAgentInquiry.getPhone());
            pd.setPrograms(getPartnerPrograms(partnerAgentInquiry.getPartnerAgentPrograms()));
            pd.setSunmittedOn(DateUtils.getDateAndTime(partnerAgentInquiry.getSubmittedOn()));
            pd.setWebsite(partnerAgentInquiry.getWebsite());
            //PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findOne(partnerAgentInquiry.getPartner().getpartnerre));
            //pd.setStatus();
         }
        }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return pwqa;
   }


   private String getPartnerPrograms(List<PartnerAgentProgram> partnerAgentPrograms) {
      StringBuilder st= new StringBuilder();
      int index =0;
      if(partnerAgentPrograms!=null)
      for (PartnerAgentProgram partnerAgentProgram : partnerAgentPrograms) {
         if(index++ >0)
            st.append(",");
         st.append(partnerAgentProgram.getDepartmentProgram().getProgramName());
      }
      return null;
   }


   @Override
   public PartnerWorkQueueCategory getWorkQueueCategory() {
      // TODO Auto-generated method stub
      return null;
   }

}
