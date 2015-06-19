package com.ccighgo.service.components.season;

import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;


public interface SeasonServiceInterface {
    //this interface is a stub
    public String getString();
    
    public SeasonsList getAllSeasons();
    
    public  SeasonBean createSeason(SeasonBean seasonBean);
    
    public String deleteSeason(String id);
    
    public SeasonBean editSeason(String id);
    
    public SeasonBean viewSeason(String id);
    
    public SeasonBean updateSeason(SeasonBean seasonBean );
    
    public String cloneSeason(String seasonId, String newSeasonName);

}
