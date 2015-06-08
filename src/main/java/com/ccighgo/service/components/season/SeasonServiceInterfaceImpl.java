package com.ccighgo.service.components.season;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Season;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;

@Component
public class SeasonServiceInterfaceImpl implements SeasonServiceInterface {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SeasonServiceInterfaceImpl.class);
	@Autowired
	SeasonRepository seasonRepository;

	// stub implementation, actual code will vary
	SeasonServiceInterfaceImpl() {

	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SeasonsList getAllSeasons() {
		try {
			List<Season> allseasons = seasonRepository.findAll();
			SeasonsList seasonsList = new SeasonsList();
			List<SeasonListObject> seasonsBeans = new ArrayList<SeasonListObject>();
			if (allseasons != null) {
				seasonsList.setRecordCount(allseasons.size());
				for (int i = 0; i < allseasons.size(); i++) {
					SeasonListObject seasonBean = new SeasonListObject();
					Season seasonEntity = allseasons.get(i);
					SeasonServiceImplUtil.convertEntitySeasonToSeasonListObject(seasonBean,seasonEntity);
					seasonsBeans.add(seasonBean);
				}
			}
			return seasonsList;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " : " + e.getCause());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createSeason() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSeason(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editSeason(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SeasonBean viewSeason(String id) {
		try {
			Season seasonEntity = seasonRepository.findOne(Integer.parseInt(id));
			SeasonBean seasonBean = new SeasonBean();
			if(seasonEntity!=null)
				SeasonServiceImplUtil.convertEntitySeasonToBeanSeason(seasonBean,seasonEntity);
			return seasonBean;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " : " + e.getCause());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateSeason(String id) {
		// TODO Auto-generated method stub

	}

}
