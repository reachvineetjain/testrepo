DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffHeirarchy`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffHeirarchy`(IN fieldStaffId INT,IN seasonnId INT,deptProgramId INT)
BEGIN 
	DECLARE roleId,fsId,fId,ssnId,sgcId INT;
	DECLARE c_done,f_done,fh_done,fh2_done,fh1_done INT DEFAULT 0;
	DECLARE goId,sIdd,dtpId INT;
	SET @goId = fieldStaffId;
	SET @sIdd = seasonnId;
	SET @dtpId = deptProgramId;
		
	SET @roleId = (SELECT fieldStaffTypeId FROM FieldStaff WHERE fieldStaffGoId = @goId);
	
	DROP TEMPORARY TABLE IF EXISTS FSHierarchy;
	CREATE TEMPORARY TABLE  FSHierarchy (fsGoId INT);
	
	CASE @roleId
	WHEN 5 THEN
		
			
			SELECT COUNT(fsls.seasonGeographyConfigurationId) INTO @count
			FROM FieldStaffLeadershipSeason fsls
			INNER JOIN SeasonGeographyConfiguration sgc ON sgc.seasonGeographyConfigurationId = fsls.seasonGeographyConfigurationId
			WHERE fsls.fieldStaffGoId = @goId
			AND sgc.seasonId = @sIdd
			AND sgc.usstatesId IS NOT NULL;
			
			IF (@count > 0) THEN
			        BEGIN
				DECLARE curHierarchy CURSOR FOR 
				SELECT seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = @goId AND seasonId = @sIdd;
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;	
			
				OPEN curHierarchy;
				get_curHierarchy : LOOP
				FETCH curHierarchy INTO sgcId;
				IF (c_done = 1) THEN	
					LEAVE get_curHierarchy;
				END IF;
				
				SELECT superRegionId,RegionId,usstatesId INTO @srId,@rId,@stateId FROM SeasonGeographyConfiguration WHERE seasonGeographyConfigurationId = sgcId;
				
				INSERT INTO FSHierarchy (fsGoId) 
				SELECT fs.fieldStaffGoId FROM `FieldStaffLeadershipSeason` fs 
				INNER JOIN SeasonGeographyConfiguration sgc ON fs.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
				WHERE superRegionId = @srId 
				AND RegionId =  @rId
				AND usstatesId IS NULL
				AND sgc.seasonId = @sIdd;
				
				INSERT INTO FSHierarchy (fsGoId) 
				SELECT fs.fieldStaffGoId FROM `FieldStaffLeadershipSeason` fs 
				INNER JOIN SeasonGeographyConfiguration sgc ON fs.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
				WHERE superRegionId = @srId 
				AND RegionId IS NULL
				AND usstatesId IS NULL
				AND sgc.seasonId = @sIdd;
					
				END LOOP get_curHierarchy;
				CLOSE curHierarchy;
				END;
	        END IF;       
                        SELECT  fh.fsGoId AS fieldStaffGoId,
			        f.photo,
			        f.firstName AS fsFirstName,
			        f.lastName AS fsLastName,
			        fst.`fieldStaffTypeName` AS Role
			FROM FSHierarchy fh 
			INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fh.fsGoId
			INNER JOIN `FieldStaffType` fst ON fst.fieldStaffTypeId = f.fieldStaffTypeId;
			        
			DROP TABLE FSHierarchy;	
        
WHEN 4 THEN
		BEGIN
		
				DECLARE curHierarchy CURSOR FOR 
				SELECT seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = @goId AND seasonId = @sIdd;
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;	
			
				OPEN curHierarchy;
				get_curHierarchy : LOOP
				FETCH curHierarchy INTO sgcId;
				IF (c_done = 1) THEN	
				LEAVE get_curHierarchy;
				END IF;
				
			/*	SELECT photo,firstName,lastName,phone,currentCity,currentStateId,currentZipCode INTO @pic,@fName,@lName,@pnumber,@ccity,@cStateId,@cCode
				FROM FieldStaff WHERE fieldStaffGoId = @goId;*/
				SELECT superRegionId,RegionId,usstatesId,seasonId INTO @srId,@rId,@stateId,@ssnId FROM SeasonGeographyConfiguration 
				                                                                                  WHERE seasonGeographyConfigurationId = sgcId 
					                                                                              AND seasonId = sIdd;
				
                INSERT INTO FSHierarchy (fsGoId) 
				SELECT fs.fieldStaffGoId FROM `FieldStaffLeadershipSeason` fs 
				INNER JOIN SeasonGeographyConfiguration sgc ON fs.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
				WHERE superRegionId = @srId 
				AND RegionId =  @rId
				AND usstatesId = @stateId;
				
				INSERT INTO FSHierarchy (fsGoId) 
				SELECT fs.fieldStaffGoId FROM `FieldStaffLeadershipSeason` fs 
				INNER JOIN SeasonGeographyConfiguration sgc ON fs.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
				WHERE superRegionId = @srId 
				AND RegionId =  @rId
				AND usstatesId IS NULL;
				
				INSERT INTO FSHierarchy (fsGoId) 
				SELECT fs.fieldStaffGoId FROM `FieldStaffLeadershipSeason` fs 
				INNER JOIN SeasonGeographyConfiguration sgc ON fs.seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
				WHERE superRegionId = @srId 
				AND RegionId IS NULL
				AND usstatesId IS NULL;
		END LOOP get_curHierarchy;
		CLOSE curHierarchy;	    
			
            SELECT  fh.fsGoId AS fieldStaffGoId,
			        f.photo,
			        f.firstName AS fsFirstName,
			        f.lastName AS fsLastName,
			        fst.`fieldStaffTypeName` AS Role
			FROM FSHierarchy fh 
			INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fh.fsGoId
			INNER JOIN `FieldStaffType` fst ON fst.fieldStaffTypeId = f.fieldStaffTypeId;
				
		DROP TABLE FSHierarchy;	
			
		END;
		
	WHEN 3 THEN
		BEGIN
			
				DECLARE curHierarchy CURSOR FOR 
				SELECT seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = @goId AND seasonId = @sIdd;
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;	
			
				OPEN curHierarchy;
				get_curHierarchy : LOOP
				FETCH curHierarchy INTO sgcId;
				IF (c_done = 1) THEN	
					LEAVE get_curHierarchy;
				END IF;
				
				
				SELECT superRegionId,RegionId,usstatesId,seasonId INTO @srId,@rId,@stateId,@ssnId1 FROM SeasonGeographyConfiguration WHERE seasonGeographyConfigurationId = sgcId;
				
				SET fh_done = 0;	
				SET fh1_done = 0;	
				SET fh2_done = 0;	
				IF (@stateId IS NOT NULL) THEN		
					BLOCK_If:BEGIN
						BLOCK_1:BEGIN
							
								
							DECLARE fsHierarchy1 CURSOR FOR
							(SELECT fsls.fieldStaffGoId FROM FieldStaffLeadershipSeason fsls
							INNER JOIN SeasonGeographyConfiguration sgc ON fsls. seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
							WHERE  superRegionId = @srId 
							AND RegionId = @rId
							AND usstatesId IS NULL
							AND sgc.seasonId = @ssnId1);
							DECLARE CONTINUE HANDLER FOR NOT FOUND SET fh_done = 1;
													  
							OPEN fsHierarchy1;
							get_fsHierarchy1 : LOOP
							FETCH fsHierarchy1 INTO fId;
							IF (fh_done = 1) THEN
								LEAVE get_fsHierarchy1;
							END IF;
						
							INSERT INTO FSHierarchy(fsGoId) 
							VALUES	
							(fId);
							END LOOP get_fsHierarchy1;
							CLOSE fsHierarchy1;
						END BLOCK_1; 	
					
						BLOCK_2:BEGIN
								
							DECLARE fsHierarchy2 CURSOR FOR
							(SELECT fsls.fieldStaffGoId FROM FieldStaffLeadershipSeason fsls
							INNER JOIN SeasonGeographyConfiguration sgc ON fsls. seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
							WHERE  superRegionId = @srId 
							AND RegionId IS NULL
							AND usstatesId IS NULL
							AND sgc.seasonId = @ssnId1);
							DECLARE CONTINUE HANDLER FOR NOT FOUND SET fh1_done = 1;	
							
							OPEN fsHierarchy2;
							get_fsHierarchy2 : LOOP
							FETCH fsHierarchy2 INTO fId;
							IF (fh1_done = 1) THEN
								LEAVE get_fsHierarchy2;
							END IF;
						
							INSERT INTO FSHierarchy(fsGoId) 
							VALUES	
							(fId);
							END LOOP get_fsHierarchy2;
							CLOSE fsHierarchy2;
						END BLOCK_2;
					END BLOCK_If;
					
				ELSE
					BEGIN
						
							
						DECLARE fsHierarchy2 CURSOR FOR
						(SELECT fsls.fieldStaffGoId FROM FieldStaffLeadershipSeason fsls
						 INNER JOIN SeasonGeographyConfiguration sgc ON fsls. seasonGeographyConfigurationId = sgc.seasonGeographyConfigurationId
						 WHERE  superRegionId = @srId 
						 AND RegionId IS NULL
						 AND usstatesId IS NULL
						 AND sgc.seasonId = @ssnId1);
						 
						DECLARE CONTINUE HANDLER FOR NOT FOUND SET fh2_done = 1;						
						OPEN fsHierarchy2;
						get_fsHierarchy2 : LOOP
						FETCH fsHierarchy2 INTO fId;
						IF (fh2_done = 1) THEN
							LEAVE get_fsHierarchy2;
						END IF;
						
							INSERT INTO FSHierarchy(fsGoId) 
							VALUES	
							(fId);
						END LOOP get_fsHierarchy2;
						CLOSE fsHierarchy2;
					END;
				END IF;
				
				END LOOP get_curHierarchy;
				CLOSE curHierarchy;	
	
            SELECT  fh.fsGoId AS fieldStaffGoId,
			        f.photo,
			        f.firstName AS fsFirstName,
			        f.lastName AS fsLastName,
			        fst.`fieldStaffTypeName` AS Role
			FROM FSHierarchy fh 
			INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fh.fsGoId
			INNER JOIN `FieldStaffType` fst ON fst.fieldStaffTypeId = f.fieldStaffTypeId;
		
	
		DROP TABLE FSHierarchy;	
			
		END;
    
    WHEN 2 THEN
		BEGIN
			
			BLOCK_fsHierarchy:BEGIN
				DECLARE curHierarchy CURSOR FOR 
				SELECT  seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = @goId AND seasonId = @sIdd;
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;	
			
				OPEN curHierarchy;
				get_curHierarchy : LOOP
				FETCH curHierarchy INTO sgcId;
				IF (c_done = 1) THEN	
					LEAVE get_curHierarchy;
				END IF;
				
				
				SELECT superRegionId,RegionId,usstatesId,seasonId INTO @srId,@rId,@stateId,@ssnId1 FROM SeasonGeographyConfiguration 
				                                                                                   WHERE seasonGeographyConfigurationId = sgcId 
				                                                                                   AND seasonId = @sIdd;
				
				SET fh_done = 0;	
				SET fh1_done = 0;	
				SET fh2_done = 0;
				IF (@stateId IS NOT NULL) THEN		
					BLOCK_If:BEGIN
						BLOCK_1:BEGIN
							DECLARE fsHierarchy1 CURSOR FOR
							(SELECT fieldStaffGoId FROM FieldStaffLeadershipSeason
									   WHERE seasonGeographyConfigurationId IN (SELECT seasonGeographyConfigurationId 
					                                          FROM SeasonGeographyConfiguration 
										  WHERE superRegionId = @srId 
										  AND RegionId =  @rId
										  AND usstatesId IS NULL
										  AND seasonId = @ssnId1));
							DECLARE CONTINUE HANDLER FOR NOT FOUND SET fh_done = 1;	
													
							OPEN fsHierarchy1;
							get_fsHierarchy1 : LOOP
							FETCH fsHierarchy1 INTO fId;
							IF (fh_done = 1) THEN
								LEAVE get_fsHierarchy1;
							END IF;
						
							INSERT INTO FSHierarchy(fsGoId) 
							VALUES	
							(fsId);
							END LOOP get_fsHierarchy1;
							CLOSE fsHierarchy1;
						END BLOCK_1;
						BLOCK_2:BEGIN
							DECLARE fsHierarchy2 CURSOR FOR
							(SELECT fieldStaffGoId FROM FieldStaffLeadershipSeason
									   WHERE seasonGeographyConfigurationId IN (SELECT seasonGeographyConfigurationId 
					                                          FROM SeasonGeographyConfiguration 
										  WHERE superRegionId = @srId 
										  AND RegionId   IS NULL
										  AND usstatesId IS NULL
										  AND seasonId = @ssnId1));
							DECLARE CONTINUE HANDLER FOR NOT FOUND SET fh1_done = 1;
							OPEN fsHierarchy2;
							get_fsHierarchy2 : LOOP
							FETCH fsHierarchy2 INTO fId;
							IF (fh1_done = 1) THEN
								LEAVE get_fsHierarchy2;
							END IF;
						
							INSERT INTO FSHierarchy(fsGoId) 
							VALUES	
							(fsId);
							END LOOP get_fsHierarchy2;
							CLOSE fsHierarchy2;
						END BLOCK_2;	
					END BLOCK_If;
					
				ELSE
					BEGIN
						DECLARE fsHierarchy2 CURSOR FOR
						(SELECT fieldStaffGoId FROM FieldStaffLeadershipSeason
									   WHERE seasonGeographyConfigurationId IN (SELECT seasonGeographyConfigurationId 
					                                          FROM SeasonGeographyConfiguration 
										  WHERE superRegionId = @srId 
										  AND RegionId  IS NULL
										  AND usstatesId IS NULL
										  AND seasonId = @ssnId1));
						DECLARE CONTINUE HANDLER FOR NOT FOUND SET fh2_done = 1;						
						OPEN fsHierarchy2;
						get_fsHierarchy2 : LOOP
						FETCH fsHierarchy2 INTO fId;
						IF (fh2_done = 1) THEN
							LEAVE get_fsHierarchy2;
						END IF;
						
							INSERT INTO FSHierarchy(fsGoId) 
							VALUES	
							(fId);
						END LOOP get_fsHierarchy2;
						CLOSE fsHierarchy2;
					END;
				END IF;
				
				END LOOP get_curHierarchy;
				CLOSE curHierarchy;	
			END BLOCK_fsHierarchy;
            SELECT  fh.fsGoId AS fieldStaffGoId,
			        f.photo,
			        f.firstName AS fsFirstName,
			        f.lastName AS fsLastName,
			        fst.`fieldStaffTypeName` AS Role
			FROM FSHierarchy fh 
			INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fh.fsGoId
			INNER JOIN `FieldStaffType` fst ON fst.fieldStaffTypeId = f.fieldStaffTypeId;	
		DROP TABLE FSHierarchy;		
		END;          
    
   	WHEN 1 THEN
	BEGIN
			SELECT 50007 INTO @supervisorId  ;
			INSERT INTO FSHierarchy(fsGoId) VALUES (@supervisorId); 
			
			
			BLOCK_fsHierarchy:BEGIN
				DECLARE curHierarchy CURSOR FOR 
				SELECT  seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = @supervisorId AND seasonId = @sIdd;
				DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;	
			
				OPEN curHierarchy;
				get_curHierarchy : LOOP
				FETCH curHierarchy INTO sgcId;
				IF (c_done = 1) THEN	
					LEAVE get_curHierarchy;
				END IF;
				
				
				SELECT superRegionId,RegionId,usstatesId,seasonId INTO @srId,@rId,@stateId,@ssnId1 
				FROM SeasonGeographyConfiguration 
				WHERE seasonGeographyConfigurationId = sgcId 
				AND seasonId = @sIdd;
				
				SET fh_done = 0;	
				SET fh1_done = 0;	
				SET fh2_done = 0;
				IF (@stateId IS NOT NULL) THEN		
					BLOCK_If:BEGIN
						BLOCK_1:BEGIN
							DECLARE fsHierarchy1 CURSOR FOR
							(SELECT fieldStaffGoId FROM FieldStaffLeadershipSeason
									   WHERE seasonGeographyConfigurationId IN (SELECT seasonGeographyConfigurationId 
					                                          FROM SeasonGeographyConfiguration 
										  WHERE superRegionId = @srId 
										  AND RegionId =  @rId
										  AND usstatesId IS NULL
										  AND seasonId = @ssnId1));
							DECLARE CONTINUE HANDLER FOR NOT FOUND SET fh_done = 1;	
													
							OPEN fsHierarchy1;
							get_fsHierarchy1 : LOOP
							FETCH fsHierarchy1 INTO fId;
							IF (fh_done = 1) THEN
								LEAVE get_fsHierarchy1;
							END IF;
						
							INSERT INTO FSHierarchy(fsGoId) 
							VALUES	
							(fsId);
							END LOOP get_fsHierarchy1;
							CLOSE fsHierarchy1;
						END BLOCK_1;
						BLOCK_2:BEGIN
							DECLARE fsHierarchy2 CURSOR FOR
							(SELECT fieldStaffGoId FROM FieldStaffLeadershipSeason
									   WHERE seasonGeographyConfigurationId IN (SELECT seasonGeographyConfigurationId 
					                                          FROM SeasonGeographyConfiguration 
										  WHERE superRegionId = @srId 
										  AND RegionId   IS NULL
										  AND usstatesId IS NULL
										  AND seasonId = @ssnId1));
							DECLARE CONTINUE HANDLER FOR NOT FOUND SET fh1_done = 1;
							OPEN fsHierarchy2;
							get_fsHierarchy2 : LOOP
							FETCH fsHierarchy2 INTO fId;
							IF (fh1_done = 1) THEN
								LEAVE get_fsHierarchy2;
							END IF;
						
							INSERT INTO FSHierarchy(fsGoId) 
							VALUES	
							(fsId);
							END LOOP get_fsHierarchy2;
							CLOSE fsHierarchy2;
						END BLOCK_2;	
					END BLOCK_If;
					
				ELSE
					BEGIN
						DECLARE fsHierarchy2 CURSOR FOR
						(SELECT fieldStaffGoId FROM FieldStaffLeadershipSeason
									   WHERE seasonGeographyConfigurationId IN (SELECT seasonGeographyConfigurationId 
					                                          FROM SeasonGeographyConfiguration 
										  WHERE superRegionId = @srId 
										  AND RegionId  IS NULL
										  AND usstatesId IS NULL
										  AND seasonId = @ssnId1));
						DECLARE CONTINUE HANDLER FOR NOT FOUND SET fh2_done = 1;						
						OPEN fsHierarchy2;
						get_fsHierarchy2 : LOOP
						FETCH fsHierarchy2 INTO fId;
						IF (fh2_done = 1) THEN
							LEAVE get_fsHierarchy2;
						END IF;
						
							INSERT INTO FSHierarchy(fsGoId) 
							VALUES	
							(fId);
						END LOOP get_fsHierarchy2;
						CLOSE fsHierarchy2;
					END;
				END IF;
				
				END LOOP get_curHierarchy;
				CLOSE curHierarchy;	
			END BLOCK_fsHierarchy;
			SELECT  fh.fsGoId AS fieldStaffGoId,
			        f.photo,
			        f.firstName AS fsFirstName,
			        f.lastName AS fsLastName,
			        fst.`fieldStaffTypeName` AS Role
			FROM FSHierarchy fh 
			INNER JOIN `FieldStaff` f ON f.`fieldStaffGoId` = fh.fsGoId
			INNER JOIN `FieldStaffType` fst ON fst.fieldStaffTypeId = f.fieldStaffTypeId;	
		DROP TABLE FSHierarchy;		
		END;			
		
         
	ELSE
		BEGIN
		END;
	END CASE;
END$$

DELIMITER ;