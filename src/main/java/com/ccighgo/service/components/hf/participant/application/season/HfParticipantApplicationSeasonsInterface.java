package com.ccighgo.service.components.hf.participant.application.season;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.hostfamily.beans.seasons.list.HFParticipantSeasonList;

/**
 *
 * @author vijay
 *
 */

@Service
public interface HfParticipantApplicationSeasonsInterface {

   public HFParticipantSeasonList getHFParticipantSeasonList(String goId);

}
