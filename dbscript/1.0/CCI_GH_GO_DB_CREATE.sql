-- ----------------------------------------------------------------------------------------------------
-- Schema cci_gh_go
-- ----------------------------------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `cci_gh_go` ;
CREATE SCHEMA IF NOT EXISTS `cci_gh_go` ;

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupCountries
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LookupCountries` (
  `countryId` INT(3) NOT NULL AUTO_INCREMENT,
  `countryCode` VARCHAR(5) NOT NULL,
  `countryName` VARCHAR(50) NOT NULL,
  `countryFlag` VARCHAR(300) NULL,
  `isReqFinalSOAonDS` TINYINT(1) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`countryId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupUSStates
-- ----------------------------------------------------------------------------------------------------   
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LookupUSStates` (
  `usStatesId` INT(3) NOT NULL AUTO_INCREMENT,
  `stateName` VARCHAR(50) NOT NULL,
  `stateCode` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`usStatesId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupGender
-- ----------------------------------------------------------------------------------------------------   

CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LookupGender` (
	`genderId` INT(3) NOT NULL AUTO_INCREMENT,
	`genderName` VARCHAR(1) NOT NULL,
	PRIMARY KEY(`genderId`)
);


-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.UserType whether cciuser, lc, partner, participant etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`UserType` (
  `userTypeId` INT(3) NOT NULL AUTO_INCREMENT,
  `userTypeCode` VARCHAR(20) NOT NULL,
  `userTypeName` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`userTypeId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.OauthHistory
-- ---------------------------------------------------------------------------- -----------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`OauthHistory` (
  `nonce` VARCHAR(40) NOT NULL,
  `timeValue` DECIMAL(38,0) NOT NULL,
  PRIMARY KEY (`nonce`, `timeValue`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SysDiagrams
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SysDiagrams` (
  `name` VARCHAR(160) NOT NULL,
  `principalId` INT(11) NOT NULL,
  `diagramId` INT(11) NOT NULL AUTO_INCREMENT,
  `version` INT(11) NULL DEFAULT NULL,
  `definition` LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`diagramId`),
  UNIQUE INDEX `UK_principal_name` (`principalId` ASC, `name` ASC)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Login
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Login` (
  `loginId` INT(11) NOT NULL AUTO_INCREMENT,
  `userTypeId` INT(3) NOT NULL,
  `loginName` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`loginId`),
  UNIQUE INDEX `loginName` (`loginName` ASC),
  INDEX `FK_Login_UserType` (`userTypeId` ASC),
  CONSTRAINT `FK_Login_UserType`
    FOREIGN KEY (`userTypeId`)
    REFERENCES `cci_gh_go`.`UserType` (`userTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.History
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LoginHistory` (
  `loginHistoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `loggedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `loginId` INT(11) NOT NULL,
  `ipAddress` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`loginHistoryId`),
  INDEX `FK_History_Login` (`loginId` ASC),
  CONSTRAINT `FK_History_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `cci_gh_go`.`Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.PasswordHistory
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`PasswordHistory` (
  `passwordHistoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `password1` VARCHAR(40) NULL DEFAULT NULL,
  `password2` VARCHAR(40) NULL DEFAULT NULL,
  `password3` VARCHAR(40) NULL DEFAULT NULL,
  `password4` VARCHAR(40) NULL DEFAULT NULL,
  `loginId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`passwordHistoryId`),
  INDEX `FK_PasswordHistory_Login` (`loginId` ASC),
  CONSTRAINT `FK_PasswordHistory_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `cci_gh_go`.`Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupDepartments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`LookupDepartments` (
  `departmentId` INT(3) NOT NULL,
  `departmentName` VARCHAR(50) NOT NULL,
  `acronym` VARCHAR(10) NULL DEFAULT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`departmentId`)
);


-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentPrograms
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentPrograms` (
  `departmentProgramId` INT(3) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(3) NOT NULL,
  `programName` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`departmentProgramId`),
  INDEX `FK_DepartmentPrograms_LookupDepartments` (`departmentId` ASC),
  CONSTRAINT `FK_DepartmentPrograms_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentProgramOptions
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentProgramOptions` (
  `departmentProgramOptionId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentProgramId` INT(11) NOT NULL,
  `programOptionCode` VARCHAR(10) NOT NULL,
  `programOptionName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`departmentProgramOptionId`),
  INDEX `FK_DepartmentProgramOptions_DepartmentPrograms` (`departmentProgramId` ASC),
  CONSTRAINT `FK_DepartmentProgramOptions_DepartmentPrograms`
   FOREIGN KEY (`departmentProgramId`)
   REFERENCES `cci_gh_go`.`DepartmentPrograms` (`departmentProgramId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentFunctions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentFunctions` (
  `deptFunctionId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(11) NOT NULL,
  `functionName` VARCHAR(100) NULL,
  `functionDescription` VARCHAR(200) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`deptFunctionId`)
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourceActions actions such as add, edit, update delete on resources
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`ResourceActions` (
  `resourceActionId` INT(11) NOT NULL AUTO_INCREMENT,
  `resourceAction` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `visibleToUser` TINYINT(1) NOT NULL DEFAULT 1,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`resourceActionId`)
);
   
-- -------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRoles such as program manager, director, sys admin etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRoles` (
  `cciStaffRoleId` INT(11) NOT NULL AUTO_INCREMENT,
  `cciStaffRoleName` VARCHAR(50) NOT NULL,
  `hierarchy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRoleId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsers
-- ---------------------------------------------------------------------------------------------------- 
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsers` (
  `cciStaffUserId` INT(11) NOT NULL AUTO_INCREMENT,
  `supervisorId` INT(11) NULL,
  `loginId` INT(11) NOT NULL,
  `cciAdminGuid` VARCHAR(64) UNIQUE NOT NULL,
  `firstName` VARCHAR(30) NOT NULL,
  `lastName` VARCHAR(30) NOT NULL,
  `genderId` INT(3) NULL,
  `primaryPhone` VARCHAR(15) NULL,
  `emergencyPhone` VARCHAR(15) NULL,
  `email` VARCHAR(50) NOT NULL,
  `homeAddressLineOne` VARCHAR(100) NULL,
  `homeAddressLineTwo` VARCHAR(100) NULL,
  `city` VARCHAR(50) NULL,
  `usStatesId` INT(11) NULL,
  `zip` VARCHAR(50) NULL,
  `countryId` INT(11) NULL,
  `photo` VARCHAR(300) NULL,
  `sevisId` VARCHAR(20) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`cciStaffUserId`),
  CONSTRAINT `FK_CCIStaffUsers_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `cci_gh_go`.`LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_LookupCountries`
    FOREIGN KEY (`countryId`)
    REFERENCES `cci_gh_go`.`LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_login`
    FOREIGN KEY (`loginId`)
    REFERENCES `cci_gh_go`.`Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_LookupGender`
    FOREIGN KEY (`genderId`)
    REFERENCES `cci_gh_go`.`LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION 	
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsersCCIStaffRoles
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsersCCIStaffRoles` (  
  `cciStaffUserId` INT(11) NOT NULL,
  `cciStaffRoleId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`, `cciStaffRoleId`),
  CONSTRAINT `FK_CCIStaffUsersCCIStaffRoles_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRoleId`)
    REFERENCES `cci_gh_go`.`CCIStaffRoles` (`cciStaffRoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersCCIStaffRoles_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------
-- Creating Table CCIStaffUserProgram
-- --------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`CCIStaffUserProgram`( 
  `cciStaffUserId` INT(11) NOT NULL,
  `departmentProgramId` INT(11) NOT NULL,  
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
    PRIMARY KEY (`cciStaffUserId`, `departmentProgramId`), 
    CONSTRAINT `FK_CCIStaffUserProgram_CciStaffUser` 
    FOREIGN KEY (`cciStaffUserId`) 
    REFERENCES `cci_gh_go`.`CCIStaffUsers`(`cciStaffUserId`), 
    CONSTRAINT `FK_CCIStaffUserProgram_DepartmentPrograms` 
    FOREIGN KEY (`departmentProgramId`) 
    REFERENCES `cci_gh_go`.`DepartmentPrograms`(`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentResourceGroups such as seasons, SEVIS, accounting etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentResourceGroups` (
  `departmentResourceGroupId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(11) NOT NULL,
  `resourceGroupName` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`departmentResourceGroupId`),
  CONSTRAINT `FK_DepartmentResourceGroups_LookupDepartments` 
   FOREIGN KEY (`departmentId`)
   REFERENCES `cci_gh_go`.`LookupDepartments`(`departmentId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`ResourcePermissions` (
  `resourcePermissionId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourceActionID` INT(11) NOT NULL,
  `resourceName` VARCHAR(50) NULL,
  `resourceDescription` VARCHAR(200) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`resourcePermissionId`),
  CONSTRAINT `FK_ResourcePermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `cci_gh_go`.`DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ResourcePermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `cci_gh_go`.`ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsersResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsersResourcePermissions` (  
  `cciStaffUserId` INT(11) NOT NULL,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourcePermissionId` INT(11) NOT NULL,  
  `resourceActionId` INT(11),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`, `resourcePermissionId`),
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_ResourcePermissions`
    FOREIGN KEY (`resourcePermissionId`)
    REFERENCES `cci_gh_go`.`ResourcePermissions` (`resourcePermissionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `cci_gh_go`.`DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `cci_gh_go`.`ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRolesDepartments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRolesDepartments` (
  `cciStaffRolesDepartmentId` INT(11) NOT NULL AUTO_INCREMENT,
  `cciStaffRoleId` INT(11) NOT NULL,
  `departmentId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRolesDepartmentId`), 
  CONSTRAINT `FK_CCIStaffRolesDepartments_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRoleId`)
    REFERENCES `cci_gh_go`.`CCIStaffRoles` (`cciStaffRoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDepartments_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRolesDefaultResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRolesDefaultResourcePermissions` (  
  `cciStaffRolesDepartmentId` INT(11) NOT NULL,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourcePermissionId` INT(11) NOT NULL,
  `resourceActionId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRolesDepartmentId`, `resourcePermissionId`),
 CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_CCIStaffRolesDepartments`
    FOREIGN KEY (`cciStaffRolesDepartmentId`)
    REFERENCES `cci_gh_go`.`CCIStaffRolesDepartments` (`cciStaffRolesDepartmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_ResourcePermissions`
    FOREIGN KEY (`resourcePermissionId`)
    REFERENCES `cci_gh_go`.`ResourcePermissions` (`resourcePermissionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `cci_gh_go`.`DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `cci_gh_go`.`ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUserNotes
-- -----------------------------------------------------------------------------------------------------    
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUserNotes`(
  `cciStaffUserNoteId` INT(11) AUTO_INCREMENT,
  `ccistaffuserID` INT(11) NOT NULL,
  `note` VARCHAR(1000) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,    
 PRIMARY KEY (`cciStaffUserNoteId`),
 CONSTRAINT `FK_CCIStaffUserNotes_CCIStaffUsers`
   FOREIGN KEY (`cciStaffUserId`)
   REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);


-- -----------------------------------------------------------------------------------------------------------------
-- SEASONS TABLES :Table cci_gh_go.SeasonStatus
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonStatus` (
  `seasonStatusId` INT(3) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`seasonStatusId`)
);

-- ------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.AnnualSeason
-- --------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`AnnualSeason` (
  `annualSeasonId` INT(3) NOT NULL AUTO_INCREMENT,
  `annualSeasonName` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`annualSeasonId`)
);
 
-- -----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Season
-- --------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Season` (
  `seasonId` INT NOT NULL AUTO_INCREMENT,
  `seasonName` VARCHAR(50) NOT NULL,
  `seasonFullName` VARCHAR(50) NOT NULL,
  `departmentId` INT(3) NOT NULL,
  `seasonStatusId` INT(3) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonId`),
  INDEX `FK_Season_SeasonStatus_idx` (`seasonStatusId` ASC),
  INDEX `FK_Season_LookupDepartments_idx` (`departmentId` ASC),
  CONSTRAINT `FK_Season_SeasonStatus`
    FOREIGN KEY (`seasonStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Season_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWnTDetails
-- ----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonWnTDetails` (
  `seasonWTDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `annualSeasonId` INT(3) NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `applicationDeadlineDate` DATETIME NOT NULL,
  `isJobBoardOpen` TINYINT(1) NOT NULL,
  `maxPendingJobApps` INT NOT NULL,
  `programStatusId` INT(3) NOT NULL,
  PRIMARY KEY (`seasonWTDetailsId`),
  INDEX `FK_SeasonWPDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_seasonWnTdetails_AnnaulSeason_idx` (`annualSeasonId` ASC),
  INDEX `FK_SeasonWnTDetails_SeasonStatus_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonWPDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_seasonWnTdetails_AnnualSeason`
    FOREIGN KEY (`annualSeasonId`)
    REFERENCES `cci_gh_go`.`AnnualSeason` (`annualSeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWnTDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonCAPDetails
-- -------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonCAPDetails` (
  `seasonCAPDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `internStartDate` DATETIME NOT NULL,
  `internEndDate` DATETIME NOT NULL,
  `internAppDeadlineDate` DATETIME NOT NULL,
  `traineeStartDate` DATETIME NOT NULL,
  `traineeEndDate` DATETIME NOT NULL,
  `traineeAppDeadlineDate` DATETIME NOT NULL,
  `programStatusId` INT(3) NOT NULL,
  PRIMARY KEY (`seasonCAPDetailsId`),
  CONSTRAINT `FK_SeasonCAPDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonCAPdetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWPAllocation
-- ------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonWPAllocation` (
  `seasonWPAllocationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `departmentProgramOptionId` INT(3) NOT NULL,
  `maxPax` INT NOT NULL,
  PRIMARY KEY (`seasonWPAllocationId`),
  INDEX `FK_SeasonWPAloocation_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWPAllocation_DepartmentProgramOptions_idx` (`departmentProgramOptionId` ASC),
  CONSTRAINT `FK_SeasonWPAllocation_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWPAllocation_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `cci_gh_go`.`DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- ----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWPConfiguration
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonWPConfiguration` (
  `seasonWPConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  PRIMARY KEY (`seasonWPConfigurationId`),
  INDEX `FK_SeasonWPConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonWPConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- ------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSPConfiguration
-- ----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonHSPConfiguration` (
  `seasonHSPConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  PRIMARY KEY (`seasonHSPConfigurationId`),
  INDEX `FK_SeasonHSPConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonHSPConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- -------------------------------------------------------------------------------------------------------------------------- 
-- Table cci_gh_go.SeasonJ1Details
-- -------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonJ1Details` (
  `seasonJ1DetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programStatusId` INT(3) NOT NULL,
  `secondSemStartDate` DATETIME NOT NULL,
  `secondSemEndDate` DATETIME NOT NULL,
  `applicationDeadlineDateForSecSem` DATETIME NOT NULL,
  `secondSemEarliestBirthDate` DATETIME NOT NULL,
  `secondSemLatestBirthDate` DATETIME NOT NULL,
  `showSecondSemToNewHF` TINYINT(1) NOT NULL,
  `activeFullYearJanProgram` TINYINT(1) NOT NULL,
  `janFullYearStartDate` DATETIME NOT NULL,
  `janApplicationDeadlineDate` DATETIME NOT NULL,
  `janFullYearEndDate` DATETIME NOT NULL,
  `showJanFullYearToNewHF` TINYINT(1) NOT NULL,
  `firstSemStartDate` DATETIME NOT NULL,
  `firstSemEndDate` DATETIME NOT NULL,
  `applicationDeadlineDateForFirstSem` DATETIME NOT NULL,
  `firstSemEarliestBirthDate` DATETIME NOT NULL,
  `firstSemLatestBirthDate` DATETIME NOT NULL,
  `showFirstSemToNewHF` TINYINT(1) NOT NULL,
  `augFullYearStartDate` DATETIME NOT NULL,
  `augFullYearEndDate` DATETIME NOT NULL,
  `augFullYearAppDeadlineDate` DATETIME NOT NULL,
  `showAugFullYearToNewHF` TINYINT(1) NOT NULL,
  `showSeasonToCurrentHF` TINYINT(1) NOT NULL,
  `fieldStaffHoldLength` INT(3) NOT NULL,
  `hoursBeforeHoldExpirationWarning` INT(3) NOT NULL,
  `lcPaymentScheduleId` INT NOT NULL,
  `fsAgreementId` INT NOT NULL,
  `hfReferences` INT(3) NOT NULL,
  `hfInquiryDate` DATE NOT NULL,
  `welcomeFamily` TINYINT(1) NOT NULL,
  `showGuaranteed` TINYINT(1) NOT NULL,
  `showUnguaranteed` TINYINT(1) NOT NULL,
  `showSpecialRequstStudent` TINYINT(1) NOT NULL,
  PRIMARY KEY (`seasonJ1DetailsId`),
  INDEX `FK_J1HSPSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonJ1Details_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonJ1Details_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonJ1Details_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- -------------------------------------------------------------------------------------------------------------- 
-- Table cci_gh_go.SeasonF1Details
-- ------------------------------------------------------------------------------------------------------------------
 CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonF1Details` (
  `seasonF1DetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programStatusId` INT(3) NOT NULL,
  `secondSemStartDate` DATETIME NOT NULL,
  `secondSemEndDate` DATETIME NOT NULL,
  `applicationDeadlineForSecSem` DATETIME NOT NULL,
  `secondSemEarliestBirthDate` DATETIME NOT NULL,
  `secondSemLatestBirthDate` DATETIME NOT NULL,
  `showSecSemToNewHF` TINYINT(1) NOT NULL,
  `activeFullYearJanProgram` TINYINT(1) NOT NULL,
  `janFullYearStartDate` DATETIME NOT NULL,
  `janFullYearAppDeadlineDate` DATETIME NOT NULL,
  `janFullYearEndDate` DATETIME NOT NULL,
  `showJanFullYearToHF` TINYINT(1) NOT NULL,
  `firstSemStartDate` DATETIME NOT NULL,
  `firstSemEndDate` DATETIME NOT NULL,
  `applicationDeadlineForFirstSem` DATETIME NOT NULL,
  `firstSemEarliestBirthDate` DATETIME NOT NULL,
  `firstSemLatestBirthDate` DATETIME NOT NULL,
  `showFirstSemToNewHF` TINYINT(1) NOT NULL,
  `augFullYearStartDate` DATETIME NOT NULL,
  `augFullYearEndDate` DATETIME NULL,
  `augFullYearAppDeadlineDate` DATETIME NOT NULL,
  `showAugFullYearToNewHF` TINYINT(1) NOT NULL,
  `showSeasonToCurrentHF` TINYINT(1) NOT NULL,
  `lcPaymentScheduleId` INT NOT NULL,
  `fsAgreementId` INT NOT NULL,
  `hfReferences` INT(3) NOT NULL,
  `hfInquiryDate` DATE NOT NULL,
  `welcomeFamily` TINYINT(1) NOT NULL,
  `allowFieldStafftoStartRenewelProcess` TINYINT(1) NOT NULL,
  `showSpecialRequstStudent` TINYINT(1) NOT NULL,
  `greenHeartMargin` INT NOT NULL,
  PRIMARY KEY (`seasonF1DetailsId`),
  INDEX `FK_J1HSPSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonF1Details_status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonF1Details_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonF1Details_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSPAllocation
-- --------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonHSPAllocation` (
  `seasonHSPAllocationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `maxGuaranteedPax` INT NOT NULL,
  `maxUnguaranteedPax` INT NOT NULL,
  `departmentProgramOptionId` INT(3) NOT NULL,
  PRIMARY KEY (`seasonHSPAllocationId`),
  INDEX `FK_SeasonHSPAllocation_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonHSPAllocation_DepartmentProgramOptions_idx` (`departmentProgramOptionId` ASC),
  CONSTRAINT `FK_SeasonHSPAllocation_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonHSPAllocation_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `cci_gh_go`.`DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonGHTConfiguration
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonGHTConfiguration` (
  `seasonGHTConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  PRIMARY KEY (`seasonGHTConfigurationId`),
  INDEX `FK_SeasonGHTConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonGHTConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonHSADetails` (
  `seasonHSADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `programStatusId` INT NOT NULL,
  PRIMARY KEY (`seasonHSADetailsId`),
  INDEX `FK_SeasonHighScoolAbroadDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonHSADetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonHSADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonHSADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonLSDetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonLSDetails` (
  `seasonLSDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `programStatusId` INT(3) NOT NULL,
  PRIMARY KEY (`seasonLSDetailsId`),
  INDEX `FK_seasonLSDetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonLSDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonLSDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonLSDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonTADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonTADetails` (
  `seasonTADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `programStatusId` INT(3) NOT NULL,
  PRIMARY KEY (`seasonTADetailsId`),
  INDEX `FK_seasonTADetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonTADetails_status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonTADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonTADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWADetails
-- ----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonWADetails` (
  `seasonWADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `programStatusId` INT(3) NOT NULL,
  PRIMARY KEY (`seasonWADetailsId`),
  INDEX `FK_seasonWADetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWADetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonWADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonVADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SeasonVADetails` (
  `seasonVADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `programStatusId` INT(3) NOT NULL,
  PRIMARY KEY (`seasonVADetailsId`),
  INDEX `FK_seasonVolunteerDetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonVolunteerDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonVolunteerDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonVolunteerDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `cci_gh_go`.`SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Region
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Region` (
  `regionId` INT(3) NOT NULL AUTO_INCREMENT,
  `regionName` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`regionId`)
);

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.RegionSeason
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`RegionSeason` (
  `regionSeasonId` INT NOT NULL AUTO_INCREMENT,
  `regionId` INT(3) NOT NULL,
  `seasonId` INT NOT NULL,
  PRIMARY KEY (`regionSeasonId`),
  INDEX `FK_RegionSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_RegionSeason_Region_idx` (`regionId` ASC),
  CONSTRAINT `FK_RegionSeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_RegionSeason_Region`
    FOREIGN KEY (`regionId`)
    REFERENCES `cci_gh_go`.`Region` (`regionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.USSchool
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`USSchool` (
  `usSchoolId` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`usSchoolId`)
); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonUSSchoolSeasonStatus
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`USSchoolSeason` (
  `usSchoolSeasonId` INT NOT NULL AUTO_INCREMENT,
  `usSchoolId` INT NOT NULL,
  `seasonId` INT NOT NULL,
  `schoolStartDate` DATETIME NOT NULL,
  `secondSemStartDate` DATETIME NOT NULL,
  `schoolEndDate` DATETIME NOT NULL,
  PRIMARY KEY (`usSchoolSeasonId`),
  INDEX `FK_USHighSchoolSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_USHighSchoolSeason_USHighSchool_idx` (`usSchoolId` ASC),
  CONSTRAINT `FK_USSchoolSeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `cci_gh_go`.`Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USSchoolSeason_USSchool`
    FOREIGN KEY (`usSchoolId`)
    REFERENCES `cci_gh_go`.`USSchool` (`usSchoolId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


