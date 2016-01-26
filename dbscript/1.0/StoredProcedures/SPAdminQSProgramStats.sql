DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPAdminQSProgramStats`$$

CREATE  PROCEDURE `SPAdminQSProgramStats`()
BEGIN
		DECLARE c_Id INTEGER DEFAULT 0; 	
		DECLARE cId INT;	
		
		DECLARE catId CURSOR FOR
		SELECT `adminQSCategoryId` FROM `AdminQuickStatsCategories` WHERE `adminQSTypeId`=2 AND `adminQSCategoryId` IN(5,6,7);
        
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_Id = 1; 
		
		DELETE FROM `AdminQuickStatsCategoryAggregate` 
		WHERE `adminQSTypeId` = 2 AND `adminQSCategoryId` IN(5,6,7);
		
		ALTER TABLE AdminQuickStatsCategoryAggregate AUTO_INCREMENT = 1;
		
		     
		OPEN catId; 
		get_catId : LOOP 
		FETCH catId INTO cId;
       
		IF c_Id = 1 THEN 
			LEAVE get_catId;
		END IF;
	
		INSERT INTO `AdminQuickStatsCategoryAggregate` 
		(`adminQSTypeId`,`adminQSCategoryId`,`adminQSCategoryName`,`adminQSCategoryAggregate`,`status`,`modifiedDate`)
		VALUES 
		(2,cId,(SELECT adminQSCategoryName FROM AdminQuickStatsCategories WHERE adminQSCategoryId = cId),100,'Placed',CURRENT_TIMESTAMP),
		(2,cId,(SELECT adminQSCategoryName FROM AdminQuickStatsCategories WHERE adminQSCategoryId = cId),100,'Probation',CURRENT_TIMESTAMP),
		(2,cId,(SELECT adminQSCategoryName FROM AdminQuickStatsCategories WHERE adminQSCategoryId = cId),100,'Written Warning',CURRENT_TIMESTAMP);
		
		END LOOP get_catId;
		CLOSE catId;
 END$$

DELIMITER ;