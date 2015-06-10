DELIMITER $$

USE `cci_gh_go`$$

DROP PROCEDURE IF EXISTS `GeneralSearch`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GeneralSearch`(IN CCIId INT(11),IN  FName VARCHAR(50), IN LName VARCHAR(50), IN LogName VARCHAR(50),IN CountryId INT(5),IN Mail VARCHAR(100), IN Rolelist VARCHAR(50),IN DepList VARCHAR(50), IN PgmList VARCHAR(50),IN Active TINYINT(1))
BEGIN
	DECLARE wher VARCHAR(21845);
	DECLARE stmt VARCHAR(21845);
	DECLARE sqlstmt VARCHAR(21845);
	DECLARE FirstName,LastName,LoginName,DeptId,PrgmId,RoleId VARCHAR(50);
	DECLARE Email VARCHAR(100);
	DECLARE CCIStaffUserID INT(11);
	DECLARE countId INT(5);
	DECLARE IsActive TINYINT(1);
  
	SET @FirstName = CONCAT(FName,'%');
	SET @LastName = CONCAT(LName,'%');
	SET @Email = CONCAT(mail,'%');
	SET @CCIStaffUserID = CCIID;
	SET @LoginName = CONCAT(LogName,'%');
	SET @DepId = DepList;
	SET @PrgmId = PgmList;
	SET @RoleId = RoleList;
	SET @countId = CountryId;
	SET @IsActive = Active;
  
	SET @wher = ' WHERE 1=1';
	IF (FName IS NOT NULL || FName != '') THEN SET @wher = CONCAT(@wher,' AND lower(cci.firstName) LIKE lower(@FirstName)');
	END IF;
	IF (LName IS NOT NULL || LName != '') THEN SET @wher = CONCAT(@wher,' AND lower(cci.lastName) LIKE lower(@LastName)');
	END IF;
	IF (LogName IS NOT NULL || LogName != '') THEN SET @wher = CONCAT(@wher,' AND lower(l.loginName) LIKE lower(@LoginName)');
	END IF;
	IF (mail IS NOT NULL || mail != '') THEN SET @wher = CONCAT(@wher,' AND lower(cci.email) LIKE lower(@Email)');
	END IF;
	IF (RoleList IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (r.cciStaffRoleId,@RoleId)');
	END IF;
	IF (DepList IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (d.departmentId,@DepId)');
	END IF;
	IF (PgmList IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND FIND_IN_SET (dp.departmentProgramId,@PrgmId)');
	END IF;
	IF (CountryId IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND c.countryId = @countId');
	END IF;
	IF (CCIId IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND cci.cciStaffUserId = @CCIStaffUserID');
	END IF;
	IF (Active IS NOT NULL) THEN SET @wher = CONCAT(@wher,' AND cci.active = @IsActive');
	END IF;
	SET @stmt = CONCAT ('SELECT cci.cciStaffUserID
		FROM CCIStaffUsers cci
		INNER JOIN Login l ON l.loginId=cci.loginId
		INNER JOIN CCIStaffUsersCCIStaffRoles ur ON cci.cciStaffUserId=ur.cciStaffUserId
		INNER JOIN CCIStaffRolesDepartments rd ON rd.cciStaffRoleId=ur.cciStaffRoleId
		INNER JOIN CCIStaffRoles r ON rd.cciStaffRoleId= r.cciStaffRoleId
		INNER JOIN Departments d ON rd.departmentId=d.departmentId
		INNER JOIN Countries c ON cci.countryId=c.countryId
		INNER JOIN DepartmentPrograms dp ON dp.departmentId=d.departmentId
		INNER JOIN CCIStaffUserProgram csp ON csp.cciStaffUserId = cci.cciStaffUserId AND csp.departmentProgramId = dp.departmentProgramId',@wher,' GROUP BY cci.cciStaffUserId');                              
	PREPARE sqlstmt FROM @stmt;   
	EXECUTE sqlstmt;
	DEALLOCATE PREPARE sqlstmt;
END$$

DELIMITER ;