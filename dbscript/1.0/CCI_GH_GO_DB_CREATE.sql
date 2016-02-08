

-- ----------------------------------------------------------------------------------------------------
-- Schema cci_gh_go
-- ----------------------------------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 0;
DROP SCHEMA IF EXISTS `cci_gh_go` ;
SET FOREIGN_KEY_CHECKS = 1;
CREATE SCHEMA IF NOT EXISTS `cci_gh_go` ;

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Countries
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `LookupCountries` (
  `countryId` INT(3) NOT NULL AUTO_INCREMENT,
  `countryCode` VARCHAR(5) NOT NULL,
  `countryName` VARCHAR(50) NOT NULL,
  `countryFlag` VARCHAR(300) NULL,
  `isReqFinalSOAonDS` TINYINT(1) NOT NULL DEFAULT 0,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`countryId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupUSStates
-- ----------------------------------------------------------------------------------------------------   
CREATE TABLE IF NOT EXISTS `LookupUSStates` (
  `usStatesId` INT(3) NOT NULL AUTO_INCREMENT,
  `stateName` VARCHAR(50) NOT NULL,
  `stateCode` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`usStatesId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupGender
-- ----------------------------------------------------------------------------------------------------   
CREATE TABLE IF NOT EXISTS `LookupGender` (
    `genderId` INT(3) NOT NULL AUTO_INCREMENT,
    `genderName` VARCHAR(1) NOT NULL,
    PRIMARY KEY(`genderId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupDepartments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `LookupDepartments` (
  `departmentId` INT(3) NOT NULL AUTO_INCREMENT,
  `departmentName` VARCHAR(50) NOT NULL,
  `acronym` VARCHAR(10) NULL DEFAULT NULL,
  `isVisibleToSeason` TINYINT(1) NOT NULL DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`departmentId`)
);


-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LookupDepartmentPrograms
-- ---------------------------------------------------------------------------------------------------- 
CREATE TABLE `LookupDepartmentPrograms` (
  `lookupDepartmentProgramId` INT(3) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(3) NOT NULL,
  `programName` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) DEFAULT NULL,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`lookupDepartmentProgramId`),
  KEY `FK_LookupDepartmentPrograms_LookupDepartments` (`departmentId`),
  CONSTRAINT `FK_LookupDepartmentPrograms_LookupDepartments` 
  FOREIGN KEY (`departmentId`) 
  REFERENCES `LookupDepartments` (`departmentId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Salutation
-- ---------------------------------------------------------------------------------------------------- 
CREATE TABLE `Salutation`
(`salutationId` int(3) NOT NULL AUTO_INCREMENT,
 `salutationName` VARCHAR(30),
 `active` TINYINT(1) DEFAULT 0,
 PRIMARY KEY (`salutationId`));

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.GoIdSequence 
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `GoIdSequence` (
  `goId` INT(3) NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (`goId`)
)AUTO_INCREMENT = 1000;

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.UserType whether cciuser, lc, partner, participant etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `UserType` (
  `userTypeId` INT(3) NOT NULL AUTO_INCREMENT,
  `userTypeCode` VARCHAR(20) NOT NULL,
  `userTypeName` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`userTypeId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Login
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `Login` (
  `loginId` INT(11) NOT NULL AUTO_INCREMENT,
  `goId` INT NOT NULL,
  `loginName` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `keyValue` VARCHAR(200) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn`TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`loginId`),
  UNIQUE INDEX `loginName` (`loginName` ASC),
  INDEX `FK_Login_GoIdSequence_idx` (`goId` ASC),
  CONSTRAINT `FK_Login_GoIdSequence`
    FOREIGN KEY (`goId`)
    REFERENCES `GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LoginHistory
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `LoginHistory` (
  `loginHistoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `loggedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `loginId` INT(11) NOT NULL,
  `ipAddress` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`loginHistoryId`),
  INDEX `FK_History_Login` (`loginId` ASC),
  CONSTRAINT `FK_History_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.PasswordHistory
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PasswordHistory` (
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
    REFERENCES `Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.LoginUserType
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `LoginUserType` (
  `loginUserTypeId` INT(11) NOT NULL AUTO_INCREMENT,
  `loginId` INT(11) NOT NULL,
  `userTypeId` INT(3) NOT NULL,
  `defaultUserType` TINYINT(1) NOT NULL DEFAULT 0,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`loginUserTypeId`),
  INDEX `FK_LoginUserType_Login_idx` (`loginId` ASC),
  INDEX `FK_LoginUserType_UserType_idx` (`userTypeId` ASC),
  CONSTRAINT `FK_LoginUserType_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_LoginUserType_UserType`
    FOREIGN KEY (`userTypeId`)
    REFERENCES `UserType` (`userTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentPrograms
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `DepartmentPrograms` (
  `departmentProgramId` INT(3) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(3) NOT NULL,
  `programName` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`departmentProgramId`),
  INDEX `FK_DepartmentPrograms_LookupDepartments` (`departmentId` ASC),
  CONSTRAINT `FK_DepartmentPrograms_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentProgramOptions
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `DepartmentProgramOptions` (
  `departmentProgramOptionId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentProgramId` INT(11) NOT NULL,
  `programOptionCode` VARCHAR(10) NOT NULL,
  `programOptionName` VARCHAR(50) NOT NULL,
PRIMARY KEY (`departmentProgramOptionId`),
  INDEX `FK_DepartmentProgramOptions_DepartmentPrograms` (`departmentProgramId` ASC),
  CONSTRAINT `FK_DepartmentProgramOptions_DepartmentPrograms`
   FOREIGN KEY (`departmentProgramId`)
   REFERENCES `DepartmentPrograms` (`departmentProgramId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);
    
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourceActions actions such as add, edit, update delete on resources
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResourceActions` (
  `resourceActionId` INT(11) NOT NULL AUTO_INCREMENT,
  `resourceAction` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  `visibleToUser` TINYINT(1) NOT NULL DEFAULT 1,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`resourceActionId`)
);
   
-- -------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRoles such as program manager, director, sys admin etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CCIStaffRoles` (
  `cciStaffRoleId` INT(11) NOT NULL AUTO_INCREMENT,
  `cciStaffRoleName` VARCHAR(50) NOT NULL,
  `hierarchy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
PRIMARY KEY (`cciStaffRoleId`)
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsers
-- ---------------------------------------------------------------------------------------------------- 
CREATE TABLE IF NOT EXISTS `CCIStaffUsers` (
  `cciStaffUserId` INT(11) NOT NULL,
  `supervisorId` INT(11) NULL,
  `cciAdminGuid` VARCHAR(64) UNIQUE NOT NULL,
  `firstName` VARCHAR(30) NOT NULL,
  `lastName` VARCHAR(30) NOT NULL,
  `genderId` INT(3) NULL,
  `primaryPhone` VARCHAR(15) NULL,
  `emergencyPhone` VARCHAR(15) NULL,
  `phoneExtension` VARCHAR(10) NULL,
  `homeAddressLineOne` VARCHAR(100) NULL,
  `homeAddressLineTwo` VARCHAR(100) NULL,
  `city` VARCHAR(50) NULL,
  `usStatesId` INT(11) NULL,
  `zip` VARCHAR(50) NULL,
  `countryId` INT(11) NULL,
  `photo` VARCHAR(300) NULL,
  `sevisId` VARCHAR(20) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`),
  CONSTRAINT `FK_CCIStaffUsers_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_LookupCountries`
    FOREIGN KEY (`countryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_LookupGender`
    FOREIGN KEY (`genderId`)
    REFERENCES `LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsers_GoIdSequence`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsersCCIStaffRoles
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CCIStaffUsersCCIStaffRoles` (  
  `cciStaffUserId` INT(11) NOT NULL,
  `cciStaffRoleId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`, `cciStaffRoleId`),
  CONSTRAINT `FK_CCIStaffUsersCCIStaffRoles_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRoleId`)
    REFERENCES `CCIStaffRoles` (`cciStaffRoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersCCIStaffRoles_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------
-- Creating Table CCIStaffUserProgram
-- --------------------------------------------------------------------------------------------------
CREATE TABLE `CCIStaffUserProgram`( 
  `cciStaffUserId` INT(11) NOT NULL,
  `lookupDepartmentProgramId` INT(11) NOT NULL,  
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
    PRIMARY KEY (`cciStaffUserId`, `lookupDepartmentProgramId`), 
    CONSTRAINT `FK_CCIStaffUserProgram_CCIStaffUser` 
    FOREIGN KEY (`cciStaffUserId`) 
    REFERENCES `CCIStaffUsers`(`cciStaffUserId`), 
    CONSTRAINT `FK_CCIStaffUserProgram_LookupDepartmentPrograms` 
    FOREIGN KEY (`lookupDepartmentProgramId`) 
    REFERENCES `LookupDepartmentPrograms`(`lookupDepartmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);  

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.DepartmentResourceGroups such as seasons, SEVIS, accounting etc
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `DepartmentResourceGroups` (
  `departmentResourceGroupId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentId` INT(11) NOT NULL,
  `resourceGroupName` VARCHAR(50) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`departmentResourceGroupId`),
  CONSTRAINT `FK_DepartmentResourceGroups_LookupDepartments` 
   FOREIGN KEY (`departmentId`)
   REFERENCES `LookupDepartments`(`departmentId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);

-- --------------------------------------------------------------------------------------------------
-- Table cci_gh_go.ResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResourcePermissions` (
  `resourcePermissionId` INT(11) NOT NULL AUTO_INCREMENT,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourceActionID` INT(11) NOT NULL,
  `resourceName` VARCHAR(50) NOT NULL,
  `resourceDescription` VARCHAR(200) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
   PRIMARY KEY (`resourcePermissionId`),
  CONSTRAINT `FK_ResourcePermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ResourcePermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUsersResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CCIStaffUsersResourcePermissions` (  
  `cciStaffUserId` INT(11) NOT NULL,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourcePermissionId` INT(11) NOT NULL,  
  `resourceActionId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffUserId`, `resourcePermissionId`),
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_ResourcePermissions`
    FOREIGN KEY (`resourcePermissionId`)
    REFERENCES `ResourcePermissions` (`resourcePermissionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffUsersResourcePermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRolesDepartments
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CCIStaffRolesDepartments` (
  `cciStaffRolesDepartmentId` INT(11) NOT NULL AUTO_INCREMENT,
  `cciStaffRoleId` INT(11) NOT NULL,
  `departmentId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRolesDepartmentId`), 
  CONSTRAINT `FK_CCIStaffRolesDepartments_CCIStaffRoles`
    FOREIGN KEY (`cciStaffRoleId`)
    REFERENCES `CCIStaffRoles` (`cciStaffRoleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDepartments_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
    
-- ----------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffRolesDefaultResourcePermissions
-- ----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CCIStaffRolesDefaultResourcePermissions` (  
  `cciStaffRolesDepartmentId` INT(11) NOT NULL,
  `departmentResourceGroupId` INT(11) NOT NULL,
  `resourcePermissionId` INT(11) NOT NULL,
  `resourceActionId` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`cciStaffRolesDepartmentId`, `resourcePermissionId`),
 CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_CCIStaffRolesDepartments`
    FOREIGN KEY (`cciStaffRolesDepartmentId`)
    REFERENCES `CCIStaffRolesDepartments` (`cciStaffRolesDepartmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_ResourcePermissions`
    FOREIGN KEY (`resourcePermissionId`)
    REFERENCES `ResourcePermissions` (`resourcePermissionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_DepartmentResourceGroups`
    FOREIGN KEY (`departmentResourceGroupId`)
    REFERENCES `DepartmentResourceGroups` (`departmentResourceGroupId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CCIStaffRolesDefaultPermissions_ResourceActions`
    FOREIGN KEY (`resourceActionId`)
    REFERENCES `ResourceActions` (`resourceActionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------
-- Table cci_gh_go.CCIStaffUserNotes
-- -----------------------------------------------------------------------------------------------------    
CREATE TABLE IF NOT EXISTS `CCIStaffUserNotes`(
  `cciStaffUserNoteId` INT(11) NOT NULL AUTO_INCREMENT,
  `ccistaffuserID` INT(11) NOT NULL,
  `note` VARCHAR(1000) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,    
 PRIMARY KEY (`cciStaffUserNoteId`),
 CONSTRAINT `FK_CCIStaffUserNotes_CCIStaffUsers`
   FOREIGN KEY (`cciStaffUserId`)
   REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
);

-- -----------------------------------------------------------------------------------------------------------------
-- SEASONS TABLES :Table cci_gh_go.SeasonStatus
-- -----------------------------------------------------------------------------------------------------------------
  CREATE TABLE IF NOT EXISTS `SeasonStatus` (
  `seasonStatusId` INT(3) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`seasonStatusId`)
);



-- -----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.PaymentSchedule
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentSchedule` (
  `paymentScheduleId` INT(3) NOT NULL ,
  `scheduleName` VARCHAR(50) NOT NULL,
   PRIMARY KEY (`paymentScheduleId`)
);
 
-- -----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Season
-- --------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `Season` (
  `seasonId` INT NOT NULL AUTO_INCREMENT,
  `seasonName` VARCHAR(50) UNIQUE NOT NULL,
  `seasonFullName` VARCHAR(50) NOT NULL,
  `departmentId` INT(3) NOT NULL,
  `seasonStatusId` INT(3) NOT NULL,
  `clonedSeasonName` VARCHAR(50) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT  NULL,
   PRIMARY KEY (`seasonId`),
  INDEX `FK_Season_SeasonStatus_idx` (`seasonStatusId` ASC),
  INDEX `FK_Season_LookupDepartments_idx` (`departmentId` ASC),
  CONSTRAINT `FK_Season_SeasonStatus`
    FOREIGN KEY (`seasonStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Season_LookupDepartments`
    FOREIGN KEY (`departmentId`)
    REFERENCES `LookupDepartments` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- ------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWnTSummerDetails
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWnTSummerDetails` (
  `seasonWnTSummerDetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(50),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `applicationDeadlineDate` DATETIME,
  `isJobBoardOpen` TINYINT(1) DEFAULT 0,
  `maxPendingJobApps` INT,
  `programStatusId` INT(3) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWnTSummerDetailsId`),
  UNIQUE KEY (`programName`),
  INDEX `FK_SeasonWnTSummerDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWnTSummerDetails_SeasonStatus_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonWnTSummerDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_seasonWnTSummerDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWnTWinterDetails
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWnTWinterDetails` (
  `seasonWnTWinterDetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(50),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `applicationDeadlineDate` DATETIME,
  `isJobBoardOpen` TINYINT(1) DEFAULT 0,
  `maxPendingJobApps` INT,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWnTWinterDetailsId`),
  UNIQUE KEY (`programName`),
  INDEX `FK_seasonWnTWinterDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_seasonWnTWinterDetails_SeasonStatus_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonWnTWinterDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWnTWinterDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWnTSpringDetails
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWnTSpringDetails` (
  `seasonWnTSpringDetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(50),
  `startDate` DATETIME,
  `endDate` DATETIME,
  `applicationDeadlineDate` DATETIME,
  `isJobBoardOpen` TINYINT(1) DEFAULT 0,
  `maxPendingJobApps` INT,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWnTSpringDetailsId`),
  UNIQUE KEY (`programName`),
  INDEX `FK_SeasonWnTSpringDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWnTSpringDetails_SeasonStatus_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonWnTSpringDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_seasonWnTSpringDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonCAPDetails
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonCAPDetails` (
  `seasonCAPDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45),
  `internStartDate` DATETIME,
  `internEndDate` DATETIME,
  `internAppDeadlineDate` DATETIME,
  `traineeStartDate` DATETIME,
  `traineeEndDate` DATETIME,
  `traineeAppDeadlineDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
   PRIMARY KEY (`seasonCAPDetailsId`),
  UNIQUE KEY (`programName`),
  CONSTRAINT `FK_SeasonCAPDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonCAPdetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWPAllocation
-- -------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWPAllocation` (
  `seasonWPAllocationId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `departmentProgramOptionId` INT(3) NOT NULL,
  `maxPax` INT DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWPAllocationId`),
  INDEX `FK_SeasonWPAloocation_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWPAllocation_DepartmentProgramOptions_idx` (`departmentProgramOptionId` ASC),
  CONSTRAINT `FK_SeasonWPAllocation_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWPAllocation_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- ----------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWPConfiguration
-- -----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWPConfiguration` (
  `seasonWPConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWPConfigurationId`),
  INDEX `FK_SeasonWPConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonWPConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- ------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSPConfiguration
-- ----------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonHSPConfiguration` (
  `seasonHSPConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonHSPConfigurationId`),
  INDEX `FK_SeasonHSPConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonHSPConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- -------------------------------------------------------------------------------------------------------------------------- 
-- Table cci_gh_go.SeasonJ1Details
-- -------------------------------------------------------------------------------------------------------------
  CREATE TABLE IF NOT EXISTS `SeasonJ1Details` (
  `seasonJ1DetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName` VARCHAR(45) UNIQUE,
  `programStatusId` INT(3),
  `secondSemStartDate` DATETIME,
  `secondSemEndDate` DATETIME,
  `secondSemAppDeadlineDate` DATETIME,
  `secondSemEarliestBirthDate` DATETIME,
  `secondSemLatestBirthDate` DATETIME,
  `showSecondSemToNewHF` TINYINT(1) DEFAULT 0,
  `activeFullYearJanProgram` TINYINT(1) DEFAULT 0,
  `janFullYearStartDate` DATETIME,
  `janFullYearAppDeadlineDate` DATETIME,
  `janFullYearEndDate` DATETIME,
  `showJanFullYearToNewHF` TINYINT(1) DEFAULT 0,
  `firstSemStartDate` DATETIME,
  `firstSemEndDate` DATETIME,
  `firstSemAppDeadlineDate` DATETIME,
  `firstSemEarliestBirthDate` DATETIME,
  `firstSemLatestBirthDate` DATETIME,
  `showFirstSemToNewHF` TINYINT(1) DEFAULT 0,
  `augFullYearStartDate` DATETIME,
  `augFullYearEndDate` DATETIME,
  `augFullYearAppDeadlineDate` DATETIME,
  `showAugFullYearToNewHF` TINYINT(1) DEFAULT 0,
  `showSeasonToCurrentHF` TINYINT(1) DEFAULT 0,
  `fieldStaffHoldLength` INT(3),
  `hoursBeforeHoldExpirationWarning` INT(3),
  `lcPaymentScheduleId` INT,
  `fsAgreementId` INT,
  `hfReferences` INT(3),
  `hfInquiryDate` DATE,
  `showWelcomeFamily` TINYINT(1) DEFAULT 0,
  `showGuaranteed` TINYINT(1) DEFAULT 0,
  `showUnguaranteed` TINYINT(1) DEFAULT 0,
  `showSpecialRequestStudent` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
   PRIMARY KEY (`seasonJ1DetailsId`),
   INDEX `FK_J1HSPSeason_Season_idx` (`seasonId` ASC),
   INDEX `FK_SeasonJ1Details_Status_idx` (`programStatusId` ASC),
   INDEX `FK_seasonJ1Details_FieldStaffAgreement_idx` (`fsAgreementId` ASC),
   INDEX `FK_seasonJ1Details_PaymentSchedule_idx` (`lcPaymentScheduleId` ASC),
   CONSTRAINT `FK_SeasonJ1Details_Season`
   FOREIGN KEY (`seasonId`)
   REFERENCES `Season` (`seasonId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
   CONSTRAINT `FK_SeasonJ1Details_SeasonStatus`
   FOREIGN KEY (`programStatusId`)
   REFERENCES `SeasonStatus` (`seasonStatusId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
   CONSTRAINT `FK_seasonJ1Details_FieldStaffAgreement`
   FOREIGN KEY (`fsAgreementId`)
   REFERENCES cci_gh_go.FieldStaffAgreement (`fieldStaffAgreementId`)
   ON UPDATE NO ACTION
   ON DELETE NO ACTION,
   CONSTRAINT `FK_seasonJ1Details_PaymentSchedule` 
   FOREIGN KEY (`lcPaymentScheduleId`)
   REFERENCES cci_gh_go.PaymentSchedule (`paymentScheduleId`)
   ON UPDATE NO ACTION
   ON DELETE NO ACTION
);
 
-- -------------------------------------------------------------------------------------------------------------- 
-- Table cci_gh_go.SeasonF1Details
-- ------------------------------------------------------------------------------------------------------------------
 CREATE TABLE IF NOT EXISTS `SeasonF1Details` (
  `seasonF1DetailsId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `programName`VARCHAR(45) UNIQUE,
  `programStatusId` INT(3),
  `secondSemStartDate` DATETIME,
  `secondSemEndDate` DATETIME,
  `secondSemAppDeadlineDate` DATETIME,
  `secondSemEarliestBirthDate` DATETIME,
  `secondSemLatestBirthDate` DATETIME,
  `showSecSemToNewHF` TINYINT(1) DEFAULT 0,
  `activeFullYearJanProgram` TINYINT(1) DEFAULT 0,
  `janFullYearStartDate` DATETIME,
  `janFullYearAppDeadlineDate` DATETIME,
  `janFullYearEndDate` DATETIME,
  `showJanFullYearToNewHF` TINYINT(1) DEFAULT 0,
  `firstSemStartDate` DATETIME,
  `firstSemEndDate` DATETIME,
  `firstSemAppDeadlineDate` DATETIME,
  `firstSemEarliestBirthDate` DATETIME,
  `firstSemLatestBirthDate` DATETIME,
  `showFirstSemToNewHF` TINYINT(1) DEFAULT 0,
  `augFullYearStartDate` DATETIME,
  `augFullYearEndDate` DATETIME,
  `augFullYearAppDeadlineDate` DATETIME,
  `showAugFullYearToNewHF` TINYINT(1) DEFAULT 0,
  `showSeasonToCurrentHF` TINYINT(1) DEFAULT 0,
  `lcPaymentScheduleId` INT(11),
  `fsAgreementId` INT(11),
  `hfReferences` INT(3),
  `hfInquiryDate` DATE,
  `showWelcomeFamily` TINYINT(1) DEFAULT 0,
  `allowFieldStaffToStartRenewalProcess` TINYINT(1) DEFAULT 0,
  `showSpecialRequestStudent` TINYINT(1) DEFAULT 0,
  `greenHeartMargin` INT(11),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonF1DetailsId`),
  INDEX `FK_J1HSPSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonF1Details_status_idx` (`programStatusId` ASC),
  INDEX `FK_seasonF1Details_FieldStaffAgreement_idx` (`fsAgreementId` ASC),
  INDEX `FK_seasonF1Details_PaymentSchedule_idx` (`lcPaymentScheduleId` ASC),
  CONSTRAINT `FK_SeasonF1Details_Season`
  FOREIGN KEY (`seasonId`)
  REFERENCES `Season` (`seasonId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonF1Details_SeasonStatus`
  FOREIGN KEY (`programStatusId`)
  REFERENCES `SeasonStatus` (`seasonStatusId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_seasonF1Details_FieldStaffAgreement`
  FOREIGN KEY (`fsAgreementId`)
  REFERENCES `FieldStaffAgreement` (`fieldStaffAgreementId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_seasonF1Details_PaymentSchedule` 
  FOREIGN KEY (`lcPaymentScheduleId`)
  REFERENCES `PaymentSchedule` (`paymentScheduleId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

-- --------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSPAllocation
-- --------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonHSPAllocation` (
  `seasonHSPAllocationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `maxGuaranteedPax` INT DEFAULT 0,
  `maxUnguaranteedPax` INT DEFAULT 0,
  `departmentProgramOptionId` INT(3) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonHSPAllocationId`),
  INDEX `FK_SeasonHSPAllocation_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonHSPAllocation_DepartmentProgramOptions_idx` (`departmentProgramOptionId` ASC),
  CONSTRAINT `FK_SeasonHSPAllocation_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonHSPAllocation_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
 
-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonGHTConfiguration
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonGHTConfiguration` (
  `seasonGHTConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `seasonStartDate` DATETIME NOT NULL,
  `seasonEndDate` DATETIME NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonGHTConfigurationId`),
  INDEX `FK_SeasonGHTConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonGHTConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonHSADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonHSADetails` (
  `seasonHSADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45) UNIQUE,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
   PRIMARY KEY (`seasonHSADetailsId`),
  INDEX `FK_SeasonHighScoolAbroadDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonHSADetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonHSADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonHSADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonLSDetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonLSDetails` (
  `seasonLSDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(50) UNIQUE,  
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonLSDetailsId`),
  INDEX `FK_seasonLSDetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonLSDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonLSDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonLSDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonTADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonTADetails` (
  `seasonTADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(50) UNIQUE,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonTADetailsId`),
  INDEX `FK_seasonTADetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonTADetails_status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonTADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonTADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonVADetails
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonVADetails` (
  `seasonVADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(50) UNIQUE,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonVADetailsId`),
  INDEX `FK_seasonVolunteerDetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonVolunteerDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonVolunteerDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonVolunteerDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonWADetails
-- ----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonWADetails` (
  `seasonWADetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(45) UNIQUE,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonWADetailsId`),
  INDEX `FK_seasonWADetails_season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonWADetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_seasonWADetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonWADetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.Region
-- ------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `Region` (
  `regionId` INT(3) NOT NULL AUTO_INCREMENT,
  `regionName` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`regionId`)
);

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.USSchool
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `USSchool` (
  `usSchoolId` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`usSchoolId`)
); 

-- -----------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonUSSchoolSeasonStatus
-- -----------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `USSchoolSeason` (
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
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USSchoolSeason_USSchool`
    FOREIGN KEY (`usSchoolId`)
    REFERENCES `USSchool` (`usSchoolId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonDepartmentNotes
-- ---------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonDepartmentNotes` (
`seasonDepartmentNotesId` INT NOT NULL AUTO_INCREMENT,
`seasonId` INT NOT NULL,
`departmentNote` VARCHAR(1000),
`active` TINYINT(1) DEFAULT 0,
`createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
`createdBy` INT(11) NOT NULL,
`modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
`modifiedBy` INT(11) NOT NULL,
PRIMARY KEY (`seasonDepartmentNotesId`),
CONSTRAINT `FK_SeasonDepartmentNotes_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- ---------------------------------------------------------------------------------------------------------------------------------------
-- Table cci_gh_go.SeasonProgramNotes
-- ---------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonProgramNotes` (
`seasonProgramNotesId` INT NOT NULL AUTO_INCREMENT,
`seasonId` INT NOT NULL,
`departmentProgramId` INT(3),
`programNote` VARCHAR(1000),
`active` TINYINT(1) DEFAULT 0,
`createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
`createdBy` INT(11) NOT NULL,
`modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
`modifiedBy` INT(11) NOT NULL,
PRIMARY KEY (`seasonProgramNotesId`),
CONSTRAINT `FK_SeasonProgramNotes_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `FK_SeasonProgramNotes_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION 
);

-- -----------------------------------------------------
-- Table `DocumentType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DocumentType` (
  `documentTypeId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentTypeName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`documentTypeId`)
);

-- -----------------------------------------------------
-- Table `DocumentCategoryProcess`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DocumentCategoryProcess` (
  `documentCategoryProcessId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentCategoryProcessName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`documentCategoryProcessId`)
);

-- -----------------------------------------------------
-- Table `DocumentTypeDocumentCategoryProcess`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DocumentTypeDocumentCategoryProcess` (
  `documentTypeDocumentCategoryProcessId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentTypeId` INT(11) NULL,
  `documentCategoryProcessId` INT(11) NULL,
  `documentTypeRole` VARCHAR(50) NULL,
  PRIMARY KEY (`documentTypeDocumentCategoryProcessId`),
  INDEX `FK_DocumentTypeDocumentCategoryProcess_DocumentType_idx` (`documentTypeId` ASC),
  INDEX `FK_DocumentTypeDocumentCategoryProcess_DocumentCateogoryPro_idx` (`documentCategoryProcessId` ASC),
  CONSTRAINT `FK_DocumentTypeDocumentCategoryProcess_DocumentType`
    FOREIGN KEY (`documentTypeId`)
    REFERENCES `DocumentType` (`documentTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_DocumentTypeDocumentCategoryProcess_DocumentCateogoryProcess`
    FOREIGN KEY (`documentCategoryProcessId`)
    REFERENCES `DocumentCategoryProcess` (`documentCategoryProcessId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `DocumentInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DocumentInformation` (
  `documentInformationId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentTypeDocumentCategoryProcessId` INT(11) NULL,
  `documentName` VARCHAR(100) NULL,
  `fileName` VARCHAR(1000) NULL,
  `url` VARCHAR(1000) NULL,
  `active` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NULL,
  PRIMARY KEY (`documentInformationId`),
  INDEX `FK_DocumentInformation_DocumentTypeDocumentCategoryProcess_idx` (`documentTypeDocumentCategoryProcessId` ASC),
  CONSTRAINT `FK_DocumentInformation_DocumentTypeDocumentCategoryProcess`
    FOREIGN KEY (`documentTypeDocumentCategoryProcessId`)
    REFERENCES `DocumentTypeDocumentCategoryProcess` (`documentTypeDocumentCategoryProcessId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `SeasonDepartmentDocument`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonDepartmentDocument` (
  `seasonDepartmentDocumentID` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `documentInformationId` INT(11) NOT NULL,
  `active` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonDepartmentDocumentID`),
  INDEX `FK_SeasonDepartmentDocument_Season_idx` (`seasonId` ASC),  
  INDEX `FK_SeasonDepartmentDocument_DocumentInformation_idx` (`documentInformationId` ASC),
  CONSTRAINT `FK_SeasonDepartmentDocument_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
  CONSTRAINT `FK_SeasonDepartmentDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `SeasonProgramDocument`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonProgramDocument` (
  `seasonProgramDocumentId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `departmentProgramId` INT NOT NULL,
  `documentInformationId` INT(11) NOT NULL,
  `active` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonProgramDocumentId`),
  INDEX `FK_SeasonProgramDocument_Season_idx` (`seasonId` ASC),  
  INDEX `FK_SeasonProgramDocument_DocumentInformation_idx` (`documentInformationId` ASC),
  INDEX `FK_SeasonProgramDocument_DepartmentPrograms_idx` (`departmentProgramId` ASC),  
  CONSTRAINT `FK_SeasonProgramDocument_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
  CONSTRAINT `FK_SeasonProgramDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonProgramDocument_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `AddendumDocumentInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AddendumDocumentInformation` (
  `addendumDocumentInformationId` INT(11) NOT NULL AUTO_INCREMENT,
  `documentInformationId` INT(11) NOT NULL,
  `documentName` VARCHAR(50) NULL,
  `fileName` VARCHAR(50) NULL,
  `url` VARCHAR(1000) NULL,
  `active` TINYINT(1) DEFAULT 0,
  `updateDate` DATETIME NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`addendumDocumentInformationId`),
  INDEX `FK_AddendumDocumentInformation_DocumentInformation_idx` (`documentInformationId` ASC),
  CONSTRAINT `FK_AddendumDocumentInformation_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
 );
 
 -- -----------------------------------------------------
-- Table `SeasonDepartmentUpdateLog`
-- -----------------------------------------------------
CREATE TABLE `SeasonDepartmentUpdateLog` (
  `updateDepartmentLogId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) NOT NULL,
  `updateLogObject` LONGTEXT,
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`updateDepartmentLogId`),
  INDEX `FK_SeasonDepartmentUpdateLog_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonDepartmentUpdateLog_Season` 
    FOREIGN KEY (`seasonId`) 
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

 -- -----------------------------------------------------
-- Table `SeasonProgramUpdateLog`
-- -----------------------------------------------------
CREATE TABLE `SeasonProgramUpdateLog` (
  `updateProgramLogId` INT(11) NOT NULL AUTO_INCREMENT,
  `seasonId` INT(11) DEFAULT NULL,
  `departmentProgramId` INT(11) DEFAULT NULL,
  `updateLogObject` LONGTEXT,
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`updateProgramLogId`),
  INDEX `FK_SeasonProgramUpdateLog_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonProgramUpdateLog_DepartmentPrograms_idx` (`departmentProgramId` ASC),
  CONSTRAINT `FK_SeasonProgramUpdateLog_Season` 
    FOREIGN KEY (`seasonId`) 
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonProgramUpdateLog_DepartmentPrograms` 
    FOREIGN KEY (`departmentProgramId`) 
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

 -- -----------------------------------------------------
-- Table `SeasonIHPDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonIHPDetails` (
  `seasonIHPDetailsId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NOT NULL,
  `programName` VARCHAR(50) UNIQUE,
  `startDate` DATETIME,
  `endDate` DATETIME,
  `programStatusId` INT(3),
  `maxParticipants` INT,
  `lcHoldTime` INT(3),
  `numberOfLCToRequestHold` INT(3),
  `splitPlacementPending` INT(3),
  `stopAcceptingApps` TINYINT(1) DEFAULT 0,
  `stopAcceptingAppsByGender` TINYINT(1) DEFAULT 0,
  `genderId` INT(3),
  `applicationDeadLineWeeks` INT(3),
  `stopAcceptingAppsStandardIHP`  TINYINT(1) DEFAULT 0,
  `stopAcceptingAppsVolunteerHomestay`  TINYINT(1) DEFAULT 0,
  `stopAcceptingAppsLanguageBuddy`  TINYINT(1) DEFAULT 0,
  `stopAcceptingAppsHolidayHomestay`  TINYINT(1) DEFAULT 0,
  `stopAcceptingAppsHighSchoolVisits`  TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonIHPDetailsId`),
  INDEX `FK_SeasonIHPDetails_Season_idx` (`seasonId` ASC),
  INDEX `FK_SeasonIHPDetails_LookupGender` (`genderId` ASC),
  INDEX `FK_SeasonIHPDetails_Status_idx` (`programStatusId` ASC),
  CONSTRAINT `FK_SeasonIHPDetails_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPDetails_LookupGender`
    FOREIGN KEY (`genderId`)
    REFERENCES `LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPDetails_SeasonStatus`
    FOREIGN KEY (`programStatusId`)
    REFERENCES `SeasonStatus` (`seasonStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
); 

-- -----------------------------------------------------
-- Table `RegionIHP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RegionIHP` (
  `regionIHPId` INT NOT NULL AUTO_INCREMENT,
  `regionName` VARCHAR(45),
  `active` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() ,
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`regionIHPId`)
);
-- -----------------------------------------------------
-- Table `SeasonIHPDetailsRegionApplications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonIHPDetailsRegionApplications` (
  `seasonIHPDetailsRegionApplicationId` INT NOT NULL AUTO_INCREMENT,  
  `seasonIHPDetailsId` INT,
  `regionIHPId` INT,
  `stopAcceptingApps`  TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonIHPDetailsRegionApplicationId`),
  INDEX `FK_SeasonIHPDetailsRegionApplications_SeasonIHPDetails_idx` (`seasonIHPDetailsId` ASC),
  INDEX `FK_SeasonIHPDetailsRegionApplications_RegionIHP_idx` (`regionIHPId` ASC),
  CONSTRAINT `FK_SeasonIHPDetailsRegionApplications_SeasonIHPDetails`
    FOREIGN KEY (`seasonIHPDetailsId`)
    REFERENCES `SeasonIHPDetails` (`seasonIHPDetailsId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPDetailsRegionApplications_RegionIHP`
    FOREIGN KEY (`regionIHPId`)
    REFERENCES `RegionIHP` (`regionIHPId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION 
);

-- -----------------------------------------------------
-- Table `SeasonIHPGeographyConfiguration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonIHPGeographyConfiguration` (
  `seasonIHPGeographyConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `regionIHPId` INT,
  `usStatesId` INT,
  `seasonId` INT,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonIHPGeographyConfigurationId`),
  INDEX `FK_SeasonIHPGeographyConfiguration_Season_idx` (`seasonId` ASC), 
  INDEX `FK_SeasonIHPGeographyConfiguration_RegionIHP_idx` (`regionIHPId` ASC),
  INDEX `FK_SeasonIHPGeographyConfiguration_LookupUSStates_idx` (`usStatesId` ASC),
  CONSTRAINT `FK_SeasonIHPGeographyConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPGeographyConfiguration_RegionIHP`
    FOREIGN KEY (`regionIHPId`)
    REFERENCES `RegionIHP` (`regionIHPId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonIHPGeographyConfiguration_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION 
);

-- -----------------------------------------------------
-- Table `SuperRegion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperRegion` (
  `superRegionId` INT(3) NOT NULL AUTO_INCREMENT,
  `superRegionName` VARCHAR(50),
  `active` TINYINT(1) DEFAULT 0,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`superRegionID`)
);

 -- -----------------------------------------------------
-- Table `SeasonGeographyConfiguration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SeasonGeographyConfiguration` (
  `seasonGeographyConfigurationId` INT NOT NULL AUTO_INCREMENT,
  `superRegionId` INT(3),
  `regionId` INT(3) DEFAULT NULL,
  `usStatesId` INT(3) DEFAULT NULL,
  `seasonId` INT,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`seasonGeographyConfigurationId`),
  INDEX `FK_SeasonGeographyConfiguration_SuperRegion_idx` (`superRegionId` ASC),
  INDEX `FK_SeasonGeographyConfiguration_Region_idx` (`regionId` ASC),
  INDEX `FK_SeasonGeographyConfiguration_LookupUSStates_idx` (`usStatesId` ASC),
  INDEX `FK_SeasonGeographyConfiguration_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_SeasonGeographyConfiguration_SuperRegion`
    FOREIGN KEY (`superRegionId`)
    REFERENCES `SuperRegion` (`superRegionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
  CONSTRAINT `FK_SeasonGeographyConfiguration_Region`
    FOREIGN KEY (`regionId`)
    REFERENCES `Region` (`regionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION, 
  CONSTRAINT `FK_SeasonGeographyConfiguration_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SeasonGeographyConfiguration_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `FieldStaffLCSeason`
-- ----------------------------------------------------- 

CREATE TABLE IF NOT EXISTS `FieldStaffLCSeason` (
  `fieldStaffLCSeasonId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffId` INT,
  `departmentProgramId` INT(3),
  `seasonId` INT,
  `seasonGeographyConfigurationId` INT,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`fieldStaffLCSeasonId`),
  INDEX `FK_FieldStaffLCSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_FieldStaffLCSeason_FieldStaff_idx` (`fieldStaffId` ASC),
  INDEX `FK_FieldStaffLCSeason_DepartmentPrograms_idx` (`departmentProgramId` ASC),
  INDEX `FK_FieldStaffLCSeason_SeasonGeographyConfiguration_idx` (`seasonGeographyConfigurationId` ASC),
  CONSTRAINT `FK_FieldStaffLCSeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLCSeason_FieldStaff`
    FOREIGN KEY (`fieldStaffId`)
    REFERENCES `FieldStaff`(`fieldStaffId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLCSeason_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLCSeason_SeasonGeographyConfiguration`
    FOREIGN KEY (`seasonGeographyConfigurationId`)
    REFERENCES `SeasonGeographyConfiguration` (`seasonGeographyConfigurationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);



-- -----------------------------------------------------
-- Table `HostFamilyPermissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS  `HostFamilyPermissions` (
  `hostFamilyPermissionsId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilyGoId` INT NULL,
  PRIMARY KEY (`hostFamilyPermissionsId`),
  INDEX `FK_HostFamilyPermissions_HostFamily_idx` (`hostFamilyGoId` ASC),
  CONSTRAINT `FK_HostFamilyPermissions_HostFamily`
    FOREIGN KEY (`hostFamilyGoId`)
    REFERENCES `HostFamily` (`hostFamilyGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `FieldStaffPermissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FieldStaffPermissions` (
  `fieldStaffPermissionsId` INT NOT NULL AUTO_INCREMENT,
  `fieldStaffGoId` INT NULL,
  PRIMARY KEY (`fieldStaffPermissionsId`),
  INDEX `FK_FieldStaffPermission_FieldStaff_idx` (`fieldStaffGoId` ASC),
  CONSTRAINT `FK_FieldStaffPermission_FieldStaff`
    FOREIGN KEY (`fieldStaffGoId`)
    REFERENCES `FieldStaff` (`fieldStaffId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `Employer`
-- -----------------------------------------------------
	
CREATE TABLE IF NOT EXISTS  `Employer` (
  `employerGoId` INT NOT NULL,
  `employerStatusId` INT(3) NULL,
  PRIMARY KEY (`employerGoId`),
  CONSTRAINT `FK_Employer_GoIdSequence`
    FOREIGN KEY (`employerGoId`)
    REFERENCES `GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	

-- -----------------------------------------------------
-- Table `EmployerPermissions`
-- -----------------------------------------------------	
CREATE TABLE IF NOT EXISTS `EmployerPermissions` (
  `empolyerPermissionsId` INT NOT NULL AUTO_INCREMENT,
  `employerGoId` INT NULL,
  PRIMARY KEY (`empolyerPermissionsId`),
  INDEX `FK_EmployerPermissions_Employer_idx` (`employerGoId` ASC),
  CONSTRAINT `FK_EmployerPermissions_Employer`
    FOREIGN KEY (`employerGoId`)
    REFERENCES `Employer` (`employerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
-- -----------------------------------------------------
-- Table `PartnerStatus`
-- -----------------------------------------------------	
	
CREATE TABLE IF NOT EXISTS  `PartnerStatus` (
  `partnerStatusId` INT NOT NULL AUTO_INCREMENT,
  `partnerStatusName` VARCHAR(50) NULL,
  `active` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`partnerStatusId`))
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `ParticipantStatus`
-- -----------------------------------------------------

CREATE TABLE `ParticipantStatus` (
  `participantStatusId` INT(3) NOT NULL AUTO_INCREMENT,
  `participantStatusName` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`participantStatusId`))
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `Partner`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `Partner` (
  `partnerGoId` INT NOT NULL,
  `companyName` VARCHAR(250) NULL,
  `acronym` VARCHAR(150) NULL,
  `dandBNumber` INT(11) NULL,
  `receiveAYPMails` TINYINT(1) DEFAULT 0,
  `subscribeToCCINewsletter` TINYINT(1) DEFAULT 0,
  `contractSigner` VARCHAR(50) NULL,
  `quickbooksCode` VARCHAR(40) NULL,
  `canHaveSubPartner` TINYINT(1) DEFAULT 0,
  `hasSubPartners` TINYINT(1) DEFAULT 0,
  `invoiceMail` VARCHAR(100) NULL,
  `multiCountrySender` TINYINT(1) DEFAULT 0,
  `isSubPartner` TINYINT(1) DEFAULT 0,
  `parentPartnerGoId` INT(11) NULL,
  `payGreenheartDirectly` TINYINT(1) DEFAULT 0,
  `deliverDSForms` TINYINT(1) DEFAULT 0,
  `needPartnerReview` TINYINT(1) DEFAULT 0,
  `mailingAddressIsSameAsPhysicalAdress` TINYINT(1) DEFAULT 0,
  `addressLineOne` VARCHAR(150) NULL,
  `addressLineTwo` VARCHAR(150) NULL,
  `city` VARCHAR(50) NULL,
  `zipcode` VARCHAR(15) NULL,
  `state` VARCHAR(50) NULL,
  `countryId` INT(3) NULL,
  `email` VARCHAR(100) DEFAULT NULL,
  `partnerLogo` VARCHAR(300) DEFAULT NULL,
  `physicalAddressLineOne` VARCHAR(150) NULL,
  `physicalAddressLineTwo` VARCHAR(150) NULL,
  `physicalCity` VARCHAR(50) NULL,
  `physicalZipcode` VARCHAR(15) NULL,
  `physicalstate` VARCHAR(50) NULL,
  `physicalcountryId` INT(3) NULL,
  `partnerGuid` VARCHAR(64) NULL,
  `contactNotes` VARCHAR(2000) NULL,
  `billingNotes` VARCHAR(1000) NULL,
  `lastSelectedProgramId` INT(11) NULL,
  `participantMedicalReleaseRequired` TINYINT(1) DEFAULT 0,
  `participantSLEPRequired` TINYINT(1) DEFAULT 0,
  `participantTranscriptRequired` TINYINT(1) DEFAULT 0,
  `oldId` INT(11) NULL,
  `unguaranteedFormRequired` TINYINT(1) DEFAULT 0,
  `participantELTISRequired` TINYINT(1) DEFAULT 0,
  `createdBy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerGoId`),
  INDEX `FK_Partner_LookupCountries_idx` (`physicalcountryId` ASC),
  INDEX `FK_Partner_LookupCountries1_idx` (`countryId` ASC),
 CONSTRAINT `FK_Partner_GoIdSequence`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Partner_LookupCountries`
    FOREIGN KEY (`physicalcountryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Partner_LookupCountries1`
    FOREIGN KEY (`countryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerSeason`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerSeason` (
  `partnerSeasonId` INT NOT NULL AUTO_INCREMENT,
  `seasonId` INT NULL,
  `partnerGoId` INT NULL,
  `departmentProgramId` INT(3) NULL,
  `partnerSeasonStatusId` INT(3) NULL,
  `canCreateSubPartner` TINYINT(1) DEFAULT 0,
  `insuranceProvidedByCCI` TINYINT(1)  DEFAULT 0,
  `sevisFeesPaidByCCI` TINYINT(1)  DEFAULT 0,
  `insuranceCarrierName` VARCHAR(200) NULL,
  `insurancePhoneNumber` VARCHAR(50) NULL,
  `insurancePolicyNumber` VARCHAR(100) NULL,
  `questionaireRequired` TINYINT(1)  DEFAULT 0,
  `questionnaireSubmittedOn` DATETIME ,
  `disableAddParticipant` TINYINT(1)  DEFAULT 0,
  `partnerSeasonStartDate` DATETIME ,
  `partnerSeasonEndDate` DATETIME ,
  `partnerSeasonAppDeadlineDate` DATETIME ,
  `partnerSeasonExtAppDeadlineDate` DATETIME ,
  `partnerSeasonSecSemDeadlineDate` DATETIME ,
  `partnerSeasonExtSecSemDeadlineDate` DATETIME,
  `contractScheduleId` INT NULL,
  `canAccessJobBoard` TINYINT(1)  DEFAULT 0,
  `partnerTaxCompanyId` INT NULL,
  `timelyReturnReportReceivedDate` DATETIME NULL,
  `originalsReceivedDate` DATETIME NULL,
  `participantPaysDeposit` TINYINT(1)  DEFAULT 0,
  `exceptionComments` VARCHAR(2000) NULL,
  `cciStaffUserId` INT(11) DEFAULT NULL,
  `partnerDeadlineRequestStatusId`  INT ,
  `partnerSecSemDeadlineRequestStatusId`  INT ,
  `deadlineRequestedBy`  INT,
  `deadlineRequestedOn` DATETIME,
  `deadlineRequestReviewedBy` INT,
  `deadlineRequestReviewedOn`  DATETIME,
  `appDeadlineFollowupDate` DATETIME,
  `appSecSemDeadlineFollowupDate` DATETIME,
  `active` TINYINT(1)  DEFAULT 0,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerSeasonId`),
  INDEX `FK_PArtnerSeason_PartnerStatus_idx` (`partnerSeasonStatusId` ASC),
  INDEX `FK_PartnerSeason_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerSeason_Season_idx` (`seasonId` ASC),
  INDEX `FK_PartnerSeason_DepartmentPrograms_idx` (`departmentProgramId` ASC),
  CONSTRAINT `FK_PartnerSeason_PartnerStatus`
    FOREIGN KEY (`partnerSeasonStatusId`)
    REFERENCES `PartnerStatus` (`partnerStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_PartnerStatus1`
    FOREIGN KEY (partnerDeadlineRequestStatusId)
    REFERENCES `PartnerStatus` (`partnerStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_PartnerStatus2`
    FOREIGN KEY (partnerSecSemDeadlineRequestStatusId)
    REFERENCES `PartnerStatus` (`partnerStatusId`)	
	ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_Login`
    FOREIGN KEY (deadlineRequestedBy)
    REFERENCES `Login` (`loginId`)
	ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeason_CCIStaffUsers1`
    FOREIGN KEY (deadlineRequestReviewedBy)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,	
  CONSTRAINT `FK_PartnerSeason_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION	)
ENGINE = INNODB;



-- -----------------------------------------------------
-- Table `PartnerSeasonAllocation`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerSeasonAllocation` (
  `partnerSeasonAllocationId` INT NOT NULL AUTO_INCREMENT,
  `partnerSeasonId` INT NULL,
  `departmentProgramOptionId` INT(3) NULL,
  `maxPax` INT NULL,
  `maxGuaranteedPax` INT NULL,
  `expectedPaxCount` INT NULL,
  `requestedMaxPax`  INT NULL,
  `requestedMaxGuaranteedPax` INT NULL,
  `allocationRequestStatusId` INT,
  `allocationRequestedBy`  INT,
  `allocationRequestedOn`  DATETIME,
  `allocationRequestReviewedBy` INT,
  `allocationRequestReviewedOn` DATETIME,
  `janStartFollowupDate` DATETIME,
  `augStartFollowupDate` DATETIME,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerSeasonAllocationId`),
  INDEX `FK_PartnerSeasonAllocation_PartnerSeason_idx` (`partnerSeasonId` ASC),
  INDEX `FK_PartnerSeasonAllocation_DepartmentProgramOptions_idx` (`departmentProgramOptionId` ASC),
  CONSTRAINT `FK_PartnerSeasonAllocation_PartnerSeason`
    FOREIGN KEY (`partnerSeasonId`)
    REFERENCES `PartnerSeason` (`partnerSeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeasonAllocation_PartnerStatus`
    FOREIGN KEY (allocationRequestStatusId)
    REFERENCES PartnerStatus (partnerStatusId)
  	ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeasonAllocation_Login`
    FOREIGN KEY (allocationRequestedBy)
    REFERENCES `Login` (`loginId`)
	ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeasonAllocation_CCIStaffUsers`
    FOREIGN KEY (allocationRequestReviewedBy)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
	ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeasonAllocation_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerOfficeType`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerOfficeType` (
  `partnerOfficeTypeId` INT NOT NULL AUTO_INCREMENT,
  `partnerOfficeType` VARCHAR(50) NULL,
  PRIMARY KEY (`partnerOfficeTypeId`))
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerOffice`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerOffice` (
  `partnerOfficeId` INT NOT NULL AUTO_INCREMENT,
  `partnerOfficeTypeId` INT(3) NULL,
  `partnerGoId` INT NULL,
  `adressOne` VARCHAR(150) NULL,
  `adressTwo` VARCHAR(150) NULL,
  `city` VARCHAR(30) NULL,
  `state` VARCHAR(30) NULL,
  `postalCode` VARCHAR(13) NULL,
  `countryId` INT NULL,
  `faxNumber` VARCHAR(150) NULL,
  `phoneNumber` VARCHAR(150) NULL,
  `website` VARCHAR(150) NULL,
  `officeNotes` VARCHAR(2000) NULL,
  `createdBy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerOfficeId`),
  INDEX `FK_PartnerOffice_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerOffice_LookupCountries_idx` (`countryId` ASC),
  INDEX `FK_PartnerOffice_PartnerOfficeType_idx` (`partnerOfficeTypeId` ASC),
  CONSTRAINT `FK_PartnerOffice_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerOffice_LookupCountries`
    FOREIGN KEY (`countryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerOffice_PartnerOfficeType`
    FOREIGN KEY (`partnerOfficeTypeId`)
    REFERENCES `PartnerOfficeType` (`partnerOfficeTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerAgentInquiries`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerAgentInquiries` (
  `partnerAgentInquiriesId` INT NOT NULL AUTO_INCREMENT,
  `partnerAgentGoId` INT NULL,
  `companyName` VARCHAR(250) NULL,
  `salutationId` INT(3) NULL,
  `rating` INT(3) NULL,
  `firstName` VARCHAR(50) NULL,
  `lastName` VARCHAR(50) NULL,
  `adressLineOne` VARCHAR(150) NULL,
  `adressLineTwo` VARCHAR(150) NULL,
  `city` VARCHAR(30) NULL,
  `state` VARCHAR(30) NULL,
  `countryId` INT NULL,
  `phone` VARCHAR(25) NULL,
  `email` VARCHAR(50) NULL,
  `website` VARCHAR(50) NULL,
  `submittedOn` DATETIME NULL,
  `teachAbroad` TINYINT(1) DEFAULT 0,
  `volunteerAbroad` TINYINT(1) DEFAULT 0,
  `highSchoolAbroad` TINYINT(1) DEFAULT 0,
  `other` TINYINT(1) DEFAULT 0,
  `ambassadorScholershipParticipants` TINYINT(1) DEFAULT 0,
  `currentlySendingParticipantToUS` TINYINT(1) DEFAULT 0,
  `businessYears` VARCHAR(50) NULL,
  `currentlyOfferingPrograms` VARCHAR(150) NULL,
  `howDidYouHearAboutCCI` VARCHAR(100) NULL,
  `followUpDate` DATETIME NULL,
  `businessName` VARCHAR(250),
  `countryFlag` VARCHAR(300) DEFAULT NULL,
  `logo` VARCHAR(300) DEFAULT NULL,
  `participantsForHomeCountry` TINYINT(4) DEFAULT 0,
  PRIMARY KEY (`partnerAgentInquiriesId`),
  INDEX `FK_PartnerAgentInquiriesPartner_idx` (`partnerAgentGoId` ASC),
  CONSTRAINT `FK_PartnerAgentInquiries_Partner`
    FOREIGN KEY (`partnerAgentGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerAgentInquiries_LookupCountries`
    FOREIGN KEY (`countryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerAgentInquiries_Salutation`
    FOREIGN KEY (`salutationId`)
    REFERENCES `Salutation` (`salutationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION	)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerProgram`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerProgram` (
  `partnerProgramId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `lookupDepartmentProgramId` INT NULL,
  `hasApplied` TINYINT(1) DEFAULT 0,
  `cciStaffUserId` INT(11),
  `isEligible` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`partnerProgramId`),
  INDEX `FK_PartnerProgramListDepartmentPrograms_idx` (`lookupDepartmentProgramId` ASC),
  INDEX `FK_PartnerAgentProgram_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerProgram_CCIStaffUser_idx` (`cciStaffUserId` ASC),
  CONSTRAINT `FK_PartnerProgram_LookupDepartmentPrograms`
    FOREIGN KEY (`lookupDepartmentProgramId`)
    REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerProgram_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 CONSTRAINT `FK_PartnerProgram_CCIStaffUser`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION	)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerMessages`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerMessages` (
  `partnerInquiryMessageId` INT NOT NULL AUTO_INCREMENT,
  `partnerInquiryMessage` VARCHAR(1000) NULL,
  `partnerGoId` INT NULL,
  `cciStaffUserId` INT NULL,
  `createdOn` DATETIME NULL,
  PRIMARY KEY (`partnerInquiryMessageId`),
  INDEX `FK_PartnerInquiryNotes_Partner_idx` (`partnerGoId` ASC),
  CONSTRAINT `FK_PartnerMessages_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 CONSTRAINT `FK_PartnerMessages_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerUser`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerUser` (
  `partnerUserId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `loginId` INT NULL,
  `genderId` INT(3) DEFAULT NULL,
  `salutationId` INT(3) NULL,
  `title` VARCHAR(150) NULL,
  `firstName` VARCHAR(150) NULL,
  `lastName` VARCHAR(150) NULL,
  `photo` varchar(300) DEFAULT NULL,
  `email` VARCHAR(50),
  `phone` VARCHAR(150) NULL,
  `emergencyPhone` VARCHAR(150) NULL,
  `isPrimary` TINYINT(1) DEFAULT 0,
  `fax` VARCHAR(150) NULL,
  `skypeId` VARCHAR(150) NULL,
  `active` TINYINT(1) DEFAULT 0,
  INDEX `FK_PartnerUser_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerUser_Login_idx` (`loginId` ASC),
  PRIMARY KEY (`partnerUserId`),
  CONSTRAINT `FK_PartnerUser_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerUser_Login`
    FOREIGN KEY (`loginId`)
    REFERENCES `Login` (`loginId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerUser_LookupGender`
    FOREIGN KEY (`genderId`)
    REFERENCES `LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerUser_Salutation`
    FOREIGN KEY (`salutationId`)
    REFERENCES `Salutation` (`salutationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerDocument`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerDocument` (
  `partnerDocumentId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `documentInformationId` INT NULL,
  `partnerProgramId` INT NULL,
  `description` TEXT,
  PRIMARY KEY (`partnerDocumentId`),
  INDEX `FK_PartnerDocument_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerDocument_PartnerProgram_idx` (`partnerProgramId` ASC),
  INDEX `FK_PartnerDocument_DocumentInformation_idx` (`documentInformationId` ASC),
  CONSTRAINT `FK_PartnerDocument_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerDocument_PartnerProgram`
    FOREIGN KEY (`partnerProgramId`)
    REFERENCES `PartnerProgram` (`partnerProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerAnnouncement`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerAnnouncement` (
  `partnerAnnouncementId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `seasonId` INT NOT NULL,
  `departmentProgramId` INT NOT NULL,
  `announcement` LONGTEXT NOT NULL,
  `title` VARCHAR(250) NOT NULL,
  `active` TINYINT(1) DEFAULT 0,
  `createdBy` INT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerAnnouncementId`),
  INDEX `FK_PartnerAnnouncement_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerAnnouncement_DepartmentPrograms_idx` (`departmentProgramId` ASC),
  INDEX `FK_PartnerAnnouncement_Season_idx` (`seasonId` ASC),
  CONSTRAINT `FK_PartnerAnnouncement_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerAnnouncement_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerAnnouncement_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerPermissions`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerPermissions` (
  `partnerPermissionsId` INT NOT NULL AUTO_INCREMENT,
  `partnerUserId` INT NOT NULL,
  `j1Admin` TINYINT(1) DEFAULT 0,
  `j1Applications` TINYINT(1) DEFAULT 0,
  `j1Flights` TINYINT(1) DEFAULT 0,
  `j1PlacementInfo` TINYINT(1) DEFAULT 0,
  `j1Monitoring` TINYINT(1) DEFAULT 0,
  `j1AccountingInsurance` TINYINT(1) DEFAULT 0,
  `j1StudentsPreProgram` TINYINT(1) DEFAULT 0,
  `j1Contracting` TINYINT(1) DEFAULT 0,
  `j1Insurance` TINYINT(1) DEFAULT 0,
  `f1Admin` TINYINT(1) DEFAULT 0,
  `f1Applications` TINYINT(1) DEFAULT 0,
  `f1Flights` TINYINT(1) DEFAULT 0,
  `f1PlacementInfo` TINYINT(1) DEFAULT 0,
  `f1Monitoring` TINYINT(1) DEFAULT 0,
  `f1AccountingInsurance` TINYINT(1) DEFAULT 0,
  `f1StudentsPreProgram` TINYINT(1) DEFAULT 0,
  `f1Contracting` TINYINT(1) DEFAULT 0,
  `f1Insurance` TINYINT(1) DEFAULT 0,
  `ihpAdmin` TINYINT(1) DEFAULT 0,
  `ihpApplications` TINYINT(1) DEFAULT 0,
  `ihpFlights` TINYINT(1) DEFAULT 0,
  `ihpPlacementInfo` TINYINT(1) DEFAULT 0,
  `ihpMonitoring` TINYINT(1) DEFAULT 0,
  `ihpAccountingInsurance` TINYINT(1) DEFAULT 0,
  `ihpStudentsPreProgram` TINYINT(1) DEFAULT 0,
  `ihpContracting` TINYINT(1) DEFAULT 0,
  `ihpInsurance` TINYINT(1) DEFAULT 0,
  `wtAdmin` TINYINT(1) DEFAULT 0,
  `wtApplications` TINYINT(1) DEFAULT 0,
  `wtFlights` TINYINT(1) DEFAULT 0,
  `wtPlacementInfo` TINYINT(1) DEFAULT 0,
  `wtMonitoring` TINYINT(1) DEFAULT 0,
  `wtAccountingInsurance` TINYINT(1) DEFAULT 0,
  `wtStudentsPreProgram` TINYINT(1) DEFAULT 0,
  `wtContracting` TINYINT(1) DEFAULT 0,
  `wtInsurance` TINYINT(1) DEFAULT 0,
  `capAdmin` TINYINT(1) DEFAULT 0,
  `capApplications` TINYINT(1) DEFAULT 0,
  `capFlights` TINYINT(1) DEFAULT 0,
  `capPlacementInfo` TINYINT(1) DEFAULT 0,
  `capMonitoring` TINYINT(1) DEFAULT 0,
  `capAccountingInsurance` TINYINT(1) DEFAULT 0,
  `capStudentsPreProgram` TINYINT(1) DEFAULT 0,
  `capContracting` TINYINT(1) DEFAULT 0,
  `capInsurance` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`partnerPermissionsId`),
  INDEX `FK_PartnerPermissions_Partner_idx` (`partnerUserId` ASC),
  CONSTRAINT `FK_PartnerPermissions_PartnerUser`
    FOREIGN KEY (`partnerUserId`)
    REFERENCES `PartnerUser` (`partnerUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `Participants`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `Participants` (
  `participantGoId` INT NOT NULL,
  `firstName` VARCHAR(50) NULL,
  `lastName` VARCHAR(50) NULL,
  `partnerGoId` INT NULL,
  `countryId` INT NULL,
  `participantStatusId` INT NULL,
  `seasonId` INT NULL,
  `departmentProgramId` INT NULL,
  `startDate` DATETIME NULL,
  `endDate` DATETIME NULL,
  `guaranteed` TINYINT(1) DEFAULT 0,
  `submittedFlightInfo` TINYINT(1) NULL,
  `subPartnerId` INT NULL,
  `email` varchar(50) DEFAULT NULL,
  `isLead` tinyint(1) DEFAULT 0,
  `departmentProgramOptionId` int(3) DEFAULT NULL,
  `photo` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`participantGoId`),
  INDEX `FK_Participants_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_Participants_Partner1_idx` (`subPartnerId` ASC),
  INDEX `FK_Participant_LookupCountries_idx` (`countryId` ASC),
  INDEX `FK_Participants_Season_idx` (`seasonId` ASC),
  INDEX `FK_Participants_DepartmentProgram_idx` (`departmentProgramId` ASC),
  CONSTRAINT `FK_Participants_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Participants_Partner1`
    FOREIGN KEY (`subPartnerId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Participant_LookupCountries`
    FOREIGN KEY (`countryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Participants_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Participants_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `FK_Participants_DepartmentProgramOptions`
    FOREIGN KEY (`departmentProgramOptionId`)
    REFERENCES `DepartmentProgramOptions` (`departmentProgramOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
	)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `ParticipantPermissions`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `ParticipantPermissions` (
  `participantPermissionsId` INT NOT NULL AUTO_INCREMENT,
  `participantGoId` INT NULL,
  PRIMARY KEY (`participantPermissionsId`),
  INDEX `FK_ParticipantPermissions_Participant_idx` (`participantGoId` ASC),
  CONSTRAINT `FK_ParticipantPermissions_Participants`
    FOREIGN KEY (`participantGoId`)
    REFERENCES `Participants` (`participantGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `PartnerReviewStatus`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerReviewStatus` (
  `partnerReviewStatusId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `partnerLeadStatusId` INT(3) NULL,
  `partnerAgentStatusId` INT(3) NULL,
  `partnerStatusReason` VARCHAR(1000) NULL,
  `cciStaffUserId` INT NULL,
  PRIMARY KEY (`partnerReviewStatusId`),
  INDEX `FK_PartnerAgentStatusReasonPartnerStatus_idx` (`partnerLeadStatusId` ASC),
  INDEX `FK_PartnerAgentStatus_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerReviewStatus_PartnerStatus1_idx` (`partnerAgentStatusId` ASC),
  CONSTRAINT `FK_PartnerReviewStatus_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerReviewStatus_PartnerStatus`
    FOREIGN KEY (`partnerLeadStatusId`)
    REFERENCES `PartnerStatus` (`partnerStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerReviewStatus_PartnerStatus1`
    FOREIGN KEY (`partnerAgentStatusId`)
    REFERENCES `PartnerStatus` (`partnerStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerReviewStatus_CCIStaffUsers`
    FOREIGN KEY (`cciStaffUserId`)
    REFERENCES `CCIStaffUsers` (`cciStaffUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerNoteTopics`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerNoteTopics` (
  `partnerNoteTopicId` INT NOT NULL AUTO_INCREMENT,
  `PartnerNoteTopicName` VARCHAR(50) NULL,
  `partnerGoId` INT NULL,
  `isPublic` TINYINT(1) DEFAULT 0,
  `w&t` TINYINT(1) DEFAULT 0,
  `j1` TINYINT(1) DEFAULT 0,
  `ght` TINYINT(1) DEFAULT 0,
  `stInbound` TINYINT(1) DEFAULT 0,
  `intern` TINYINT(1) DEFAULT 0,
  `trainee` TINYINT(1) DEFAULT 0,
  `meeting/visit` TINYINT(1) DEFAULT 0,
  `competitorInfo` TINYINT(1) DEFAULT 0,
  `embassy/VisaInfo` TINYINT(1) DEFAULT 0,
  `seasonInfo` TINYINT(1) DEFAULT 0,
  `f1` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`partnerNoteTopicId`),
  INDEX `FK_PartnerNoteTopics_Partner_idx` (`partnerGoId` ASC),
  CONSTRAINT `FK_PartnerNoteTopics_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerNotes`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerNotes` (
  `partnerNotesId` INT NOT NULL AUTO_INCREMENT,
  `partnerNoteTopicId` INT NULL,
  `partnerGoId` INT NULL,
  `partnerNote` LONGTEXT NULL,
  `followupDate` DATETIME,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `createdBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  PRIMARY KEY (`partnerNotesId`),
  INDEX `FK_PartnerNotes_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerNotes_PartnerNoteTopics_idx` (`partnerNoteTopicId` ASC),
  CONSTRAINT `FK_PartnerNotes_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerNotes_PartnerNoteTopics`
    FOREIGN KEY (`partnerNoteTopicId`)
    REFERENCES `PartnerNoteTopics` (`partnerNoteTopicId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerContact`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerContact` (
  `partnerContactId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `salutationId` INT(3) NULL,
  `isPrimary` TINYINT(1) DEFAULT 0,
  `firstName` VARCHAR(150) NULL,
  `lastName` VARCHAR(150) NULL,
  `title` VARCHAR(150) NULL,
  `partnerOfficeId` INT(11) NULL,
  `email` VARCHAR(150) NULL,
  `phone` VARCHAR(150) NULL,
  `emergencyPhone` VARCHAR(150) NULL,
  `fax` VARCHAR(150) NULL,
  `receiveNotificationEmails` TINYINT(1) DEFAULT 0,
  `website` VARCHAR(50) DEFAULT NULL,
  `skypeId` VARCHAR(50) NULL,
  `active` TINYINT(1) DEFAULT 0,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`partnerContactId`),
  INDEX `FK_partnerContact_Partner_idx` (`partnerGoId` ASC),
  INDEX `FK_PartnerContact_PartnerOffice_idx` (`partnerOfficeId` ASC),
  CONSTRAINT `FK_partnerContact_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerContact_PartnerOffice`
    FOREIGN KEY (`partnerOfficeId`)
    REFERENCES `PartnerOffice` (`partnerOfficeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerContact_Salutation`
    FOREIGN KEY (salutationId)
	REFERENCES `Salutation` (salutationId)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerReferenceChecks`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerReferenceChecks` (
  `partnerReferenceCheckId` INT NOT NULL AUTO_INCREMENT,
  `partnerGoId` INT NULL,
  `referenceCompletedOn` DATETIME NULL,
  `referenceCompletedBy` VARCHAR(50) NULL,
  `referenceApprovedOn` DATETIME NULL,
  `referenceApprovedBy` VARCHAR(50) NULL,
  `businessLicenseExpiryDate` DATETIME NULL,
  `referenceCheckNotes` LONGTEXT NULL,
  PRIMARY KEY (`partnerReferenceCheckId`),
  INDEX `FK_PartnerReferenceChecks_Partner_idx` (`partnerGoId` ASC),
  CONSTRAINT `FK_PartnerReferenceChecks_Partner`
    FOREIGN KEY (`partnerGoId`)
    REFERENCES `Partner` (`partnerGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerNoteTags`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerNoteTags` (
  `partnerNoteTagId` INT NOT NULL AUTO_INCREMENT,
  `tagName` VARCHAR(50) NULL,
  PRIMARY KEY (`partnerNoteTagId`))
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerUserRoles`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerUserRoles` (
  `partnerUserRoleId` INT NOT NULL AUTO_INCREMENT,
  `partnerUserRoleName` VARCHAR(50) NULL,
  `partnerUserId` INT(11) DEFAULT NULL,
  PRIMARY KEY (`partnerUserRoleId`),
  CONSTRAINT FK_PartnerUserRoles_PartnerUsers
  FOREIGN KEY (partnerUserId)
  REFERENCES PartnerUser (partnerUserId)
  )
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `PartnerSeasonContract`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerSeasonContract` (
  `partnerSeasonContractId` INT NOT NULL AUTO_INCREMENT,
  `partnerSeasonId` INT NULL,
  `isSigned` TINYINT(1) DEFAULT 0,
  `documentInformationId` INT,
  `description` TEXT,
  PRIMARY KEY (`partnerSeasonContractId`),
  INDEX `FK_PartnerSeasonContract_PartnerSeason_idx` (`partnerSeasonId` ASC),
  CONSTRAINT `FK_PartnerSeasonContract_PartnerSeason`
    FOREIGN KEY (`partnerSeasonId`)
    REFERENCES `PartnerSeason` (`partnerSeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeasonContract_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerSeasonDocument`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerSeasonDocument` (
  `partnerSeasonDocumentId` INT NOT NULL AUTO_INCREMENT,
  `partnerSeasonId` INT,
  `documentInformationId` INT,
  `description` TEXT,
  PRIMARY KEY (`partnerSeasonDocumentId`),
  INDEX `FK_PartnerSeasonDocument_PartnerSeason_idx` (`partnerSeasonId` ASC),
  INDEX `FK_PartnerSeasonDocument_DepartmentInformation_idx` (`documentInformationId` ASC),
  CONSTRAINT `FK_PartnerSeasonDocument_PartnerSeason`
    FOREIGN KEY (`partnerSeasonId`)
    REFERENCES `PartnerSeason` (`partnerSeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PartnerSeasonDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
	)
ENGINE = INNODB;

-- -----------------------------------------------------
-- Table `PartnerHelpOption`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS  `PartnerHelpOption` (
  `partnerHelpOptionId` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `partnerHelpOptionName` VARCHAR(50) NULL,
  `createdBy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `active` TINYINT(1) DEFAULT 0
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerHelpOptionProgram`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerHelpOptionProgram` (
  `partnerHelpOptionProgramId` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `partnerHelpOptionId` INT(11) NULL,
  `lookupDepartmentProgramId` INT(11) NULL,
  `createdBy` INT(11) NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    CONSTRAINT `FK_PartnerHelpOptionProgram_LookupDepartmentPrograms`
  FOREIGN KEY(`lookupDepartmentProgramId`)
  REFERENCES `LookupDepartmentPrograms`(`lookupDepartmentProgramId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `HelpContactMode`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `HelpContactMode` (
 `helpContactModeId` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `helpContactModeName` VARCHAR(50) NULL,
 `active` TINYINT(1) DEFAULT 0
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerHelpRequest`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS  `PartnerHelpRequest` (
  `partnerHelpRequestId` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `partnerHelpRequestGUID` VARCHAR(64) NULL,
  `partnerHelpOptionId` INT(11) NULL,
  `helpContactModeId` INT(11) NULL,
  `partnerGoId` INT(11) NULL,
  `subPartnerGoId` INT(11) NULL,
  `loginId` INT(11) NULL,
  `lookupDepartmentProgramId` INT(11) NULL,
  `requestName` VARCHAR(200) NULL,
  `requestEmailAddress` VARCHAR(50) NULL,
  `requestMessage` VARCHAR(1000) NULL,
  `createdBy` INT(11) NOT NULL,
  `createdOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `modifiedBy` INT(11) NOT NULL,
  `modifiedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `active` TINYINT(1) DEFAULT 0,
  UNIQUE KEY `PartnerHelpRequestGUID` (`PartnerHelpRequestGUID`),
  CONSTRAINT `FK_PartnerHelpRequest_PartnerHelpOption`
  FOREIGN KEY(`partnerHelpOptionId`)
  REFERENCES `PartnerHelpOption`(`partnerHelpOptionId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_PartnerHelpRequest_Partner`
  FOREIGN KEY (`partnerGoId`)
  REFERENCES `Partner`(`partnerGoId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_PartnerHelpRequest_Partner1`
  FOREIGN KEY (`subPartnerGoId`)
  REFERENCES `Partner`(`partnerGoId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_PartnerHelpRequest_Login`
  FOREIGN KEY (`loginId`)
  REFERENCES `Login`(`loginId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_PartnerHelpRequest_LookupDepartmentPrograms`
  FOREIGN KEY (`lookupDepartmentProgramId`)
  REFERENCES `LookupDepartmentPrograms`(`lookupDepartmentProgramId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT `FK_PartnerHelpRequest_HelpContactMode`
  FOREIGN KEY (`helpContactModeId`)
  REFERENCES `HelpContactMode`(`helpContactModeId`)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `StateProcess`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StateProcess` (
  `stateProcessId` int(11) NOT NULL AUTO_INCREMENT,
  `stateProcessName` varchar(50) DEFAULT NULL,
  `workQueue` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`stateProcessId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `StateTypes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StateTypes` (
  `stateTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `stateName` varchar(45) DEFAULT NULL,
  `stateProcessId` int(11) DEFAULT NULL,
  `isLastStep` tinyint(1) DEFAULT 0,
  `workQueueRoleType` varchar(50) DEFAULT NULL,
  `workQueueTypeId` int(11) DEFAULT NULL,
  `workQueueCategoryId` int(11) DEFAULT NULL,
  `isVisibleToAdmin` tinyint(1) DEFAULT 0,
  `isVisibleToPartner` tinyint(1) DEFAULT 0,
  `isVisibleToParticipant` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`stateTypeId`),
  KEY `FK_StateTypes_Stateprocess` (`stateProcessId`),
  CONSTRAINT `FK_StateTypes_Stateprocess` 
  FOREIGN KEY (`stateProcessId`) 
  REFERENCES `StateProcess` (`stateProcessId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `StateActions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StateActions` (
  `stateActionId` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(50) DEFAULT NULL,
  `stateTypeId` int(11) NOT NULL,
  PRIMARY KEY (`stateActionId`),
  KEY `FK_StateActions_StateTypes_idx` (`stateTypeId`),
  CONSTRAINT `FK_StateActions_StateTypes` 
  FOREIGN KEY (`stateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `StateTransitions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StateTransitions` (
  `stateTransitionId` int(11) NOT NULL AUTO_INCREMENT,
  `fromStateTypeId` int(11) NOT NULL,
  `toStateTypeId` int(11) NOT NULL,
  `fromRoletype` varchar(45) DEFAULT NULL,
  `toRoleType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`stateTransitionId`),
  KEY `FK_Statetransitions_StateTypes_idx` (`fromStateTypeId`),
  KEY `FK_Statetransitions_StateTypes_1_idx` (`toStateTypeId`),
  CONSTRAINT `FK_Statetransitions_StateTypes` 
  FOREIGN KEY (`fromStateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_Statetransitions_StateTypes_1` 
  FOREIGN KEY (`toStateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `StateTypeResourcePermission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StateTypeResourcePermission` (
  `stateTypeRPId` int(11) NOT NULL AUTO_INCREMENT,
  `stateTypeId` int(11) DEFAULT NULL,
  `resourcePermissionId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  PRIMARY KEY (`stateTypeRPId`),
  KEY `FK_StateTypeRP_StateType` (`stateTypeId`),
  KEY `FK_StateTypeRP_ResourcePermissions` (`resourcePermissionId`),
  KEY `FK_StateTypeRP_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_StateTypeRP_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_StateTypeRP_ResourcePermissions` 
  FOREIGN KEY (`resourcePermissionId`) 
  REFERENCES `ResourcePermissions` (`resourcePermissionId`),
  CONSTRAINT `FK_StateTypeRP_StateType` 
  FOREIGN KEY (`stateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerQuickStatsType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerQuickStatsType` (
  `partnerQSTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerQSTypeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`partnerQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerQuickStatsCategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerQuickStatsCategories` (
  `partnerQSCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerQSCategoryName` varchar(50) DEFAULT NULL,
  `partnerQSTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`partnerQSCategoryId`),
  KEY `FK_PartnerQuickStatsCategories_PartnerQuickStatsType` (`partnerQSTypeId`),
  CONSTRAINT `FK_PartnerQuickStatsCategories_PartnerQuickStatsType` 
  FOREIGN KEY (`partnerQSTypeId`) 
  REFERENCES `PartnerQuickStatsType` (`partnerQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerQuickStatsTypeAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerQuickStatsTypeAggregate` (
  `partnerQSTypeAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerQSTypeId` int(11) DEFAULT NULL,
  `partnerQSTypeName` varchar(50) DEFAULT NULL,
  `partnerQSTypeAggregate` int(11) DEFAULT NULL,
  `partnerGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`partnerQSTypeAggregateId`),
  KEY `FK_PartnerQSTypeAggregate_PartnerQSType` (`partnerQSTypeId`),
  KEY `FK_PartnerQSTypeAggregate_Partner` (`partnerGoId`),
  KEY `FK_PartnerQSTypeAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerQSTypeAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerQSTypeAggregate_Partner` 
  FOREIGN KEY (`partnerGoId`) 
  REFERENCES `Partner` (`partnerGoId`),
  CONSTRAINT `FK_PartnerQSTypeAggregate_PartnerQSType` 
  FOREIGN KEY (`partnerQSTypeId`) 
  REFERENCES `PartnerQuickStatsType` (`partnerQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerQuickStatsCategoryAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerQuickStatsCategoryAggregate` (
  `PartnerQSCategoryAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerQSCategoryId` int(11) DEFAULT NULL,
  `partnerQSCategoryName` varchar(50) DEFAULT NULL,
  `partnerQSTypeId` int(11) DEFAULT NULL,
  `partnerGoId` int(11) DEFAULT NULL,
  `lookupdepartmentProgramId` int(11) DEFAULT NULL,
  `modifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PartnerQSCategoryAggregateId`),
  KEY `FK_PartnerQSCategoryAggregate_PartnerQSCategories` (`partnerQSCategoryId`),
  KEY `FK_PartnerQSCategoryAggregate_PartnerQSType` (`partnerQSTypeId`),
  KEY `FK_PartnerQSCategoryAggregate_Partner` (`partnerGoId`),
  KEY `FK_PartnerQSCategoryAggregate_LookupDepartmentPrograms` (`lookupdepartmentProgramId`),
  CONSTRAINT `FK_PartnerQSCategoryAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupdepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerQSCategoryAggregate_Partner` 
  FOREIGN KEY (`partnerGoId`) 
  REFERENCES `Partner` (`partnerGoId`),
  CONSTRAINT `FK_PartnerQSCategoryAggregate_PartnerQSCategories` 
  FOREIGN KEY (`partnerQSCategoryId`) 
  REFERENCES `PartnerQuickStatsCategories` (`partnerQSCategoryId`),
  CONSTRAINT `FK_PartnerQSCategoryAggregate_PartnerQSType` 
  FOREIGN KEY (`partnerQSTypeId`) 
  REFERENCES `PartnerQuickStatsType` (`partnerQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `PartnerWorkQueueType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerWorkQueueType` (
  `partnerWQTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerWQTypeName` varchar(50) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `stateProcessId` int(11) DEFAULT NULL,
  `displayWQTypeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`partnerWQTypeId`),
  KEY `FK_PartnerWQType_LookupDepartment` (`lookupDepartmentProgramId`),
  KEY `FK_PartnerWQType_StateProcess` (`stateProcessId`),
  CONSTRAINT `FK_PartnerWQType_LookupDepartment` 
  FOREIGN KEY (`lookupDepartmentProgramId`)
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQType_StateProcess` 
  FOREIGN KEY (`stateProcessId`) 
  REFERENCES `StateProcess` (`stateProcessId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerWorkQueueCategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerWorkQueueCategories` (
  `partnerWQCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerWQCategoryName` varchar(50) DEFAULT NULL,
  `partnerWQTypeId` int(11) NOT NULL,
  PRIMARY KEY (`partnerWQCategoryId`),
  KEY `FK_PartnerWorkQueueCategories_PartnerWorkQueueType_idx` (`partnerWQTypeId`),
  CONSTRAINT `FK_PartnerWorkQueueCategories_PartnerWorkQueueType` 
  FOREIGN KEY (`partnerWQTypeId`) 
  REFERENCES `PartnerWorkQueueType` (`partnerWQTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerWorkQueueCategoryAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerWorkQueueCategoryAggregate` (
  `partnerWQCategoryAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `partnerWQCategoryId` int(11) DEFAULT NULL,
  `partnerWQTypeId` int(11) DEFAULT NULL,
  `partnerWQCategoryName` varchar(50) DEFAULT NULL,
  `partnerGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `partnerWQCategoryAggregate` int(11) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`partnerWQCategoryAggregateId`),
  KEY `FK_PartnerWQCategoryAggregate_PartnerWQCategory` (`partnerWQCategoryId`),
  KEY `FK_PartnerWQCategoryAggregate_ParnerWQType` (`partnerWQTypeId`),
  KEY `FK_PartnerWQCategoryAggregate_Partner` (`partnerGoId`),
  KEY `FK_PartnerWQCategoryAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQCategoryAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQCategoryAggregate_ParnerWQType` 
  FOREIGN KEY (`partnerWQTypeId`) 
  REFERENCES `PartnerWorkQueueType` (`partnerWQTypeId`),
  CONSTRAINT `FK_PartnerWQCategoryAggregate_Partner` 
  FOREIGN KEY (`partnerGoId`) 
  REFERENCES `Partner` (`partnerGoId`),
  CONSTRAINT `FK_PartnerWQCategoryAggregate_PartnerWQCategory` 
  FOREIGN KEY (`partnerWQCategoryId`) 
  REFERENCES `PartnerWorkQueueCategories` (`partnerWQCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerWorkQueueTypeAggregate`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `PartnerWorkQueueTypeAggregate` (
  `partnerWQTypeAggregateId` INT(11) NOT NULL AUTO_INCREMENT,
  `partnerWQTypeId` INT(11) DEFAULT NULL,
  `partnerWQTypeName` VARCHAR(50) DEFAULT NULL,
  `partnerGoId` INT(11) DEFAULT NULL,
  `lookupDepartmentProgramId` INT(11) DEFAULT NULL,
  `partnerWQTypeAggregate` INT(11) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`partnerWQTypeAggregateId`),
  KEY `FK_PartnerWQTypeAggregate_PartnerWorkQueueType` (`partnerWQTypeId`),
  KEY `FK_PartnerWQTypeAggregate_Partner` (`partnerGoId`),
  KEY `FK_PartnerWQTypeAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQTypeAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQTypeAggregate_Partner` 
  FOREIGN KEY (`partnerGoId`) 
  REFERENCES `Partner` (`partnerGoId`),
  CONSTRAINT `FK_PartnerWQTypeAggregate_PartnerWorkQueueType` 
  FOREIGN KEY (`partnerWQTypeId`) 
  REFERENCES `PartnerWorkQueueType` (`partnerWQTypeId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PartnerWorkQueue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PartnerWorkQueue` (
  `partnerWQId` int(11) NOT NULL AUTO_INCREMENT,
  `queueData` longtext,
  `createdDate` datetime DEFAULT NULL,
  `partnerGoId` int(11) DEFAULT NULL,
  `seasonId` int(11) DEFAULT NULL,
  `departmentProgramId` int(3) DEFAULT NULL,
  `partnerWQTypeId` int(11) DEFAULT NULL,
  `partnerWQCategoryId` int(11) DEFAULT NULL,
  `stateTypeId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(3) DEFAULT NULL,
  `targetGoId` int(11) DEFAULT NULL,
  `targetRoleType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`partnerWQId`),
  KEY `FK_PartnerWQ_Partner` (`partnerGoId`),
  KEY `FK_PartnerWQ_Season` (`seasonId`),
  KEY `FK_PartnerWQ_DepartmentProgram` (`departmentProgramId`),
  KEY `FK_PartnerWQ_PartnerWQType` (`partnerWQTypeId`),
  KEY `FK_PartnerWQ_PartnerWQCategory` (`partnerWQCategoryId`),
  KEY `FK_PartnerWQ_StateType` (`stateTypeId`),
  KEY `FK_PartnerWQ_LookupDepartmentPRogram` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQ_DepartmentProgram` 
  FOREIGN KEY (`departmentProgramId`) 
  REFERENCES `DepartmentPrograms` (`departmentProgramId`),
  CONSTRAINT `FK_PartnerWQ_LookupDepartmentPRogram` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_PartnerWQ_Partner` 
  FOREIGN KEY (`partnerGoId`) 
  REFERENCES `Partner` (`partnerGoId`),
  CONSTRAINT `FK_PartnerWQ_PartnerWQCategory` 
  FOREIGN KEY (`partnerWQCategoryId`) 
  REFERENCES `PartnerWorkQueueCategories` (`partnerWQCategoryId`),
  CONSTRAINT `FK_PartnerWQ_PartnerWQType` 
  FOREIGN KEY (`partnerWQTypeId`) 
  REFERENCES `PartnerWorkQueueType` (`partnerWQTypeId`),
  CONSTRAINT `FK_PartnerWQ_Season` 
  FOREIGN KEY (`seasonId`) 
  REFERENCES `Season` (`seasonId`),
  CONSTRAINT `FK_PartnerWQ_StateType` 
  FOREIGN KEY (`stateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminQuickStatsType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminQuickStatsType` (
  `adminQSTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `adminQSTypeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`adminQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminQuickStatsCategories`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `AdminQuickStatsCategories` (
  `adminQSCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `adminQSCategoryName` varchar(50) DEFAULT NULL,
  `adminQSTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`adminQSCategoryId`),
  KEY `FK_AdminQuickStatsCategories_AdminQuickStatsType_idx` (`adminQSTypeId`),
  CONSTRAINT `FK_AdminQuickStatsCategories_AdminQuickStatsType` 
  FOREIGN KEY (`adminQSTypeId`) 
  REFERENCES `AdminQuickStatsType` (`adminQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `AdminQuickStatsCategoryAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminQuickStatsCategoryAggregate` (
  `adminQSCategoryAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `adminQSCategoryId` int(11) DEFAULT NULL,
  `adminQSCategoryName` varchar(50) DEFAULT NULL,
  `adminQSCategoryAggregate` INT NULL,
  `status` VARCHAR(50) NULL,
  `adminQSTypeId` int(11) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`adminQSCategoryAggregateId`),
  KEY `FK_AdminQSCategoryAggregate_AdminQSCategories` (`adminQSCategoryId`),
  KEY `FK_AdminQSCategoryAggregate_AdminQSType` (`adminQSTypeId`),
  CONSTRAINT `FK_AdminQSCategoryAggregate_AdminQSCategories` 
  FOREIGN KEY (`adminQSCategoryId`) 
  REFERENCES `AdminQuickStatsCategories` (`adminQSCategoryId`),
  CONSTRAINT `FK_AdminQSCategoryAggregate_AdminQSType` 
  FOREIGN KEY (`adminQSTypeId`) 
  REFERENCES `AdminQuickStatsType` (`adminQSTypeId`)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminWorkQueueType`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `AdminWorkQueueType` (
  `adminWQTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `adminWQTypeName` varchar(45) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `roleType` VARCHAR(45),
  PRIMARY KEY (`adminWQTypeId`),
  KEY `FK_AdminWQType` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_AdminWQType` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminWorkQueueCategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminWorkQueueCategories` (
  `adminWorkQueueCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `adminWorkQueueCategoryName` varchar(45) DEFAULT NULL,
  `adminWQTypeId` int(11) NOT NULL,
  `roleType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`adminWorkQueueCategoryId`),
  KEY `FK_AdminWorkQueueCategories_AdminWorkQueueType_idx` (`adminWQTypeId`),
  CONSTRAINT `FK_AdminWorkQueueCategories_AdminWorkQueueType` 
  FOREIGN KEY (`adminWQTypeId`) 
  REFERENCES `AdminWorkQueueType` (`adminWQTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminWorkQueueTypeAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminWorkQueueTypeAggregate` (
  `adminWQTypeAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `adminWQTypeId` int(11) DEFAULT NULL,
  `adminWQTypeName` varchar(50) DEFAULT NULL,
  `adminWQTypeAggregate` int(11) DEFAULT NULL,
  `adminGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`adminWQTypeAggregateId`),
  KEY `FK_AdminWQTypeAggregate_AdminWorkQueueType_idx` (`adminWQTypeId`),
  KEY `FK_AdminWQTypeAggregate_CCIStaffUsers` (`adminGoId`),
  KEY `FK_AdminWQTypeAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_AdminWQTypeAggregate_AdminWorkQueueType` 
  FOREIGN KEY (`adminWQTypeId`) 
  REFERENCES `AdminWorkQueueType` (`adminWQTypeId`),
  CONSTRAINT `FK_AdminWQTypeAggregate_CCIStaffUsers` 
  FOREIGN KEY (`adminGoId`) 
  REFERENCES `CCIStaffUsers` (`cciStaffUserId`),
  CONSTRAINT `FK_AdminWQTypeAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminWorkQueueCategoryAggregate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminWorkQueueCategoryAggregate` (
  `adminWQCategoryAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `adminWQCategoryId` int(11) DEFAULT NULL,
  `adminWQCategoryAggregate` int(11) DEFAULT NULL,
  `adminWQTypeId` int(11) DEFAULT NULL,
  `adminWQCategoryName` varchar(50) DEFAULT NULL,
  `modifiedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `adminGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  PRIMARY KEY (`adminWQCategoryAggregateId`),
  KEY `FK_AdminWQCategoryAggregate_AdminWQCategory_idx` (`adminWQCategoryId`),
  KEY `FK_AdminWQCategoryAggregate_AdminWQType` (`adminWQTypeId`),
  KEY `FK_AdminWQCategoryAggregate_CCIStaffUsers` (`adminGoId`),
  KEY `FK_AdminWQCategoryAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_AdminWQCategoryAggregate_AdminWQCategory` 
  FOREIGN KEY (`adminWQCategoryId`) 
  REFERENCES `AdminWorkQueueCategories` (`adminWorkQueueCategoryId`),
  CONSTRAINT `FK_AdminWQCategoryAggregate_AdminWQType` 
  FOREIGN KEY (`adminWQTypeId`) 
  REFERENCES `AdminWorkQueueType` (`adminWQTypeId`),
  CONSTRAINT `FK_AdminWQCategoryAggregate_CCIStaffUsers` 
  FOREIGN KEY (`adminGoId`) 
  REFERENCES `CCIStaffUsers` (`cciStaffUserId`),
  CONSTRAINT `FK_AdminWQCategoryAggregate_LookupDepartmentPrograms` 
  FOREIGN KEY (`lookupDepartmentProgramId`) 
  REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `AdminWorkQueue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AdminWorkQueue` (
  `adminWQId` int(11) NOT NULL AUTO_INCREMENT,
  `queueData` longtext,
  `adminWQTypeId` int(11) NOT NULL,
  `adminWQCategoryId` int(11) NOT NULL,
  `cciStaffUserGoId` int(11) DEFAULT NULL,
  `cciStaaffUserRole` varchar(45) DEFAULT NULL,
  `targetGoId` int(11) DEFAULT NULL,
  `targetRoleType` varchar(45) DEFAULT NULL,
  `seasonId` int(11) DEFAULT NULL,
  `departmentProgramId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `stateTypeId` int(11) DEFAULT NULL,
  `createdDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`adminWQId`),
  KEY `FK_AdminWorkQueue_AdminWorkQueueType_idx` (`adminWQTypeId`),
  KEY `FK_AdminWorkQueue_AdminWorkQueueCategories_idx` (`adminWQCategoryId`),
  KEY `FK_AdminWorkQueue_StateType_idx` (`stateTypeId`),
  CONSTRAINT `FK_AdminWorkQueue_AdminWorkQueueCategories` 
  FOREIGN KEY (`adminWQCategoryId`) 
  REFERENCES `AdminWorkQueueCategories` (`adminWorkQueueCategoryId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_AdminWorkQueue_AdminWorkQueueType` 
  FOREIGN KEY (`adminWQTypeId`) 
  REFERENCES `AdminWorkQueueType` (`adminWQTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_AdminWorkQueue_StateType` 
  FOREIGN KEY (`stateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `WorkQueueHistory` (
  `workQueueHistoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `shooterGoId` INT(11) DEFAULT NULL,
  `shooterRoleType` VARCHAR(50) DEFAULT NULL,
  `targetGoId` INT(11) DEFAULT NULL,
  `targetRoleType` VARCHAR(50) DEFAULT NULL,
  `fromStateTypeId` INT(11) DEFAULT NULL,
  `toStateTypeId` INT(11) DEFAULT NULL,
  `createdDate` DATETIME DEFAULT NULL,
  PRIMARY KEY (`workQueueHistoryId`),
  CONSTRAINT `FK_WorkQueueHistory_StateType` 
  FOREIGN KEY (`toStateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_WorkQueueHistory_StateType` 
  FOREIGN KEY (`fromStateTypeId`) 
  REFERENCES `StateTypes` (`stateTypeId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION,
  CONSTRAINT `FK_WorkQueueHistory_CCIStaffUsers` 
  FOREIGN KEY (`shooterGoId`) 
  REFERENCES `CCIStaffUsers` (`cciStaffUserId`) 
  ON DELETE NO ACTION 
  ON UPDATE NO ACTION  
) ENGINE=INNODB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `FieldStaffStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffStatus` ;
CREATE TABLE `FieldStaffStatus` (
  `fieldStaffStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffStatusName` varchar(50) NOT NULL,
  `isSeasonStatus` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffStatusId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffType` ;
CREATE TABLE `FieldStaffType` (
  `fieldStaffTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffTypeName` varchar(45) DEFAULT NULL,
  `fieldStaffTypeCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffTypeId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaff` ;
CREATE TABLE `FieldStaff` (
  `fieldStaffGoId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffTypeId` int(11) DEFAULT NULL,
  `photo` varchar(300) DEFAULT NULL,
  `salutationId` int(11) DEFAULT NULL,
  `ERDId` int(11) DEFAULT NULL,
  `RDId` int(11) DEFAULT NULL,
  `RMId` int(11) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `mailingAddress1` varchar(100) DEFAULT NULL,
  `mailingAddress2` varchar(100) DEFAULT NULL,
  `mailingCity` varchar(100) DEFAULT NULL,
  `mailingStateId` int(3) DEFAULT NULL,
  `mailingZipCode` varchar(25) DEFAULT NULL,
  `currentAddress1` varchar(100) DEFAULT NULL,
  `currentAddress2` varchar(100) DEFAULT NULL,
  `currentCity` varchar(100) DEFAULT NULL,
  `currentStateId` int(3) DEFAULT NULL,
  `currentZipCode` varchar(25) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `tollFreePhone` varchar(25) DEFAULT NULL,
  `workPhone` varchar(25) DEFAULT NULL,
  `cellPhone` varchar(25) DEFAULT NULL,
  `fax` varchar(25) DEFAULT NULL,
  `emergencyPhone` varchar(25) DEFAULT NULL,
  `bestTimeToCall` varchar(50) DEFAULT NULL,
  `whereToCall` varchar(50) DEFAULT NULL,
  `ssNumber` varchar(15) DEFAULT NULL,
  `selfDescription` longtext,
  `communityDescription` longtext,
  `areaHighSchools` longtext,
  `fieldStaffStatusId` int(3) DEFAULT NULL,
  `agreeToTerms` tinyint(1) DEFAULT '0',
  `residesAlone` tinyint(1) DEFAULT '0',
  `cciStaffUserId` int(11) DEFAULT NULL,
  `backgroundCheckPassed` int(11) DEFAULT NULL,
  `backgroundCheckDate` datetime DEFAULT NULL,
  `reasonForRejection` tinyint(1) DEFAULT '0',
  `comments` longtext,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` int(11) DEFAULT NULL,
  `receiveEmails` tinyint(1) DEFAULT '0',
  `reasonsForApplying` longtext,
  `hasBeenARBefore` int(11) DEFAULT NULL,
  `pastARExperience` longtext,
  `isCurrentlyAR` int(11) DEFAULT NULL,
  `hasHostedBefore` int(11) DEFAULT NULL,
  `pastHostingDetails` longtext,
  `pastHostingOverview` longtext,
  `internationalExperienceDetails` longtext,
  `teenagerExperienceDetails` longtext,
  `birthDate` datetime DEFAULT NULL,
  `genderId` int(3) DEFAULT NULL,
  `voLastSlideViewed` int(11) DEFAULT NULL,
  `voCompletionDate` datetime DEFAULT NULL,
  `currentSeasonId` int(11) DEFAULT NULL,
  `submittedDate` datetime DEFAULT NULL,
  `approvedDate` datetime DEFAULT NULL,
  `approvedBy` int(11) DEFAULT NULL,
  `isBlacklisted` tinyint(1) DEFAULT '0',
  `isDoNotContact` tinyint(1) DEFAULT '0',
  `dateDOSCertTestTaken` datetime DEFAULT NULL,
  `canPresentGrantPax` tinyint(1) DEFAULT NULL,
  `childServicesContact` int(11) DEFAULT NULL,
  `childServicesContactDetails` longtext,
  `dateW9FormReceived` datetime DEFAULT NULL,
  `crime` int(11) DEFAULT NULL,
  `crimeDetails` longtext,
  `agreementNoticeSent` tinyint(1) DEFAULT '0',
  `originalStartDate` datetime DEFAULT NULL,
  `totalPlacementsAuto` int(11) DEFAULT NULL,
  `totalPlacementsManual` int(11) DEFAULT NULL,
  `currentCommunityVolunteer` tinyint(1) DEFAULT '0',
  `currentCommunityVolunteerDetails` longtext,
  `fieldStaffWillingToHostId` int(11) DEFAULT NULL,
  `interestedInLocalCoordinatorForYear` int(11) DEFAULT NULL,
  `interestedInLocalCoordinatorForSummer` int(11) DEFAULT NULL,
  `hostFamilyApplicationStarted` tinyint(1) DEFAULT '0',
  `bestNumberHome` tinyint(1) DEFAULT '0',
  `bestNumberWork` tinyint(1) DEFAULT '0',
  `bestNumberCell` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`fieldStaffGoId`),
  KEY `FK_FieldStaff_FieldStaffType` (`fieldStaffTypeId`),
  KEY `FK_FieldStaff_FieldStaffStatus` (`fieldStaffStatusId`),
  KEY `FK_FieldStaff_LookupUSStates` (`mailingStateId`),
  KEY `FK_FieldStaff_LookupUSStates1` (`currentStateId`),
  KEY `FK_FieldStaff_CCIStaffUsers` (`cciStaffUserId`),
  KEY `FK_FieldStaff_LookupGender` (`genderId`),
  KEY `FK_FieldStaff_Salutation` (`salutationId`),
  CONSTRAINT `FK_FieldStaff_CCIStaffUsers` FOREIGN KEY (`cciStaffUserId`) REFERENCES `CCIStaffUsers` (`cciStaffUserId`),
  CONSTRAINT `FK_FieldStaff_FieldStaffStatus` FOREIGN KEY (`fieldStaffStatusId`) REFERENCES `FieldStaffStatus` (`fieldStaffStatusId`),
  CONSTRAINT `FK_FieldStaff_FieldStaffType` FOREIGN KEY (`fieldStaffTypeId`) REFERENCES `FieldStaffType` (`fieldStaffTypeId`),
  CONSTRAINT `FK_FieldStaff_GoIdSequence` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `GoIdSequence` (`goId`),
  CONSTRAINT `FK_FieldStaff_LookupGender` FOREIGN KEY (`genderId`) REFERENCES `LookupGender` (`genderId`),
  CONSTRAINT `FK_FieldStaff_LookupUSStates` FOREIGN KEY (`mailingStateId`) REFERENCES `LookupUSStates` (`usStatesId`),
  CONSTRAINT `FK_FieldStaff_LookupUSStates1` FOREIGN KEY (`currentStateId`) REFERENCES `LookupUSStates` (`usStatesId`),
  CONSTRAINT `FK_FieldStaff_Salutation` FOREIGN KEY (`salutationId`) REFERENCES `Salutation` (`salutationId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffAgreement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffAgreement` ;
CREATE TABLE `FieldStaffAgreement` (
  `fieldStaffAgreementId` int(11) NOT NULL AUTO_INCREMENT,
  `agreementName` varchar(50) DEFAULT NULL,
  `agreementHTML` longtext,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`fieldStaffAgreementId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffAnnouncement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffAnnouncement` ;
CREATE TABLE `FieldStaffAnnouncement` (
  `fieldStaffAnnouncementId` int(11) NOT NULL AUTO_INCREMENT,
  `announcement` longtext,
  `title` varchar(250) DEFAULT NULL,
  `seasonId` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  `archived` tinyint(1) DEFAULT '0',
  `showERD` tinyint(1) DEFAULT '0',
  `showRD` tinyint(1) DEFAULT '0',
  `showRM` tinyint(1) DEFAULT '0',
  `showLC` tinyint(1) DEFAULT '0',
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` int(11) DEFAULT NULL,
  `departmentProgramId` int(11) DEFAULT NULL,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffAnnouncementId`),
  KEY `FK_FieldStaffAnnouncement_Season` (`seasonId`),
  KEY `FK_FieldStaffAnnouncement_DepartmentPrograms` (`departmentProgramId`),
  KEY `FK_FieldStaffAnnouncement_FieldStaff` (`fieldStaffGoId`),
  CONSTRAINT `FK_FieldStaffAnnouncement_DepartmentPrograms` FOREIGN KEY (`departmentProgramId`) REFERENCES `DepartmentPrograms` (`departmentProgramId`) ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffAnnouncement_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`) ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffAnnouncement_Season` FOREIGN KEY (`seasonId`) REFERENCES `Season` (`seasonId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffDocument`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffDocument` ;
CREATE TABLE `FieldStaffDocument` (
  `fieldStaffDocumentId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `documentInformationId` int(11) DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`fieldStaffDocumentId`),
  KEY `FK_FieldStaffDocument_FieldStaff_idx` (`fieldStaffGoId`),
  KEY `FK_FieldStaffDocument_DocumentInformation` (`documentInformationId`),
  CONSTRAINT `FK_FieldStaffDocument_DocumentInformation` FOREIGN KEY (`documentInformationId`) REFERENCES `DocumentInformation` (`documentInformationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffDocument_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffFamilyMember`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffFamilyMember` ;
CREATE TABLE `FieldStaffFamilyMember` (
  `fieldStaffFamilyMemberId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `genderId` int(11) DEFAULT NULL,
  `relationship` varchar(100) DEFAULT NULL,
  `livingAtHome` tinyint(1) DEFAULT '0',
  `backgroundCheckPassed` int(11) DEFAULT NULL,
  `backgroundCheckDate` datetime DEFAULT NULL,
  `reasonForRejection` tinyint(1) DEFAULT '0',
  `active` tinyint(1) DEFAULT '0',
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffFamilyMemberId`),
  KEY `FK_FieldStaffFamilyMember_FieldStaff_idx` (`fieldStaffGoId`),
  KEY `FK_FieldStaffFamilyMember_LookupGender` (`genderId`),
  CONSTRAINT `FK_FieldStaffFamilyMember_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffFamilyMember_LookupGender` FOREIGN KEY (`genderId`) REFERENCES `LookupGender` (`genderId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffHistory` ;
CREATE TABLE `FieldStaffHistory` (
  `fieldStaffHistoryId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `oldFieldStaffTypeId` int(11) DEFAULT NULL,
  `newFieldStffTypeId` int(11) DEFAULT NULL,
  `changeDate` datetime DEFAULT NULL,
  `seasonId` int(11) DEFAULT NULL,
  `departmentProgramId` int(11) DEFAULT NULL,
  `erdId` int(11) DEFAULT NULL,
  `rdId` int(11) DEFAULT NULL,
  `rmId` int(11) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffHistoryId`),
  KEY `FK_FieldStaffHistory_FieldStaff` (`fieldStaffGoId`),
  KEY `FK_FieldStaffHistory_Season` (`seasonId`),
  KEY `FK_FieldStaffHistory_DepartmentProgram` (`departmentProgramId`),
  CONSTRAINT `FK_FieldStaffHistory_DepartmentProgram` FOREIGN KEY (`departmentProgramId`) REFERENCES `DepartmentPrograms` (`departmentProgramId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffHistory_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffHistory_Season` FOREIGN KEY (`seasonId`) REFERENCES `Season` (`seasonId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffLeadershipSeason`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffLeadershipSeason` ;
CREATE TABLE `FieldStaffLeadershipSeason` (
  `fieldStaffLeadershipSeasonId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `seasonId` int(11) DEFAULT NULL,
  `seasonGeographyConfigurationId` int(11) DEFAULT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `modifiedBy` int(11) DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`fieldStaffLeadershipSeasonId`),
  KEY `FK_FieldStaffLeadershipSeason_FieldStaff_idx` (`fieldStaffGoId`),
  KEY `FK_FieldStaffLeadershipSeason_Season` (`seasonId`),
  KEY `FK_FieldStaffLeadershipSeason_SeasonGeographyConfiguration` (`seasonGeographyConfigurationId`),
  CONSTRAINT `FK_FieldStaffLeadershipSeason_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLeadershipSeason_Season` FOREIGN KEY (`seasonId`) REFERENCES `Season` (`seasonId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffLeadershipSeason_SeasonGeographyConfiguration` FOREIGN KEY (`seasonGeographyConfigurationId`) REFERENCES `SeasonGeographyConfiguration` (`seasonGeographyConfigurationId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- -----------------------------------------------------
-- Table `FieldStaffNoteTopics`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffNoteTopics` ;
CREATE TABLE `FieldStaffNoteTopics` (
  `fieldStaffNoteTopicsId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffNoteTopicName` varchar(50) DEFAULT NULL,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `isPublic` tinyint(1) DEFAULT '0',
  `title` varchar(50) DEFAULT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffNoteTopicsId`),
  KEY `FK_FieldStaffNoteTopics_FieldStaff` (`fieldStaffGoId`),
  CONSTRAINT `FK_FieldStaffNoteTopics_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffNote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffNote` ;
CREATE TABLE `FieldStaffNote` (
  `fieldStaffNoteId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffNoteTopicsId` int(11) DEFAULT NULL,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `fieldStaffNote` longtext,
  `hasRead` tinyint(1) DEFAULT '0',
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`fieldStaffNoteId`),
  KEY `FK_FieldStaffNote_FieldStaff_idx` (`fieldStaffGoId`),
  KEY `FK_FieldStaffNote_FieldStaffNoteTopics` (`fieldStaffNoteTopicsId`),
  CONSTRAINT `FK_FieldStaffNote_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffNote_FieldStaffNoteTopics` FOREIGN KEY (`fieldStaffNoteTopicsId`) REFERENCES `FieldStaffNoteTopics` (`fieldStaffNoteTopicsId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffParticipant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffParticipant` ;
CREATE TABLE `FieldStaffParticipant` (
  `fieldStaffParticipantId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `participantGoId` int(11) DEFAULT NULL,
  `holdRequested` tinyint(1) DEFAULT '0',
  `holdRequestedDate` datetime DEFAULT NULL,
  `holdExpirationDate` datetime DEFAULT NULL,
  `holdExpirationWarningDate` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `adjustedEndDate` datetime DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `isDirectPlacement` tinyint(1) DEFAULT '0',
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`fieldStaffParticipantId`),
  KEY `FK_FieldStaffParticipant_FieldStaff_idx` (`fieldStaffGoId`),
  KEY `FK_FieldStaffParticipant_Participants` (`participantGoId`),
  CONSTRAINT `FK_FieldStaffParticipant_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffParticipant_Participants` FOREIGN KEY (`participantGoId`) REFERENCES `Participants` (`participantGoId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffPermissions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffPermissions` ;
CREATE TABLE `FieldStaffPermissions` (
  `fieldStaffPermissionsId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffPermissionsId`),
  KEY `FK_FieldStaffPermission_FieldStaff_idx` (`fieldStaffGoId`),
  CONSTRAINT `FK_FieldStaffPermission_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffQuickStatsType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffQuickStatsType` ;
CREATE TABLE `FieldStaffQuickStatsType` (
  `fieldStaffQSTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffQSTypeName` varchar(50) DEFAULT NULL,
  `lookupDepartmentProgramId` int(3) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffQSTypeId`),
  KEY `FK_FieldStaffQSType_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_FieldStaffQSType_LookupDepartmentPrograms` FOREIGN KEY (`lookupDepartmentProgramId`) REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffQuickStatsCategoryAggregate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffQuickStatsCategories` ;
CREATE TABLE `FieldStaffQuickStatsCategories` (
  `fieldStaffQSCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffQSCategoryName` varchar(50) DEFAULT NULL,
  `fieldStaffQSTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffQSCategoryId`),
  KEY `FK_FSQuickStatsCategories_FSQuickStatsType` (`fieldStaffQSTypeId`),
  CONSTRAINT `FK_FSQuickStatsCategories_FSQuickStatsType` FOREIGN KEY (`fieldStaffQSTypeId`) REFERENCES `FieldStaffQuickStatsType` (`fieldStaffQSTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffQuickStatsCategoryAggregate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffQuickStatsCategoryAggregate` ;
CREATE TABLE `FieldStaffQuickStatsCategoryAggregate` (
  `fieldStaffQSCategoryAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffQSCategoryId` int(11) DEFAULT NULL,
  `fieldStaffQSCategoryName` varchar(50) DEFAULT NULL,
  `fieldStaffQSTypeId` int(11) DEFAULT NULL,
  `fieldStaffQSCategoryAggregate` int(11) DEFAULT NULL,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `modifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`fieldStaffQSCategoryAggregateId`),
  KEY `FK_FSQSCategoryAggregate_FSQSCategories` (`fieldStaffQSCategoryId`),
  KEY `FK_FSQSCategoryAggregate_FSQSType` (`fieldStaffQSTypeId`),
  KEY `FK_FSQSCategoryAggregate_FieldStaff` (`fieldStaffGoId`),
  KEY `FK_FSQSCategoryAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_FSQSCategoryAggregate_FSQSCategories` FOREIGN KEY (`fieldStaffQSCategoryId`) REFERENCES `FieldStaffQuickStatsCategories` (`fieldStaffQSCategoryId`),
  CONSTRAINT `FK_FSQSCategoryAggregate_FSQSType` FOREIGN KEY (`fieldStaffQSTypeId`) REFERENCES `FieldStaffQuickStatsType` (`fieldStaffQSTypeId`),
  CONSTRAINT `FK_FSQSCategoryAggregate_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`),
  CONSTRAINT `FK_FSQSCategoryAggregate_LookupDepartmentPrograms` FOREIGN KEY (`lookupDepartmentProgramId`) REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `FieldStaffQuickStatsTypeAggregate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffQuickStatsTypeAggregate` ;
CREATE TABLE `FieldStaffQuickStatsTypeAggregate` (
  `fieldStaffQSTypeAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffQSTypeId` int(11) DEFAULT NULL,
  `fieldStaffQSTypeName` varchar(50) DEFAULT NULL,
  `fieldStaffQSTypeAggregate` int(11) DEFAULT NULL,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `modifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`fieldStaffQSTypeAggregateId`),
  KEY `FK_FSQSTypeAggregate_FieldStaffQSType` (`fieldStaffQSTypeId`),
  KEY `FK_FSQSTypeAggregate_FieldStaff` (`fieldStaffGoId`),
  KEY `FK_FSQSTypeAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_FSQSTypeAggregate_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`),
  CONSTRAINT `FK_FSQSTypeAggregate_FieldStaffQSType` FOREIGN KEY (`fieldStaffQSTypeId`) REFERENCES `FieldStaffQuickStatsType` (`fieldStaffQSTypeId`),
  CONSTRAINT `FK_FSQSTypeAggregate_LookupDepartmentPrograms` FOREIGN KEY (`lookupDepartmentProgramId`) REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffReferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffReferences` ;
CREATE TABLE `FieldStaffReferences` (
  `feldStaffReferencesId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `streetAddress` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `stateId` int(11) DEFAULT NULL,
  `zipCode` varchar(25) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `relationshipToApplicant` varchar(100) DEFAULT NULL,
  `monthsKnown` int(11) DEFAULT NULL,
  `yearsKnown` int(11) DEFAULT NULL,
  `knownFamilyMethod` varchar(50) DEFAULT NULL,
  `ownChildSupervised` int(11) DEFAULT NULL,
  `ownChildReasons` longtext,
  `objectivityRating` varchar(20) DEFAULT NULL,
  `warmthRating` varchar(20) DEFAULT NULL,
  `maturityRating` varchar(20) DEFAULT NULL,
  `flexibilityRating` varchar(20) DEFAULT NULL,
  `teenRating` varchar(20) DEFAULT NULL,
  `communityRating` varchar(20) DEFAULT NULL,
  `comments` longtext,
  `dateOfReference` datetime DEFAULT NULL,
  `needsPhoneCall` tinyint(1) DEFAULT '0',
  `submitted` tinyint(1) DEFAULT '0',
  `approved` tinyint(1) DEFAULT '0',
  `rejected` tinyint(1) DEFAULT '0',
  `emailSent` tinyint(1) DEFAULT '0',
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modifiedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`feldStaffReferencesId`),
  KEY `FK_FieldStaffReferences_FieldStaff_idx` (`fieldStaffGoId`),
  KEY `FK_FieldStaffReferences_LookupUSStates` (`stateId`),
  CONSTRAINT `FK_FieldStaffReferences_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffReferences_LookupUSStates` FOREIGN KEY (`stateId`) REFERENCES `LookupUSStates` (`usStatesId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffSeason`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffSeason` ;
CREATE TABLE `FieldStaffSeason` (
  `filedStaffSeasonId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `fieldStaffSeasonStatusId` int(11) DEFAULT NULL,
  `seasonId` int(11) DEFAULT NULL,
  `departmentProgramId` int(11) DEFAULT NULL,
  `paymentScheduleId` int(11) DEFAULT NULL,
  `defaultMonitoringStipendId` int(11) DEFAULT NULL,
  `agreeToTerms` tinyint(1) DEFAULT '0',
  `signature` varchar(100) DEFAULT NULL,
  `signedDate` datetime DEFAULT NULL,
  `canRepresentGrantPax` tinyint(1) DEFAULT '0',
  `isRecruiterLC` tinyint(1) DEFAULT '0',
  `active` tinyint(1) DEFAULT '0',
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`filedStaffSeasonId`),
  KEY `FK_FiledStaffSeason_FieldStaff_idx` (`fieldStaffGoId`),
  KEY `FK_FiledStaffSeason_Season` (`seasonId`),
  KEY `FK_FiledStaffSeason_DeptProgram` (`departmentProgramId`),
  KEY `FK_FiledStaffSeason_PaymentSchedule` (`paymentScheduleId`),
  KEY `FK_FiledStaffSeason_FieldStaffStatus` (`fieldStaffSeasonStatusId`),
  CONSTRAINT `FK_FiledStaffSeason_DeptProgram` FOREIGN KEY (`departmentProgramId`) REFERENCES `DepartmentPrograms` (`departmentProgramId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FiledStaffSeason_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FiledStaffSeason_FieldStaffStatus` FOREIGN KEY (`fieldStaffSeasonStatusId`) REFERENCES `FieldStaffStatus` (`fieldStaffStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FiledStaffSeason_PaymentSchedule` FOREIGN KEY (`paymentScheduleId`) REFERENCES `PaymentSchedule` (`paymentScheduleId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FiledStaffSeason_Season` FOREIGN KEY (`seasonId`) REFERENCES `Season` (`seasonId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffSeasonDocument`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffSeasonDocument` ;
CREATE TABLE `FieldStaffSeasonDocument` (
  `fieldStaffSeasonDocumentId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffSeasonId` int(11) DEFAULT NULL,
  `documentInformationId` int(11) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `submittedToCCI` tinyint(1) DEFAULT '0',
  `approvedByCCI` tinyint(1) DEFAULT '0',
  `rejectionMessage` varchar(150) DEFAULT NULL,
  `approvedDate` datetime DEFAULT NULL,
  `submittedDate` datetime DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `modfiedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`fieldStaffSeasonDocumentId`),
  KEY `FK_FieldStaffSeasonDocument_FieldStaffSeason_idx` (`fieldStaffSeasonId`),
  KEY `FK_FieldStaffLSeasonDocument_DocumentInformation` (`documentInformationId`),
  CONSTRAINT `FK_FieldStaffLSeasonDocument_DocumentInformation` FOREIGN KEY (`documentInformationId`) REFERENCES `DocumentInformation` (`documentInformationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FieldStaffSeasonDocument_FieldStaffSeason` FOREIGN KEY (`fieldStaffSeasonId`) REFERENCES `FieldStaffSeason` (`filedStaffSeasonId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffWorkQueueType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffWorkQueueType` ;
CREATE TABLE `FieldStaffWorkQueueType` (
  `fieldStaffWQTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffWQTypeName` varchar(50) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `stateProcessId` int(11) DEFAULT NULL,
  `displayWQTypeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffWQTypeId`),
  KEY `FK_FieldStaffWQType_LookupDepartment` (`lookupDepartmentProgramId`),
  KEY `FK_FieldStaffWQType_StateProcess` (`stateProcessId`),
  CONSTRAINT `FK_FieldStaffWQType_LookupDepartment` FOREIGN KEY (`lookupDepartmentProgramId`) REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_FieldStaffWQType_StateProcess` FOREIGN KEY (`stateProcessId`) REFERENCES `StateProcess` (`stateProcessId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffWorkQueueCategories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffWorkQueueCategories` ;
CREATE TABLE `FieldStaffWorkQueueCategories` (
  `fieldStaffWQCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffWQCategoryName` varchar(50) DEFAULT NULL,
  `fieldStaffWQTypeId` int(11) NOT NULL,
  PRIMARY KEY (`fieldStaffWQCategoryId`),
  KEY `FK_FSWorkQueueCategories_FSWorkQueueType_idx` (`fieldStaffWQTypeId`),
  CONSTRAINT `FK_FSWorkQueueCategories_FSWorkQueueType` FOREIGN KEY (`fieldStaffWQTypeId`) REFERENCES `FieldStaffWorkQueueType` (`fieldStaffWQTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `FieldStaffWorkQueue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffWorkQueue` ;
CREATE TABLE `FieldStaffWorkQueue` (
  `fieldStaffWQId` int(11) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `seasonId` int(11) DEFAULT NULL,
  `departmentProgramId` int(3) DEFAULT NULL,
  `fieldStaffWQTypeId` int(11) DEFAULT NULL,
  `fieldStaffWQCategoryId` int(11) DEFAULT NULL,
  `stateTypeId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(3) DEFAULT NULL,
  `targetGoId` int(11) DEFAULT NULL,
  `targetRoleType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fieldStaffWQId`),
  KEY `FK_FieldStaffWQ_FieldStaff` (`fieldStaffGoId`),
  KEY `FK_FieldStaffWQ_Season` (`seasonId`),
  KEY `FK_FieldStaffWQ_DepartmentProgram` (`departmentProgramId`),
  KEY `FK_FieldStaffWQ_FieldStaffWQType` (`fieldStaffWQTypeId`),
  KEY `FK_FieldStaffWQ_FieldStaffWQCategory` (`fieldStaffWQCategoryId`),
  KEY `FK_FieldStaffWQ_StateType` (`stateTypeId`),
  KEY `FK_FieldStaffWQ_LookupDepartmentPRogram` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_FieldStaffWQ_DepartmentProgram` FOREIGN KEY (`departmentProgramId`) REFERENCES `DepartmentPrograms` (`departmentProgramId`),
  CONSTRAINT `FK_FieldStaffWQ_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`),
  CONSTRAINT `FK_FieldStaffWQ_FieldStaffWQCategory` FOREIGN KEY (`fieldStaffWQCategoryId`) REFERENCES `FieldStaffWorkQueueCategories` (`fieldStaffWQCategoryId`),
  CONSTRAINT `FK_FieldStaffWQ_FieldStaffWQType` FOREIGN KEY (`fieldStaffWQTypeId`) REFERENCES `FieldStaffWorkQueueType` (`fieldStaffWQTypeId`),
  CONSTRAINT `FK_FieldStaffWQ_LookupDepartmentPRogram` FOREIGN KEY (`lookupDepartmentProgramId`) REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_FieldStaffWQ_Season` FOREIGN KEY (`seasonId`) REFERENCES `Season` (`seasonId`),
  CONSTRAINT `FK_FieldStaffrWQ_StateType` FOREIGN KEY (`stateTypeId`) REFERENCES `StateTypes` (`stateTypeId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;



-- -----------------------------------------------------
-- Table `FieldStaffWorkQueueCategoryAggregate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffWorkQueueCategoryAggregate` ;
CREATE TABLE `FieldStaffWorkQueueCategoryAggregate` (
  `fieldStaffWQCategoryAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffWQCategoryId` int(11) DEFAULT NULL,
  `fieldStaffWQTypeId` int(11) DEFAULT NULL,
  `fieldStaffWQCategoryName` varchar(50) DEFAULT NULL,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `fieldStaffWQCategoryAggregate` int(11) DEFAULT NULL,
  `modifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`fieldStaffWQCategoryAggregateId`),
  KEY `FK_FSWQCategoryAggregate_FSWQCategory` (`fieldStaffWQCategoryId`),
  KEY `FK_FSWQCategoryAggregate_FSWQType` (`fieldStaffWQTypeId`),
  KEY `FK_FSWQCategoryAggregate_FieldStaff` (`fieldStaffGoId`),
  KEY `FK_FSWQCategoryAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_FSWQCategoryAggregate_FSWQCategory` FOREIGN KEY (`fieldStaffWQCategoryId`) REFERENCES `FieldStaffWorkQueueCategories` (`fieldStaffWQCategoryId`),
  CONSTRAINT `FK_FSWQCategoryAggregate_FSWorkQueueType` FOREIGN KEY (`fieldStaffWQTypeId`) REFERENCES `FieldStaffWorkQueueType` (`fieldStaffWQTypeId`),
  CONSTRAINT `FK_FSWQCategoryAggregate_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`),
  CONSTRAINT `FK_FSWQCategoryAggregate_LookupDepartmentPrograms` FOREIGN KEY (`lookupDepartmentProgramId`) REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;



-- -----------------------------------------------------
-- Table `FieldStaffWorkQueueTypeAggregate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `FieldStaffWorkQueueTypeAggregate` ;
CREATE TABLE `FieldStaffWorkQueueTypeAggregate` (
  `fieldStaffWQTypeAggregateId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldStaffWQTypeId` int(11) DEFAULT NULL,
  `fieldStaffWQTypeName` varchar(50) DEFAULT NULL,
  `fieldStaffGoId` int(11) DEFAULT NULL,
  `lookupDepartmentProgramId` int(11) DEFAULT NULL,
  `fieldStaffWQTypeAggregate` int(11) DEFAULT NULL,
  `modifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`fieldStaffWQTypeAggregateId`),
  KEY `FK_FSWQTypeAggregate_FSWorkQueueType` (`fieldStaffWQTypeId`),
  KEY `FK_FSWQTypeAggregate_FieldStaff` (`fieldStaffGoId`),
  KEY `FK_FSWQTypeAggregate_LookupDepartmentPrograms` (`lookupDepartmentProgramId`),
  CONSTRAINT `FK_FSWQTypeAggregate_FSWorkQueueType` FOREIGN KEY (`fieldStaffWQTypeId`) REFERENCES `FieldStaffWorkQueueType` (`fieldStaffWQTypeId`),
  CONSTRAINT `FK_FSWQTypeAggregate_FieldStaff` FOREIGN KEY (`fieldStaffGoId`) REFERENCES `FieldStaff` (`fieldStaffGoId`),
  CONSTRAINT `FK_FSWQTypeAggregate_LookupDepartmentPrograms` FOREIGN KEY (`lookupDepartmentProgramId`) REFERENCES `LookupDepartmentPrograms` (`lookupDepartmentProgramId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `HostFamilyStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyStatus` ;

CREATE TABLE IF NOT EXISTS `HostFamilyStatus` (
  `hostFamilyStatusId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilyStatusName` VARCHAR(50) NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `isSeasonStatus` TINYINT(1) NULL DEFAULT 0,
  `isParticipantStatus` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`hostFamilyStatusId`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `HostFamily`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamily` ;

CREATE TABLE IF NOT EXISTS `HostFamily` (
  `hostFamilyGoId` INT NOT NULL,
  `hostFamilyStatusId` INT NULL,
  `firstName` VARCHAR(50) NULL,
  `lastName` VARCHAR(50) NULL,
  `phone` VARCHAR(25) NULL,
  `mailingAddress1` VARCHAR(50) NULL,
  `mailingAddress2` VARCHAR(50) NULL,
  `mailingCity` VARCHAR(50) NULL,
  `mailingStateId` INT NULL,
  `mailingZipCode` VARCHAR(25) NULL,
  `physicalAddress1` VARCHAR(50) NULL,
  `physicalAddress2` VARCHAR(50) NULL,
  `physicalCity` VARCHAR(50) NULL,
  `physicalStateId` INT NULL,
  `physicalCountryId` INT NULL,
  `physicalZipCode` VARCHAR(25) NULL,
  `currentSeasonId` INT NULL,
  `receiveEmails` TINYINT(1) NULL DEFAULT 0,
  `isBlacklisted` TINYINT(1) NULL DEFAULT 0,
  `isDoNotContact` TINYINT(1) NULL DEFAULT 0,
  `isNotQuilified` TINYINT(1) NULL DEFAULT 0,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `liveAlone` TINYINT(1) NULL DEFAULT 0,
  `hasHomeBusiness` TINYINT(1) NULL DEFAULT 0,
  `homeBusinessType` TEXT NULL,
  `skipIntroScreen` TINYINT(1) NULL DEFAULT 0,
  `mailingAddressSameAsCurrentAddress` TINYINT(1) NULL DEFAULT 0,
  `haveAHomePhone` TINYINT(1) NULL,
  `homePhone` VARCHAR(25) NULL,
  `bestContactNumberToReach` VARCHAR(50) NULL,
  `bestWayToReachYou` VARCHAR(50) NULL,
  `emergencyContact` VARCHAR(100) NULL,
  `emergencyPhone` VARCHAR(25) NULL,
  PRIMARY KEY (`hostFamilyGoId`),
  INDEX `FK_HostFamily_HostFamilyStatus_idx` (`hostFamilyStatusId` ASC),
  CONSTRAINT `FK_HostFamily_HostFamilyStatus`
    FOREIGN KEY (`hostFamilyStatusId`)
    REFERENCES `HostFamilyStatus` (`hostFamilyStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamily_GoIdSequence`
    FOREIGN KEY (`hostFamilyGoId`)
    REFERENCES `GoIdSequence` (`goId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamily_LookupUSStates`
    FOREIGN KEY (`mailingStateId`)
    REFERENCES `LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamily_LookupUSStates1`
    FOREIGN KEY (`physicalStateId`)
    REFERENCES `LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamily_LookupCountries`
    FOREIGN KEY (`physicalCountryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamily_Season`
    FOREIGN KEY (`currentSeasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Airport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Airport` ;

CREATE TABLE IF NOT EXISTS `Airport` (
  `airportid` INT NOT NULL AUTO_INCREMENT,
  `airportName` VARCHAR(50) NOT NULL,
  `airportCity` VARCHAR(50) NOT NULL,
  `airportCode` VARCHAR(5) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `isInternational` TINYINT(1) NOT NULL,
  PRIMARY KEY (`airportid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyAirport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyAirport` ;

CREATE TABLE IF NOT EXISTS `HostFamilyAirport` (
  `hostFamilyAirportId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilyGoId` INT NULL,
  `airportId` INT NULL,
  `distanceToAirport` INT NULL,
  `createdBy` INT NULL,
  `createdOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  `active` TINYINT(1) NULL,
  PRIMARY KEY (`hostFamilyAirportId`),
  INDEX `FK_HostFamilyAirport_HostFamily_idx` (`hostFamilyGoId` ASC),
  INDEX `FK_HostFamilyAirport_Airport_idx` (`airportId` ASC),
  CONSTRAINT `FK_HostFamilyAirport_HostFamily`
    FOREIGN KEY (`hostFamilyGoId`)
    REFERENCES `HostFamily` (`hostFamilyGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyAirport_Airport`
    FOREIGN KEY (`airportId`)
    REFERENCES `Airport` (`airportid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyAnnouncement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyAnnouncement` ;

CREATE TABLE IF NOT EXISTS `HostFamilyAnnouncement` (
  `hostFamilyAnnouncementId` INT NOT NULL AUTO_INCREMENT,
  `announcement` TEXT NULL,
  `title` VARCHAR(300) NULL,
  `seasonId` INT NULL,
  `departmentProgramId` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `archived` TINYINT(1) NULL DEFAULT 0,
  `currentlyHosting` TINYINT(1) NULL DEFAULT 0,
  `unplaced` TINYINT(1) NULL DEFAULT 0,
  `createdBy` INT NULL,
  `createdOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  PRIMARY KEY (`hostFamilyAnnouncementId`),
    CONSTRAINT `FK_HostFamilyAnnouncement_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `FK_HostFamilyAnnouncement_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilySeason`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilySeason` ;

CREATE TABLE IF NOT EXISTS `HostFamilySeason` (
  `hostFamilySeasonId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilyGoId` INT NULL,
  `seasonId` INT NULL,
  `departmentProgramId` INT NULL,
  `hostFamilySeasonStatusId` INT NULL,
  `areaRepresentativeId` INT NULL,
  `regionalManagerId` INT NULL,
  `regionalDirectorId` INT NULL,
  `paymentScheduleId` INT NULL,
  `learnAboutCCIMethod` TEXT NULL,
  `localNewspaperName` TEXT NULL,
  `recommendHost` INT NULL,
  `hostRecommendationsName` VARCHAR(100) NULL,
  `hostRecommendationsPhone` VARCHAR(100) NULL,
  `agreeToTerms` TINYINT(1) NULL,
  `signature` VARCHAR(100) NULL,
  `rejectionReason` TEXT NULL,
  `hasNoChildren` TINYINT(1) NULL DEFAULT 0,
  `createdOn` DATETIME NULL,
  `createdBy` INT NULL,
  `modifiedOn` DATETIME NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `applicationSubmittedDate` DATETIME NULL,
  `applicationApprovedDate` DATETIME NULL,
  `isDoublePlacement` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`hostFamilySeasonId`),
  INDEX `FK_HostFamilySeason_HostFamily_idx` (`hostFamilyGoId` ASC),
  INDEX `FK_HostFamilySeason_HostFamilyStatus_idx` (`hostFamilySeasonStatusId` ASC),
  CONSTRAINT `FK_HostFamilySeason_HostFamily`
    FOREIGN KEY (`hostFamilyGoId`)
    REFERENCES `HostFamily` (`hostFamilyGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilySeason_HostFamilyStatus`
    FOREIGN KEY (`hostFamilySeasonStatusId`)
    REFERENCES `HostFamilyStatus` (`hostFamilyStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilySeason_Season`
    FOREIGN KEY (`seasonId`)
    REFERENCES `Season` (`seasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilySeason_DepartmentPrograms`
    FOREIGN KEY (`departmentProgramId`)
    REFERENCES `DepartmentPrograms` (`departmentProgramId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilySeason_PaymentSchedule`
    FOREIGN KEY (`paymentScheduleId`)
    REFERENCES `PaymentSchedule` (`paymentScheduleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyCommunity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyCommunity` ;

CREATE TABLE IF NOT EXISTS `HostFamilyCommunity` (
  `hostFamilyCommunityId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `population` VARCHAR(50) NULL,
  `cityWebsite` VARCHAR(50) NULL,
  `nearestCity` VARCHAR(50) NULL,
  `cityPopulation` VARCHAR(50) NULL,
  `distanceFromCity` VARCHAR(50) NULL,
  `usRegion` VARCHAR(50) NULL,
  `placesOfInterest` TEXT NULL,
  `areasToAvoid` TEXT NULL,
  `volunteeringOpportunitiesCommunity` TEXT NULL,
  `schoolTravelMethod` VARCHAR(100) NULL,
  `distanceToSchool` VARCHAR(50) NULL,
  `transportationToActivities` TINYINT(1) NULL DEFAULT 0,
  `transportationToActivitiesDetails` TEXT NULL,
  `childrenEnrolled` TEXT NULL,
  `childrenActivities` TEXT NULL,
  `contactedByCoach` TINYINT(1) NULL DEFAULT 0,
  `contactByCoachDetails` TEXT NULL,
  `parentIsTeacher` TINYINT(1) NULL DEFAULT 0,
  `parentIsTeacherDetails` TEXT NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `communityDetails` TEXT NULL,
  `involvedInVolunteerService` TINYINT(1) NULL DEFAULT 0,
  `involvedInVolunteerServiceDetails` TEXT NULL,
  PRIMARY KEY (`hostFamilyCommunityId`),
  INDEX `FK_HostFamilyCommunity_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyCommunity_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyDetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyDetail` ;

CREATE TABLE IF NOT EXISTS `HostFamilyDetail` (
  `hostFamilyDetailsId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `familyMembers` TEXT NULL,
  `adaptCircumtances` TEXT NULL,
  `houseHoldActivity` VARCHAR(25) NULL,
  `typicalWeekday` TEXT NULL,
  `typicalWeekend` TEXT NULL,
  `specialWeekend` TEXT NULL,
  `religiousAffiliation` VARCHAR(30) NULL,
  `otherReligiousDetails` VARCHAR(100) NULL,
  `religiousAttendance` VARCHAR(30) NULL,
  `preferStudentJoins` VARCHAR(50) NULL,
  `problemWithReligiousDifference` TINYINT(1) NULL,
  `agreeToServeMeals` TINYINT(1) NULL,
  `dietaryRestrictions` TINYINT(1) NULL,
  `describeDietaryRestrictions` VARCHAR(50) NULL,
  `participantFollowDiet` TINYINT(1) NULL,
  `descPaxDietaryRestrictions` VARCHAR(50) NULL,
  `comfortableHostingDiet` TINYINT(1) NULL,
  `hasAutoInsurance` VARCHAR(50) NULL,
  `familySmoker` VARCHAR(50) NULL,
  `drinkAlcohol` VARCHAR(50) NULL,
  `illness` TINYINT(1) NULL,
  `illnessDetails` TEXT NULL,
  `disability` TINYINT(1) NULL,
  `disabilityDetails` TEXT NULL,
  `crimeConviction` TINYINT(1) NULL,
  `crimeConvictionDetails` TEXT NULL,
  `childServicesContact` TINYINT(1) NULL,
  `childServicesContactDetails` TEXT NULL,
  `incomeRange` VARCHAR(30) NULL,
  `receiveGovernmentAssistance` TINYINT(1) NULL,
  `governmentAssistanceMembers` TEXT NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL,
  PRIMARY KEY (`hostFamilyDetailsId`),
  INDEX `FK_HostFamilyDetails_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyDetails_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyHome`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyHome` ;

CREATE TABLE IF NOT EXISTS `HostFamilyHome` (
  `hostFamilyHomeId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `homeType` VARCHAR(30) NULL,
  `homeLocation` VARCHAR(30) NULL,
  `homeDescription` TEXT NULL,
  `sharesBedroom` TINYINT(1) NULL DEFAULT 0,
  `sharesBedroomWith` VARCHAR(50) NULL,
  `sharingBedroomGenderId` INT NULL,
  `sharingAge` INT(3) NULL,
  `sharingOther` VARCHAR(50) NULL,
  `extraFacilities` TEXT NULL,
  `isStudentsRoomBasement` TINYINT(1) NULL DEFAULT 0,
  `exitBasement` TINYINT(1) NULL,
  `studentHomeNeighbourhood` TEXT NULL,
  `residenceSiteFunctioningBusiness` TINYINT(1) NULL,
  `specifyTypeOfBusiness` VARCHAR(25) NULL,
  `otherTypeOfBusiness` VARCHAR(50) NULL,
  `utilities` TEXT NULL,
  `specialFeaturesInHome` VARCHAR(50) NULL,
  `amenities` TEXT NULL,
  `hostingReason` TEXT NULL,
  `hopeToLearn` TEXT NULL,
  `extraActivities` TEXT NULL,
  `localCoordinatorOther` TINYINT(1) NULL,
  `localCoordinatorDetails` TEXT NULL,
  `hostedOther` TINYINT(1) NULL,
  `hostedOtherDetails` TEXT NULL,
  `isLocalCordinator` TINYINT(1) NULL,
  `localCoordinatorCCI` INT NULL,
  `createdOn` DATETIME NULL,
  `createdBy` INT NULL,
  `modifiedOn` DATETIME NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`hostFamilyHomeId`),
  INDEX `FK_HostFamilyHome_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyHome_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyHome_LookupGender`
    FOREIGN KEY (`sharingBedroomGenderId`)
    REFERENCES `LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

/*
-- -----------------------------------------------------
-- Table `HostFamilyEmployment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyEmployment` ;

CREATE TABLE IF NOT EXISTS `HostFamilyEmployment` (
  `hostFamilyEmploymentId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `hostFamilyMemberFirstName` VARCHAR(100) NULL,
  `hostFamilyMemberLastName` VARCHAR(100) NULL,
  `employerName` VARCHAR(100) NULL,
  `jobTitle` VARCHAR(100) NULL,
  `contactName` VARCHAR(100) NULL,
  `phoneNumber` VARCHAR(100) NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `residenceSiteFunctioningBusiness` TINYINT(1) NULL DEFAULT 0,
  `specifyTypeOfBusiness` VARCHAR(50) NULL,
  PRIMARY KEY (`hostFamilyEmploymentId`),
  INDEX `FK_HostFamilyEmployment_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyEmployment_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

*/
-- -----------------------------------------------------
-- Table `HostFamilyInterview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyInterview` ;

CREATE TABLE IF NOT EXISTS `HostFamilyInterview` (
  `hostFamilyInterviewId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `reasonsForHosting` TEXT NULL,
  `childrenFeelingsAboutHosting` TEXT NULL,
  `roomSharedWithDescription` TEXT NULL,
  `hasFamilyHostedBefore` INT NULL,
  `previousHostingDescription` TEXT NULL,
  `financialSituation` TEXT NULL,
  `homeCondition` TEXT NULL,
  `religiousBackground` TEXT NULL,
  `discussedPlacement` INT NULL,
  `interviewDate` DATETIME NULL,
  `interviewIsAtHome` TINYINT(1) NULL DEFAULT 0,
  `hostingCommitment` TINYINT(1) NULL,
  `isStarted` TINYINT(1) NULL,
  `isCompleted` TINYINT(1) NULL,
  `createdBy` INT NULL,
  `createdOn` DATETIME NULL,
  `modifiedBy` INT NULL,
  `modifiedOn` DATETIME NULL,
  PRIMARY KEY (`hostFamilyInterviewId`),
  INDEX `FK_HostFamilyInterview_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyInterview_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyLookup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyLookup` ;

CREATE TABLE IF NOT EXISTS `HostFamilyLookup` (
  `hostFamilyLookUpId` INT NOT NULL AUTO_INCREMENT,
  `displayValue` VARCHAR(50) NULL,
  `displayType` VARCHAR(50) NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`hostFamilyLookUpId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Relationship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Relationship` ;

CREATE TABLE IF NOT EXISTS `Relationship` (
  `relationshipId` INT NOT NULL AUTO_INCREMENT,
  `relationshipType` VARCHAR(25) NULL,
  PRIMARY KEY (`relationshipId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyMember`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyMember` ;

CREATE TABLE IF NOT EXISTS `HostFamilyMember` (
  `hostFamilyMemberId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `firstName` VARCHAR(50) NULL,
  `lastName` VARCHAR(50) NULL,
  `birthDate` DATETIME NULL,
  `genderId` INT NULL,
  `relationshipId` INT NULL,
  `interests` TEXT NULL,
  `isHostParent` TINYINT(1) NULL,
  `educationLevel` VARCHAR(50) NULL,
  `communityInvolvement` TEXT NULL,
  `employed` TINYINT(1) NULL DEFAULT 0,
  `jobTitle` VARCHAR(100) NULL,
  `contactName` VARCHAR(100) NULL,
  `phone` VARCHAR(100) NULL,
  `isFamilyChild` TINYINT(1) NULL,
  `livingAtHome` TINYINT(1) NULL DEFAULT 0,
  `isSingleAdult` TINYINT(1) NULL,
  `residencyTime` VARCHAR(20) NULL,
  `backGroundCheckPassed` TINYINT(1) NULL DEFAULT 0,
  `backGroundCheckDate` DATETIME NULL,
  `backGroundCheckSubmitted` TINYINT(1) NULL DEFAULT 0,
  `backGroundCheckReportUrl` VARCHAR(200) NULL,
  `reasonForRejection` TINYINT(1) NULL DEFAULT 0,
  `createdOn` DATETIME NULL,
  `createdBy` INT NULL,
  `modifiedOn` DATETIME NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`hostFamilyMemberId`),
  INDEX `FK_HostFamilyMember_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  INDEX `FK_HostFamilyMember_Relationship_idx` (`relationshipId` ASC),
  CONSTRAINT `FK_HostFamilyMember_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyMember_Relationship`
    FOREIGN KEY (`relationshipId`)
    REFERENCES `Relationship` (`relationshipId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyMember_LookupGender`
    FOREIGN KEY (`genderId`)
    REFERENCES `LookupGender` (`genderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyNote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyNote` ;

CREATE TABLE IF NOT EXISTS `HostFamilyNote` (
  `hostFamilyNoteId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilyGoId` INT NULL,
  `hostFamilyNoteTopicsId` INT NULL,
  `note` TEXT NULL,
  `hasRead` TINYINT(1) NULL,
  `createdBy` INT NULL,
  `createdOn` DATETIME NULL,
  `modifiedBy` INT NULL,
  `modifiedOn` DATETIME NULL,
  PRIMARY KEY (`hostFamilyNoteId`),
  INDEX `FK_HostFamilyNote_HostFamily_idx` (`hostFamilyGoId` ASC),
  CONSTRAINT `FK_HostFamilyNote_HostFamily`
    FOREIGN KEY (`hostFamilyGoId`)
    REFERENCES `HostFamily` (`hostFamilyGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_HostFamilyNote_HostFamilyNoteTopics
	FOREIGN KEY (hostFamilyNoteTopicsId)
	REFERENCES HostFamilyNoteTopics (hostFamilyNoteTopicsId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MoveReason`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MoveReason` ;

CREATE TABLE IF NOT EXISTS `MoveReason` (
  `moveReasonId` INT NOT NULL AUTO_INCREMENT,
  `moveReasonName` VARCHAR(100) NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `changeHomeCode` VARCHAR(2) NULL,
  `reasonDescription` VARCHAR(500) NULL,
  PRIMARY KEY (`moveReasonId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyParticipant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyParticipant` ;

CREATE TABLE IF NOT EXISTS `HostFamilyParticipant` (
  `hostFamilyParticipantId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `participantGoId` INT NULL,
  `hostFamilyInterested` INT NULL,
  `hostFamilyScore` INT NULL,
  `hostFamilyParticipantStatusId` INT NULL,
  `showToParticipant` TINYINT(1) NULL DEFAULT 0,
  `uploadPermissionFormLater` TINYINT(1) NULL DEFAULT 0,
  `uploadPermissionLaterReason` TEXT NULL,
  `usHighSchoolId` INT NULL,
  `permissionFormFileName` VARCHAR(100) NULL,
  `permissionFormFilePath` VARCHAR(100) NULL,
  `permissionFormName` VARCHAR(50) NULL,
  `participantPayableId` INT NULL,
  `approvedDate` DATETIME NULL,
  `movedDate` DATETIME NULL,
  `moveReasonId` INT NULL,
  `moveExplanation` TEXT NULL,
  `rejectionMessage` TEXT NULL,
  `movingParticipant` TINYINT(1) NULL DEFAULT 0,
  `createdOn` DATETIME NULL,
  `createdBy` INT NULL,
  `modifiedOn` DATETIME NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `preferredDepartureDate` DATETIME NULL,
  `preferredArrivalDate` DATETIME NULL,
  `isWelcomeFamily` TINYINT(1) NULL DEFAULT 0,
  `isWelcomeFamilyChangedDate` DATETIME NULL,
  `hidePlacement` TINYINT(1) NULL DEFAULT 0,
  `uploadPermissionLaterExpectedDate` DATETIME NULL,
  `partnerApprovalDate` DATETIME NULL,
  `partnerRejectionMessage` TEXT NULL,
  `secondVisitLCId` INT NULL,
  `isRecruiterLCLeadId` INT NULL,
  `isRecruiterLCLead` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`hostFamilyParticipantId`),
  INDEX `FK_HostFamilyParticipant_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  INDEX `FK_HostFamilyParticipant_HFPaxStatus_idx` (`hostFamilyParticipantStatusId` ASC),
  INDEX `FK_HostFamilyParticipant_MoveReason_idx` (`moveReasonId` ASC),
  CONSTRAINT `FK_HostFamilyParticipant_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyParticipant_HFPaxStatus`
    FOREIGN KEY (`hostFamilyParticipantStatusId`)
    REFERENCES `HostFamilyStatus` (`hostFamilyStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyParticipant_MoveReason`
    FOREIGN KEY (`moveReasonId`)
    REFERENCES `MoveReason` (`moveReasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyParticipant_Participants`
    FOREIGN KEY (`participantGoId`)
    REFERENCES `Participants` (`participantGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyParticipant_USSchool`
    FOREIGN KEY (`usHighSchoolId`)
    REFERENCES `USSchool` (`usSchoolId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
 /*,CONSTRAINT `FK_HostFamilyParticipant_ParticipantPayable`
    FOREIGN KEY (`participantPayableId`)
    REFERENCES `ParticipantPayable` (`participantPayableId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION*/)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyParticipantHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyParticipantHistory` ;

CREATE TABLE IF NOT EXISTS `HostFamilyParticipantHistory` (
  `hostFamilyParticipantHistoryId` INT NOT NULL AUTO_INCREMENT,
  `participantGoId` INT NULL,
  `hostFamilySeasonId` INT NULL,
  `hostFamilyGoId` INT NULL,
  `hostFamilyName` VARCHAR(50) NULL,
  `localCoordinatorId` INT NULL,
  `localCoordinatorName` VARCHAR(50) NULL,
  `beginDate` DATETIME NULL,
  `endDate` DATETIME NULL,
  `createdOn` DATETIME NULL,
  `createdBy` INT NULL,
  `modifiedOn` DATETIME NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`hostFamilyParticipantHistoryId`),
  INDEX `FK_HostFamilyParticipantHistory_HostFamily_idx` (`hostFamilyGoId` ASC),
  INDEX `FK_HostFamilyParticipantHistory_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyParticipantHistory_HostFamily`
    FOREIGN KEY (`hostFamilyGoId`)
    REFERENCES `HostFamily` (`hostFamilyGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyParticipantHistory_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyParticipantHistory_Participants`
    FOREIGN KEY (`participantGoId`)
    REFERENCES `Participants` (`participantGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyParticipantHistory_FieldStaff`
    FOREIGN KEY (`localCoordinatorId`)
    REFERENCES `FieldStaff` (`fieldStaffGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyReference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyReference` ;

CREATE TABLE IF NOT EXISTS `HostFamilyReference` (
  `hostFamilyReferenceId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `dateOfReference` DATETIME NULL,
  `firstName` VARCHAR(100) NULL,
  `lastName` VARCHAR(100) NULL,
  `address` VARCHAR(100) NULL,
  `city` VARCHAR(50) NULL,
  `usStatesId` INT NULL,
  `zipCode` VARCHAR(30) NULL,
  `phone` VARCHAR(30) NULL,
  `relationship` VARCHAR(50) NULL,
  `personNotRelatedToBlood` TINYINT(1) NULL DEFAULT 0,
  `interestVariety` VARCHAR(30) NULL,
  `communityInvolvement` VARCHAR(30) NULL,
  `lengthKnownQuantity` VARCHAR(50) NULL,
  `lengthKnown` VARCHAR(50) NULL,
  `knownFamilyMethod` VARCHAR(30) NULL,
  `allowOwnChildStay` INT NULL,
  `ownChildStayReasons` TEXT NULL,
  `positiveExperiences` VARCHAR(30) NULL,
  `stability` VARCHAR(30) NULL,
  `closeness` VARCHAR(30) NULL,
  `flexibility` VARCHAR(30) NULL,
  `internationalAwareness` VARCHAR(50) NULL,
  `comments` TEXT NULL,
  `completionMethod` VARCHAR(50) NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `visitFrequency` VARCHAR(100) NULL,
  `lastVisit` VARCHAR(100) NULL,
  `additionalSupport` INT NULL,
  `supportExplanation` TEXT NULL,
  `communityTies` TEXT NULL,
  PRIMARY KEY (`hostFamilyReferenceId`),
  INDEX `FK_HostFamilyReference_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyReference_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyReference_LookupUSStates`
    FOREIGN KEY (`usStatesId`)
    REFERENCES `LookupUSStates` (`usStatesId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilySeasonNoteTopics`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilySeasonNoteTopics` ;

CREATE TABLE IF NOT EXISTS `HostFamilySeasonNoteTopics` (
  `hostFamilySeasonNoteTopicsId` INT NOT NULL AUTO_INCREMENT,
  `topicName` VARCHAR(50) NULL,
  `hostFamilySeasonId` INT NULL,
  `isPublic` TINYINT(1) NULL DEFAULT 0,
  `title` VARCHAR(50) NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  PRIMARY KEY (`hostFamilySeasonNoteTopicsId`),
  INDEX `FK_HostFamilySeasonNoteTopic_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilySeasonNoteTopic_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilySeasonNote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilySeasonNote` ;

CREATE TABLE IF NOT EXISTS `HostFamilySeasonNote` (
  `hostFamilySeasonNoteId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `hostFamilySeasonNoteTopicsId` INT NULL,
  `note` TEXT NULL,
  `hasRead` TINYINT(1) NULL,
  `createdBy` INT NULL,
  `createdOn` DATETIME NULL,
  `modifiedBy` INT NULL,
  `modifiedOn` DATETIME NULL,
  PRIMARY KEY (`hostFamilySeasonNoteId`),
  INDEX `FK_HostFamilySeasonNote_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  INDEX `FK_HostFamilySeasonNote_HostFamilySeasonNoteTopics_idx` (`hostFamilySeasonNoteTopicsId` ASC),
  CONSTRAINT `FK_HostFamilySeasonNote_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilySeasonNote_HostFamilySeasonNoteTopics`
    FOREIGN KEY (`hostFamilySeasonNoteTopicsId`)
    REFERENCES `HostFamilySeasonNoteTopics` (`hostFamilySeasonNoteTopicsId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyDocument`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyDocument` ;

CREATE TABLE IF NOT EXISTS `HostFamilyDocument` (
  `hostFamilyDocumentId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `documentInformationId` INT NULL,
  `description` VARCHAR(1000) NULL,
  `submittedToCCI` TINYINT(1) NULL,
  `approvedByCCI` TINYINT(1) NULL,
  `rejectedByCCI` TINYINT(1) NULL,
  PRIMARY KEY (`hostFamilyDocumentId`),
  INDEX `FK_HostFamilyDocument_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyDocument_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyDocument_DocumentInformation`
    FOREIGN KEY (`documentInformationId`)
    REFERENCES `DocumentInformation` (`documentInformationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyInquiry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyInquiry` ;

CREATE TABLE IF NOT EXISTS `HostFamilyInquiry` (
  `HostFamilyInquiryId` INT NOT NULL,
  `hostFamilyGoId` INT NULL,
  `firstName` VARCHAR(50) NULL,
  `lastName` VARCHAR(50) NULL,
  `address` VARCHAR(50) NULL,
  `currentCity` VARCHAR(50) NULL,
  `currentState` VARCHAR(50) NULL,
  `zipCode` VARCHAR(30) NULL,
  `emailAddress` VARCHAR(45) NULL,
  `preferredPhoneNumber` VARCHAR(20) NULL,
  `optionalPhoneNumber` VARCHAR(20) NULL,
  `heardAboutCCIThrough` VARCHAR(50) NULL,
  `friendRelative` VARCHAR(50) NULL,
  `cciHostFamily` VARCHAR(50) NULL,
  `submittedOn` DATETIME NULL,
  `18YearsOrOlder` TINYINT(1) NULL,
  `nearestLargeCityOrMetroArea` VARCHAR(50) NULL,
  `localPublicHighSchool` VARCHAR(50) NULL,
  `cityTownHighSchoolLocated` VARCHAR(50) NULL,
  `interestedStudentFrom` VARCHAR(50) NULL,
  `requestedLocalCoordinator` VARCHAR(50) NULL,
  `previousHostingExperience` VARCHAR(50) NULL,
  `followUpDate` DATETIME NULL,
  `leadStatusId` INT NULL,
  `website` VARCHAR(50) NULL,
  `cciComments` TEXT NULL,
  PRIMARY KEY (`HostFamilyInquiryId`),
  INDEX `FK_HostFamilyInquiry_HostFamilyStatus_idx` (`leadStatusId` ASC),
  CONSTRAINT `FK_HostFamilyInquiry_HostFamily`
    FOREIGN KEY (`hostFamilyGoId`)
    REFERENCES `HostFamily` (`hostFamilyGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyInquiry_HostFamilyStatus`
    FOREIGN KEY (`leadStatusId`)
    REFERENCES `HostFamilyStatus` (`hostFamilyStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyPhotosType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyPhotosType` ;

CREATE TABLE IF NOT EXISTS `HostFamilyPhotosType` (
  `hostFamilyPhotoTypeId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilyPhotoTypeName` VARCHAR(50) NULL,
  PRIMARY KEY (`hostFamilyPhotoTypeId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyPhotos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyPhotos` ;

CREATE TABLE IF NOT EXISTS `HostFamilyPhotos` (
  `hostFamilyPhotoId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `fileName` VARCHAR(50) NULL,
  `filePath` VARCHAR(50) NULL,
  `photoName` VARCHAR(50) NULL,
  `hostFamilyPhotoTypeId` INT NOT NULL,
  `photoTitle` VARCHAR(50) NULL,
  `description` VARCHAR(100) NULL,
  `createdOn` DATETIME NULL,
  `createdBy` INT NULL,
  `modifiedOn` DATETIME NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `submittedToCCI` TINYINT(1) NULL DEFAULT 0,
  `approvedByCCI` TINYINT(1) NULL DEFAULT 0,
  `rejectedByCCI` TINYINT(1) NULL DEFAULT 0,
  `default` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`hostFamilyPhotoId`),
  INDEX `FK_HostFamilyPhotos_HostFamilyPhotosType_idx` (`hostFamilyPhotoTypeId` ASC),
  CONSTRAINT `FK_HostFamilyPhotos_HostFamilyPhotosType`
    FOREIGN KEY (`hostFamilyPhotoTypeId`)
    REFERENCES `HostFamilyPhotosType` (`hostFamilyPhotoTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HostFamilyPhotos_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyPet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyPet` ;

CREATE TABLE IF NOT EXISTS `HostFamilyPet` (
  `hostFamilyPetId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `animalType` VARCHAR(25) NULL,
  `number` INT NULL,
  `additionalInformation` VARCHAR(50) NULL,
  `isIndoor` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`hostFamilyPetId`),
  INDEX `FK_HostFamilyPet_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyPet_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyPotentialReference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyPotentialReference` ;

CREATE TABLE IF NOT EXISTS `HostFamilyPotentialReference` (
  `hostFamilyPotentialReference` INT NOT NULL AUTO_INCREMENT,
  `hostFamilyGoId` INT NULL,
  `referenceFirstName` VARCHAR(50) NULL,
  `referenceLastName` VARCHAR(50) NULL,
  `referenceReason` VARCHAR(150) NULL,
  `referencePhone` VARCHAR(30) NULL,
  `referenceEmail` VARCHAR(50) NULL,
  `referenceMailingAddress` VARCHAR(50) NULL,
  `referenceStreetAddress` VARCHAR(50) NULL,
  `referenceCity` VARCHAR(20) NULL,
  `referenceStateOrRegion` VARCHAR(50) NULL,
  `referenceZipCode` VARCHAR(10) NULL,
  `referenceCountryId` INT NULL,
  `firstName` VARCHAR(50) NULL,
  `lastName` VARCHAR(50) NULL,
  `userTypeId` INT NULL,
  `email` VARCHAR(50) NULL,
  `mailingAddress` VARCHAR(50) NULL,
  `streetAddress` VARCHAR(50) NULL,
  `city` VARCHAR(20) NULL,
  `stateOrRegion` VARCHAR(50) NULL,
  `zipCode` VARCHAR(10) NULL,
  `counrtyId` INT NULL,
  `lcName` VARCHAR(50) NULL,
  `cretatedOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `createdBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedBy` INT NULL,
  PRIMARY KEY (`hostFamilyPotentialReference`),
  INDEX `FK_HFPotentialReference_HostFamily_idx` (`hostFamilyGoId` ASC),
  CONSTRAINT `FK_HFPotentialReference_HostFamily`
    FOREIGN KEY (`hostFamilyGoId`)
    REFERENCES `HostFamily` (`hostFamilyGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HFPotentialReference_LookupCountries`
    FOREIGN KEY (`referenceCountryId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HFPotentialReference_LookupCountries1`
    FOREIGN KEY (`counrtyId`)
    REFERENCES `LookupCountries` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HFPotentialReference_UserType`
    FOREIGN KEY (`userTypeId`)
    REFERENCES `UserType` (`userTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyContactHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyContactHistory` ;

CREATE TABLE IF NOT EXISTS `HostFamilyContactHistory` (
  `hostFamilyContactHistoryId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `praposedContactDate` DATETIME NULL,
  `contactMode` VARCHAR(25) NULL,
  `agenda` VARCHAR(50) NULL,
  `praposedTime` VARCHAR(50) NULL,
  `duration` VARCHAR(50) NULL,
  `notes` VARCHAR(100) NULL,
  `isDone` TINYINT(1) NULL,
  PRIMARY KEY (`hostFamilyContactHistoryId`),
  INDEX `FK_HostFamilyContactHistory_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyContactHistory_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HostFamilyEvaluation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyEvaluation` ;

CREATE TABLE IF NOT EXISTS `HostFamilyEvaluation` (
  `hostFamilyEvaluationId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `participantGoId` INT NULL,
  `relationship` VARCHAR(50) NULL,
  `relationshipComments` VARCHAR(100) NULL,
  `relationshipScale` INT NULL,
  `communicationEffort` INT NULL,
  `effortToAdopt` INT NULL,
  `interestInFamily` INT NULL,
  `effortForHygiene` INT NULL,
  `additionalComments` VARCHAR(100) NULL,
  `familyActivityParticipation` VARCHAR(50) NULL,
  `behaviorProblems` TINYINT(1) NULL,
  `academicProblems` TINYINT(1) NULL,
  `contactWithFieldStaff` INT NULL,
  `problems` VARCHAR(1000) NULL,
  `evaluationMonth` VARCHAR(15) NULL,
  `submittedToCCI` TINYINT(1) NULL,
  `approvedByCCI` TINYINT(1) NULL,
  `showToPartner` TINYINT(1) NULL,
  `submittedDate` DATETIME NULL,
  `approvedDate` DATETIME NULL,
  `fieldStaffGoId` INT NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL,
  PRIMARY KEY (`hostFamilyEvaluationId`),
  INDEX `FK_HostFamilyEvaluation_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyEvaluation_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `FK_HostFamilyEvaluation_Participants`
    FOREIGN KEY (`participantGoId`)
    REFERENCES `Participants` (`participantGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
     CONSTRAINT `FK_HostFamilyEvaluation_FieldStaff`
    FOREIGN KEY (`fieldStaffGoId`)
    REFERENCES `FieldStaff` (`fieldStaffGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `HostFamilyFinalEvaluation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyFinalEvaluation` ;

CREATE TABLE IF NOT EXISTS `HostFamilyFinalEvaluation` (
  `hostFamilyFinalEvaluationId` INT NOT NULL AUTO_INCREMENT,
  `hostFamilySeasonId` INT NULL,
  `participantGoId` INT NULL,
  `overallExperience` VARCHAR(10) NULL,
  `participantFlexibility` VARCHAR(10) NULL,
  `participantCompatibility` VARCHAR(10) NULL,
  `fieldStaffHelpfulness` VARCHAR(10) NULL,
  `materialQuality` VARCHAR(10) NULL,
  `insuranceQuality` VARCHAR(10) NULL,
  `didChores` TINYINT(1) NULL,
  `followedRules` TINYINT(1) NULL,
  `followedHygiene` TINYINT(1) NULL,
  `didWellAcademically` TINYINT(1) NULL,
  `didExtraCurriculars` TINYINT(1) NULL,
  `relatedToUSStudents` TINYINT(1) NULL,
  `adequateOrientation` TINYINT(1) NULL,
  `problem` VARCHAR(1000) NULL,
  `problemHandledBy` VARCHAR(100) NULL,
  `overallExpectations` VARCHAR(1000) NULL,
  `adviceForHostFamilies` VARCHAR(1000) NULL,
  `recommendCCI` TINYINT(1) NULL,
  `submittedToCCI` TINYINT(1) NULL,
  `approvedByCCI` TINYINT(1) NULL,
  `showToPartner` TINYINT(1) NULL,
  `submittedDate` DATETIME NULL,
  `approvedDate` DATETIME NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  `active` TINYINT(1) NULL,
  PRIMARY KEY (`hostFamilyFinalEvaluationId`),
  INDEX `FK_HostFamilyFinalEvaluation_HostFamilySeason_idx` (`hostFamilySeasonId` ASC),
  CONSTRAINT `FK_HostFamilyFinalEvaluation_HostFamilySeason`
    FOREIGN KEY (`hostFamilySeasonId`)
    REFERENCES `HostFamilySeason` (`hostFamilySeasonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `FK_HostFamilyFinalEvaluation_Participants`
    FOREIGN KEY (`participantGoId`)
    REFERENCES `Participants` (`participantGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;


-- -----------------------------------------------------
-- Table `HostFamilyNoteTopics`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HostFamilyNoteTopics` ;

CREATE TABLE IF NOT EXISTS `HostFamilyNoteTopics` (
  `hostFamilyNoteTopicsId` INT NOT NULL AUTO_INCREMENT,
  `topicName` VARCHAR(50) NULL,
  `hostFamilygoId` INT NULL,
  `isPublic` TINYINT(1) NULL,
  `title` VARCHAR(50) NULL,
  `createdOn` TIMESTAMP NULL,
  `createdBy` INT NULL,
  `modifiedOn` TIMESTAMP NULL,
  `modifiedBy` INT NULL,
  PRIMARY KEY (`hostFamilyNoteTopicsId`),
  INDEX `FK_HostFamilyNoteTopics_HostFamily_idx` (`hostFamilygoId` ASC),
  CONSTRAINT `FK_HostFamilyNoteTopics_HostFamily`
    FOREIGN KEY (`hostFamilygoId`)
    REFERENCES `HostFamily` (`hostFamilyGoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


