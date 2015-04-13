package com.ccighgo.services.components.season;

import com.ccighgo.seasons.front.SeasonProgramDTO;
import com.ccighgo.seasons.front.SeasonSearchResponse;

public interface SeasonServiceInterface {
    
    public SeasonSearchResponse getAllSeasons();
    
    public SeasonSearchResponse createSeason(SeasonProgramDTO dto);
    
    public SeasonSearchResponse deleteSeason(String id);
    
    public SeasonProgramDTO editSeason(String id);
    
    public SeasonProgramDTO viewSeason(String id);
    
    public SeasonSearchResponse updateSeason(String id,SeasonProgramDTO dto );
    

}
