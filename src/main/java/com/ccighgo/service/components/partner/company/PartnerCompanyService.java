/**
 * 
 */
package com.ccighgo.service.components.partner.company;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.add.partner.office.NewPartnerOffice;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetail;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerCompanyService {

   PartnerCompanyDetail getPartnerCompanyDetails(String partnerGoId);
   
   PartnerCompanyDetail updatePartnerCompanyDetails(PartnerCompanyDetail partnerCompanyDetail);

   Response addNewPartnerOffice(String partnerGoId, NewPartnerOffice newPartnerOffice);

}
