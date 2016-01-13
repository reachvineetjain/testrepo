DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPAdminWQPartnerNotesReview`$$

CREATE  PROCEDURE `SPAdminWQPartnerNotesReview`(IN typeId INT(3), IN categoryId INT(3), IN cciStaffUserId INT, roletype VARCHAR(45))
BEGIN
DECLARE GoId,tId,cId INT;
		DECLARE rType VARCHAR(45);
		SET @GoId = cciStaffUserId;
		SET @tId = typeId;
		SET @cId = categoryId;
		SET @rType = roleType;
    
SELECT p.`companyName`,p.`partnerGoId`,l.`countryName`,l.`countryFlag`,
prs.`partnerAgentStatusId`,pnt.`PartnerNoteTopicName`,pnt.`isPublic`,pn.`createdOn` AS notesCreatedOn,CONCAT(cs2.firstName,cs2.lastName) AS notesCreatedBy,
pn.`partnerNote`,CONCAT(cs1.firstName,cs1.lastName) AS notesTopicCreatedBy, pnt.`createdOn` AS notesTopicCreatedOn,csr1.cciStaffRoleName AS CCIAdminNoteTopicRole,csr2.cciStaffRoleName AS CCIAdminNoteRole
FROM `Partner` p
INNER JOIN `LookupCountries` l ON p.`countryId` = l.`countryId`
INNER JOIN `PartnerReviewStatus` prs ON prs.`partnerGoId`=p.`partnerGoId`
INNER JOIN `PartnerNoteTopics` pnt ON pnt.`partnerGoId`=p.`partnerGoId`
INNER JOIN `PartnerNotes` pn ON pn.`partnerGoId`=p.`partnerGoId` AND pn.`partnerNoteTopicId`=pnt.`partnerNoteTopicId`
INNER JOIN `Login` lnt ON pnt.createdBy = lnt.loginId
INNER JOIN `Login` lnts ON pn.createdBy = lnts.loginId
INNER JOIN `CCIStaffUsers` cs1 ON lnt.goId = cs1.cciStaffUserId
INNER JOIN `CCIStaffUsers` cs2 ON lnts.goId = cs2.cciStaffUserId
INNER JOIN `CCIStaffUsersCCIStaffRoles` cstr1 ON cstr1.cciStaffUserId = lnt.goId AND cs1.cciStaffUserId = cstr1.cciStaffUserId
INNER JOIN `CCIStaffUsersCCIStaffRoles` cstr2 ON cstr2.cciStaffUserId = lnts.goId AND cs2.cciStaffUserId = cstr2.cciStaffUserId
INNER JOIN `CCIStaffRoles` csr1 ON cstr1.cciStaffRoleId = csr1.cciStaffRoleId
INNER JOIN `CCIStaffRoles` csr2 ON cstr2.cciStaffRoleId = csr2.cciStaffRoleId

WHERE p.partnerGoId IN (SELECT targetGoId 
						FROM AdminWorkQueue
						WHERE adminWQTypeId = @tId
						AND adminWQCategoryId = @cId
						AND cciStaffUserGoId = @GoId
						AND targetRoleType = @rType GROUP BY targetGoId) 
AND pn.`hasRead` = 0 AND pnt.`isVisibleToPartner` = 0;
    END$$

DELIMITER ;