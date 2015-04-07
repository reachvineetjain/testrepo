package com.ccighgo.seasons.front;

import java.util.List;

public class SeasonSearchRequest {

    private String departmentID;

    private String programType;

    private List<String> programOptions;

    private String seasonStatus;
    
    private SeasonProgramDTO seasonProgramDTO;

    /**
     * @return the departmentID
     */
    public String getDepartmentID() {
        return departmentID;
    }

    /**
     * @param departmentID
     *            the departmentID to set
     */
    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * @return the programType
     */
    public String getProgramType() {
        return programType;
    }

    /**
     * @param programType
     *            the programType to set
     */
    public void setProgramType(String programType) {
        this.programType = programType;
    }

    /**
     * @return the programOptions
     */
    public List<String> getProgramOptions() {
        return programOptions;
    }

    /**
     * @param programOptions
     *            the programOptions to set
     */
    public void setProgramOptions(List<String> programOptions) {
        this.programOptions = programOptions;
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

    public SeasonProgramDTO getSeasonProgramDTO() {
        return seasonProgramDTO;
    }

    public void setSeasonProgramDTO(SeasonProgramDTO seasonProgramDTO) {
        this.seasonProgramDTO = seasonProgramDTO;
    }

}
