DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPAdminWQFieldStaffPendingApproval`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPAdminWQFieldStaffPendingApproval`(IN typeId INT(3), IN categoryId INT(3), IN cciStaffUserId INT, roletype VARCHAR(45))
BEGIN
    
         	DECLARE GoId,tId,cId INT;
		DECLARE rType VARCHAR(45);
		SET @GoId = cciStaffUserId;
		SET @tId = typeId;
		SET @cId = categoryId;
		SET @rType = roleType;
		
		SELECT fs.`fieldStaffGoId`,
		       fs.`firstName`,
		       fs.`lastName`,
		       fsss.`fieldStaffTypeName` AS Role,
		       fst.`fieldStaffStatusName` AS STATUS,
		       fs.`submittedDate` AS submittedDate,
		       fs.`phone`,
		       l.email,
		       fs.`currentCity`,
		       ls.`stateName`,
		       fs.`currentZipCode`,
		       CURRENT_TIMESTAMP AS followUpdate
		FROM  `FieldStaff` fs 
		INNER JOIN `Login` l ON l.goId = fs.`fieldStaffGoId` AND fs.`fieldStaffStatusId` = 2
		INNER JOIN `FieldStaffStatus` fst ON fst.`fieldStaffStatusId` = fs.`fieldStaffStatusId`
		INNER JOIN `FieldStaffType` fsss ON fsss.`fieldStaffTypeId` = fs.`fieldStaffTypeId`
		INNER JOIN `LookupUSStates` ls ON ls.`usStatesId` = fs.`currentStateId`
		WHERE fs.`fieldStaffGoId` IN (SELECT `targetGoId`
		                              FROM `AdminWorkQueue`
		                              WHERE `adminWQTypeId` = @tId
		                              AND `adminWQCategoryId` = @cId
		                              AND `cciStaffUserGoId` = @GoId
		                              AND targetRoleType = @rType GROUP BY targetGoId);
		
		       
    END$$

DELIMITER ;