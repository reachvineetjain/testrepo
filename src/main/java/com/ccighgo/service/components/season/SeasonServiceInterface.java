package com.ccighgo.service.components.season;

import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;


public interface SeasonServiceInterface {
    //this interface is a stub
    public String getString();
    
    public SeasonsList getAllSeasons();
    
    public  SeasonBean createSeason(SeasonBean seasonBean);
    
    public void deleteSeason(String id);
    
    public void editSeason(String id);
    
    public SeasonBean viewSeason(String id);
    
    public void updateSeason(String id );
    

}
