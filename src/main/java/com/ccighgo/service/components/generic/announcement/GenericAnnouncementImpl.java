package com.ccighgo.service.components.generic.announcement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.DocumentType;
import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffDocument;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonContract;
import com.ccighgo.db.entities.PartnerSeasonDocument;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.PartnerCodes;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.FieldStaffDocumentRepository;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonContractRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonDocumentRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.GenericMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocument;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocumentUpLoadedBy;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocuments;
import com.ccighgo.service.transport.generic.beans.documents.fieldstaff.FieldStaffGenericDocument;
import com.ccighgo.service.transport.generic.beans.documents.fieldstaff.FieldStaffGenericDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partner.DocumentUploadUser;
import com.ccighgo.service.transport.generic.beans.documents.partner.PartnerGenericDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partnerseasonparameters.PartnerSeasonDocumentParameters;
import com.ccighgo.service.transport.generic.beans.documents.seasoncontract.GenericPartnerSeasonContract;
import com.ccighgo.service.transport.generic.beans.documents.seasoncontract.GenericPartnerSeasonContracts;
import com.ccighgo.service.transport.partner.beans.genericannouncement.announcements.GenericAnnoucement;
import com.ccighgo.service.transport.partner.beans.genericquicklink.quicklinks.GenericQuickLinks;
import com.ccighgo.service.transport.partner.beans.genericquicklink.quicklinks.GenericQuickLinksDetails;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quicklinks.PartnerAdminDashboardQuickLinks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quicklinks.PartnerAdminDashboardQuickLinksDetails;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.ExtraData;
import com.ccighgo.utils.WSDefaultResponse;
import com.ccighgo.utils.reuse.function.ReusedFunctions;
import com.ccighgo.utils.reuse.function.pojo.UserInformationOfCreatedBy;

@Component
public class GenericAnnouncementImpl implements GenericAnnouncementInterface {

	private static final Logger logger = LoggerFactory.getLogger(GenericAnnouncementInterface.class);

	@Autowired
	CommonComponentUtils componentUtils;
	@Autowired
	MessageUtils messageUtil;

	@Override
	public GenericAnnoucement fetchAnnouncement() {
		// TODO Auto-generated method stub
		return null;
	}

}
