DELIMITER $$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `cci_gh_go`.`SPSubPartnerSeasonAssign`(IN subPartnerId INT)
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	DECLARE spId,pId,pssId,scount INT;
	SET @spId = subPartnerId;
	SET @pId = (SELECT parentPartnerGoId FROM Partner WHERE partnerGoId = @spId);
	SET @pssId = (SELECT partnerStatusId FROM `PartnerStatus` WHERE partnerStatusName = 'Active');
        SET @scount = (SELECT COUNT(*) FROM PartnerSeason WHERE partnerGoId = @spId);
	
	IF (@scount = 0) THEN
		SELECT pgms.seasonId,pgms.programName,pgms.departmentProgramId
		FROM `ProgramSeasons` pgms
		INNER JOIN `PartnerSeason` ps ON pgms.seasonId = ps.seasonId AND pgms.departmentProgramId = ps.departmentProgramId
		WHERE ps.partnerGoId = @pId AND ps.partnerSeasonStatusId = @pssId ;
	
	ELSE
		SELECT pgms.seasonId,pgms.programName,pgms.departmentProgramId
		FROM `ProgramSeasons` pgms
		INNER JOIN `PartnerSeason` ps ON pgms.seasonId = ps.seasonId AND pgms.departmentProgramId = ps.departmentProgramId
		WHERE ps.partnerGoId = @pId 
		AND ps.partnerSeasonStatusId = @pssId 
		AND (pgms.seasonId,pgms.departmentProgramId) NOT IN (SELECT p.seasonId,p.departmentProgramId FROM PartnerSeason p WHERE p.partnerGoId = @spId);	
	
	END IF;
	
    END$$

DELIMITER ;