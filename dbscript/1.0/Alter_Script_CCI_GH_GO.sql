
--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for adding columns passwordSalt to Login Table and isVisibleToSeason to LookupDepartments table on 6th August 2015---------------
--------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE `cci_gh_go`.`Login` 
CHANGE `password` `password` VARCHAR(200) NOT NULL, 
ADD COLUMN `passwordSalt` VARCHAR(200) NOT NULL AFTER `password`;

ALTER TABLE `cci_gh_go`.`LookupDepartments` 
ADD COLUMN `isVisibleToSeason` TINYINT(1) DEFAULT 0 AFTER `acronym`;


--------------------------------------------------------------------------------------------------------------------------------------------------
--- Alter script for adding id column and changing FK relationship for CCIStaffUsers table on 10th August 2015---------------
--------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE `cci_gh_go`.`CCIStaffUsers`   
  ADD COLUMN `id` INT(11) NOT NULL AUTO_INCREMENT FIRST,
  CHANGE `cciStaffUserId` `cciStaffUserId` INT(11) NOT NULL, 
  DROP FOREIGN KEY `FK_CCIStaffUsers_login`,
  DROP COLUMN `loginId`,
  DROP PRIMARY KEY,
  ADD PRIMARY KEY (`id`),
  ADD  UNIQUE INDEX `IND_cciStaffUserId` (`cciStaffUserId`),
  ADD CONSTRAINT `FK_CCIStaffUsers_GoIdSequence` FOREIGN KEY (`cciStaffUserId`) REFERENCES `cci_gh_go`.`GoIdSequence`(`goId`) ON UPDATE NO ACTION;


