DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPAdminWQPartnerAdminReview`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPAdminWQPartnerAdminReview`(IN typeId INT(3), IN categoryId INT(3), IN cciStaffUserId INT, roletype VARCHAR(45))
BEGIN
		DECLARE GoId,tId,cId INT;
		DECLARE rType VARCHAR(45);
		SET @GoId = cciStaffUserId;
		SET @tId = typeId;
		SET @cId = categoryId;
		SET @rType = roleType;
				
		SELECT pa.companyName,
		       pa.firstName,
		       pa.lastName,
		       pa.phone,
		       pa.email,
		       pa.website,
		       lc.countryName,
		       lc.countryFlag,
		       pa.followUpDate,
		       pa.submittedOn,
		       lc.countryFlag,
		       GROUP_CONCAT(ldp.programName) AS programNames,
		       IFNULL(pnc.NotesCount,0) AS NotesCount, 
		       pa.partnerAgentGoId
		FROM `PartnerAgentInquiries` pa 
		INNER JOIN `LookupCountries` lc ON pa.countryId = lc.countryId
		INNER JOIN `PartnerProgram` pp ON pa.partnerAgentGoId = pp.partnerGoId
		INNER JOIN `LookupDepartmentPrograms` ldp ON pp.lookupDepartmentProgramId = ldp.lookupDepartmentProgramId
		LEFT JOIN (SELECT COUNT(pn.partnerNotesId) AS NotesCount,partnerGoId 
		           FROM `PartnerNotes` pn 
		           INNER JOIN `PartnerAgentInquiries` pai ON pn.partnerGoId = pai.partnerAgentGoId 
		           GROUP BY pn.partnerGoId) pnc ON pnc.partnerGoId = pa.partnerAgentGoId
		WHERE pa.partnerAgentGoId IN (SELECT targetGoId 
						FROM AdminWorkQueue
						WHERE adminWQTypeId = @tId
						AND adminWQCategoryId = @cId
						AND cciStaffUserGoId = @GoId
						AND targetRoleType = @rType)
		AND pp.hasApplied=1
		GROUP BY pa.companyName;
    END$$

DELIMITER ;