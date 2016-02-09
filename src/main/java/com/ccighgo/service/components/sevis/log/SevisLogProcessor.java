package com.ccighgo.service.components.sevis.log;

import java.io.File;

import org.springframework.stereotype.Component;

import com.ccighgo.service.components.sevis.common.SevisUtils;
import com.ccighgo.service.transport.common.response.beans.Response;

import gov.ice.xmlschema.sevisbatch.log.TransactionLogType;
import gov.ice.xmlschema.sevisbatch.log.TransactionLogType.BatchDetail.Process.Record;

@Component
public class SevisLogProcessor implements ISevisLogProcessor {

	@Override
	public Response processLog(File xmlFile) {
		// test log file
		TransactionLogType log = SevisUtils
				.unmarshalSevisLog(new File("C:\\git\\CCIGHGO\\xsd\\sevisbatch\\gov\\SevisTransLog.xml"));

		SevisLogUtils logUtils = new SevisLogUtils(log);
		System.out.println("Total participants processed = " + logUtils.getTotalRecords().size());
		System.out.println("Processing failed participants = " + logUtils.getFailedRecords().size());

		for (Record r : logUtils.getFailedRecords()) {
			System.out.println("failed participant sevis = " + r.getRequestID());
			System.out.println("Error = " + r.getResult().getErrorMessage());
		}

		for (Record r : logUtils.getSuccessRecords()) {
			System.out.println("New participant sevis id = " + r.getSevisID());
			System.out.println("Any Dependents? = " + r.getDependent().size());
			System.out.println("Dependent sevis id = " + r.getDependent().get(0).getDependentSevisID());
		}

		// TODO convert log JAXB into appropriate format required for front.
		// or save to DB

		return new Response();
	}

}
