DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationHome`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationHome`(IN hostFamilyId INT,IN seasonId INT,IN depPgmId INT)
BEGIN
		DECLARE hfGoId, sId,pgmId INT;
		SET @sId = seasonId;
		SET @hfGoId = hostFamilyId;
		SET @pgmId = depPgmId;
		
		IF (@sId IS NULL AND @pgmId IS NULL) THEN
		
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfGoId;
		SELECT MAX(hfs.departmentProgramId) INTO @pgmId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfGoId;
		
		END IF;
		
		SELECT  ps.programName AS seasonName,
				ps.seasonId AS seasonId,
				ps.departmentProgramId AS depProgramId,				
				hfh.homeType AS homeType,
				hfh.homeLocation AS homeLocation,
				hfh.bedroomNumber AS bedroomNumber,
				hfh.bathroomNumber AS bathroomNumber,
				hfh.homeDescription AS homeDescription,
				hfh.interestedForTwoStudents AS interestedForTwoStudents,
				hfh.sharesBedroom AS sharesBedroom,
				hfh.sharesBedroomWith AS sharesBedroomWith,
				hfh.sharingBedroomGenderId,
				hfh.sharingAge AS sharingAge,
				hfh.extraFacilities AS extraFacilities,
				hfh.isStudentsRoomBasement AS isStudentsRoomBasement,
				hfh.exitBasement AS exitBasement,
				hfh.residenceSiteFunctioningBusiness AS residenceSiteFunctioningBusiness,
				hfh.specifyTypeOfBusiness AS specifyTypeOfBusiness,
				hfh.otherTypeOfBusiness AS otherTypeOfBusiness,
				hfh.utilities AS utilities,
				hfh.specialFeaturesInHome AS specialFeaturesInHome,
				hfh.amenities AS amenities,
                                hfs.hostFamilySeasonId,
                                hfh.hostFamilyHomeId
		FROM HostFamilySeason hfs
		INNER JOIN HostFamilyHome hfh ON hfs.hostFamilySeasonId = hfh.hostFamilySeasonId
		INNER JOIN LookupGender lg ON hfh.sharingBedroomGenderId = lg.genderId
		INNER JOIN ProgramSeasons ps ON hfs.seasonId = ps.seasonId AND hfs.departmentProgramId = ps.departmentProgramId
		WHERE hfs.hostFamilyGoId = @hfGoId AND hfs.seasonId = @sId AND hfs.departmentProgramId = @pgmId;
				
				
				
				
		
    END$$

DELIMITER ;