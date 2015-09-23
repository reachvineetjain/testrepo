/**
 * 
 */
package com.ccighgo.service.components.partner.company;

import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetail;

/**
 * @author ravi
 *
 */
public interface PartnerCompanyService {

   PartnerCompanyDetail getPartnerCompanyDetails(String partnerGoId);

}
