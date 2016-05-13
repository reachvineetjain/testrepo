DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPAdminWQCategoryAggregate`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPAdminWQCategoryAggregate`()
BEGIN
DECLARE admin_done INT DEFAULT 0;
DECLARE goId INT;
DECLARE cciId CURSOR FOR 
SELECT cciStaffUserId FROM CCIStaffUsers;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET admin_done = 1;
TRUNCATE TABLE `AdminWorkQueueCategoryAggregate`;
ALTER TABLE `AdminWorkQueueCategoryAggregate` AUTO_INCREMENT = 1;
OPEN cciId;
get_goId : LOOP
FETCH cciId INTO goId;
IF admin_done = 1 THEN
LEAVE get_goId;
END IF;

				BLOCK_Type:	BEGIN	
							DECLARE t_done INT DEFAULT 0;
							DECLARE tId INT;
							DECLARE typeId CURSOR FOR
							SELECT `adminWQTypeId` FROM `AdminWorkQueueType`;
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
											SELECT `adminWorkQueueCategoryId` FROM `AdminWorkQueueCategories` WHERE adminWQTypeId = tId;
	
											DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;
											
											OPEN catId;
											get_categoryId : LOOP
											FETCH catId INTO cId;
											IF c_done = 1 THEN
											LEAVE get_categoryId;
											END IF;
											INSERT INTO `AdminWorkQueueCategoryAggregate` (`adminWQCategoryId`,`adminWQCategoryAggregate`,`adminWQTypeId`,`adminWQCategoryName`,
																						   `modifiedDate`,`adminGoId`) 
											SELECT IFNULL((SELECT adminWQCategoryId FROM `AdminWorkQueue` WHERE adminWQCategoryId = cId AND adminWQTypeId = tId AND cciStaffUserGoId = goId GROUP BY adminWQCategoryId),cId),
											       (SELECT COUNT(adminWQCategoryId) FROM `AdminWorkQueue` WHERE adminWQCategoryId = cId AND adminWQTypeId = tId AND cciStaffUserGoId = goId ), 
											       IFNULL((SELECT adminWQTypeId FROM `AdminWorkQueue` WHERE adminWQCategoryId = cId AND adminWQTypeId = tId AND cciStaffUserGoId = goId  GROUP BY adminWQTypeId),tId),
											       (SELECT adminWorkQueueCategoryName FROM `AdminWorkQueueCategories` WHERE adminWorkQueueCategoryId = cId),
												   CURRENT_TIMESTAMP,
												   IFNULL((SELECT cciStaffUserGoId FROM `AdminWorkQueue` WHERE adminWQCategoryId = cId AND adminWQTypeId = tId AND cciStaffUserGoId = goId GROUP BY cciStaffUserGoId),goId)
												 ;
											END LOOP get_categoryId;
											CLOSE catId;
							END BLOCK_Category ;
							END LOOP get_typeId;
							CLOSE typeId;
							
				END BLOCK_Type;	
			
END LOOP get_goId;
CLOSE cciId;
END$$

DELIMITER ;