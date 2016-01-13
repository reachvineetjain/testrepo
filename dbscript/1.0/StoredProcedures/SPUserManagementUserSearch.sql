DELIMITER $$

USE `cci_gh_go`$$

DROP PROCEDURE IF EXISTS `SPUserManagementUserSearch`$$

CREATE PROCEDURE `SPUserManagementUserSearch`(IN globalSearch VARCHAR (100),IN GoId INT(11),IN CountryId INT(5), IN Rolelist VARCHAR(50),IN DepList VARCHAR(50), IN PgmList VARCHAR(50),IN Active VARCHAR(10),IN sortField VARCHAR(50),IN sortOrder VARCHAR (10),searchFlag TINYINT(1))
BEGIN
	DECLARE wher LONGTEXT; 
	DECLARE stmt LONGTEXT;
	DECLARE sqlstmt LONGTEXT;
	DECLARE stmt1 LONGTEXT;
	DECLARE DeptId,PrgmId,RoleId,sField VARCHAR(50);
	DECLARE gSearch VARCHAR(100);
	DECLARE CCIStaffUserID INT(11);
	DECLARE countId INT(5);
	DECLARE IsActive VARCHAR(10);
	DECLARE sOrder VARCHAR(10);
	DECLARE Flag TINYINT(1);
  
	SET @gSearch = globalSearch;
	SET @CCIStaffUserID = GoID;
	SET @DeptId = DepList;
	SET @PrgmId = PgmList;
	SET @RoleId = Rolelist;
	SET @countId = CountryId;
	SET @IsActive = Active;
	SET @sOrder = sortOrder;
	SET @sField = sortField;
	SET @Flag = searchFlag;
 
 IF @Flag = 0 THEN
	SELECT cci.cciStaffUserID
		FROM CCIStaffUsers cci
		LEFT OUTER JOIN  Login l ON l.goId=cci.cciStaffUserId
		LEFT OUTER JOIN CCIStaffUsersCCIStaffRoles ur ON cci.cciStaffUserId=ur.cciStaffUserId
		LEFT OUTER  JOIN CCIStaffRolesDepartments rd ON rd.cciStaffRoleId=ur.cciStaffRoleId
		LEFT OUTER  JOIN CCIStaffRoles r ON rd.cciStaffRoleId= r.cciStaffRoleId
		LEFT OUTER  JOIN LookupDepartments d ON rd.departmentId=d.departmentId
		LEFT OUTER JOIN LookupCountries c ON cci.countryId=c.countryId
		LEFT OUTER JOIN LookupDepartmentPrograms dp ON dp.departmentId=d.departmentId
		LEFT OUTER  JOIN CCIStaffUserProgram csp ON csp.cciStaffUserId = cci.cciStaffUserId 
		AND csp.lookupDepartmentProgramId = dp.lookupDepartmentProgramId 
	WHERE
		LOWER(cci.firstName) LIKE CONCAT('%',LOWER(@gSearch),'%')
		OR LOWER(cci.lastName) LIKE CONCAT('%',LOWER(@gSearch),'%')
		OR LOWER(l.loginName) LIKE CONCAT('%',LOWER(@gSearch),'%')
		OR LOWER(l.email) LIKE CONCAT('%',LOWER(@gSearch),'%')
		OR LOWER(r.cciStaffRoleName) LIKE CONCAT('%',LOWER(@gSearch),'%')
		OR LOWER(d.departmentName) LIKE CONCAT('%',LOWER(@gSearch),'%')
		OR LOWER(d.acronym) LIKE CONCAT('%',LOWER(@gSearch),'%')
		OR LOWER(dp.programName) LIKE CONCAT('%',LOWER(@gSearch),'%')
		OR c.countryName LIKE CONCAT('%',LOWER(@gSearch),'%')
		OR c.countryCode LIKE CONCAT('%',LOWER(@gSearch),'%')
		OR cci.cciStaffUserId = CAST(@gSearch AS UNSIGNED)
		OR cci.primaryPhone LIKE CONCAT('%',LOWER(@gSearch),'%')
	GROUP BY cci.cciStaffUserId
	ORDER BY cci.createdOn DESC;
	
 END IF;
 
 IF @Flag = 1 THEN
	SET @wher = ' WHERE 1=1';
	IF (RoleList IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (r.cciStaffRoleId,@RoleId)');
	END IF;
	IF (DepList IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (d.departmentId,@DeptId)');
	END IF;
	IF (PgmList IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (dp.lookupDepartmentProgramId,@PrgmId)');
	END IF;
	IF (CountryId IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND c.countryId = @countId');
	END IF;
	IF (GoId IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND cci.cciStaffUserId = @CCIStaffUserID');
	END IF;
	IF (Active IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (l.active,@IsActive)');
	END IF;
	IF (sortField IS NULL) THEN SET @sField = 'cci.createdOn';
	END IF;
	IF (sortField = 'active') THEN SET @sField = 'l.active';
	END IF;
	IF (sortOrder IS NULL) THEN SET @sOrder = 'DESC';
	END IF;
	
	SET @stmt = CONCAT ('SELECT cci.cciStaffUserID
		FROM CCIStaffUsers cci
		INNER JOIN Login l ON l.goId=cci.cciStaffUserId
		LEFT JOIN CCIStaffUsersCCIStaffRoles ur ON cci.cciStaffUserId=ur.cciStaffUserId
		INNER JOIN CCIStaffRolesDepartments rd ON rd.cciStaffRoleId=ur.cciStaffRoleId
		INNER JOIN CCIStaffRoles r ON rd.cciStaffRoleId= r.cciStaffRoleId
		INNER JOIN LookupDepartments d ON rd.departmentId=d.departmentId
		LEFT JOIN LookupCountries c ON cci.countryId=c.countryId
		INNER JOIN LookupDepartmentPrograms dp ON dp.departmentId=d.departmentId
		LEFT JOIN CCIStaffUserProgram csp ON csp.cciStaffUserId = cci.cciStaffUserId AND csp.lookupDepartmentProgramId = dp.lookupDepartmentProgramId',@wher,' GROUP BY cci.cciStaffUserId ORDER BY',' ',@sField,' ',@sOrder);                              
	PREPARE sqlstmt FROM @stmt;   
	EXECUTE sqlstmt;
	DEALLOCATE PREPARE sqlstmt; 
 END IF;	
 IF @Flag = 2 THEN
	
	SET @wher = ' WHERE 1=1';
	IF (RoleList IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (r.cciStaffRoleId,@RoleId)');
	END IF;
	IF (DepList IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (d.departmentId,@DeptId)');
	END IF;
	IF (PgmList IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (dp.lookupDepartmentProgramId,@PrgmId)');
	END IF;
	IF (CountryId IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND c.countryId = @countId');
	END IF;
	IF (GoId IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND cci.cciStaffUserId = @CCIStaffUserID');
	END IF;
	IF (Active IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (l.active,@IsActive)');
	END IF;
	IF (sortField IS NULL) THEN SET @sField = 'cci.createdOn';
	END IF;
	IF (sortField = 'active') THEN SET @sField = 'l.active';
	END IF;
	IF (sortOrder IS NULL) THEN SET @sOrder = 'DESC';
	END IF;
	SET @stmt1 = CONCAT ('SELECT cci.cciStaffUserID
		FROM CCIStaffUsers cci
		LEFT OUTER JOIN Login l ON l.goId=cci.cciStaffUserId
		LEFT OUTER JOIN CCIStaffUsersCCIStaffRoles ur ON cci.cciStaffUserId=ur.cciStaffUserId
		LEFT OUTER  JOIN CCIStaffRolesDepartments rd ON rd.cciStaffRoleId=ur.cciStaffRoleId
		LEFT OUTER  JOIN CCIStaffRoles r ON rd.cciStaffRoleId= r.cciStaffRoleId
		LEFT OUTER  JOIN LookupDepartments d ON rd.departmentId=d.departmentId
		LEFT OUTER JOIN LookupCountries c ON cci.countryId=c.countryId
		LEFT OUTER JOIN LookupDepartmentPrograms dp ON dp.departmentId=d.departmentId
		LEFT OUTER  JOIN CCIStaffUserProgram csp ON csp.cciStaffUserId = cci.cciStaffUserId AND csp.lookupDepartmentProgramId = dp.lookupDepartmentProgramId 
		WHERE
		LOWER(cci.firstName) LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		OR LOWER(cci.lastName) LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		OR LOWER(l.loginName) LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		OR LOWER(l.email) LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		OR LOWER(r.cciStaffRoleName) LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		OR LOWER(d.departmentName) LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		OR LOWER(d.acronym) LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		OR LOWER(dp.programName) LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		OR c.countryName LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		OR c.countryCode LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		OR cci.cciStaffUserId = CAST(','@gSearch',' AS UNSIGNED)
		OR cci.primaryPhone LIKE CONCAT(','''','%','''',',LOWER(','@gSearch','),','''','%','''',')
		GROUP BY cci.cciStaffUserId
		ORDER BY cci.createdOn DESC');
	SET @stmt = CONCAT ('SELECT cci.cciStaffUserID
		FROM CCIStaffUsers cci
		INNER JOIN Login l ON l.goId = cci.cciStaffUserId
		LEFT JOIN CCIStaffUsersCCIStaffRoles ur ON cci.cciStaffUserId=ur.cciStaffUserId
		INNER JOIN CCIStaffRolesDepartments rd ON rd.cciStaffRoleId=ur.cciStaffRoleId
		INNER JOIN CCIStaffRoles r ON rd.cciStaffRoleId= r.cciStaffRoleId
		INNER JOIN LookupDepartments d ON rd.departmentId=d.departmentId
		LEFT JOIN LookupCountries c ON cci.countryId=c.countryId
		INNER JOIN LookupDepartmentPrograms dp ON dp.departmentId=d.departmentId
		LEFT JOIN CCIStaffUserProgram csp ON csp.cciStaffUserId = cci.cciStaffUserId AND csp.lookupDepartmentProgramId = dp.lookupDepartmentProgramId
		INNER JOIN (',@stmt1,')  AS gsresult ON gsresult.cciStaffUserID = cci.cciStaffUserId',@wher,' GROUP BY cci.cciStaffUserId ORDER BY',' ',@sField,' ',@sOrder);                              
	
	PREPARE sqlstmt FROM @stmt;   
	EXECUTE sqlstmt;
	DEALLOCATE PREPARE sqlstmt; 
 END IF;
 END$$

DELIMITER ;