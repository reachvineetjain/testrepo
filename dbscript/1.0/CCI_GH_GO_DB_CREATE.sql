-- ----------------------------------------------------------------------------------------------------
-- Schema cci_gh_go
-- ----------------------------------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `cci_gh_go` ;
CREATE SCHEMA IF NOT EXISTS `cci_gh_go` ;

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Countries
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Countries` (
  `countryID` INT(11) NOT NULL AUTO_INCREMENT,
  `countryCode` VARCHAR(5) NOT NULL,  
  `countryName` VARCHAR(50) NOT NULL,
  `countryFlag` VARCHAR(300) ,
  `reqFinalSOAonDS` TINYINT(1) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
   PRIMARY KEY (`countryID`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.USStates
-- ----------------------------------------------------------------------------------------------------   
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`USStates` (
  `usstatesID` INT(11) NOT NULL AUTO_INCREMENT,
  `stateCode` VARCHAR(5) NOT NULL,
  `stateName` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`usstatesID`)
);
  
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.UserType whether cciuser, lc, partner, participant etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`UserType` (
  `userTypeId` INT(11) NOT NULL AUTO_INCREMENT,
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
-- ----------------------------------------------------------------------------	-----------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`OauthHistory` (
  `nonce` VARCHAR(40) NOT NULL,
  `timevalue` DECIMAL(38,0) NOT NULL,
  PRIMARY KEY (`nonce`,`timevalue`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SysDiagrams
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`SysDiagrams` (
  `name` VARCHAR(160) NOT NULL,
  `principalID` INT(11) NOT NULL,
  `diagramID` INT(11) NOT NULL AUTO_INCREMENT,
  `version` INT(11) NULL,
  `definition` LONGBLOB NULL,
  PRIMARY KEY (`diagramID`),
  UNIQUE INDEX `UK_principal_name` (`principalID` ASC, `name` ASC)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Login
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Login` (
  `loginId` INT(11) NOT NULL AUTO_INCREMENT,
  `userTypeId` INT(11) NOT NULL,
  `loginName` VARCHAR(50) UNIQUE NOT NULL,
  `password` VARCHAR(10) NOT NULL,
   PRIMARY KEY (`loginId`),
   CONSTRAINT `FK_Login_UserType`
    FOREIGN KEY (`userTypeId`)
    REFERENCES `cci_gh_go`.`UserType` (`userTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.History
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`History` (
  `historyId` INT(11) NOT NULL AUTO_INCREMENT,
  `loggedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `loginId` INT(11) NOT NULL,
  `ipAddress` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`historyId`),
 CONSTRAINT `FK_Login_History`
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
  `password1` VARCHAR(40) DEFAULT NULL,
  `password2` VARCHAR(40) DEFAULT NULL,
  `password3` VARCHAR(40) DEFAULT NULL,
  `password4` VARCHAR(40) DEFAULT NULL,
  `loginId` INT(11) DEFAULT NULL,
  PRIMARY KEY (`passwordHistoryId`),
 CONSTRAINT `FK_Login_PasswordHistory`
    FOREIGN KEY (`loginId`)
    REFERENCES `cci_gh_go`.`Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Departments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Departments` (
  `departmentID` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentName` VARCHAR(50) NOT NULL,
  `acronym` VARCHAR(10) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`departmentID`)
); 

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentPrograms
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentPrograms` (
  `programID` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentID` INT(11) NOT NULL,
  `program` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`programID`),
  CONSTRAINT `FK_DepartmentPrograms_Departments`
    FOREIGN KEY (`departmentID`)
    REFERENCES `cci_gh_go`.`Departments` (`departmentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentProgramOptions
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentProgramOptions` (
  `programOptionID` INT(11) NOT NULL AUTO_INCREMENT,
  `programID` INT(11) NOT NULL,
  `programOptionCode` VARCHAR(10) NOT NULL,
  `programOption` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`programOptionID`),
  CONSTRAINT `FK_DepartmentProgramOptions_DepartmentPrograms`
    FOREIGN KEY (`programID`)
    REFERENCES `cci_gh_go`.`DepartmentPrograms` (`programID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentFunctions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentFunctions` (
  `deptFunctionID` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentID` INT(11) NOT NULL,
  `functionName` VARCHAR(100) NULL,
  `functionDescription` VARCHAR(200) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`deptFunctionID`),
  CONSTRAINT `FK_DepartmentFunctions_Departments`
    FOREIGN KEY (`departmentID`)
    REFERENCES `cci_gh_go`.`Departments` (`departmentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourceActions actions such as add, edit, update delete on resources
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`ResourceActions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `resourceAction` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `visibleToUser` TINYINT(1) NOT NULL DEFAULT 1,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`id`)
);
   
-- -------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRoles such as program manager, director, sys admin etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRoles` (
  `cciStaffRoleID` INT(11) NOT NULL AUTO_INCREMENT,
  `cciStaffRole` VARCHAR(50) NOT NULL,
  `hierarchy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRoleID`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsers
-- ----------------------------------------------------------------------------------------------------	
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsers` (
  `cciStaffUserID` INT(11) NOT NULL AUTO_INCREMENT,
  `supervisorId` INT(11) NULL,
  `loginID` INT(11) NOT NULL,
  `cciAdminGuid` VARCHAR(64) UNIQUE NOT NULL,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(25) NULL,
  `emergencyPhone` VARCHAR(50) NULL,
  `email` VARCHAR(50) NOT NULL,
  `homeAddressLineOne` VARCHAR(100) NULL,
  `homeAddressLineTwo` VARCHAR(100) NULL,
  `city` VARCHAR(50) NULL,
  `stateID` INT(11) NULL,
  `zip` VARCHAR(50) NULL,
  `countryID` INT(11) NULL,
  `photo` VARCHAR(300) NULL,
  `sevisID` VARCHAR(20) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `sequenceNo` INT(11) NULL,
  `stamp` BINARY(8) NULL,
  PRIMARY KEY (`cciStaffUserID`),
  CONSTRAINT `FK_CCIStaffUsers_usstates`
    FOREIGN KEY (`stateID`)
    REFERENCES `cci_gh_go`.`USStates` (`usstatesID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_countries`
    FOREIGN KEY (`countryID`)
    REFERENCES `cci_gh_go`.`Countries` (`countryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_login`
    FOREIGN KEY (`loginID`)
    REFERENCES `cci_gh_go`.`Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsersCCIStaffRoles
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsersCCIStaffRoles` (  
  `cciStaffID` INT(11) NOT NULL,
  `cciStaffRolesID` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffID`, `cciStaffRolesID`),
  CONSTRAINT `FK_CCIStaffUsersCCIStaffRoles_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRolesID`)
    REFERENCES `cci_gh_go`.`CCIStaffRoles` (`cciStaffRoleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersCCIStaffRoles_CCIStaffUsers`
    FOREIGN KEY (`cciStaffID`)
    REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	
-- --------------------------------------------------------------------------------------------------
-- Creating Table CCIStaffUserProgram
-- --------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`CCIStaffUserProgram`( 
    `cciStaffUserID` INT(11) NOT NULL,
	`programID` INT(11) NOT NULL, 
	createdOn TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	createdBy INT(11),
	modifiedOn TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	modifiedBy INT(11),
	PRIMARY KEY (`cciStaffUserID`, `programID`), 
	CONSTRAINT `FK_CCIStaffUserProgram_CciStaffUser` 
	FOREIGN KEY (`cciStaffUserID`) 
	REFERENCES `cci_gh_go`.`CCIStaffUsers`(`cciStaffUserID`), 
	CONSTRAINT `FK_CCIStaffUserProgram_DepartmentPrograms` 
	FOREIGN KEY (`programID`) 
	REFERENCES `cci_gh_go`.`DepartmentPrograms`(`programID`)
	ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentResourceGroups such as seasons, SEVIS, accounting etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentResourceGroups` (
  `deptResourceGroupID` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentID` INT(11) NOT NULL,
  `resourceGroupName` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`deptResourceGroupID`),
  CONSTRAINT `FK_DepartmentResourceGroups_Departments` 
  FOREIGN KEY (`departmentID`)
  REFERENCES `cci_gh_go`.`Departments`(`departmentID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`ResourcePermissions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `deptResourceGroupID` INT(11) NOT NULL,
  `resourceActionID` INT(11) NOT NULL,
  `resourceName` VARCHAR(50) NULL,
  `resourceDescription` VARCHAR(200) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ResourcePermissions_DepartmentResourceGroups`
    FOREIGN KEY (`deptResourceGroupID`)
    REFERENCES `cci_gh_go`.`DepartmentResourceGroups` (`deptResourceGroupID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ResourcePermissions_ResourceActions`
    FOREIGN KEY (`resourceActionID`)
    REFERENCES `cci_gh_go`.`ResourceActions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsersResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUsersResourcePermissions` (  
  `cciStaffUserId` INT(11) NOT NULL,
  `resourcesPermissionsId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`, `resourcesPermissionsId`),
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_ResourcePermissions`
    FOREIGN KEY (`resourcesPermissionsId`)
    REFERENCES `cci_gh_go`.`ResourcePermissions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRolesDepartments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRolesDepartments` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cciStaffRoleId` INT(11) NOT NULL,
  `departmentId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CCIStaffRoles_Departments_Departments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`Departments` (`departmentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRoles_Departments_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRoleId`)
    REFERENCES `cci_gh_go`.`CCIStaffRoles` (`cciStaffRoleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRolesDefaultResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffRolesDefaultResourcePermissions` (  
  `cciStaffDepartmentRoleId` INT(11) NOT NULL,
  `resourcePermissionsId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffDepartmentRoleId`, `resourcePermissionsId`),
  CONSTRAINT `FK_CCIStaffRoles_DefaultResourcePermissions_ResourcePermissions`
    FOREIGN KEY (`resourcePermissionsId`)
    REFERENCES `cci_gh_go`.`ResourcePermissions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDResourcePermissions_CCIStaffRolesDepartments`
    FOREIGN KEY (`cciStaffDepartmentRoleId`)
    REFERENCES `cci_gh_go`.`CCIStaffRolesDepartments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUserNotes
-- -----------------------------------------------------------------------------------------------------	
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`CCIStaffUserNotes`(
	`ccistaffusernotesID` INT(11) AUTO_INCREMENT PRIMARY KEY,
	`ccistaffuserID` INT(11) NOT NULL,
	`note` VARCHAR(1000) NOT NULL,
	`createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`createdBy` INT(11) NOT NULL,
	`modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`modifiedBy` INT(11) NOT NULL,	
	 CONSTRAINT `FK_ccistaffusernotes_ccistaffusers`
		FOREIGN KEY (`cciStaffUserID`)
		REFERENCES `cci_gh_go`.`CCIStaffUsers` (`cciStaffUserID`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

---******************************************************************************************************---
----------------------------------------SEASONS TABLES------------------------------------------------------
---******************************************************************************************************---

-- -----------------------------------------------------------------------------------------------------------------
-- creating seasonstatus
-- -----------------------------------------------------------------------------------------------------------------

 CREATE TABLE `cci_gh_go`.`SeasonStatus`(
 `seasonStatusID` INT NOT NULL AUTO_INCREMENT,
 `status` VARCHAR(50) NOT NULL, 
 `active` TINYINT(1) NOT NULL, 
 PRIMARY KEY (`seasonStatusID`)
 ); 

-- -----------------------------------------------------------------------------------------------------------------
-- creating season table
-- --------------------------------------------------------------------------------------------------------------

 CREATE TABLE `cci_gh_go`.`Season`(
 `seasonID` INT NOT NULL AUTO_INCREMENT, 
 `seasonName` VARCHAR(50) NOT NULL, 
 `seasonFullName` VARCHAR(50) NOT NULL,
 `departmentID` INT(3) NOT NULL,
 `seasonStatusID` INT(3) NOT NULL,
 `createdOn` TIMESTAMP NOT NULL, 
 `createdBy` INT(3) NOT NULL,
 `modifiedOn` TIMESTAMP NOT NULL, 
 `modifiedBy` INT(3) NOT NULL, 
 `active` TINYINT(1) NOT NULL, 
 PRIMARY KEY (`SeasonID`) ,
 CONSTRAINT `fk_Season_SeasonStatus`
 FOREIGN KEY (`seasonStatusID`)
 REFERENCES `cci_gh_go`.`SeasonStatus`(`seasonStatusID`),
 CONSTRAINT `fk_Season_department`
 FOREIGN KEY (`departmentID`)
 REFERENCES `cci_gh_go`.`Departments`(`departmentID`)
); 

-- -------------------------------------------------------------------------------------------------------------------
-- creating W&T table
-- ----------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`SeasonWnTDetails`( 
`seasonWnTDetailsID` INT NOT NULL AUTO_INCREMENT, 
`seasonID` INT NOT NULL,
`annualSeason` INT(3) NOT NULL,
`startDate` TIMESTAMP NOT NULL,
`endDate` TIMESTAMP NOT NULL,
`cutOffDate` TIMESTAMP NOT NULL,
`isJobBoardOpen` TINYINT(1) NOT NULL,
`maxPendingJobApps` INT NOT NULL,  
`programStatus` TINYINT(1) NOT NULL,
PRIMARY KEY (`seasonWnTdetailsID`),
CONSTRAINT `fk_SeasonWnTdetails_Season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
); 

-- ------------------------------------------------------------------------------------------------------------------------
-- creating CAP table
-- -------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`SeasonCAPDetails`( 
`seasonCAPDetailsID` INT NOT NULL  AUTO_INCREMENT, 
`seasonID` INT NOT NULL,
`internStartDate` TIMESTAMP NOT NULL,
`internEndDate` TIMESTAMP NOT NULL,
`internCutoffDate` TIMESTAMP NOT NULL,
`traineeStartDate` TIMESTAMP NOT NULL,
`traineeEndDate` TIMESTAMP NOT NULL,
`traineeCutOffDate` TIMESTAMP NOT NULL,
`programSeasonStatus` TINYINT(1) NOT NULL,
PRIMARY KEY (`seasonCAPDetailsID`),
CONSTRAINT `fk_SeasonCAPdetails_Season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
); 

-- --------------------------------------------------------------------------------------------------------------------
-- creating WP Allocation table
-- ------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`SeasonWPAllocation`( 
`seasonWPAllocationID` INT NOT NULL AUTO_INCREMENT, 
`seasonID` INT NOT NULL,
`programOptionID` INT(3) NOT NULL,
`maxPax` INT,
PRIMARY KEY (`seasonWPAllocationID`),
CONSTRAINT `fk_SeasonWPAllocation_Season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`),
CONSTRAINT `fk_SeasonWPAllocation_DepartmentProgramOptions`
 FOREIGN KEY (`programOptionID`) 
 REFERENCES `cci_gh_go`.`DepartmentProgramOptions`(`programOptionID`) 
 ); 
 
-- ----------------------------------------------------------------------------------------------------------------
-- creating WP Configuration
-- -----------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`SeasonWPConfiguration`( 
`seasonWPConfigurationID` INT NOT NULL AUTO_INCREMENT, 
`seasonID` INT NOT NULL,
`seasonStartDate` TIMESTAMP NOT NULL,
`seasonEndDate` TIMESTAMP NOT NULL,
PRIMARY KEY (`seasonWPConfigurationID`),
CONSTRAINT `fk_SeasonWPConfiguration_Season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`)
 ); 
 
-- -------------------------------------------------------------------------------------------------------------------------- creating HSPConfiguration
-- ----------------------------------------------------------------------------------------------------------------

 CREATE TABLE `cci_gh_go`.`SeasonHSPConfiguration`(
 `seasonHSPConfigurationID` INT NOT NULL AUTO_INCREMENT, 
 `seasonID` INT NOT NULL, 
 `seasonStartDate` TIMESTAMP NOT NULL,
 `seasonEndDate` TIMESTAMP NOT NULL, 
 `showFirstSemToHF` TINYINT(1) NOT NULL,
 `showSecSemToHF` TINYINT(1) NOT NULL,
 `showSeasonToCurrentHF` TINYINT(1) NOT NULL,
 `welComeFamily` TINYINT(1) NOT NULL, 
 `showSpecialRequestStudent` TINYINT(1) NOT NULL,
 `fieldStaffAgreementID` INT(3) NOT NULL, 
 `hfRequiredReferences` INT(3) NOT NULL,
 `fieldStaffHoldLength` INT(3) NOT NULL, 
 `hoursBeforeHoldExpirationWarning` INT(3) NOT NULL, 
 `lcPaymenstSchedule` INT(3) NOT NULL,
 `hfInquiry` DATE NOT NULL,
 PRIMARY KEY (`seasonHSPConfigurationID`),
 CONSTRAINT `FK_SeasonHSPConfiguration_Season`
 FOREIGN KEY (`seasonID`) REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
 ); 
 
-- -------------------------------------------------------------------------------------------------------------------------- 
-- creating SeasonJ1Details
-- -------------------------------------------------------------------------------------------------------------

 CREATE TABLE `cci_gh_go`.`SeasonJ1Details`(
 `seasonJ1DetailsID` INT NOT NULL AUTO_INCREMENT,
 `seasonID` INT NOT NULL,
 `firstSemStartDate` TIMESTAMP NOT NULL, 
 `firstSemEndDate` TIMESTAMP NOT NULL,
 `secSemStartDate` TIMESTAMP NOT NULL,
 `secSemEndDate` TIMESTAMP NOT NULL,
 `firstSemEarliestBirthDate` TIMESTAMP NOT NULL, 
 `firstSemLatestBirthDate` TIMESTAMP NOT NULL, 
 `secSemEarliestBirthDate` TIMESTAMP NOT NULL,
 `secSemLatestBirthDate` TIMESTAMP NOT NULL, 
 `showGuaranteed` TINYINT(1),
 `showUnguaranteed` TINYINT(1),
 `programStatus` TINYINT(1) NOT NULL,
 PRIMARY KEY (`seasonJ1DetailsID`),
 CONSTRAINT `fk_SeasonJ1Details_Season` 
 FOREIGN KEY (`seasonID`) REFERENCES `cci_gh_go`.`Season`(`SeasonID`)
 );
 
-- -------------------------------------------------------------------------------------------------------------- 
-- creating SeasonF1etails tables
-- ------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`SeasonF1Details`(
 `seasonF1DetailsID` INT NOT NULL AUTO_INCREMENT, 
`seasonID` INT NOT NULL,
 `firstSemStartDate` TIMESTAMP NOT NULL, 
`firstSemEndDate` TIMESTAMP NOT NULL, 
`secSemStartDate` TIMESTAMP NOT NULL, 
`secSemEndDate` TIMESTAMP NOT NULL, 
`firstSemEarliestBirthDate` TIMESTAMP NOT NULL,
 `firstSemLatestBirthDate` TIMESTAMP NOT NULL, 
`secSemEarliestBirthDate` TIMESTAMP NOT NULL, 
`secSemLatestBirthDate` TIMESTAMP NOT NULL, 
`allowFieldStaffToStartRenewelProcess` TINYINT(1) NOT NULL, 
 `programStatus` TINYINT(1) NOT NULL,
PRIMARY KEY (`seasonF1DetailsID`), 
CONSTRAINT `fk_SeasonF1Details_Season` 
FOREIGN KEY (`seasonID`) REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
); 

-- --------------------------------------------------------------------------------------------------------------
-- creating SeasonHSPAllocation Table
-- --------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`SeasonHSPAllocatin`( 
 `seasonHSPAllocationID` INT NOT NULL AUTO_INCREMENT, 
 `seasonID` INT NOT NULL, 
 `maxGuaranteedPax` INT NOT NULL, 
 `maxUnguaranteedPax` INT NOT NULL, 
 `programOptionID` INT(3) NOT NULL, 
 PRIMARY KEY (`seasonHSPAllocationID`),
 CONSTRAINT `fk_seasonHSPAllocation_Season`
 FOREIGN KEY (`seasonID`) 
 REFERENCES `cci_gh_go`.`Season`(`SeasonID`),
 CONSTRAINT `fk_seasonHSPAllocation_departmentprogramoptions`
 FOREIGN KEY (`programOptionID`) 
 REFERENCES `cci_gh_go`.`DepartmentProgramOptions`(`programOptionID`)
 ); 
 
-- ------------------------------------------------------------------------------------------------------------------------------------
-- creating table AnnualSeason
-- --------------------------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`AnnualSeason`( 
 `annualSeasonID` INT(3) NOT NULL AUTO_INCREMENT,
 `annualSeasonName` VARCHAR(50) NOT NULL,
 `createdOn` TIMESTAMP NOT NULL, 
 `createdBy` INT(3) NOT NULL,
 `modifiedOn` TIMESTAMP NOT NULL, 
 `modifiedBy` INT(3) NOT NULL, 
 `active` TINYINT NOT NULL,
 PRIMARY KEY (`annualSeasonID`) 
 );
 
-- --------------------------------------------------------------------------------------------------------------------------------------
-- creating table ExtentionHSP
-- --------------------------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`ExtensionHSP`( 
 `extentionID` INT NOT NULL AUTO_INCREMENT, 
 `seasonJ1DetailsID` INT NOT NULL, 
 `seasonF1DetailsId` INT NOT NULL, 
 `extentionStartDate` TIMESTAMP NOT NULL, 
 `extentionEbdDate` TIMESTAMP NOT NULL,
 PRIMARY KEY (`extentionID`), 
 CONSTRAINT `FK_ExtentionHSP_seasonJ1Details`
 FOREIGN KEY (`seasonJ1DetailsID`) 
 REFERENCES `cci_gh_go`.`SeasonJ1Details`(`seasonJ1DetailsID`), 
 CONSTRAINT `FK_ExtentionHSP_seasonF1Details` 
 FOREIGN KEY (`seasonF1DetailsId`) 
 REFERENCES `cci_gh_go`.`SeasonF1Details`(`seasonF1DetailsID`) 
 ); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonGHTConfiguration
-- ------------------------------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`SeasonGHTConfiguration`( 
 `seasonGHTConfigurationID` INT NOT NULL AUTO_INCREMENT, 
 `SeasonID` INT NOT NULL,  
 `startDate` TIMESTAMP NOT NULL, 
 `endDate` TIMESTAMP NOT NULL, 
 `deposite` INT NOT NULL,
 `fieldStaffStipend` INT NOT NULL,
 PRIMARY KEY (`seasonGHTConfigurationID`), 
 CONSTRAINT `FK_seasonGHTConfiguration_seasom` 
 FOREIGN KEY (`SeasonID`) 
 REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
 ); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonHSADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`SeasonHSADetails`( 
`ID` INT NOT NULL AUTO_INCREMENT, 
`seasonID` INT NOT NULL, 
`startDate` TIMESTAMP NOT NULL, 
`endDate` TIMESTAMP NOT NULL, 
`cutOffDate` TIMESTAMP NOT NULL, 
PRIMARY KEY (`ID`), 
CONSTRAINT `FK_seasonHSADetails_season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonLSDetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`SeasonLSDetails`( 
`SeasonLSDetailsID` INT NOT NULL AUTO_INCREMENT, 
`seasonID` INT NOT NULL, 
`startDate` TIMESTAMP NOT NULL, 
`endDate` TIMESTAMP NOT NULL, 
`cutOffDate` TIMESTAMP NOT NULL, 
`departureBoundry` INT NOT NULL, 
PRIMARY KEY (`SeasonLSDetailsID`), 
CONSTRAINT `FK_seasonLSDetails_season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonTADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`SeasonTADetails`( 
`seasonTADetailsID` INT NOT NULL AUTO_INCREMENT, 
`seasonID` INT NOT NULL, 
`startDate` TIMESTAMP NOT NULL, 
`endDate` TIMESTAMP NOT NULL, 
`cutOffDate` TIMESTAMP NOT NULL, 
`departureBoundry` INT NOT NULL, 
PRIMARY KEY (`seasonTADetailsID`), 
CONSTRAINT `FK_seasonTADetails_season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
); 

-- ----------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonWADetails
-- ----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`SeasonWADetails`( 
`seasonWADetailsID` INT NOT NULL AUTO_INCREMENT, 
`seasonID` INT NOT NULL, 
`startDate` TIMESTAMP NOT NULL, 
`endDate` TIMESTAMP NOT NULL, 
`cutOffDate` TIMESTAMP NOT NULL, 
`departureBoundry` INT NOT NULL, 
PRIMARY KEY (`seasonWADetailsID`), 
CONSTRAINT `FK_seasonWADetails_season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonvolunteersDetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`SeasonVolunteersDetails`( 
`seasonVolunteersDetailsID` INT NOT NULL AUTO_INCREMENT, 
`seasonID` INT NOT NULL, 
`startDate` TIMESTAMP NOT NULL, 
`endDate` TIMESTAMP NOT NULL, 
`cutOffDate` TIMESTAMP NOT NULL, 
`departureBoundry` INT NOT NULL, 
PRIMARY KEY (`seasonVolunteersDetailsID`), 
CONSTRAINT `FK_seasonVolunteersDetails_season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- creating table Region
-- ------------------------------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`Region`( 
`regionID` INT(3) NOT NULL AUTO_INCREMENT, 
`regionName` VARCHAR(50) NOT NULL, 
`active` TINYINT NOT NULL, 
PRIMARY KEY (`regionID`) 
); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- creating table RegionSeason
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`RegionSeason`( 
`regionSeasonID` INT NOT NULL AUTO_INCREMENT, 
`regionID` INT(3) NOT NULL, 
`seasonID` INT NOT NULL, 
PRIMARY KEY (`regionSeasonID`), 
CONSTRAINT `FK_regionSeason_season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`), 
CONSTRAINT `FK_regionSeason_Region` 
FOREIGN KEY (`regionID`) 
REFERENCES `cci_gh_go`.`Region`(`regionID`) 
); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- creating table USSchool
-- -----------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`USSchool`( 
`usschoolID` INT NOT NULL AUTO_INCREMENT, 
PRIMARY KEY (`usschoolID`) 
); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- creating table USSchoolSeason
-- -----------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`USSchoolSeason`( 
`usSchoolSeasonID` INT NOT NULL AUTO_INCREMENT, 
`seasonID` INT NOT NULL, 
`usSchoolID` INT NOT NULL, 
`schoolStartDate` TIMESTAMP NOT NULL, 
`secondSemStartDate` TIMESTAMP NOT NULL, 
`schoolEndDate` TIMESTAMP NOT NULL, 
PRIMARY KEY (`usSchoolSeasonID`), 
CONSTRAINT `FK_USSchoolSeason_season` 
FOREIGN KEY (`seasonID`) 
REFERENCES `cci_gh_go`.`Season`(`SeasonID`) 
); 











