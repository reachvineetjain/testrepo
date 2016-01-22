package com.ccighgo.service.components.sevis.data;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.sevis.BatchParam;

@Service
public interface IBatchDataService<T> {
	public T fetchBatchData(BatchParam batchParam);
}