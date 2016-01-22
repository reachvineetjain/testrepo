package com.ccighgo.service.components.sevis;

import javax.servlet.ServletContext;

import com.ccighgo.service.components.sevis.data.IBatchDataService;
import com.ccighgo.service.transport.sevis.BatchDetails;
import com.ccighgo.service.transport.sevis.BatchParam;

public interface IBatchGenerator<T> {
	public BatchDetails createBatch(BatchParam batchParam, IBatchDataService<T> batchDataService,
			ServletContext servletContext);

	public String getBatchSchemaFile();

}
