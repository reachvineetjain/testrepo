package com.ccighgo.seasons.front;

import java.util.Date;

public class SeasonProgramDTO {

    private Integer seasonProgramID;

    private String seasonProgramName;

    private Date seasonStartDate;

    private Date seasonEndDate;

    private String seasonStatus;

    private String programName;

    /**
     * @return the seasonProgramID
     */
    public Integer getSeasonProgramID() {
        return seasonProgramID;
    }

    /**
     * @param seasonProgramID
     *            the seasonProgramID to set
     */
    public void setSeasonProgramID(Integer seasonProgramID) {
        this.seasonProgramID = seasonProgramID;
    }

    /**
     * @return the seasonProgramName
     */
    public String getSeasonProgramName() {
        return seasonProgramName;
    }

    /**
     * @param seasonProgramName
     *            the seasonProgramName to set
     */
    public void setSeasonProgramName(String seasonProgramName) {
        this.seasonProgramName = seasonProgramName;
    }

    /**
     * @return the seasonStartDate
     */
    public Date getSeasonStartDate() {
        return seasonStartDate;
    }

    /**
     * @param seasonStartDate
     *            the seasonStartDate to set
     */
    public void setSeasonStartDate(Date seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    /**
     * @return the seasonEndDate
     */
    public Date getSeasonEndDate() {
        return seasonEndDate;
    }

    /**
     * @param seasonEndDate
     *            the seasonEndDate to set
     */
    public void setSeasonEndDate(Date seasonEndDate) {
        this.seasonEndDate = seasonEndDate;
    }

    /**
     * @return the seasonStatus
     */
    public String getSeasonStatus() {
        return seasonStatus;
    }

    /**
     * @param seasonStatus
     *            the seasonStatus to set
     */
    public void setSeasonStatus(String seasonStatus) {
        this.seasonStatus = seasonStatus;
    }

    /**
     * @return the programName
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * @param programName
     *            the programName to set
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

}
