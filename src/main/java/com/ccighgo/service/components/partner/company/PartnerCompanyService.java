/**
 * 
 */
package com.ccighgo.service.components.partner.company;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetail;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerCompanyService {

   PartnerCompanyDetail getPartnerCompanyDetails(String partnerGoId);
   
   PartnerCompanyDetail updatePartnerCompanyDetails(PartnerCompanyDetail partnerCompanyDetail);

}
