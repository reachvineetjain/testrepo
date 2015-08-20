package com.ccighgo.service.components.backgroundcheck;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.seasons.beans.backgroundscreenrequest.ScreenRequest;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse.ScreenResponse;

@Service
public interface BackgroundServiceInterface {
   ScreenResponse requestScreen(ScreenRequest screenRequest);

   String test(ScreenResponse screenRequest);

}
