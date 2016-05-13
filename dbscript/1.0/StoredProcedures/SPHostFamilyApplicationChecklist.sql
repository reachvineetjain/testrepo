DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyApplicationChecklist`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyApplicationChecklist`(IN hostFamilyId INT, IN ssId INT, IN deptprgmId INT)
BEGIN
	DECLARE hfId,sId,dpId INT;
	DECLARE hfName VARCHAR(100);
    SET @hfId = hostfamilyId;
    SET @sId = ssId;
    SET @dpId = deptprgmId;
    SET @hfName = (SELECT CONCAT(firstName,' ',lastName) FROM HostFamily WHERE hostFamilyGoId = @hfId);
    
  
    
    IF (@sId IS NULL AND @dpId IS NULL) THEN
    
		SELECT MAX(hfs.seasonId) INTO @sId FROM HostFamilySeason hfs WHERE hfs.hostFamilyGoId = @hfId;
		SELECT MAX(hfs.departmentProgramId) INTO @dpId FROM HostFamilySeason hfs WHERE hfs.seasonId = @sId AND hfs.hostFamilyGoId = @hfId;
    END IF;
    
    SELECT 	IFNULL (GROUP_CONCAT(DISTINCT(CONCAT(hfm.firstName,' ',hfm.lastName)) SEPARATOR ' and '),@hfName) AS hostFamilyName,
		hfac.hostFamilyApplicationCategoryName,
		hfsc.status,
		hf.photo,
		hfs.hostFamilySeasonId,
		hfs.seasonId,
		hfs.`departmentProgramId`,
		hf.hostFamilyGoId,
		hfac.hostFamilyApplicationCategoriesId
     --           hfs.seasonId,
     --           hfs.departmentProgramId,
     --           hfs.hostFamilySeasonId
    
    FROM 	HostFamily hf 
    INNER JOIN	HostFamilySeason hfs ON hf.hostFamilyGoId = hfs.hostFamilyGoId
    INNER JOIN  HostFamilySeasonCategories hfsc ON hfs.hostFamilySeasonId = hfsc.hostFamilySeasonId
    INNER JOIN 	HostFamilyApplicationCategories	hfac ON hfsc.hostFamilyApplicationCategoriesId = hfac.hostFamilyApplicationCategoriesId
    LEFT JOIN   HostFamilyMember hfm ON hfs.hostFamilySeasonId = hfm.hostFamilySeasonId AND hfm.hostFamilySeasonId = hfsc.hostFamilySeasonId AND isHostParent  =1
    WHERE 	hfs.seasonId = @sId AND hfs.departmentProgramId = @dpId AND hf.hostFamilyGoId = @hfId
    GROUP BY 	hfac.hostFamilyApplicationCategoryName
    ORDER BY 	hfac.hostFamilyApplicationCategoriesId;
		
     
    END$$

DELIMITER ;