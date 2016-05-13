DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationLifeStyle`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationLifeStyle`(IN hostFamilyId INT,IN seasonId INT,IN depPgmId INT)
BEGIN
		DECLARE hfGoId, sId,pgmId INT;
		SET @sId = seasonId;
		SET @hfGoId = hostFamilyId;
		SET @pgmId = depPgmId;
		
		IF (@sId IS NULL AND @pgmId IS NULL) THEN
		
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfGoId;
		SELECT MAX(hfs.departmentProgramId) INTO @pgmId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfGoId;
		
		END IF;
		
		SELECT  hfd.familyMemberDescription AS familyDescription,
				hfd.illness AS hasIllness,
				hfd.illnessDetails AS illnessDetails,
				hfd.disability AS hasDisability,
				hfd.disabilityDetails AS disabilityDetails,
				hfd.adaptCircumtances AS adaptCircumstances,
				hfd.houseHoldType AS houseHoldType,
				hfd.typicalWeekday AS typicalWeekDay,
				hfd.typicalWeekend AS typicalWeekend,
				hfd.favouriteWeekend AS favouriteWeekend,
				hfd.religiousAffiliation AS religiousAffiliation,
				hfd.otherReligiousDetails AS otherReligiousDetails,
				hfd.religiousAttendance AS religiousAttendance,
				hfd.preferStudentJoins AS preferStudentJoins,
				hfd.inviteStudentForReligiousExperience AS inviteStudentForReligiousExperience,
				hfd.problemWithReligiousDifference AS problemWithReligiousDifference,
				hfd.agreeToServeMeals AS agreeToServeMeals,
				hfd.dietaryRestrictions AS dietaryRestrictions,
				hfd.describeDietaryRestrictions AS describeDietaryRestrictions,
				hfd.participantFollowDiet AS participantFollowDiet,
				hfd.descPaxDietaryRestrictions AS descPaxDietaryRestrictions,
				hfd.comfortableHostingDiet AS comfortableHostingDiet,
				hfd.hasAutoInsurance AS hasAutoInsurance,
				hfd.familySmoker AS familySmoker,
				hfd.familySmokingPlace AS familySmokingPlace,
				hfd.drinkAlcohol AS drinkAlcohol,
				hfd.crimeConviction AS crimeConviction,
				hfd.crimeConvictionDetails AS crimeConvictionDetails,
				hfd.childServicesContact AS childServicesContact,
				hfd.childServicesContactDetails AS childServicesContactDetails,
				hfd.incomeRange AS incomeRange,
				hfd.receivePublicAssistance AS receivePublicAssistance,
				hfd.publicAssistanceExplanation AS publicAssistanceExplanation,
                                hfs.seasonId,
                                hfs.departmentProgramId,
                                hfs.hostFamilySeasonId,
                                hfd.hostFamilyDetailsId
		FROM HostFamilySeason hfs
		INNER JOIN HostFamilyDetail hfd ON hfs.hostFamilySeasonId = hfd.hostFamilySeasonId
		WHERE hfs.hostFamilyGoId = @hfGoId AND hfs.seasonId = @sId AND hfs.departmentProgramId = @pgmId;
				
				
				
				
		
    END$$

DELIMITER ;