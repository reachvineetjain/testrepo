-- ----------------------------------------------------------------------------------------------------
-- Schema cci_gh_go
-- ----------------------------------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `cci_gh_go` ;
CREATE SCHEMA IF NOT EXISTS `cci_gh_go` ;

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Countries
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Countries` (
  `countryId` INT(11) NOT NULL AUTO_INCREMENT,
  `countryCode` VARCHAR(5) NOT NULL,  
  `countryName` VARCHAR(50) NOT NULL,
  `countryFlag` VARCHAR(300) ,
  `reqFinalSOAonDS` TINYINT(1) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  
  
   PRIMARY KEY (`countryId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.USStates
-- ----------------------------------------------------------------------------------------------------   
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`USStates` (
  `usStatesId` INT(11) NOT NULL AUTO_INCREMENT,
  `stateCode` VARCHAR(5) NOT NULL,
  `stateName` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`usStatesId`)
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
-- ---------------------------------------------------------------------------- -----------------------
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
  `principalId` INT(11) NOT NULL,
  `diagramId` INT(11) NOT NULL AUTO_INCREMENT,
  `version` INT(11) NULL,
  `definition` LONGBLOB NULL,
  PRIMARY KEY (`diagramId`),
  UNIQUE INDEX `UK_principal_name` (`principalId` ASC, `name` ASC)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Login
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`Login` (
  `loginId` INT(11) NOT NULL AUTO_INCREMENT,
  `userTypeId` INT(11) NOT NULL,
  `loginName` VARCHAR(50) UNIQUE NOT NULL,
  `password` VARCHAR(50) NOT NULL,
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
  `departmentId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentName` VARCHAR(50) NOT NULL,
  `acronym` VARCHAR(10) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`departmentId`)
); 

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentPrograms
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `cci_gh_go`.`DepartmentPrograms` (
  `departmentProgramId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(11) NOT NULL,
  `programName` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`departmentProgramId`),
  CONSTRAINT `FK_DepartmentPrograms_Departments`
   FOREIGN KEY (`departmentId`)
   REFERENCES `cci_gh_go`.`Departments` (`departmentId`)
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
  PRIMARY KEY (`deptFunctionId`),
  CONSTRAINT `FK_DepartmentFunctions_Departments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`Departments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
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
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(25) NULL,
  `emergencyPhone` VARCHAR(50) NULL,
  `email` VARCHAR(50) NOT NULL,
  `homeAddressLineOne` VARCHAR(100) NULL,
  `homeAddressLineTwo` VARCHAR(100) NULL,
  `city` VARCHAR(50) NULL,
  `usStatesId` INT(11) NULL,
  `zip` VARCHAR(50) NULL,
  `countryId` INT(11) NULL,
  `photo` VARCHAR(300) NULL,
  `sevisID` VARCHAR(20) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `sequenceNo` INT(11) NULL,
  `stamp` BINARY(8) NULL,
  PRIMARY KEY (`cciStaffUserId`),
  CONSTRAINT `FK_CCIStaffUsers_USStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `cci_gh_go`.`USStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_countries`
    FOREIGN KEY (`countryId`)
    REFERENCES `cci_gh_go`.`Countries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_login`
    FOREIGN KEY (`loginId`)
    REFERENCES `cci_gh_go`.`Login` (`loginId`)
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
  CONSTRAINT `FK_DepartmentResourceGroups_Departments` 
   FOREIGN KEY (`departmentId`)
   REFERENCES `cci_gh_go`.`Departments`(`departmentId`)
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
  CONSTRAINT `FK_CCIStaffRolesDepartments_Departments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `cci_gh_go`.`Departments` (`departmentId`)
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
-- SEASONS TABLES :creating seasonstatus
-- -----------------------------------------------------------------------------------------------------------------

 CREATE TABLE `cci_gh_go`.`SeasonStatus`(
 `seasonStatusId` INT NOT NULL AUTO_INCREMENT,
 `status` VARCHAR(50) NOT NULL, 
 `active` TINYINT(1) NOT NULL, 
 PRIMARY KEY (`seasonStatusId`)
 ); 

-- -----------------------------------------------------------------------------------------------------------------
-- creating season table
-- --------------------------------------------------------------------------------------------------------------

 CREATE TABLE `cci_gh_go`.`Season`(
 `seasonId` INT NOT NULL AUTO_INCREMENT, 
 `seasonName` VARCHAR(50) NOT NULL, 
 `seasonFullName` VARCHAR(50) NOT NULL,
 `departmentId` INT(3) NOT NULL,
 `seasonStatusID` INT(3) NOT NULL,
 `createdOn` TIMESTAMP NOT NULL, 
 `createdBy` INT(3) NOT NULL,
 `modifiedOn` TIMESTAMP NOT NULL, 
 `modifiedBy` INT(3) NOT NULL, 
 `active` TINYINT(1) NOT NULL, 
 PRIMARY KEY (`seasonId`) ,
 CONSTRAINT `FK_Season_SeasonStatus`
 FOREIGN KEY (`seasonStatusID`)
 REFERENCES `cci_gh_go`.`SeasonStatus`(`seasonStatusId`),
 CONSTRAINT `FK_Season_Departments`
 FOREIGN KEY (`departmentId`)
 REFERENCES `cci_gh_go`.`Departments`(`departmentId`)
); 

-- -------------------------------------------------------------------------------------------------------------------
-- creating W&T table
-- ----------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`SeasonWnTDetails`( 
`seasonWnTDetailsId` INT NOT NULL AUTO_INCREMENT, 
`seasonId` INT NOT NULL,
`annualSeason` INT(3) NOT NULL,
`startDate` TIMESTAMP NOT NULL,
`endDate` TIMESTAMP NOT NULL,
`cutOffDate` TIMESTAMP NOT NULL,
`isJobBoardOpen` TINYINT(1) NOT NULL,
`maxPendingJobApps` INT NOT NULL,  
`programStatus` TINYINT(1) NOT NULL,
PRIMARY KEY (`seasonWnTDetailsId`),
CONSTRAINT `fk_SeasonWnTdetails_Season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
); 

-- ------------------------------------------------------------------------------------------------------------------------
-- creating CAP table
-- -------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`SeasonCAPDetails`( 
`seasonCAPDetailsId` INT NOT NULL  AUTO_INCREMENT, 
`seasonId` INT NOT NULL,
`internStartDate` TIMESTAMP NOT NULL,
`internEndDate` TIMESTAMP NOT NULL,
`internCutoffDate` TIMESTAMP NOT NULL,
`traineeStartDate` TIMESTAMP NOT NULL,
`traineeEndDate` TIMESTAMP NOT NULL,
`traineeCutOffDate` TIMESTAMP NOT NULL,
`programSeasonStatus` TINYINT(1) NOT NULL,
PRIMARY KEY (`seasonCAPDetailsId`),
CONSTRAINT `fk_SeasonCAPdetails_Season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
); 

-- --------------------------------------------------------------------------------------------------------------------
-- creating WP Allocation table
-- ------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`SeasonWPAllocation`( 
`seasonWPAllocationId` INT NOT NULL AUTO_INCREMENT, 
`seasonId` INT NOT NULL,
`departmentProgramOptionId` INT(3) NOT NULL,
`maxPax` INT,
PRIMARY KEY (`seasonWPAllocationId`),
CONSTRAINT `fk_SeasonWPAllocation_Season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`),
CONSTRAINT `fk_SeasonWPAllocation_DepartmentProgramOptions`
 FOREIGN KEY (`departmentProgramOptionId`) 
 REFERENCES `cci_gh_go`.`DepartmentProgramOptions`(`departmentProgramOptionId`) 
 ); 
 
-- ----------------------------------------------------------------------------------------------------------------
-- creating WP Configuration
-- -----------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`SeasonWPConfiguration`( 
`seasonWPConfigurationId` INT NOT NULL AUTO_INCREMENT, 
`seasonId` INT NOT NULL,
`seasonStartDate` TIMESTAMP NOT NULL,
`seasonEndDate` TIMESTAMP NOT NULL,
PRIMARY KEY (`seasonWPConfigurationId`),
CONSTRAINT `fk_SeasonWPConfiguration_Season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`)
 ); 
 
-- ------------------------------------------------------------------------------------------------------------------
-- creating HSPConfiguration
-- ----------------------------------------------------------------------------------------------------------------

 CREATE TABLE `cci_gh_go`.`SeasonHSPConfiguration`(
 `seasonHSPConfigurationId` INT NOT NULL AUTO_INCREMENT, 
 `seasonId` INT NOT NULL, 
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
 PRIMARY KEY (`seasonHSPConfigurationId`),
 CONSTRAINT `FK_SeasonHSPConfiguration_Season`
 FOREIGN KEY (`seasonID`) REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
 ); 
 
-- -------------------------------------------------------------------------------------------------------------------------- 
-- creating SeasonJ1Details
-- -------------------------------------------------------------------------------------------------------------

 CREATE TABLE `cci_gh_go`.`SeasonJ1Details`(
 `seasonJ1DetailsId` INT NOT NULL AUTO_INCREMENT,
 `seasonId` INT NOT NULL,
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
 PRIMARY KEY (`seasonJ1DetailsId`),
 CONSTRAINT `FK_SeasonJ1Details_Season` 
 FOREIGN KEY (`seasonId`) REFERENCES `cci_gh_go`.`Season`(`seasonId`)
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
PRIMARY KEY (`seasonF1DetailsId`), 
CONSTRAINT `FK_SeasonF1Details_Season` 
FOREIGN KEY (`seasonID`) REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
); 

-- --------------------------------------------------------------------------------------------------------------
-- creating SeasonHSPAllocation Table
-- --------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`SeasonHSPAllocation`( 
 `seasonHSPAllocationId` INT NOT NULL AUTO_INCREMENT, 
 `seasonId` INT NOT NULL, 
 `maxGuaranteedPax` INT NOT NULL, 
 `maxUnguaranteedPax` INT NOT NULL, 
 `departmentProgramOptionId` INT(3) NOT NULL, 
 PRIMARY KEY (`seasonHSPAllocationId`),
 CONSTRAINT `FK_SeasonHSPAllocation_Season`
 FOREIGN KEY (`seasonId`) 
 REFERENCES `cci_gh_go`.`Season`(`seasonId`),
 CONSTRAINT `FK_SeasonHSPAllocation_DepartmentProgramOptions`
 FOREIGN KEY (`departmentProgramOptionId`) 
 REFERENCES `cci_gh_go`.`DepartmentProgramOptions`(`departmentProgramOptionId`)
 ); 
 
-- ------------------------------------------------------------------------------------------------------------------------------------
-- creating table AnnualSeason
-- --------------------------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`AnnualSeason`( 
 `annualSeasonId` INT(3) NOT NULL AUTO_INCREMENT,
 `annualSeasonName` VARCHAR(50) NOT NULL,
 `createdOn` TIMESTAMP NOT NULL, 
 `createdBy` INT(3) NOT NULL,
 `modifiedOn` TIMESTAMP NOT NULL, 
 `modifiedBy` INT(3) NOT NULL, 
 `active` TINYINT NOT NULL,
 PRIMARY KEY (`annualSeasonId`) 
 );
 
-- --------------------------------------------------------------------------------------------------------------------------------------
-- creating table ExtentionHSP
-- --------------------------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`ExtensionHSP`( 
 `extentionId` INT NOT NULL AUTO_INCREMENT, 
 `seasonJ1DetailsId` INT NOT NULL, 
 `seasonF1DetailsId` INT NOT NULL, 
 `extentionStartDate` TIMESTAMP NOT NULL, 
 `extentionEbdDate` TIMESTAMP NOT NULL,
 PRIMARY KEY (`extentionId`), 
 CONSTRAINT `FK_ExtentionHSP_seasonJ1Details`
 FOREIGN KEY (`seasonJ1DetailsId`) 
 REFERENCES `cci_gh_go`.`SeasonJ1Details`(`seasonJ1DetailsId`), 
 CONSTRAINT `FK_ExtentionHSP_seasonF1Details` 
 FOREIGN KEY (`seasonF1DetailsId`) 
 REFERENCES `cci_gh_go`.`SeasonF1Details`(`seasonF1DetailsId`) 
 ); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonGHTConfiguration
-- ------------------------------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`SeasonGHTConfiguration`( 
 `seasonGHTConfigurationId` INT NOT NULL AUTO_INCREMENT, 
 `seasonId` INT NOT NULL,  
 `startDate` TIMESTAMP NOT NULL, 
 `endDate` TIMESTAMP NOT NULL, 
 `deposite` INT NOT NULL,
 `fieldStaffStipend` INT NOT NULL,
 PRIMARY KEY (`seasonGHTConfigurationId`), 
 CONSTRAINT `FK_SeasonGHTConfiguration_Season` 
 FOREIGN KEY (`seasonId`) 
 REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
 ); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonHSADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`SeasonHSADetails`( 
`seasonHSADetailId` INT NOT NULL AUTO_INCREMENT, 
`seasonId` INT NOT NULL, 
`startDate` TIMESTAMP NOT NULL, 
`endDate` TIMESTAMP NOT NULL, 
`cutOffDate` TIMESTAMP NOT NULL, 
PRIMARY KEY (`seasonHSADetailId`), 
CONSTRAINT `FK_SeasonHSADetails_Season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonLSDetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`SeasonLSDetails`( 
`seasonLSDetailsId` INT NOT NULL AUTO_INCREMENT, 
`seasonId` INT NOT NULL, 
`startDate` TIMESTAMP NOT NULL, 
`endDate` TIMESTAMP NOT NULL, 
`cutOffDate` TIMESTAMP NOT NULL, 
`departureBoundry` INT NOT NULL, 
PRIMARY KEY (`seasonLSDetailsId`), 
CONSTRAINT `FK_SeasonLSDetails_Season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonTADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`SeasonTADetails`( 
`seasonTADetailId` INT NOT NULL AUTO_INCREMENT, 
`seasonId` INT NOT NULL, 
`startDate` TIMESTAMP NOT NULL, 
`endDate` TIMESTAMP NOT NULL, 
`cutOffDate` TIMESTAMP NOT NULL, 
`departureBoundry` INT NOT NULL, 
PRIMARY KEY (`seasonTADetailId`), 
CONSTRAINT `FK_SeasonTADetails_Season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
); 

-- ----------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonWADetails
-- ----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`SeasonWADetails`( 
`seasonWADetailId` INT NOT NULL AUTO_INCREMENT, 
`seasonId` INT NOT NULL, 
`startDate` TIMESTAMP NOT NULL, 
`endDate` TIMESTAMP NOT NULL, 
`cutOffDate` TIMESTAMP NOT NULL, 
`departureBoundry` INT NOT NULL, 
PRIMARY KEY (`seasonWADetailId`), 
CONSTRAINT `FK_SeasonWADetails_Season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- creating table seasonvolunteersDetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`SeasonVolunteersDetails`( 
`seasonVolunteersDetailId` INT NOT NULL AUTO_INCREMENT, 
`seasonId` INT NOT NULL, 
`startDate` TIMESTAMP NOT NULL, 
`endDate` TIMESTAMP NOT NULL, 
`cutOffDate` TIMESTAMP NOT NULL, 
`departureBoundry` INT NOT NULL, 
PRIMARY KEY (`seasonVolunteersDetailId`), 
CONSTRAINT `FK_SeasonVolunteersDetails_Season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- creating table Region
-- ------------------------------------------------------------------------------------------------------------------------------------------
 CREATE TABLE `cci_gh_go`.`Region`( 
`regionId` INT(3) NOT NULL AUTO_INCREMENT, 
`regionName` VARCHAR(50) NOT NULL, 
`active` TINYINT NOT NULL, 
PRIMARY KEY (`regionId`) 
); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- creating table RegionSeason
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `cci_gh_go`.`RegionSeason`( 
`regionSeasonId` INT NOT NULL AUTO_INCREMENT, 
`regionId` INT(3) NOT NULL, 
`seasonId` INT NOT NULL, 
PRIMARY KEY (`regionSeasonId`), 
CONSTRAINT `FK_RegionSeason_Season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`), 
CONSTRAINT `FK_RegionSeason_Region` 
FOREIGN KEY (`regionId`) 
REFERENCES `cci_gh_go`.`Region`(`regionId`) 
); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- creating table USSchool
-- -----------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`USSchool`( 
`usSchoolId` INT NOT NULL AUTO_INCREMENT, 
PRIMARY KEY (`usSchoolId`) 
); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- creating table USSchoolSeason
-- -----------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `cci_gh_go`.`USSchoolSeason`( 
`usSchoolSeasonId` INT NOT NULL AUTO_INCREMENT, 
`seasonId` INT NOT NULL, 
`usSchoolId` INT NOT NULL, 
`schoolStartDate` TIMESTAMP NOT NULL, 
`secondSemStartDate` TIMESTAMP NOT NULL, 
`schoolEndDate` TIMESTAMP NOT NULL, 
PRIMARY KEY (`usSchoolSeasonId`), 
CONSTRAINT `FK_USSchoolSeason_season` 
FOREIGN KEY (`seasonId`) 
REFERENCES `cci_gh_go`.`Season`(`seasonId`) 
); 