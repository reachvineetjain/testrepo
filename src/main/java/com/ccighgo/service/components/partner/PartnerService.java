/**
 * 
 */
package com.ccighgo.service.components.partner;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.PartnerAdminDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDetails;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerService {
   
 PartnerAdminDashboard getDashboard();
   /**
    * Service to load partner dashboard
    * 
    * @param userId
    * @return
    */
   public PartnerDashboard getPartnerDashboard(String partnerGoId);
   
   /**
    * @param partnerGoId
    * @return
    */
   public PartnerDetails getJ1HSDashboard(String partnerGoId);
   
   /**
    * @param partnerGoId
    * @return
    */
   public PartnerDetails getF1Dashboard(String partnerGoId);
   
   /**
    * @param partnerGoId
    * @return
    */
   public PartnerDetails getIHPDashboard(String partnerGoId);
   
   /**
    * @param partnerGoId
    * @return
    */
   public PartnerDetails getWnTDashboard(String partnerGoId);
   
   /**
    * @param partnerGoId
    * @return
    */
   public PartnerDetails getCAPDashboard(String partnerGoId);
   
}
