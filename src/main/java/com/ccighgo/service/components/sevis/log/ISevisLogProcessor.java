package com.ccighgo.service.components.sevis.log;

import java.io.File;

import com.ccighgo.service.transport.common.response.beans.Response;

public interface ISevisLogProcessor {
	public Response processLog(File xmlFile);

}
