/**
 * 
 */
package com.ccighgo.service.components.partner.admin.season;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.PartnerAdminF1SeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.PartnerAdminIHPSeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Document;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreement;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerAdminJ1SeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeasonList;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.status.PartnerSeasonStatus;
import com.ccighgo.service.transport.partner.beans.partner.season.admin.application.PartnerAdminSeasonApplicationList;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerAdminSeasonInterface {

   /**
    * @param partnerGoId
    * @return
    */
   public PartnerAdminSeasonList getPartnerAdminSeasons(String partnerGoId);

   /**
    * @param partnerGoId
    * @return
    */
   public PartnerAdminSeasonApplicationList getPartnerAdminSeasonApplicationList(String partnerGoId);

   /**
    * @param partnerGoId
    * @param partnerSeasonId
    * @return
    */
   public PartnerAdminJ1SeasonDetails getPartnerAdminJ1Details(String partnerGoId, String partnerSeasonId);

   /**
    * @param partnerGoId
    * @param partnerSeasonId
    * @return
    */
   public PartnerAdminF1SeasonDetails getPartnerAdminF1Details(String partnerGoId, String partnerSeasonId);

   /**
    * @param partnerGoId
    * @param partnerSeasonId
    * @return
    */
   public PartnerAdminIHPSeasonDetails getPartnerAdminIHPDetails(String partnerGoId, String partnerSeasonId);

   /**
    * @param statusVal
    * @param partnerSeasonId
    * @return
    */
   public Response updatePartnerSeasonActiveStatus(String statusVal, String partnerSeasonId);

   /**
    * @param statusVal
    * @param partnerSeasonId
    * @return
    */
   public Response updateSeasonActiveStatus(String statusVal, String partnerSeasonId);

   /**
    * @param partnerAdminSeasonApplicationList
    * @return
    */
   public Response addNewSeasonsToPartner(PartnerAdminSeasonApplicationList partnerAdminSeasonApplicationList);

   /**
    * @param partnerAdminJ1SeasonDetails
    * @return
    */
   public PartnerAdminJ1SeasonDetails updateJ1AdminSeason(PartnerAdminJ1SeasonDetails partnerAdminJ1SeasonDetails);

   /**
    * @param partnerAdminF1SeasonDetails
    * @return
    */
   public PartnerAdminF1SeasonDetails updateF1AdminSeason(PartnerAdminF1SeasonDetails partnerAdminF1SeasonDetails);

   /**
    * @param partnerAdminIHPSeasonDetails
    * @return
    */
   public PartnerAdminIHPSeasonDetails updateIHPAdminSeason(PartnerAdminIHPSeasonDetails partnerAdminIHPSeasonDetails);

   /**
    * @param loginId
    * @param partnerSeasonId
    * @param doc
    * @return
    */
   public Response addAdminSeasonDocument(String loginId, String partnerSeasonId, Document doc);

   /**
    * @param loginId
    * @param partnerSeasonId
    * @param contract
    * @return
    */
   public Response addSeasonOperatingAgreement(String loginId, String partnerSeasonId, OperatingAgreement contract);

   /**
    * @param partnerSeasonDocumentId
    * @return
    */
   public Response deleteAdminSeasonDocument(String partnerSeasonDocumentId);

   /**
    * @param partnerSeasonContractId
    * @return
    */
   public Response deleteAdminSeasonAgreement(String partnerSeasonContractId);

   /**
    * @return
    */
   public PartnerSeasonStatus getPartnerSeasonStatuses();
}
