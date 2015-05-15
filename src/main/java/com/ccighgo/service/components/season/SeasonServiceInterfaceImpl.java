package com.ccighgo.service.components.season;

import org.springframework.stereotype.Component;

import com.ccighgo.seasons.front.SeasonProgramDTO;
import com.ccighgo.seasons.front.SeasonSearchResponse;

@Component
public class SeasonServiceInterfaceImpl implements SeasonServiceInterface {

   /* @Autowired
    SeasonProgramRepository seasonProgramRepository;*/

    SeasonServiceInterfaceImpl() {

    }

    public String getString() {
        return "funny";
    }

    public SeasonSearchResponse createSeason(SeasonProgramDTO dto) {/*
        CciSeasonProgram program = new CciSeasonProgram();
        program.setSeasonProgramID(dto.getSeasonProgramID());
        program.setSeasonFullName(dto.getSeasonProgramName());
        program.setStartDate(dto.getSeasonStartDate());
        program.setEndDate(dto.getSeasonEndDate());
        program.setCreatedOn(new Timestamp(dto.getSeasonStartDate().getTime()));
        // TODO fix modified by
        program.setCreatedBy(1);
        program.setModifiedBy(1);
        program.setModifiedOn(new Timestamp(dto.getSeasonEndDate().getTime()));
        seasonProgramRepository.saveAndFlush(program);

        return getAllSeasons();
    */ return null;}

    public SeasonSearchResponse deleteSeason(String id) {/*
        Integer spID = Integer.valueOf(id);
        if (spID > 0) {
            seasonProgramRepository.delete(spID);
        }
        return getAllSeasons();
    */return null;}

    public SeasonSearchResponse getAllSeasons() {/*
        SeasonSearchResponse resp = new SeasonSearchResponse();
        List<CciSeasonProgram> seasonPrograms = seasonProgramRepository.findAll();
        System.out.println("size of the list is" +seasonPrograms.size());
        List<SeasonProgramDTO> dtos = new ArrayList<SeasonProgramDTO>();
        if (seasonPrograms.size() > 0) {
            for (CciSeasonProgram program : seasonPrograms) {
                SeasonProgramDTO dto = new SeasonProgramDTO();
                dto.setSeasonProgramID(program.getSeasonProgramID());
                dto.setSeasonProgramName(program.getSeasonFullName());
                dto.setSeasonStartDate(program.getStartDate());
                dto.setSeasonEndDate(program.getEndDate());
                dtos.add(dto);
            }
        }
        resp.setSeasonPrograms(dtos);
        return resp;
    */return null;}

    public SeasonProgramDTO editSeason(String id) {/*
        SeasonProgramDTO dto = null;
        CciSeasonProgram program = seasonProgramRepository.findOne(Integer.valueOf(id));
        if (program != null) {
            dto = new SeasonProgramDTO();
            dto.setSeasonProgramID(program.getSeasonProgramID());
            dto.setSeasonProgramName(program.getSeasonFullName());
            dto.setSeasonStartDate(program.getStartDate());
            dto.setSeasonEndDate(program.getEndDate());
        }
        return dto;
    */return null;}
    
    public SeasonProgramDTO viewSeason(String id) {/*
        SeasonProgramDTO dto = null;
        CciSeasonProgram program = seasonProgramRepository.findOne(Integer.valueOf(id));
        if (program != null) {
            dto = new SeasonProgramDTO();
            dto.setSeasonProgramID(program.getSeasonProgramID());
            dto.setSeasonProgramName(program.getSeasonFullName());
            dto.setSeasonStartDate(program.getStartDate());
            dto.setSeasonEndDate(program.getEndDate());
        }
        return dto;
    */return null;}

    public SeasonSearchResponse updateSeason(String id, SeasonProgramDTO dto) {/*
        CciSeasonProgram program = seasonProgramRepository.findOne(Integer.valueOf(id));

        if (dto.getSeasonProgramName() != null) {
            program.setSeasonFullName(dto.getSeasonProgramName());
        }
        if (dto.getSeasonStartDate() != null) {
            program.setStartDate(dto.getSeasonStartDate());
        }
        if (dto.getSeasonEndDate() != null) {
            program.setEndDate(dto.getSeasonEndDate());
        }
        // TODO fix modified by
        program.setModifiedBy(1);
        program.setModifiedOn(new Timestamp(new Date().getTime()));
        seasonProgramRepository.saveAndFlush(program);

        return getAllSeasons();
    */return null;}

}
