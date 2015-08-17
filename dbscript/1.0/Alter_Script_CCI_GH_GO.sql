
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


