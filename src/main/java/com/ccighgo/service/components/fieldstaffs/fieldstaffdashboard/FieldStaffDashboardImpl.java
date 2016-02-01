package com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffWorkQueueCategory;
import com.ccighgo.db.entities.FieldStaffWorkQueueCategoryAggregate;
import com.ccighgo.db.entities.FieldStaffWorkQueueType;
import com.ccighgo.db.entities.FieldStaffWorkQueueTypeAggregate;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueCategoriesRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueCategoryAggregateRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueTypeAggregateRepository;
import com.ccighgo.jpa.repositories.FieldStaffWorkQueueTypeRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.FieldStaffMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminSeasonConstants;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboard;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardCategorieDetails;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardType;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboardTypes;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.utils.CCIConstants;

@Component
public class FieldStaffDashboardImpl implements FieldStaffDashboardInterface {

	private static final Logger LOGGER = Logger.getLogger(FieldStaffDashboardInterface.class);
	@Autowired
	FieldStaffWorkQueueTypeRepository fieldStaffWorkQueueTypeRepository;
	@Autowired
	FieldStaffWorkQueueTypeAggregateRepository fieldStaffWorkQueueTypeAggregateRepository;
	@Autowired
	FieldStaffWorkQueueCategoryAggregateRepository fieldStaffWorkQueueCategoryAggregateRepository;
	@Autowired
	FieldStaffWorkQueueCategoriesRepository fieldStaffWorkQueueCategoriesRepository;
	@Autowired
	CommonComponentUtils componentUtils;
	@Autowired
	MessageUtils messageUtil;
	@Autowired
	EmailServiceImpl email;
	@Autowired
	LoginRepository loginRepository;

	@Autowired
	FieldStaffRepository fieldStaffRepository;

	@Override
	public ErdDashboard getErdDashboardWorkQueues(String fieldStaffGoId) {

		ErdDashboard erdDashboard = new ErdDashboard();
		ErdDashboardTypes erdDashboardTypes = new ErdDashboardTypes();
		try {
			List<FieldStaffWorkQueueType> fieldStaffWorkQueueTypes = fieldStaffWorkQueueTypeRepository.findAll();
			if (fieldStaffWorkQueueTypes != null)
				for (FieldStaffWorkQueueType type : fieldStaffWorkQueueTypes) {
					ErdDashboardType t = new ErdDashboardType();
					t.setTypeId(type.getFieldStaffWQTypeId());
					t.setType(type.getFieldStaffWQTypeName());
					FieldStaffWorkQueueTypeAggregate aggregate = fieldStaffWorkQueueTypeAggregateRepository.getTypeAggregate(Integer.valueOf(fieldStaffGoId), type.getLookupDepartmentProgram()
							.getLookupDepartmentProgramId(), type.getFieldStaffWQTypeId());
					t.setNumber(aggregate.getFieldStaffWQTypeAggregate().toString());
					List<FieldStaffWorkQueueCategory> FieldStaffWorkQueueCategories = fieldStaffWorkQueueCategoriesRepository.findAllCategoriesByTypeId(type.getFieldStaffWQTypeId());
					for (FieldStaffWorkQueueCategory catagory : FieldStaffWorkQueueCategories) {
						ErdDashboardCategorieDetails details = new ErdDashboardCategorieDetails();
						details.setCategorieId(catagory.getFieldStaffWQCategoryId());
						details.setCategorie(catagory.getFieldStaffWQCategoryName());
						FieldStaffWorkQueueCategoryAggregate catAggregate = fieldStaffWorkQueueCategoryAggregateRepository.getCategoryAggregate(Integer.valueOf(fieldStaffGoId), catagory
								.getFieldStaffWorkQueueType().getLookupDepartmentProgram().getLookupDepartmentProgramId(), catagory.getFieldStaffWQCategoryId());
						details.setNumber(catAggregate.getFieldStaffWQCategoryAggregate().toString());
						t.getCategories().add(details);
					}
					erdDashboardTypes.getTypes().add(t);
				}
			erdDashboard.setErdDashboardTypes(erdDashboardTypes);
			erdDashboard.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		} catch (Exception e) {
			e.printStackTrace();
			erdDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_DASHBOARD.getValue(),
					messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_DASHBOARD)));
			LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_DASHBOARD));
		}
		return erdDashboard;
	}

	@Override
	public Response resetFieldStaffUserPassword(String fsGoId, HttpServletRequest request) {
		Response response = new Response();
		if (fsGoId == null) {
			response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_FIELD_STAFF.getValue(),
					messageUtil.getMessage(FiledStaffConstants.INVALID_FIELD_STAFF_ID)));
			LOGGER.error(messageUtil.getMessage(FiledStaffConstants.INVALID_FIELD_STAFF_ID));
			return response;
		} else {
			try {
				FieldStaff fieldStaff = fieldStaffRepository.findOne(Integer.valueOf(fsGoId));

				if (fieldStaff != null) {
					Login login = loginRepository.findByCCIGoId(fieldStaff.getGoIdSequence().getGoId());
					if (login != null) {
						String body = "<p>Ciao! </p>" + "<p>This email was sent automatically by Greenheart Online (GO) in response to your request for a new password. </p>" + "<p>"
								+ "Your username is : " + login.getLoginName() + "</p>" + "<p>Please click on the link below to create a new password:</p> " + "<p>"
								+ formResetURL(request).concat(login.getKeyValue()) + "</p>" + "<p>If you didn't request a new password, please let us know.</p>"
								+ "<p>Thank you,</p>" + "<p>CCI Greenheart.</p>";
						email.send(login.getEmail(), CCIConstants.RESET_PASSWORD_SUBJECT, body, true);
						response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(), "An email has been sent to address "
								+ "\'" + login.getEmail() + "\'" + " for login name " + "\'" + login.getLoginName() + "\'" + " with instructions to reset password"));
					} else {
						response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
						LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
					}
				}
				else {
					response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
					LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
				}
			} catch (CcighgoException e) {
				response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_FIELD_STAFF.getValue(),
						messageUtil.getMessage(FiledStaffConstants.ERROR_RESETING_PASSWORD_FOR_FIELD_STAFF)));
				LOGGER.error(messageUtil.getMessage(FiledStaffConstants.ERROR_RESETING_PASSWORD_FOR_FIELD_STAFF));
			}
		}
		return response;
	}

	private String formResetURL(HttpServletRequest request) {
		String url = "";
		try {
			url = request.getHeader("Origin") + CCIConstants.RESET_PASSWORD_LINK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
