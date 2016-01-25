DELIMITER $$

USE `cci_gh_go_dev`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffSearch`$$

CREATE DEFINER=`phanipothula`@`%` PROCEDURE `SPFieldStaffSearch`(IN role INT)
BEGIN 
	DECLARE roleId,fsId,fId,ssnId,sgcId INT;
	DECLARE c_done,f_done,fh_done,fh2_done,fh1_done INT DEFAULT 0;
	
	IF (role IS NULL) THEN
		SET @roleId = (SELECT fieldStaffTypeId FROM FieldStaff WHERE fieldStaffGoId = @goId);
	ELSE
		SET @roleId = role;
	END IF;
	
	DROP TEMPORARY TABLE IF EXISTS FSHierarchy;
	CREATE TEMPORARY TABLE FSHierarchy (fsGoId INT, fsGoIdphoto VARCHAR (150),sesnId INT,depPgmId INT,fsHierarchyGoId INT,phone VARCHAR(25),city VARCHAR(50									  ),stateId INT,zCode VARCHAR(20),fsGoIdFirstName VARCHAR(50),fsGoIdLastName VARCHAR(50));
	
	CASE @roleId
	WHEN 5 THEN
		
				SELECT  fs.fieldStaffGoId AS fieldStaffGoId,
						fs.photo AS photo,
				        fs.firstName AS firstName,
						fs.lastName AS lastName,
						fs.phone AS phone,
						fs.currentCity AS city,
						fs.currentZipCode AS zipCode,
						lus.stateName AS stateName,
						l.active AS active,
						l.email AS email,
						fss.fieldStaffStatusName AS fieldStaffStatus,
						ps.programName AS seasonName,
						ss.Status AS seasonStatus
				
				FROM 		FieldStaff fs
				INNER JOIN	FieldStaffSeason fsls ON  fsls.fieldStaffGoId = fs.fieldStaffGoId
				INNER JOIN 	FieldStaffStatus fss ON fs.fieldStaffStatusId = fss.fieldStaffStatusId
				INNER JOIN 	Login l ON fs.fieldStaffGoId = l.goId
				INNER JOIN 	ProgramSeasons ps ON fsls.seasonId = ps.seasonId AND fsls.departmentProgramId = ps.departmentProgramId
				INNER JOIN	SeasonStatus ss ON ps.programStatusId = ss.seasonStatusId
				INNER JOIN 	LookupUSStates lus ON fs.currentStateId = lus.usStatesId
				WHERE 		fieldStaffTypeId = @roleId ;
			
				DROP TABLE FSHierarchy;	
	
	WHEN 4 THEN
		BEGIN
			DECLARE curHierarchy CURSOR FOR 
			SELECT seasonId, seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = @goId;
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET c_done = 1;	
			
			IF (@sId IS NOT NULL) THEN
				SELECT 1;
			ELSE
				
				OPEN curHierarchy;
				get_curHierarchy : LOOP
				FETCH curHierarchy INTO ssnId,sgcId;
				IF (c_done = 1) THEN	
					LEAVE get_curHierarchy;
				END IF;
				
				SELECT superRegionId,RegionId,usstatesId INTO @srId,@rId,@stateId FROM SeasonGeographyConfiguration WHERE seasonGeographyConfigurationId = sgcId;
				
				IF (@stateId IS NOT NULL) THEN		
					
					INSERT INTO FSHierarchy(fsGoId,roleTypeId,sesnId,depPgmId) 
					VALUES	
					((SELECT fs.fieldStaffGoId FROM FieldStaffSeason fs
					                           INNER JOIN FieldStaff f ON f.fieldStaffGoId = fs.fieldStaffGoId
					                           AND f.fieldStaffTypeId = 4
					                           INNER JOIN SeasonGeographyConfiguration sgc ON sgc.seasonId = fs.seasonId
					                           AND f.`currentStateId` = sgc.`usstatesId`
					                           WHERE superRegionId = @srId 
							                              AND RegionId =  @rId
							                              AND usstatesId = @stateId 
							                              AND sgc.seasonId = ssnId),
					 @roleId,ssnId,
					 (SELECT fs.departmentProgramId FROM FieldStaffSeason fs WHERE fs.fieldStaffGoId = @goId AND fs.seasonId = ssnId));
					 
															 
				END IF;
				
				END LOOP get_curHierarchy;
				CLOSE curHierarchy;	
				
				
			END IF;	
		END;
		
	WHEN 3 THEN
		BEGIN
			DECLARE curfsId CURSOR FOR 
			SELECT fieldStaffGoId FROM FieldStaff WHERE fieldStaffTypeId = 3;
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET f_done = 1;
			OPEN curfsId;
			get_curfsId : LOOP
			FETCH curfsId INTO fsId;
			IF (f_done = 1) THEN
			LEAVE get_curfsId;
			END IF;
			SET c_done = 0;
			SELECT photo,firstName,lastName,phone,currentCity,currentStateId,currentZipCode INTO @pic,@fName,@lName,@pnumber,@ccity,@cStateId,@cCode
			FROM FieldStaff WHERE fieldStaffGoId = fsId;
			
			BLOCK_fsHierarchy:BEGIN
				DECLARE curHierarchy CURSOR FOR 
				SELECT seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = fsId;
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
						
							INSERT INTO FSHierarchy(fsGoId,fsGoIdphoto,sesnId,fsHierarchyGoId,phone,city,stateId,zCode,fsGoIdFirstName,fsGoIdLastName) 
							VALUES	
							(fsId,@pic,@ssnId1,fId,@pnumber,@ccity,@cStateId,@cCode,@fName,@lName);
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
						
							INSERT INTO FSHierarchy(fsGoId,fsGoIdphoto,sesnId,fsHierarchyGoId,phone,city,stateId,zCode,fsGoIdFirstName,fsGoIdLastName) 
							VALUES	
							(fsId,@pic,@ssnId1,fId,@pnumber,@ccity,@cStateId,@cCode,@fName,@lName);
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
						
							INSERT INTO FSHierarchy(fsGoId,fsGoIdphoto,sesnId,fsHierarchyGoId,phone,city,stateId,zCode,fsGoIdFirstName,fsGoIdLastName) 
							VALUES	
							(fsId,@pic,@ssnId1,fId,@pnumber,@ccity,@cStateId,@cCode,@fName,@lName);
						END LOOP get_fsHierarchy2;
						CLOSE fsHierarchy2;
					END;
				END IF;
				
				END LOOP get_curHierarchy;
				CLOSE curHierarchy;	
			END BLOCK_fsHierarchy;
			END LOOP get_curfsId;
			CLOSE curfsId;	
		SELECT  fh.fsGoId AS fieldStaffGoId,
			fh.fsGoIdphoto,
			fh.fsGoIdFirstName AS fsFirstName,
			fh.fsGoIdLastName AS fsLastName,
			fh.phone AS phone,
			fh.city AS city,
			fh.zCode AS zipCode,
			lus.stateName AS stateName,	
			l.active,
			l.email,
			ps.programName AS seasonName,
			ss.Status AS seasonStatus,
			fh.fsHierarchyGoId AS fsHierarchyGoId,
			fs.photo AS  fsHierarchyGoIdphoto,
			fst.fieldStaffTypeName AS fieldStaffType,
			fs.firstName AS fsHierarchyFirstName,
			fs.lastName AS fsHierarchyLastName	
		FROM FSHierarchy fh
		INNER JOIN FieldStaff fs ON fs.fieldStaffGoId = fh.fsHierarchyGoId
		INNER JOIN ProgramSeasons ps ON fh.sesnId = ps.seasonId
		INNER JOIN FieldStaffType fst ON fs.fieldStaffTypeId = fst.fieldStaffTypeId
		INNER JOIN Login l ON fs.fieldStaffGoId = l.goId
		INNER JOIN LookupUSStates lus ON fs.currentStateId = lus.usStatesId
		INNER JOIN SeasonStatus ss ON ps.programStatusId = ss.seasonStatusId
		ORDER BY fh.fsGoId;
		
	--	SELECT * FROM FSHierarchy;
	
		DROP TABLE FSHierarchy;	
			
		END;
    
    WHEN 2 THEN
		BEGIN
			DECLARE curfsId CURSOR FOR 
			SELECT fieldStaffGoId FROM FieldStaff WHERE fieldStaffTypeId = 2;
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET f_done = 1;
			OPEN curfsId;
			get_curfsId : LOOP
			FETCH curfsId INTO fsId;
			IF (f_done = 1) THEN
			LEAVE get_curfsId;
			END IF;
			SET c_done = 0;
			SELECT photo,firstName,lastName,phone,currentCity,currentStateId,currentZipCode INTO @pic,@fName,@lName,@pnumber,@ccity,@cStateId,@cCode
			FROM FieldStaff WHERE fieldStaffGoId = fsId;
			
			BLOCK_fsHierarchy:BEGIN
				DECLARE curHierarchy CURSOR FOR 
				SELECT  seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = fsId;
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
						
							INSERT INTO FSHierarchy(fsGoId,fsGoIdphoto,sesnId,fsHierarchyGoId,phone,city,stateId,zCode,fsGoIdFirstName,fsGoIdLastName) 
							VALUES	
							(fsId,@pic,@ssnId1,fId,@pnumber,@ccity,@cStateId,@cCode,@fName,@lName);
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
						
							INSERT INTO FSHierarchy(fsGoId,fsGoIdphoto,sesnId,fsHierarchyGoId,phone,city,stateId,zCode,fsGoIdFirstName,fsGoIdLastName) 
							VALUES	
							(fsId,@pic,@ssnId1,fId,@pnumber,@ccity,@cStateId,@cCode,@fName,@lName);
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
						
							INSERT INTO FSHierarchy(fsGoId,fsGoIdphoto,sesnId,fsHierarchyGoId,phone,city,stateId,zCode,fsGoIdFirstName,fsGoIdLastName) 
							VALUES	
							(fsId,@pic,@ssnId1,fId,@pnumber,@ccity,@cStateId,@cCode,@fName,@lName);
						END LOOP get_fsHierarchy2;
						CLOSE fsHierarchy2;
					END;
				END IF;
				
				END LOOP get_curHierarchy;
				CLOSE curHierarchy;	
			END BLOCK_fsHierarchy;
			END LOOP get_curfsId;
			CLOSE curfsId;	
		SELECT  fh.fsGoId AS fieldStaffGoId,
			fh.fsGoIdphoto,
			fh.fsGoIdFirstName AS fsFirstName,
			fh.fsGoIdLastName AS fsLastName,
			fh.phone AS phone,
			fh.city AS city,
			fh.zCode AS zipCode,
			lus.stateName AS stateName,	
			l.active,
			l.email,
			ps.programName AS seasonName,
			ss.Status AS seasonStatus,
			fh.fsHierarchyGoId AS fsHierarchyGoId,
			fs.photo AS  fsHierarchyGoIdphoto,
			fst.fieldStaffTypeName AS fieldStaffType,
			fs.firstName AS fsHierarchyFirstName,
			fs.lastName AS fsHierarchyLastName	
				
		FROM FSHierarchy fh
		INNER JOIN FieldStaff fs ON fs.fieldStaffGoId = fh.fsHierarchyGoId
		INNER JOIN ProgramSeasons ps ON fh.sesnId = ps.seasonId
		INNER JOIN FieldStaffType fst ON fs.fieldStaffTypeId = fst.fieldStaffTypeId
		INNER JOIN Login l ON fs.fieldStaffGoId = l.goId
		INNER JOIN SeasonStatus ss ON ps.programStatusId = ss.seasonStatusId
		INNER JOIN LookupUSStates lus ON fs.currentStateId = lus.usStatesId
		ORDER BY fh.fsGoId; 
	
		 -- SELECT * FROM FSHierarchy;
		DROP TABLE FSHierarchy;		
		END;          
    
   	WHEN 1 THEN
		BEGIN
			DECLARE curfsId CURSOR FOR 
			SELECT fieldStaffGoId FROM FieldStaff WHERE fieldStaffTypeId = 1;
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET f_done = 1;
			OPEN curfsId;
			get_curfsId : LOOP
			FETCH curfsId INTO fsId;
			IF (f_done = 1) THEN
			LEAVE get_curfsId;
			END IF;
			SET c_done = 0;
			SELECT photo,firstName,lastName,phone,currentCity,currentStateId,currentZipCode INTO @pic,@fName,@lName,@pnumber,@ccity,@cStateId,@cCode
			FROM FieldStaff WHERE fieldStaffGoId = fsId;
			
			BLOCK_fsHierarchy:BEGIN
				DECLARE curHierarchy CURSOR FOR 
				SELECT  seasonGeographyConfigurationId FROM FieldStaffLeadershipSeason WHERE fieldStaffGoId = 50006;
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
						
							INSERT INTO FSHierarchy(fsGoId,fsGoIdphoto,sesnId,fsHierarchyGoId,phone,city,stateId,zCode,fsGoIdFirstName,fsGoIdLastName) 
							VALUES	
							(fsId,@pic,@ssnId1,fId,@pnumber,@ccity,@cStateId,@cCode,@fName,@lName);
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
						
							INSERT INTO FSHierarchy(fsGoId,fsGoIdphoto,sesnId,fsHierarchyGoId,phone,city,stateId,zCode,fsGoIdFirstName,fsGoIdLastName) 
							VALUES	
							(fsId,@pic,@ssnId1,fId,@pnumber,@ccity,@cStateId,@cCode,@fName,@lName);
							END LOOP get_fsHierarchy2;
							CLOSE fsHierarchy2;
					END BLOCK_2;	
				
				END LOOP get_curHierarchy;
				CLOSE curHierarchy;	
			END BLOCK_fsHierarchy;
			END LOOP get_curfsId;
			CLOSE curfsId;	
		SELECT  fh.fsGoId AS fieldStaffGoId,
			fh.fsGoIdphoto,
			fh.fsGoIdFirstName AS fsFirstName,
			fh.fsGoIdLastName AS fsLastName,
			fh.phone AS phone,
			fh.city AS city,
			fh.zCode AS zipCode,
			lus.stateName AS stateName,	
			l.active,
			l.email,
			ps.programName AS seasonName,
			ss.Status AS seasonStatus,
			fh.fsHierarchyGoId AS fsHierarchyGoId,
			fs.photo AS  fsHierarchyGoIdphoto,
			fst.fieldStaffTypeName AS fieldStaffType,
			fs.firstName AS fsHierarchyFirstName,
			fs.lastName AS fsHierarchyLastName	
				
		FROM FSHierarchy fh
		INNER JOIN FieldStaff fs ON fs.fieldStaffGoId = fh.fsHierarchyGoId
		INNER JOIN ProgramSeasons ps ON fh.sesnId = ps.seasonId
		INNER JOIN FieldStaffType fst ON fs.fieldStaffTypeId = fst.fieldStaffTypeId
		INNER JOIN Login l ON fs.fieldStaffGoId = l.goId
		INNER JOIN SeasonStatus ss ON ps.programStatusId = ss.seasonStatusId
		INNER JOIN LookupUSStates lus ON fs.currentStateId = lus.usStatesId
		ORDER BY fh.fsGoId; 
	
		 -- SELECT * FROM FSHierarchy;
		DROP TABLE FSHierarchy;		
		END;
         
	ELSE
		BEGIN
		END;
	END CASE;
END$$

DELIMITER ;