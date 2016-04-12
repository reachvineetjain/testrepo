package com.ccighgo.service.components.backgroundcheck;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck;
import com.ccighgo.service.transport.seasons.beans.backgroundcheckstatus.BackgroundReports;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenrequest.ScreenRequest;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse.ScreenResponse;

@Service
public interface BackgroundServiceInterface {
   ScreenResponse requestScreen(ScreenRequest screenRequest);

   BackgroundCheck applyNow();

   String sendReport(BackgroundReports backgroundReports);

   BackgroundCheck applyNow(int hostFamilyId, int hostFamilyMemberId);

}
