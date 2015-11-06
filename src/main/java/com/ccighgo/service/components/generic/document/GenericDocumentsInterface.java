package com.ccighgo.service.components.generic.document;

import java.util.List;

import com.ccighgo.service.transport.generic.beans.documents.partner.PartnerGenericDocuments;
import com.ccighgo.utils.WSDefaultResponse;

public interface GenericDocumentsInterface {
	public List<PartnerGenericDocuments> viewPartnerDocument(String partnerId);

	WSDefaultResponse addPartnerDocument(
			PartnerGenericDocuments partnerGenericDocuments);
}
