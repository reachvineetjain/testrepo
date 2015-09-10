
--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for adding columns passwordSalt to Login Table and isVisibleToSeason to LookupDepartments table on 6th August 2015---------------
--------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE `cci_gh_go`.`Login` 
  CHANGE `password` `password` VARCHAR(200) NOT NULL, 
  ADD COLUMN `passwordSalt` VARCHAR(200) NOT NULL AFTER `password`;

ALTER TABLE `cci_gh_go`.`LookupDepartments` 
  ADD COLUMN `isVisibleToSeason` TINYINT(1) DEFAULT 0 AFTER `acronym`;


--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for adding PK for cciStaffUserId and dropping previously created id column and UNIQUE INDEX on cciStaffUserId on 12th Aug 2015---
--------------------------------------------------------------------------------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;
ALTER TABLE `cci_gh_go`.`CCIStaffUsers`   
  CHANGE `cciStaffUserId` `cciStaffUserId` INT(11), 
  DROP FOREIGN KEY `FK_CCIStaffUsers_login`,
  DROP COLUMN `loginId`,
  ADD CONSTRAINT `FK_CCIStaffUsers_GoIdSequence` FOREIGN KEY (`cciStaffUserId`) REFERENCES `cci_gh_go`.`GoIdSequence`(`goId`) ON UPDATE NO ACTION;

SET FOREIGN_KEY_CHECKS = 1;


--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for adding Unique Key to sesaonName column in Season table on 20th Aug 2015------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE `cci_gh_go`.`Season` ADD UNIQUE INDEX IND_seasonName(seasonName);

--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for adding email column and changing passwordSalt column to keyValue in Login table on 24th Aug 2015 ----------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE `Login` CHANGE `passwordSalt` `keyValue` VARCHAR(200) NOT NULL; 

ALTER TABLE `Login` ADD COLUMN `email` VARCHAR(50) NOT NULL AFTER `keyValue`;


--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for changing departmentProgram column to lookupDepartmentProgram in CCIStaffUserProgram table on 8th Sep 2015 -------------------
--------------------------------------------------------------------------------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS =0;

ALTER TABLE `cci_gh_go`.`CCIStaffUserProgram`    
  CHANGE `departmentProgramId` `lookupDepartmentProgramId` INT(11) NOT NULL  AFTER `cciStaffUserId`, 
  DROP INDEX `FK_CCIStaffUserProgram_DepartmentPrograms`,
  DROP FOREIGN KEY `FK_CCIStaffUserProgram_DepartmentPrograms`,
  ADD CONSTRAINT `FK_CCIStaffUserProgram_LookupDepartmentPrograms` FOREIGN KEY (`lookupDepartmentProgramId`) REFERENCES `cci_gh_go`.`LookupDepartmentPrograms`(`lookupDepartmentProgramId`);
 
 SET FOREIGN_KEY_CHECKS =1;
 
 
---------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for changing length of seasonName and programName columns in all Season related tables  as per BUG # 171,192,197 on 8th Sep 2015--
---------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE Season MODIFY seasonName VARCHAR(35);
ALTER TABLE Season MODIFY seasonFullName VARCHAR(35);
ALTER TABLE SeasonCAPDetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonF1Details MODIFY programName VARCHAR(55);
ALTER TABLE SeasonHSADetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonIHPDetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonJ1Details MODIFY programName VARCHAR(55);
ALTER TABLE SeasonLSDetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonTADetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonVADetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonWADetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonWnTSpringDetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonWnTSummerDetails MODIFY programName VARCHAR(55);
ALTER TABLE SeasonWnTWinterDetails MODIFY programName VARCHAR(55);