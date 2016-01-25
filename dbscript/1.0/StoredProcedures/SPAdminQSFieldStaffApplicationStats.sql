DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPAdminQSFieldStaffApplicationStats`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPAdminQSFieldStaffApplicationStats`()
BEGIN
		
	
	DELETE FROM `AdminQuickStatsCategoryAggregate` 
	WHERE `adminQSTypeId` = 1 AND `adminQSCategoryId` = 4;
	
	ALTER TABLE AdminQuickStatsCategoryAggregate AUTO_INCREMENT = 1;
	
	INSERT INTO `AdminQuickStatsCategoryAggregate` 
	(`adminQSTypeId`,`adminQSCategoryId`,`adminQSCategoryName`,`adminQSCategoryAggregate`,`status`,`modifiedDate`)
	VALUES 
	(1,4,'FieldStaff',(SELECT COUNT(`fieldStaffGoId`) FROM `FieldStaff` WHERE `fieldStaffStatusId` = 2),
	'Pending',CURRENT_TIMESTAMP),
	(1,4,'FieldStaff',(SELECT COUNT(`fieldStaffGoId`) FROM `FieldStaff` WHERE `fieldStaffStatusId` = 5),
	'Future Prospect',CURRENT_TIMESTAMP),
	(1,4,'FieldStaff',(SELECT COUNT(`fieldStaffGoId`) FROM `FieldStaff` WHERE `fieldStaffStatusId` = 3),
	'Approved',CURRENT_TIMESTAMP),
	(1,4,'FieldStaff',(SELECT COUNT(`fieldStaffGoId`) FROM `FieldStaff` WHERE `fieldStaffStatusId` = 1),
	'Not Approved',CURRENT_TIMESTAMP),
	(1,4,'FieldStaff',(SELECT COUNT(`fieldStaffGoId`) FROM `FieldStaff` WHERE `fieldStaffStatusId` = 4),
	'Rejected',CURRENT_TIMESTAMP);
 END$$

DELIMITER ;