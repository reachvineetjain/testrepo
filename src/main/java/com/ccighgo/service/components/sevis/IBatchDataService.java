package com.ccighgo.service.components.sevis;

import org.springframework.stereotype.Service;

@Service
public interface IBatchDataService<T, V> {
	public T fetchBatchData(V batchParam);
}