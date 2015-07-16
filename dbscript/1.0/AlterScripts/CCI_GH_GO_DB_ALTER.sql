ALTER TABLE `cci_gh_go`.`Region`
 ADD `createdOn` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 ADD `createdBy` INT(11) NOT NULL,
 ADD `modifiedOn` TIMESTAMP NOT NULL DEFAULT   '0000-00-00 00:00:00',
 ADD `modifiedBy` INT(11) NOT NULL;
 
UPDATE `cci_gh_go`.`Region`
SET createdOn = CURRENT_TIMESTAMP,
    createdBy = 1,
    modifiedOn = CURRENT_TIMESTAMP,
    modifiedBy = 1;