DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffHeirarchy`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffHeirarchy`(IN fieldStaffId INT,IN seasnId INT,IN depPgmId INT)
BEGIN 
	DECLARE roleId,sgcId,ssnId INT;  
	DECLARE c_done INT DEFAULT 0;
	DECLARE goId,sId,pgmId INT;
	SET @goId = fieldStaffId;
	SET @sId = seasnId;
	SET @pgmId = depPgmId;	
		
	SET @roleId = (SELECT fieldStaffTypeId FROM FieldStaff WHERE fieldStaffGoId = @goId);
	
	DROP TEMPORARY TABLE IF EXISTS FSHierarchy;
	CREATE TEMPORARY TABLE  FSHierarchy (fsGoId INT,firstName VARCHAR(150),lastName VARCHAR (150),fsType VARCHAR (50),photo VARCHAR(200),season VARCHAR(100),seasonStatus VARCHAR(50));
	
	CASE @roleId
	WHEN 5 THEN
		BEGIN
			
				DECLARE curSGC CURSOR FOR 
				SELECT  seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = @goId AND seasonId = @sId;
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;
				
				
				OPEN curSGC;
				get_curSGC : LOOP
				FETCH curSGC INTO sgcId;
				IF (c_done = 1) THEN	
					LEAVE get_curSGC;
				END IF;
				           /*
						SELECT superRegionId,regionId,usStatesId,sgc.seasonId INTO @srId,@rId,@stateId,@sId 
						FROM FieldStaffLeadershipSeason fsl 
						INNER JOIN SeasonGeographyConfiguration sgc ON fsl.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
						WHERE fsl.fieldStaffGoId = @goId AND fsl.seasonGeographyConfigurationId = sgcId;
					
					IF (@stateId IS NULL AND @rId IS NULL) THEN
						
						INSERT INTO FSHierarchy (season,seasonStatus) 
						SELECT Season,programStatus FROM SeasonERDList WHERE seasonId = @sId GROUP BY Season;
					END IF;		*/			
					
					IF (@stateId IS NULL AND @rId IS NOT NULL) THEN
					
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,Season,programStatus
						FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId 
						AND departmentProgramId = @pgmId;
						
					END IF;
					IF (@stateId IS NOT NULL) THEN
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,Season,programStatus
						FROM SeasonRDList WHERE superRegionId = @srId AND regionId = @rId AND usStatesId IS NULL AND seasonId = @sId 
						AND departmentProgramId = @pgmId;					
						
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,Season,programStatus
						FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
					END IF;
				
				END LOOP get_curSGC;
				CLOSE curSGC;
				
				SELECT fsGoId  AS fieldStaffGoId,photo,firstName AS fsFirstName,lastName AS fsLastName,fsType AS Role FROM FSHierarchy;
		END;
		
	WHEN 4 THEN
		BEGIN
				DECLARE curSGC CURSOR FOR 
				SELECT  seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = @goId AND seasonId = @sId ;
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;
				
				
				OPEN curSGC;
				get_curSGC : LOOP
				FETCH curSGC INTO sgcId;
				IF (c_done = 1) THEN	
					LEAVE get_curSGC;
				END IF;
				
				SELECT superRegionId,regionId,usStatesId INTO @srId,@rId,@stateId 
				FROM FieldStaffLeadershipSeason fsl 
				INNER JOIN SeasonGeographyConfiguration sgc ON fsl.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
				WHERE fsl.fieldStaffGoId = @goId AND fsl.seasonGeographyConfigurationId = sgcId;
				
			
				INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
				SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
				FROM SeasonRDList WHERE superRegionId = @srId AND regionId = @rId AND usStatesId IS NULL AND seasonId = @sId 
				AND departmentProgramId = @pgmId;
						
				INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
				SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
				FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
				AND departmentProgramId = @pgmId;
				
				INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
				SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
				FROM SeasonRMList WHERE superRegionId = @srId AND regionId = @rId  AND usStatesId IS NULL AND seasonId = @sId
				AND departmentProgramId = @pgmId;				
				
				
				END LOOP get_curSGC;
				CLOSE curSGC;
				
				SELECT fsGoId  AS fieldStaffGoId,photo,firstName AS fsFirstName,lastName AS fsLastName,fsType AS Role FROM FSHierarchy;
				
		END;
		
	WHEN 3 THEN
		BEGIN
				DECLARE curSGC CURSOR FOR 
				SELECT  seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = @goId AND seasonId = @sId;
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;
				
				
				OPEN curSGC;
				get_curSGC : LOOP
				FETCH curSGC INTO sgcId;
				IF (c_done = 1) THEN	
					LEAVE get_curSGC;
				END IF;
				
				SELECT superRegionId,regionId,usStatesId INTO @srId,@rId,@stateId  
				FROM FieldStaffLeadershipSeason fsl 
				INNER JOIN SeasonGeographyConfiguration sgc ON fsl.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
				WHERE fsl.fieldStaffGoId = @goId AND fsl.seasonGeographyConfigurationId = sgcId;
				
				IF (@stateId IS NULL) THEN
					
					INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
					SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
					FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
					AND departmentProgramId = @pgmId;
						
				END IF;
					
				IF (@stateId IS NOT NULL) THEN
					
					INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
					SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
					FROM SeasonRDList WHERE superRegionId = @srId AND regionId = @rId AND usStatesId IS NULL AND seasonId = @sId 
					AND departmentProgramId = @pgmId;
						
					INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
					SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
					FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
					AND departmentProgramId = @pgmId;
				END IF;
				
				END LOOP get_curSGC;
				CLOSE curSGC;
				
				SELECT fsGoId  AS fieldStaffGoId,photo,firstName AS fsFirstName,lastName AS fsLastName,fsType AS Role FROM FSHierarchy;
				
		END;
    
    WHEN 2 THEN
		BEGIN
				DECLARE curSGC CURSOR FOR 
				SELECT  seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = @goId AND seasonId = @sId;
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;
				
				
				OPEN curSGC;
				get_curSGC : LOOP
				FETCH curSGC INTO sgcId;
				IF (c_done = 1) THEN	
					LEAVE get_curSGC;
				END IF;
				
				SELECT superRegionId,regionId,usStatesId INTO @srId,@rId,@stateId
				FROM FieldStaffLeadershipSeason fsl 
				INNER JOIN SeasonGeographyConfiguration sgc ON fsl.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
				WHERE fsl.fieldStaffGoId = @goId AND fsl.seasonGeographyConfigurationId = sgcId;
				
				IF (@stateId IS NULL) THEN
					
					INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
					SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
					FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId 
					AND departmentProgramId = @pgmId;
						
				END IF;
					
				IF (@stateId IS NOT NULL) THEN
					
					INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
					SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
					FROM SeasonRDList WHERE superRegionId = @srId AND regionId = @rId AND usStatesId IS NULL AND seasonId = @sId 
					AND departmentProgramId = @pgmId;
					
					INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
					SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
					FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
					AND departmentProgramId = @pgmId;
				END IF;
				
				END LOOP get_curSGC;
				CLOSE curSGC;
				
				SELECT fsGoId  AS fieldStaffGoId,photo,firstName AS fsFirstName,lastName AS fsLastName,fsType AS Role FROM FSHierarchy;
				
		END;
   	WHEN 1 THEN
	BEGIN
			
				
		/*		DECLARE curSeason CURSOR FOR 
				SELECT  seasonId FROM FieldStaffSeason WHERE fieldStaffGoId = @goId GROUP BY seasonId;
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;
				
				
				OPEN curSeason;
				get_curSeason : LOOP
				FETCH curSeason INTO ssnId;
				IF (c_done = 1) THEN	
					LEAVE get_curSeason;
				END IF;
		*/		
				SET @supervisorId = (SELECT 50039);
				SET @typeId = (SELECT fieldStaffTypeId FROM FieldStaff WHERE fieldStaffGoId = @supervisorId);
				
	
				 
				CASE @typeId
				
				WHEN 5 THEN
						
	
						
						SELECT superRegionId,regionId,usStatesId INTO @srId,@rId,@stateId 
						FROM FieldStaffLeadershipSeason fsl 
						INNER JOIN SeasonGeographyConfiguration sgc ON fsl.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
						WHERE fsl.fieldStaffGoId = @supervisorId AND fsl.seasonId = @sId;
					
					
					IF (@stateId IS NULL AND @rId IS NOT NULL) THEN
					
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
						
					END IF;
					IF (@stateId IS NOT NULL) THEN
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonRDList WHERE superRegionId = @srId AND regionId = @rId AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId; 
						
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
					END IF;
					
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonERDList WHERE fieldStaffGoId = @supervisorId AND seasonId = @sId
						AND departmentProgramId = @pgmId; 						
				
				WHEN 4 THEN
				
	
						SELECT superRegionId,regionId,usStatesId INTO @srId,@rId,@stateId 
						FROM FieldStaffLeadershipSeason fsl 
						INNER JOIN SeasonGeographyConfiguration sgc ON fsl.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
						WHERE fsl.fieldStaffGoId = @supervisorId AND fsl.seasonId = @sId;
						
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonRDList WHERE superRegionId = @srId AND regionId = @rId AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId; 
						
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
						
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonRMList WHERE superRegionId = @srId AND regionId = @rId  AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
						
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonACList WHERE fieldStaffGoId = @supervisorId AND seasonId = @sId
						AND departmentProgramId = @pgmId; 
				
				WHEN 3 THEN
				
												
						SELECT superRegionId,regionId,usStatesId INTO @srId,@rId,@stateId 
						FROM FieldStaffLeadershipSeason fsl 
						INNER JOIN SeasonGeographyConfiguration sgc ON fsl.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
						WHERE fsl.fieldStaffGoId = @supervisorId AND fsl.seasonId = @sId;					
					
					IF (@stateId IS NULL) THEN
					
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
						
					END IF;
					
					IF (@stateId IS NOT NULL) THEN
					
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonRDList WHERE superRegionId = @srId AND regionId = @rId AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
						
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
					END IF;
					
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonRDList WHERE fieldStaffGoId = @supervisorId AND seasonId = @sId
						AND departmentProgramId = @pgmId; 						
				
				WHEN 2 THEN
				
								
						SELECT superRegionId,regionId,usStatesId INTO @srId,@rId,@stateId 
						FROM FieldStaffLeadershipSeason fsl 
						INNER JOIN SeasonGeographyConfiguration sgc ON fsl.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
						WHERE fsl.fieldStaffGoId = @supervisorId AND fsl.seasonId = @sId;
				
				
					IF (@stateId IS NULL) THEN
					
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
						
					END IF;
					
					IF (@stateId IS NOT NULL) THEN
					
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonRDList WHERE superRegionId = @srId AND regionId = @rId AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
						
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonERDList WHERE superRegionId = @srId AND regionId IS NULL AND usStatesId IS NULL AND seasonId = @sId
						AND departmentProgramId = @pgmId;
					END IF;
					
						INSERT INTO FSHierarchy (fsGoId,firstName,lastName,photo,fsType,season,seasonStatus)
						SELECT fieldStaffGoId, firstName,lastName,photo,fieldStaffTypeName,season,programStatus
						FROM SeasonRMList WHERE fieldStaffGoId = @supervisorId AND seasonId = @sId
						AND departmentProgramId = @pgmId; 
				
				ELSE
					BEGIN
					END;
				
				END CASE;
						
		--		END LOOP get_curSeason;
		--		CLOSE curSeason;
				
				SELECT fsGoId  AS fieldStaffGoId,photo,firstName AS fsFirstName,lastName AS fsLastName,fsType AS Role FROM FSHierarchy;
				
	
	END;			
		
         
	ELSE
		BEGIN
		END;
	END CASE;
END$$

DELIMITER ;