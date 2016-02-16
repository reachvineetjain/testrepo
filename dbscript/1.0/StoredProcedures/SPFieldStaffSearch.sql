DELIMITER $$

USE `cci_gh_go`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffSearch`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffSearch`(IN role INT)
BEGIN 
	DECLARE roleId,fsId,fId,ssnId,sgcId INT;
	
	
	SET @roleId = role;
	
	
	
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
						GROUP_CONCAT(ps.programName) AS seasonName,
						GROUP_CONCAT(ss.Status) AS seasonStatus
				
				FROM 		FieldStaff fs
				LEFT JOIN	FieldStaffSeason fsls ON  fsls.fieldStaffGoId = fs.fieldStaffGoId
				INNER JOIN 	FieldStaffStatus fss ON fs.fieldStaffStatusId = fss.fieldStaffStatusId
				INNER JOIN 	Login l ON fs.fieldStaffGoId = l.goId
				INNER JOIN 	ProgramSeasons ps ON fsls.seasonId = ps.seasonId AND fsls.departmentProgramId = ps.departmentProgramId
				INNER JOIN	SeasonStatus ss ON ps.programStatusId = ss.seasonStatusId
				INNER JOIN 	LookupUSStates lus ON fs.currentStateId = lus.usStatesId
				WHERE 		fieldStaffTypeId = @roleId
				GROUP BY 	fs.fieldStaffGoId;
			
	
	WHEN 4 THEN
				
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
						fss.fieldStaffStatusName AS fieldStaffStatus
				
				FROM 		FieldStaff fs
				LEFT JOIN	FieldStaffSeason fsls ON  fsls.fieldStaffGoId = fs.fieldStaffGoId
				INNER JOIN 	FieldStaffStatus fss ON fs.fieldStaffStatusId = fss.fieldStaffStatusId
				INNER JOIN 	Login l ON fs.fieldStaffGoId = l.goId
				INNER JOIN 	LookupUSStates lus ON fs.currentStateId = lus.usStatesId
				WHERE 		fieldStaffTypeId = @roleId 
				GROUP BY 	fs.fieldStaffGoId;
		
	WHEN 3 THEN
				
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
						fss.fieldStaffStatusName AS fieldStaffStatus
				
				FROM 		FieldStaff fs
				LEFT JOIN	FieldStaffSeason fsls ON  fsls.fieldStaffGoId = fs.fieldStaffGoId
				INNER JOIN 	FieldStaffStatus fss ON fs.fieldStaffStatusId = fss.fieldStaffStatusId
				INNER JOIN 	Login l ON fs.fieldStaffGoId = l.goId
				INNER JOIN 	LookupUSStates lus ON fs.currentStateId = lus.usStatesId
				WHERE 		fieldStaffTypeId = @roleId 
				GROUP BY 	fs.fieldStaffGoId;
    
    WHEN 2 THEN
				
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
						fss.fieldStaffStatusName AS fieldStaffStatus
				
				FROM 		FieldStaff fs
				LEFT JOIN	FieldStaffSeason fsls ON  fsls.fieldStaffGoId = fs.fieldStaffGoId
				INNER JOIN 	FieldStaffStatus fss ON fs.fieldStaffStatusId = fss.fieldStaffStatusId
				INNER JOIN 	Login l ON fs.fieldStaffGoId = l.goId
				INNER JOIN 	LookupUSStates lus ON fs.currentStateId = lus.usStatesId
				WHERE 		fieldStaffTypeId = @roleId 
				GROUP BY 	fs.fieldStaffGoId;          
    
   	WHEN 1 THEN
				
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
						fss.fieldStaffStatusName AS fieldStaffStatus
				
				FROM 		FieldStaff fs
				LEFT JOIN	FieldStaffSeason fsls ON  fsls.fieldStaffGoId = fs.fieldStaffGoId
				INNER JOIN 	FieldStaffStatus fss ON fs.fieldStaffStatusId = fss.fieldStaffStatusId
				INNER JOIN 	Login l ON fs.fieldStaffGoId = l.goId
				INNER JOIN 	LookupUSStates lus ON fs.currentStateId = lus.usStatesId
				WHERE 		fieldStaffTypeId = @roleId 
				GROUP BY 	fs.fieldStaffGoId;
         
	ELSE
		BEGIN
		END;
	END CASE;
END$$

DELIMITER ;