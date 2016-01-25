DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffWQCategoryAggregate`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffWQCategoryAggregate`()
BEGIN
DECLARE fs_done INT DEFAULT 0;
DECLARE goId INT;
DECLARE cciId CURSOR FOR 
SELECT `fieldStaffGoId` FROM `FieldStaff`;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET fs_done = 1;
TRUNCATE TABLE `FieldStaffWorkQueueCategoryAggregate`;
ALTER TABLE `FieldStaffWorkQueueCategoryAggregate` AUTO_INCREMENT = 1;
OPEN cciId;
get_goId : LOOP
FETCH cciId INTO goId;
IF fs_done = 1 THEN
LEAVE get_goId;
END IF;
  BLOCK_DepPgm: BEGIN
				DECLARE pgm_done INT DEFAULT 0;
				DECLARE pgmId INT;
				DECLARE depPgmId CURSOR FOR 
				SELECT lookupDepartmentProgramId FROM LookupDepartmentPrograms WHERE lookupDepartmentProgramId IN (1,2,3);
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET pgm_done = 1;
				OPEN depPgmId;
				get_pgmId : LOOP
				FETCH depPgmId INTO pgmId;
				IF pgm_done = 1 THEN
				LEAVE get_pgmId;
				END IF;   
				BLOCK_Type:	BEGIN	
							DECLARE t_done INT DEFAULT 0;
							DECLARE tId INT;
							DECLARE typeId CURSOR FOR
							SELECT `fieldStaffWQTypeId` FROM `FieldStaffWorkQueueType`;
							DECLARE CONTINUE HANDLER FOR NOT FOUND SET t_done = 1;
	
							OPEN typeId;
							get_typeId : LOOP
							FETCH typeId INTO tId;
							IF t_done = 1 THEN
							LEAVE get_typeId;
							END IF;
							BLOCK_Category: BEGIN
	
											DECLARE c_done INT DEFAULT 0;
											DECLARE cId INT;
	
											DECLARE catId CURSOR FOR
											SELECT `fieldStaffWQCategoryId` FROM `FieldStaffWorkQueueCategories` WHERE `fieldStaffWQTypeId` = tId;
	
											DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;
											
											OPEN catId;
											get_categoryId : LOOP
											FETCH catId INTO cId;
											IF c_done = 1 THEN
											LEAVE get_categoryId;
											END IF;
											INSERT INTO `FieldStaffWorkQueueCategoryAggregate` (`fieldStaffWQCategoryId`,`fieldStaffWQTypeId`,`fieldStaffWQCategoryName`,`fieldStaffGoId`,`lookupDepartmentProgramId`,
											                                                `fieldStaffWQCategoryAggregate`,`modifiedDate`)
											SELECT IFNULL((SELECT `fieldStaffWQCategoryId` FROM `FieldStaffWorkQueue` WHERE `fieldStaffWQCategoryId` = cId AND `fieldStaffWQTypeId` = tId AND `fieldStaffGoId` = goId AND lookupDepartmentProgramId = pgmId GROUP BY `fieldStaffWQCategoryId`),cId),
											       IFNULL((SELECT `fieldStaffWQTypeId` FROM `FieldStaffWorkQueue` WHERE `fieldStaffWQCategoryId` = cId AND `fieldStaffWQTypeId` = tId AND `fieldStaffGoId` = goId AND lookupDepartmentProgramId = pgmId GROUP BY `fieldStaffWQTypeId`),tId),
												   (SELECT `fieldStaffWQCategoryName` FROM `FieldStaffWorkQueueCategories` WHERE `fieldStaffWQCategoryId` = cId),
												   IFNULL((SELECT `fieldStaffGoId` FROM `FieldStaffWorkQueue` WHERE `fieldStaffWQCategoryId` = cId AND `fieldStaffWQTypeId` = tId AND `fieldStaffGoId` = goId AND lookupDepartmentProgramId = pgmId GROUP BY `fieldStaffGoId`),goId),
												   IFNULL((SELECT lookupDepartmentProgramId FROM `FieldStaffWorkQueue` WHERE `fieldStaffWQCategoryId` = cId AND `fieldStaffWQTypeId` = tId AND `fieldStaffGoId` = goId AND lookupDepartmentProgramId = pgmId GROUP BY lookupDepartmentProgramId),pgmId),
											       (SELECT COUNT(`fieldStaffWQCategoryId`) FROM `FieldStaffWorkQueue` WHERE `fieldStaffWQCategoryId` = cId AND `fieldStaffWQTypeId` = tId AND `fieldStaffGoId` = goId AND lookupDepartmentProgramId = pgmId ),
											      CURRENT_TIMESTAMP;
												    
												
											END LOOP get_categoryId;
											CLOSE catId;
							END BLOCK_Category ;
							END LOOP get_typeId;
							CLOSE typeId;
							
				END BLOCK_Type;	
				END LOOP get_pgmId;
				CLOSE depPgmId;
END BLOCK_DepPgm;  
END LOOP get_goId;
CLOSE cciId;
END$$

DELIMITER ;