/**
 * 
 */
package com.ccighgo.service.components.partner.admin.season;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.PartnerAdminF1SeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Document;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreement;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerAdminJ1SeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeasonList;
import com.ccighgo.service.transport.partner.beans.partner.season.admin.application.PartnerAdminSeasonApplicationList;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerAdminSeasonInterface {
   
   public PartnerAdminSeasonList getPartnerAdminSeasons(String partnerGoId);

   public PartnerAdminSeasonApplicationList getPartnerAdminSeasonApplicationList(String partnerGoId);

   public PartnerAdminJ1SeasonDetails getPartnerAdminJ1Details(String partnerGoId, String partnerSeasonId);

   public PartnerAdminF1SeasonDetails getPartnerAdminF1Details(String partnerGoId, String partnerSeasonId);

   public Response updatePartnerSeasonActiveStatus(String statusVal, String partnerSeasonId);

   public Response updateSeasonActiveStatus(String statusVal, String partnerSeasonId);

   public Response addNewSeasonsToPartner(PartnerAdminSeasonApplicationList partnerAdminSeasonApplicationList);

   public PartnerAdminJ1SeasonDetails updateJ1AdminSeason(PartnerAdminJ1SeasonDetails partnerAdminJ1SeasonDetails);

   public PartnerAdminF1SeasonDetails updateF1AdminSeason(PartnerAdminF1SeasonDetails partnerAdminF1SeasonDetails);

   public Response addAdminSeasonDocument(String loginId, String partnerSeasonId, Document doc);

   public Response addSeasonOperatingAgreement(String loginId, String partnerSeasonId, OperatingAgreement contract);

   public Response deleteAdminSeasonDocument(String partnerSeasonDocumentId);

   public Response deleteAdminSeasonAgreement(String partnerSeasonContractId);

}
