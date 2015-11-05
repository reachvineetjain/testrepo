package com.ccighgo.service.component.partner.generic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.GenericMessageConstants;
import com.ccighgo.service.components.errormessages.constants.SubPartnerMessageConstants;
import com.ccighgo.service.components.partner.subpartner.SubPartnerInterfaceImpl;
import com.ccighgo.service.transport.partner.beans.generic.deletenote.DeleteNote;
import com.ccighgo.service.transport.partner.beans.generic.notes.ScreeNote;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;

@Component
public class PartnerGenericNote implements PartnerGenericNoteInterface {

	private static final Logger LOGGER = Logger
			.getLogger(SubPartnerInterfaceImpl.class);

	@Autowired
	CommonComponentUtils componentUtils;

	@Autowired
	PartnerRepository partnerRepository;

	@Autowired
	PartnerNoteRepository partnerNoteRepository;

	@Autowired
	PartnerNoteTopicRepository partnerNoteTopicRepository;

	@Autowired
	MessageUtils messageUtil;

	@Override
	public WSDefaultResponse addNote(ScreeNote note) {
		WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
		try {
			PartnerNote noteEntity = new PartnerNote();
			noteEntity.setCreatedBy(note.getUserId());
			noteEntity.setCreatedOn(new java.sql.Timestamp(System
					.currentTimeMillis()));
			noteEntity.setModifiedBy(note.getUserId());

			noteEntity.setModifiedOn(new java.sql.Timestamp(System
					.currentTimeMillis()));
			Partner partner = partnerRepository.findOne(note.getPartnerId());
			noteEntity.setPartner(partner);
			noteEntity.setPartnerNote(note.getNoteValue());

			PartnerNoteTopic partnerNoteTopic = partnerNoteTopicRepository
					.findOne(note.getTopicId());
			noteEntity.setPartnerNoteTopic(partnerNoteTopic);
			
			wsDefaultResponse.setStatus(componentUtils.getStatus(
					CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
					ErrorCode.NOTE_CREATED.getValue(),
					messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
			
			partnerNoteRepository.saveAndFlush(noteEntity);

		} catch (Exception e) {
			ExceptionUtil.logException(e, LOGGER);

			wsDefaultResponse
					.setStatus(componentUtils.getStatus(
							CCIConstants.FAILURE,
							CCIConstants.TYPE_ERROR,
							ErrorCode.FAILED_TO_CREATE_NOTE.getValue(),
							messageUtil
									.getMessage(GenericMessageConstants.FAILED_TO_CREATE_GENERIC_NOTE)));
			LOGGER.error(messageUtil
					.getMessage(GenericMessageConstants.FAILED_TO_CREATE_GENERIC_NOTE));

		}
		return wsDefaultResponse;
	}

	@Override
	public WSDefaultResponse deleteNote(DeleteNote deleteNote) {
		WSDefaultResponse responce= new WSDefaultResponse();
		try
		{
		PartnerNote noteEntity = partnerNoteRepository.findOne(deleteNote.getNoteId());
		
		partnerNoteRepository.delete(noteEntity);
		
		responce.setStatus(componentUtils.getStatus(
				CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
				ErrorCode.NOTE_DELETED.getValue(),
				messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		
		}catch(Exception e)
		{
			ExceptionUtil.logException(e, LOGGER);

			responce
					.setStatus(componentUtils.getStatus(
							CCIConstants.FAILURE,
							CCIConstants.TYPE_ERROR,
							ErrorCode.FAILED_TO_CREATE_NOTE.getValue(),
							messageUtil
									.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_NOTE)));
			LOGGER.error(messageUtil
					.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_NOTE));
		}
		return responce;
	}

}
