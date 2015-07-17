package com.ccighgo.service.components.backgroundcheck;

import com.ccighgo.service.transport.seasons.beans.backgroundscreenrequest.ScreenRequest;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse.ScreenResponse;

public interface BackgroundServiceInterface {

   ScreenResponse requestScreen(ScreenRequest screenRequest);

}
