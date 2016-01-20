package com.ccighgo.service.components.sevis;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.ccighgo.service.component.serviceutils.ApplicationContextProvider;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.sevis.CreateSEVISBatch;

@Service
public interface SevisService {

	public default <T> T getServiceBean(Class<T> klass) {
		return ApplicationContextProvider.getContext().getBean(klass);
	}

//	public Response createBatch(CreateSEVISBatch batchParam, ServletContext context);

	public Response processSevisLog(File xmlFile);

	/**
	 * Create student request.
	 * <p>
	 * Structure used when creating new student records.
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response createStudent(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * Edit exchange visitor biographical information.
	 * <p>
	 * Structure used to edit an existing exchange visitor’s biographical
	 * information.
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateEVBiographical(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * Edit existing site of activity. Update.EV.SiteOfActivity.Edit
	 * <p>
	 * Structure used to provide information on site of activity being updated.
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateEVSOAEdit(CreateSEVISBatch batchParam, ServletContext servletContext);

	public Response updateEVStatusInvalid(CreateSEVISBatch batchParam, ServletContext servletContext);

	public Response updateEVDependentEndStatus(CreateSEVISBatch batchParam, ServletContext servletContext);

	public Response updateEVStatusTerminate(CreateSEVISBatch batchParam, ServletContext servletContext);

	public Response updateStudentStatusTerminate(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * Edit EV's subject or field of study.
	 * <p/>
	 * Structure used to update the subject/field for the exchange visitor.
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateEVProgramEditSubject(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * Update EV financial information
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateEVFinancialInfo(CreateSEVISBatch batchParam, ServletContext servletContext);

	public Response updateEVTIPP(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * Reprint EV Form DS-2019
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateEVReprint(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * Reprint EV.Dependent DS-2019
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateEVDependentReprint(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * Reprint Student I-20
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateStudentReprint(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * Reprint Student.Dependent I-20
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateStudentDependentReprint(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * EV Program Extension within maximum duration of stay.
	 * <p>
	 * Structure used to provide information on an extension to the exchange
	 * visitor’s program, within the maximum duration of stay
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateEVProgramExtension(CreateSEVISBatch batchParam, ServletContext servletContext);

	/***
	 * Shorten EV program.
	 * <p>
	 * Structure used to shorten program before the program end date.
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateEVProgramShorten(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * Extend Student Program.
	 * <p>
	 * Structure used to extend student’s program beyond the original program
	 * end date.
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateStudentProgramExtension(CreateSEVISBatch batchParam, ServletContext servletContext);

	/**
	 * Shorten Student program.
	 * <p>
	 * Structure used to shorten student’s program within the original program
	 * end date.
	 * 
	 * @param batchParam
	 * @param servletContext
	 * @return
	 */
	public Response updateStudentProgramShorten(CreateSEVISBatch batchParam, ServletContext servletContext);
	
	public javax.ws.rs.core.Response downloadBatchFile(String file, ServletContext servletContext);

}
