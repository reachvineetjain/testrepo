/**
 * 
 */
package com.ccighgo.service.components.partner;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.LookupDepartmentProgram;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerMessagesRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerReferenceCheckRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerReviewStatusRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.PartnerAdminDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDetails;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class PartnerServiceImpl implements PartnerService {

   private static final Logger LOGGER = Logger.getLogger(PartnerServiceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired PartnerRepository partnerRepository;
   @Autowired LookupDepartmentProgramRepository lookupDepartmentProgramRepository;
  
   @Autowired
   PartnerAgentInquiryRepository partnerAgentInquiryRepository;
   @Autowired
   PartnerProgramRepository partnerProgramRepository;
   @Autowired
   PartnerMessagesRepository partnerMessagesRepository;
   @Autowired
   PartnerOfficeRepository partnerOfficeRepository;
   @Autowired
   PartnerReviewStatusRepository partnerReviewStatusRepository;
   @Autowired
   PartnerReferenceCheckRepository partnerReferenceCheckRepository;
   @Autowired
   PartnerDocumentsRepository partnerDocumentsRepository;
   @Autowired
   PartnerNoteRepository partnerNoteRepository;
   @Override
   public PartnerDashboard getPartnerDashboard(String partnerGoId) {
      PartnerDashboard partnerDashboard = new PartnerDashboard();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return partnerDashboard;
      } else {
         try {
            Partner partner = partnerRepository.findOne(Integer.valueOf(partnerGoId));
            if (partner != null) {
               partnerDashboard.setPartnerId(partner.getPartnerGoId());
               partnerDashboard.setPartnerCompany(partner.getCompanyName());
               List<PartnerSeason> partnerSeasons = partner.getPartnerSeasons();
               if (partnerSeasons != null && partnerSeasons.size() > 0) {
                  List<com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram> partnerProgramsList = new ArrayList<com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram>();
                  for (PartnerSeason partnerSeason : partnerSeasons) {
                     com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram prg = new com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram();
                     prg.setPartnerSeasonId(partnerSeason.getSeason().getSeasonId());
                     List<LookupDepartmentProgram> lkDeptPrgList =  lookupDepartmentProgramRepository.findAll();
                     for(LookupDepartmentProgram deptPrg:lkDeptPrgList){
                        if(deptPrg.getProgramName().equals(CCIConstants.HSP_J1_HS) && deptPrg.getLookupDepartmentProgramId()==CCIConstants.HSP_J1_HS_ID){
                           prg.setPartnerDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.HSP_J1_HS);
                           prg.setProgramDetailsUrl("partner/j1/program/details/");
                        }
                        if(deptPrg.getProgramName().equals(CCIConstants.HSP_F1) && deptPrg.getLookupDepartmentProgramId()==CCIConstants.HSP_F1_ID){
                           prg.setPartnerDepartmentProgramId(CCIConstants.HSP_F1_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.HSP_F1);
                           prg.setProgramDetailsUrl("partner/f1/program/details/");
                        }
                        if(deptPrg.getProgramName().equals(CCIConstants.HSP_STP_IHP) && deptPrg.getLookupDepartmentProgramId()==CCIConstants.HSP_STP_IHP_ID){
                           prg.setPartnerDepartmentProgramId(CCIConstants.HSP_STP_IHP_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.HSP_STP_IHP);
                           prg.setProgramDetailsUrl("partner/ihp/program/details/");
                        }
                        if(deptPrg.getProgramName().equals(CCIConstants.WP_WT_CAP) && deptPrg.getLookupDepartmentProgramId()==CCIConstants.WP_WT_CAP_ID){
                           prg.setPartnerDepartmentProgramId(CCIConstants.WP_WT_CAP_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.WP_WT_CAP);
                           prg.setProgramDetailsUrl("partner/cap/program/details/");
                        }
                        if(deptPrg.getProgramName().equals(CCIConstants.WP_WT_SUMMER) && deptPrg.getLookupDepartmentProgramId()==CCIConstants.WP_WT_SUMMER_ID){
                           prg.setPartnerDepartmentProgramId(CCIConstants.WP_WT_SUMMER_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.WP_WT_SUMMER);
                           prg.setProgramDetailsUrl("partner/wnt/summer/program/details/");
                        }
                        if(deptPrg.getProgramName().equals(CCIConstants.WP_WT_WINTER) && deptPrg.getLookupDepartmentProgramId()==CCIConstants.WP_WT_WINTER_ID){
                           prg.setPartnerDepartmentProgramId(CCIConstants.WP_WT_WINTER_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.WP_WT_WINTER);
                           prg.setProgramDetailsUrl("partner/wnt/winter/program/details/");
                        }
                     }
                     partnerProgramsList.add(prg);
                  }
                  partnerDashboard.getPartnerPrograms().addAll(partnerProgramsList);
               } else {
                  // no programs for this partner found
               }
            } else {
               // no partner found with the goid provided
               partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(),
                     messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
               LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
            }
         } catch (CcighgoException e) {
            // error getting partner details
            partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(),
                  messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
            LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
         }
      }
      return partnerDashboard;
   }

   @Override
   public PartnerDetails getJ1HSDashboard(String partnerGoId) {
      return null;
   }

   @Override
   public PartnerDetails getF1Dashboard(String partnerGoId) {
      return null;
   }

   @Override
   public PartnerDetails getIHPDashboard(String partnerGoId) {
      return null;
   }

   @Override
   public PartnerDetails getWnTDashboard(String partnerGoId) {
      return null;
   }

   @Override
   public PartnerDetails getCAPDashboard(String partnerGoId) {
      return null;
   }

   @Override
   public PartnerAdminDashboard getDashboard() {
      // TODO Auto-generated method stub
      return null;
   }


}
