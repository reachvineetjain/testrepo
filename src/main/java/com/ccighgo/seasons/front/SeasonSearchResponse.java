package com.ccighgo.seasons.front;

import java.util.List;

public class SeasonSearchResponse {

    private List<SeasonProgramDTO> seasonPrograms;

   // private String status;

    /**
     * @return the seasonPrograms
     */
    public List<SeasonProgramDTO> getSeasonPrograms() {
        return seasonPrograms;
    }

    /**
     * @param seasonPrograms
     *            the seasonPrograms to set
     */
    public void setSeasonPrograms(List<SeasonProgramDTO> seasonPrograms) {
        this.seasonPrograms = seasonPrograms;
    }

   /* *//**
     * @return the status
     *//*
    public String getStatus() {
        return status;
    }

    *//**
     * @param status
     *            the status to set
     *//*
    public void setStatus(String status) {
        this.status = status;
    }*/

}
