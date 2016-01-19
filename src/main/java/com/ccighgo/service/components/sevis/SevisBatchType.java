package com.ccighgo.service.components.sevis;

import java.io.File;
import java.util.EnumSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.sevis.BatchType;
import com.ccighgo.service.transport.sevis.CreateSEVISBatch;

import gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISBatchCreateUpdateEV;
import gov.ice.xmlschema.sevisbatch.student.SEVISBatchCreateUpdateStudent;

/**
 * 
 * A proxy of JAXB class {@link BatchType} but it adds data fetch behavior
 * specific to a batch type.
 *
 */
public enum SevisBatchType implements SevisBatchSchema,SevisBatchDataProvider {

	CREATE_STUDENT(BatchType.CREATE_STUDENT) {

		@SuppressWarnings("unchecked")
		@Override
		public SEVISBatchCreateUpdateStudent fetchBatchData(Object params) {
			return dataService.getCreateStudentsBatchData((CreateSEVISBatch) params);
		}

		@Override
		public String getSchemaFile() {
			String schemaDir = SevisUtils.getProperty(SevisConstants.SCHEMA_DIR);
			String schemaFile = SevisUtils.getProperty(SevisConstants.STU_SCHEMA);
			return schemaDir + File.separator + schemaFile;
		}
	},

	UPDATE_EV_BIOGRAPHICAL(BatchType.UPDATE_EV_BIOGRAPHICAL) {

		@Override
		public String getSchemaFile() {
			String schemaDir = SevisUtils.getProperty(SevisConstants.SCHEMA_DIR);
			String schemaFile = SevisUtils.getProperty(SevisConstants.EV_SCHEMA);
			return schemaDir + File.separator + schemaFile;
		}

		@SuppressWarnings("unchecked")
		@Override
		public SEVISBatchCreateUpdateEV fetchBatchData(Object params) {
			return dataService.getUpdateEVBiographicalBatchData((CreateSEVISBatch) params);
		}

	};

	private static BatchDataService dataService;
	private BatchType value;

	private SevisBatchType(BatchType value) {
		this.value = value;
	}

	public static SevisBatchType fromValue(BatchType v) {
		for (SevisBatchType type : SevisBatchType.values()) {
			if (type.value == v) {
				return type;
			}
		}
		throw new IllegalArgumentException(v.value());
	}

	public static void setSevisBatchDataService(BatchDataService sevisBatchDataService) {
		SevisBatchType.dataService = sevisBatchDataService;
	}

	// /**
	// * Fetch batch type specific data from DB and convert it into respective
	// * JAXB classes ({@link SEVISBatchCreateUpdateStudent} or
	// * {@link SEVISBatchCreateUpdateEV}).
	// *
	// * @param params
	// * @return
	// */
	// public abstract <T> T fetchBatchData(Object params);
	//
	// public abstract String getSchemaFile();

	@Component
	public static class DataServiceInjector {
		@Autowired
		BatchDataService service;

		@PostConstruct
		public void postConstruct() {
			for (SevisBatchType type : EnumSet.allOf(SevisBatchType.class))
				type.setSevisBatchDataService(service);
		}
	}

}
