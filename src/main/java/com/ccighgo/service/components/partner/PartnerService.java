/**
 * 
 */
package com.ccighgo.service.components.partner;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.integration.thirdparty.beans.partnerLeadViewForPartnerInquiryData.PartnerRecruitmentLead;
import com.ccighgo.service.transport.partner.beans.partnercapdetails.PartnerCAPDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerDashboard;
import com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerF1Dashboard;
import com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerIHPDashboard;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSDashboard;
import com.ccighgo.service.transport.partner.beans.partnerwntdetails.PartnerWnTDashboard;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerService {

   /**
    * Service to load partner dashboard.
    * 
    * @param userId
    * @return
    */
   public PartnerDashboard getPartnerDashboard(String partnerGoId);

   /**
    * Load Partner J1 Dashboard data.
    * 
    * @param partnerGoId
    * @return
    */
   public PartnerJ1HSDashboard getJ1HSDashboard(String partnerGoId);

   /**
    * Load Partner F1 Dashboard data.
    * 
    * @param partnerGoId
    * @return
    */
   public PartnerF1Dashboard getF1Dashboard(String partnerGoId);

   /**
    * Load Partner IHP Dashboard data.
    * 
    * @param partnerGoId
    * @return
    */
   public PartnerIHPDashboard getIHPDashboard(String partnerGoId);

   /**
    * Load Partner IHP Dashboard data.
    * 
    * @param partnerGoId
    * @return
    */
   public PartnerCAPDashboard getWnTDashboard(String partnerGoId);

   /**
    * Load Partner Cap Dashboard data.
    * 
    * @param partnerGoId
    * @return
    */
   public PartnerWnTDashboard getCAPDashboard(String partnerGoId);

   /**
    * Load Partner Inquiry Lead Data.
    * 
    * @param partnerAgentGoId
    * @return
    */
   public PartnerRecruitmentLead getPartnerInquiryLeadData(String partnerAgentGoId);

}
