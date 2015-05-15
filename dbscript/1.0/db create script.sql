-- ----------------------------------------------------------------------------------------------------
-- Schema cci_gh_go
-- ----------------------------------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `cci_gh_go` ;
CREATE SCHEMA IF NOT EXISTS `cci_gh_go` ;

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.countries
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`countries` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `countryCode` VARCHAR(5) NOT NULL,  
  `countryName` VARCHAR(50) NOT NULL,
   PRIMARY KEY (`id`)
);
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.usstates
-- ----------------------------------------------------------------------------------------------------   
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`usstates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `stateName` VARCHAR(50) NOT NULL,
  `stateCode` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`)
);
  
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.login_type weather cciuser, lc, partner, participant etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`user_type` (
  `userTypeId` INT NOT NULL AUTO_INCREMENT,
  `userTypeCode` VARCHAR(20) NOT NULL,
  `userTypeName` VARCHAR(50) NOT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  PRIMARY KEY (`userTypeId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.oauth_history-
-- ----------------------------------------------------------------------------	-----------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`oauth_history` (
  `nonce` varchar(40) NOT NULL,
  `timevalue` decimal(38,0) NOT NULL,
  PRIMARY KEY (`nonce`,`timevalue`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.sysdiagrams
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`sysdiagrams` (
  `name` VARCHAR(160) NOT NULL,
  `principal_id` INT NOT NULL,
  `diagram_id` INT NOT NULL AUTO_INCREMENT,
  `version` INT NULL,
  `definition` LONGBLOB NULL,
  PRIMARY KEY (`diagram_id`),
  UNIQUE INDEX `UK_principal_name` (`principal_id` ASC, `name` ASC)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Login
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`login` (
  `loginId` INT NOT NULL AUTO_INCREMENT,
  `userTypeId` INT NOT NULL,
  `loginName` VARCHAR(50) UNIQUE NOT NULL,
  `password` VARCHAR(10) NOT NULL,
   PRIMARY KEY (`loginId`),
   CONSTRAINT `FK_login_user_type`
    FOREIGN KEY (`userTypeId`)
    REFERENCES `cci_gh_go`.`user_type` (`userTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.History
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`history` (
  `historyId` int(11) NOT NULL AUTO_INCREMENT,
  `loggedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `loginId` int(11) NOT NULL,
  `ipAddress` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`historyId`),
 CONSTRAINT `FK_login_history`
    FOREIGN KEY (`loginId`)
    REFERENCES `cci_gh_go`.`login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.password_history
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`password_history` (
  `passwordHistoryId` int(11) NOT NULL AUTO_INCREMENT,
  `password1` varchar(40) DEFAULT NULL,
  `password2` varchar(40) DEFAULT NULL,
  `password3` varchar(40) DEFAULT NULL,
  `password4` varchar(40) DEFAULT NULL,
  `loginId` int(11) DEFAULT NULL,
  PRIMARY KEY (`passwordHistoryId`),
 CONSTRAINT `FK_login_password_history`
    FOREIGN KEY (`loginId`)
    REFERENCES `cci_gh_go`.`login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
  
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Departments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`departments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `departmentName` VARCHAR(50) NOT NULL,
  `acronym` VARCHAR(10) NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentPrograms
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentPrograms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `departmentID` INT NOT NULL,
  `program` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_DepartmentPrograms_Departments`
    FOREIGN KEY (`departmentID`)
    REFERENCES `cci_gh_go`.`Departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentProgramOptions
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentProgramOptions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `programID` INT NOT NULL,
  `programOptionCode` VARCHAR(10) NOT NULL,
  `programOption` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `FK_DepartmentProgramOptions_DepartmentPrograms`
    FOREIGN KEY (`programID`)
    REFERENCES `cci_gh_go`.`DepartmentPrograms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentFunctions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentFunctions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `departmentID` INT NOT NULL,
  `functionName` VARCHAR(100) NULL,
  `functionDescription` VARCHAR(200) NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_DepartmentFunctions_Departments`
    FOREIGN KEY (`departmentID`)
    REFERENCES `cci_gh_go`.`Departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourceActions actions such as add, edit, update delete on resources
-- ----------------------------------------------------------------------------------------------------
	
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`ResourceActions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `resourceAction` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `visibleToUser` TINYINT(1) NOT NULL DEFAULT 1,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  PRIMARY KEY (`id`)
);
  
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourceGroups such as seasons, SEVIS, accounting etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`ResourceGroups` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `resourceGroupName` VARCHAR(50) NOT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  PRIMARY KEY (`id`)
);
  
----- -------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRoles such as program manager, director, sys admin etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRoles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cciStaffRole` VARCHAR(50) NOT NULL,
  `hierarchy` INT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  PRIMARY KEY (`id`)
);
  
  -- --------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`ResourcePermissions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `resourceGroupID` INT NOT NULL,
  `resourceActionID` INT NOT NULL,
  `resourceName` VARCHAR(50) NULL,
  `resourceDescription` VARCHAR(200) NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ResourcePermissions_ResourceGroups`
    FOREIGN KEY (`resourceGroupID`)
    REFERENCES `cci_gh_go`.`ResourceGroups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ResourcePermissions_ResourceActions`
    FOREIGN KEY (`resourceActionID`)
    REFERENCES `cci_gh_go`.`ResourceActions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsers
-- ----------------------------------------------------------------------------------------------------	
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsers` (
  `cciStaffUserID` INT NOT NULL AUTO_INCREMENT,
  `supervisorId` INT NULL,
  `loginID` INT NOT NULL,
  `cciAdminGuid` VARCHAR(64) UNIQUE NOT NULL,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(25) NOT NULL,
  `emergencyPhone` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `homeAddressLineOne` VARCHAR(100) NOT NULL,
  `homeAddressLineTwo` VARCHAR(100) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `stateID` INT NOT NULL,
  `zip` VARCHAR(50) NOT NULL,
  `countryID` INT NOT NULL,
  `photo` VARCHAR(300) NULL,
  `sevisID` VARCHAR(20) NOT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `sequenceNo` INT NOT NULL,
  `stamp` BINARY(8) NOT NULL,
  PRIMARY KEY (`cciStaffUserID`),
  CONSTRAINT `FK_CCIStaffUsers_usstates`
    FOREIGN KEY (`stateID`)
    REFERENCES `cci_gh_go`.`usstates` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_countries`
    FOREIGN KEY (`countryID`)
    REFERENCES `cci_gh_go`.`countries` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_login`
    FOREIGN KEY (`loginID`)
    REFERENCES `cci_gh_go`.`login` (`loginID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsers_CCIStaffRoles
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsers_CCIStaffRoles` (  
  `cciStaffID` INT NOT NULL,
  `cciStaffRolesID` INT NOT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  PRIMARY KEY (`cciStaffID`, `cciStaffRolesID`),
  CONSTRAINT `FK_CCIStaffUsers_CCIStaffRoles_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRolesID`)
    REFERENCES `cci_gh_go`.`CCIStaffRoles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_CCIStaffRoles_CCIStaffUsers`
    FOREIGN KEY (`cciStaffID`)
    REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	
	-- ------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsers_Departments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsers_Departments` ( 
  `cciStaffUserId` INT NOT NULL,
  `departmentID` INT NOT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
   PRIMARY KEY (`cciStaffUserId`, `departmentID`),
  CONSTRAINT `FK_CCIStaffUsers_Departments_Departments`
    FOREIGN KEY (`departmentID`)
    REFERENCES `cci_gh_go`.`Departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_Departments_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsers_ResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsers_ResourcePermissions` (  
  `cciStaffUserId` INT NOT NULL,
  `resourcesPermissionsId` INT NOT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  PRIMARY KEY (`cciStaffUserId`, `resourcesPermissionsId`),
  CONSTRAINT `FK_CCIStaffUsers_ResourcePermissions_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_ResourcePermissions_ResourcePermissions`
    FOREIGN KEY (`resourcesPermissionsId`)
    REFERENCES `cci_gh_go`.`ResourcePermissions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRoles_Departments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRoles_Departments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cciStaffRoleId` INT NOT NULL,
  `departmentId` INT NOT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CCIStaffRoles_Departments_Departments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`Departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRoles_Departments_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRoleId`)
    REFERENCES `cci_gh_go`.`CCIStaffRoles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRoles_DefaultResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRoles_DefaultResourcePermissions` (  
  `cciStaffDepartmentRoleId` INT NOT NULL,
  `resourcePermissionsId` INT NOT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT NOT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT NOT NULL,
  PRIMARY KEY (`cciStaffDepartmentRoleId`, `resourcePermissionsId`),
  CONSTRAINT `FK_CCIStaffRoles_DefaultResourcePermissions_ResourcePermissions`
    FOREIGN KEY (`resourcePermissionsId`)
    REFERENCES `cci_gh_go`.`ResourcePermissions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRoles_DefaultResourcePermissions_CCIStaffRoles_Depa1`
    FOREIGN KEY (`cciStaffDepartmentRoleId`)
    REFERENCES `cci_gh_go`.`CCIStaffRoles_Departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
-- ----------------------------------------------------------------------------
-- Table cci_gh_go.Department_ResourceGroups
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Department_ResourceGroups` (
  `departmentId` INT NOT NULL,
  `resourceGroupId` INT NOT NULL,
  PRIMARY KEY (`departmentId`, `resourceGroupId`),
  CONSTRAINT `FK_Department_ResourceGroups_Departments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`Departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Department_ResourceGroups_ResourceGroups`
    FOREIGN KEY (`resourceGroupId`)
    REFERENCES `cci_gh_go`.`ResourceGroups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);



