DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationGeneralQuestions`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationGeneralQuestions`(IN hostfamilyId INT,IN ssId INT,IN deptprgmId INT)
BEGIN
    
          DECLARE hfId,sId,dpId INT;
   
          SET @hfId = hostfamilyId;
          SET @sId = ssId;
          SET @dpId = deptprgmId;
          
    IF (@sId IS NULL AND @dpId IS NULL) THEN
    
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfId;
		SELECT MAX(hfs.departmentProgramId) INTO @dpId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfId;
    END IF;
          SELECT hfg.`previousHostingWithCCI`,
                 hfg.`internet`,
                 hfg.`internetOthers`,
                 hfg.`community`,
                 hfg.`communityEvent`,
                 hfg.`communityMagazine`,
                 hfg.`communityOthers`,
                 hfs.seasonId,
                 hfs.departmentProgramId,
                 hfs.hostFamilySeasonId
          FROM `HostFamilyGeneralQuestions` hfg
          INNER JOIN `HostFamilySeason` hfs ON hfs.`hostFamilySeasonId` = hfg.`hostFamilySeasonId`
          WHERE hfs.`hostFamilyGoId` = @hfId AND hfs.`seasonId` = @sId AND hfs.`departmentProgramId` = @dpId;
          
      
    END$$

DELIMITER ;