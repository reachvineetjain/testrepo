package com.ccighgo.service.components.season;

import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonProgram;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;

public interface SeasonServiceInterface {

   public SeasonsList getAllSeasons();

   public SeasonBean createSeason(SeasonBean seasonBean);

   public String deleteSeason(String id);

   public SeasonBean editSeason(String id);

   public SeasonBean viewSeason(String id);

   public SeasonBean updateSeason(SeasonBean seasonBean);

   /**
    * Returns list of season programs for a department
    * 
    * @param seasonId
    * @return
    */
   public SeasonProgram getSeasonPrograms(String seasonId);
   
   /**     
    * Method fetches list of available season status
    *  
    * @return SeasonStatuses
    */
   public SeasonStatuses getSeasonStatus();

}
