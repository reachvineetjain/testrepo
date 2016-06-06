
-- USE cci_gh_go_qa;

SET FOREIGN_KEY_CHECKS= 0;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Truncating Season Module tables.
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
TRUNCATE TABLE `SeasonWPConfiguration` ;
TRUNCATE TABLE `SeasonWnTSpringDetails` ;
TRUNCATE TABLE `SeasonWnTWinterDetails` ;
TRUNCATE TABLE `SeasonWnTSummerDetails` ;
TRUNCATE TABLE `SeasonCAPDetails` ;
TRUNCATE TABLE `SeasonWPAllocation` ;



/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Season Migration
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
INSERT INTO `Season` (seasonId,seasonName,seasonFullName,departmentId,createdBy,modifiedBy) 
VALUES (8,'WP 2009','WP 2009',2,1,1),
		(9,'WP 2010','WP 2010',2,1,1),
		(10,'WP 2011','WP 2011',2,1,1),
		(11,'WP 2012','WP 2012',2,1,1),
		(12,'WP 2013','WP 2013',2,1,1),
		(13,'WP 2014','WP 2014',2,1,1),
		(14,'WP 2015','WP 2015',2,1,1),
		(15,'WP 2016','WP 2016',2,1,1);

UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(7))
WHERE seasonId= 8;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(33,34,35,59) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 9;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(45,46,51,58) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 10;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(57,62,66,68) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 11;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(71,72,73,77) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 12;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(79,80,86,87) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 13;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(90,91,93,95) ORDER BY `SeasonStatusID` ASC LIMIT 1)
WHERE seasonId= 14;
UPDATE `Season`
SET `seasonStatusId` = (SELECT `SeasonStatusID` FROM `cci_go`.`Season` WHERE `SeasonID` IN(96,97,98) ORDER BY `SeasonStatusID` ASC LIMIT 1) 
WHERE seasonId= 15;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          SeasonWPConfiguration Migration
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
INSERT INTO `SeasonWPConfiguration` (seasonId,createdBy,modifiedBy) 
VALUES  (8,1,1),
		(9,1,1),
		(10,1,1),
		(11,1,1),
		(12,1,1),
		(13,1,1),
		(14,1,1),
		(15,1,1);

UPDATE `SeasonWPConfiguration` 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (7)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (7))
WHERE seasonId = 8;
UPDATE `SeasonWPConfiguration` 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (33,34,35,59)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (33,34,35,59))
WHERE seasonId = 9;
UPDATE `SeasonWPConfiguration` 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (45,46,51,58)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (45,46,51,58))
WHERE seasonId = 10;
UPDATE `SeasonWPConfiguration` 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (57,62,66,68)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (57,62,66,68))
WHERE seasonId = 11;
UPDATE `SeasonWPConfiguration`
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (71,72,73,77)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (71,72,73,77))
WHERE seasonId = 12;
UPDATE `SeasonWPConfiguration` 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (79,80,86,87)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (79,80,86,87))
WHERE seasonId = 13;
UPDATE `SeasonWPConfiguration`
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (90,91,93,95)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (90,91,93,95))
WHERE seasonId = 14;
UPDATE `SeasonWPConfiguration` 
SET seasonStartDate = (SELECT MIN(StartDate) FROM `cci_go`.`Season` WHERE SeasonID IN (96,97,98)),
	seasonEndDate = (SELECT MAX(EndDate) FROM `cci_go`.`Season` WHERE SeasonID IN (96,97,98))
WHERE seasonId = 15;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          SeasonWnTSpringDetails` Migration
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
-- Remove NOT NULL for seasonId in SeasonWnTSpringDetails table.


INSERT INTO `SeasonWnTSpringDetails`(`seasonWnTSpringDetailsId`,`seasonId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES (1,8,'W&T - Spring - 2009','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',0,0,2,'2015-07-24 08:11:47',1,'2015-07-24 08:11:47',1);

INSERT INTO `SeasonWnTSpringDetails`(`seasonWnTSpringDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 34;

UPDATE `SeasonWnTSpringDetails` 
SET `seasonId` = 9
WHERE `SeasonWnTSpringDetailsId`= 34;

INSERT INTO `SeasonWnTSpringDetails`(`seasonWnTSpringDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 46;

UPDATE `SeasonWnTSpringDetails` 
SET `seasonId` = 10
WHERE `SeasonWnTSpringDetailsId`= 46;

INSERT INTO `SeasonWnTSpringDetails`(`seasonWnTSpringDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 62;

UPDATE `SeasonWnTSpringDetails` 
SET `seasonId` = 11
WHERE `SeasonWnTSpringDetailsId`= 62;

INSERT INTO `SeasonWnTSpringDetails`(`seasonWnTSpringDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 72;

UPDATE `SeasonWnTSpringDetails` 
SET `seasonId` = 12
WHERE `SeasonWnTSpringDetailsId`= 72;

INSERT INTO `SeasonWnTSpringDetails`(`seasonWnTSpringDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 80;

UPDATE `SeasonWnTSpringDetails` 
SET `seasonId` = 13
WHERE `SeasonWnTSpringDetailsId`= 80;

INSERT INTO `SeasonWnTSpringDetails`(`seasonWnTSpringDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 91;

UPDATE `SeasonWnTSpringDetails` 
SET `seasonId` = 14
WHERE `SeasonWnTSpringDetailsId`= 91;

INSERT INTO `SeasonWnTSpringDetails`(`seasonWnTSpringDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 97;

UPDATE `SeasonWnTSpringDetails` 
SET `seasonId` = 15
WHERE `SeasonWnTSpringDetailsId`= 97;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          SeasonWnTWinterDetails Migration
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
-- Remove NOT NULL for seasonId in SeasonWnTWinterDetails table.



INSERT INTO `SeasonWnTWinterDetails`(`seasonWnTWinterDetailsId`,`seasonId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES (1,8,'W&T - Winter - 2009','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',0,0,2,'2015-07-24 08:18:41',1,'2015-07-24 08:18:41',1);


INSERT INTO `SeasonWnTWinterDetails`(`seasonWnTWinterDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 33;

UPDATE `SeasonWnTWinterDetails` 
SET `seasonId` = 9
WHERE `SeasonWnTWinterDetailsId`= 33;


INSERT INTO `SeasonWnTWinterDetails`(`seasonWnTWinterDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 45;

UPDATE `SeasonWnTWinterDetails` 
SET `seasonId` = 10
WHERE `SeasonWnTWinterDetailsId`= 45;

INSERT INTO `SeasonWnTWinterDetails`(`seasonWnTWinterDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 57;

UPDATE `SeasonWnTWinterDetails` 
SET `seasonId` = 11
WHERE `SeasonWnTWinterDetailsId`= 57;

INSERT INTO `SeasonWnTWinterDetails`(`seasonWnTWinterDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 71;

UPDATE `SeasonWnTWinterDetails` 
SET `seasonId` = 12
WHERE `SeasonWnTWinterDetailsId`= 71;

INSERT INTO `SeasonWnTWinterDetails`(`seasonWnTWinterDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 79;

UPDATE `SeasonWnTWinterDetails` 
SET `seasonId` = 13
WHERE `SeasonWnTWinterDetailsId`= 79;

INSERT INTO `SeasonWnTWinterDetails`(`seasonWnTWinterDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 90;

UPDATE `SeasonWnTWinterDetails` 
SET `seasonId` = 14
WHERE `SeasonWnTWinterDetailsId`= 90;

INSERT INTO `SeasonWnTWinterDetails`(`seasonWnTWinterDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 96;

UPDATE `SeasonWnTWinterDetails` 
SET `seasonId` = 15
WHERE `SeasonWnTWinterDetailsId`= 96;


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          SeasonWnTSummerDetails Migration
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

-- Remove NOT NULL for seasonId in SeasonWnTSummerDetails table.

INSERT INTO `SeasonWnTSummerDetails`(`seasonWnTSummerDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 7;

UPDATE `SeasonWnTSummerDetails` 
SET `seasonId` = 8
WHERE `SeasonWnTSummerDetailsId`= 7;

INSERT INTO `SeasonWnTSummerDetails`(`seasonWnTSummerDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 35;

UPDATE `SeasonWnTSummerDetails` 
SET `seasonId` = 9
WHERE `SeasonWnTSummerDetailsId`= 35;

INSERT INTO `SeasonWnTSummerDetails`(`seasonWnTSummerDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 51;

UPDATE `SeasonWnTSummerDetails` 
SET `seasonId` = 10
WHERE `SeasonWnTSummerDetailsId`= 51;

INSERT INTO `SeasonWnTSummerDetails`(`seasonWnTSummerDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 66;

UPDATE `SeasonWnTSummerDetails` 
SET `seasonId` = 11
WHERE `SeasonWnTSummerDetailsId`= 66;

INSERT INTO `SeasonWnTSummerDetails`(`seasonWnTSummerDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 73;

UPDATE `SeasonWnTSummerDetails` 
SET `seasonId` = 12
WHERE `SeasonWnTSummerDetailsId`= 73;

INSERT INTO `SeasonWnTSummerDetails`(`seasonWnTSummerDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 87;

UPDATE `SeasonWnTSummerDetails` 
SET `seasonId` = 13
WHERE `SeasonWnTSummerDetailsId`= 87;

INSERT INTO `SeasonWnTSummerDetails`(`seasonWnTSummerDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 93;

UPDATE `SeasonWnTSummerDetails` 
SET `seasonId` = 14
WHERE `SeasonWnTSummerDetailsId`= 93;

INSERT INTO `SeasonWnTSummerDetails`(`seasonWnTSummerDetailsId`,`programName`,`startDate`,`endDate`,`applicationDeadlineDate`,`isJobBoardOpen`,`maxPendingJobApps`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`IsJobBoardOpen`,`MaxPendingJobApps`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 98;

UPDATE `SeasonWnTSummerDetails` 
SET `seasonId` = 15
WHERE `SeasonWnTSummerDetailsId`= 98;


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          SeasonCAPDetails Migration
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

-- Remove NOT NULL for seasonId in SeasonCAPDetails table.

INSERT INTO `SeasonCAPDetails`(`seasonCAPDetailsId`,`seasonId`,`programName`,`internStartDate`,`internEndDate`,`internAppDeadlineDate`,`traineeStartDate`,`traineeEndDate`,`traineeAppDeadlineDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
VALUES (1,8,'CAP-2009','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',2,'2015-07-24 07:14:19',1,'2015-07-24 07:14:19',1);

INSERT INTO `SeasonCAPDetails`(`seasonCAPDetailsId`,`programName`,`internStartDate`,`internEndDate`,`internAppDeadlineDate`,`traineeStartDate`,`traineeEndDate`,`traineeAppDeadlineDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 59;

UPDATE `SeasonCAPDetails`
SET `seasonId` = 9
WHERE `seasonCAPDetailsId`= 59;

INSERT INTO `SeasonCAPDetails`(`seasonCAPDetailsId`,`programName`,`internStartDate`,`internEndDate`,`internAppDeadlineDate`,`traineeStartDate`,`traineeEndDate`,`traineeAppDeadlineDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 58;

UPDATE `SeasonCAPDetails`
SET `seasonId` = 10
WHERE `seasonCAPDetailsId`= 58 ;

INSERT INTO `SeasonCAPDetails`(`seasonCAPDetailsId`,`programName`,`internStartDate`,`internEndDate`,`internAppDeadlineDate`,`traineeStartDate`,`traineeEndDate`,`traineeAppDeadlineDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 68;

UPDATE `SeasonCAPDetails`
SET `seasonId` = 11
WHERE `seasonCAPDetailsId`= 68 ;

INSERT INTO `SeasonCAPDetails`(`seasonCAPDetailsId`,`programName`,`internStartDate`,`internEndDate`,`internAppDeadlineDate`,`traineeStartDate`,`traineeEndDate`,`traineeAppDeadlineDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 77;

UPDATE `SeasonCAPDetails`
SET `seasonId` = 12
WHERE `seasonCAPDetailsId`= 77 ;

INSERT INTO `SeasonCAPDetails`(`seasonCAPDetailsId`,`programName`,`internStartDate`,`internEndDate`,`internAppDeadlineDate`,`traineeStartDate`,`traineeEndDate`,`traineeAppDeadlineDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 86;

UPDATE `SeasonCAPDetails`
SET `seasonId` = 13
WHERE `seasonCAPDetailsId`= 86;

INSERT INTO `SeasonCAPDetails`(`seasonCAPDetailsId`,`programName`,`internStartDate`,`internEndDate`,`internAppDeadlineDate`,`traineeStartDate`,`traineeEndDate`,`traineeAppDeadlineDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
SELECT `SeasonID`,`SeasonFullName`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`StartDate`,`EndDate`,`ApplicationDeadlineDate`,`SeasonStatusID`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy` FROM `cci_go`.`Season` WHERE `SeasonID` = 95;

UPDATE `SeasonCAPDetails`
SET `seasonId` = 14
WHERE `seasonCAPDetailsId`= 95;

INSERT INTO `SeasonCAPDetails`(`seasonCAPDetailsId`,`seasonId`,`programName`,`internStartDate`,`internEndDate`,`internAppDeadlineDate`,`traineeStartDate`,`traineeEndDate`,`traineeAppDeadlineDate`,`programStatusId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES (2,15,'CAP-2016','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00','9999-09-09 00:00:00',2,'2015-07-24 07:14:23',1,'2015-07-24 07:14:23',1);
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          SeasonWPAllocation Migration
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
 
INSERT INTO `SeasonWPAllocation` (`seasonId`,`departmentProgramOptionId`)
VALUES (8,10),
       (8,11),
	   (8,12),
	   (8,13),
	   (8,14),
	   (8,15),
	   (8,16),
	   (8,17),
	   (8,18),
	   (8,19),
	   (8,20),
	   (9,10),
       (9,11),
	   (9,12),
	   (9,13),
	   (9,14),
	   (9,15),
	   (9,16),
	   (9,17),
	   (9,18),
	   (9,19),
	   (9,20),
	   (10,10),
       (10,11),
	   (10,12),
	   (10,13),
	   (10,14),
	   (10,15),
	   (10,16),
	   (10,17),
	   (10,18),
	   (10,19),
	   (10,20),
	   (11,10),
       (11,11),
	   (11,12),
	   (11,13),
	   (11,14),
	   (11,15),
	   (11,16),
	   (11,17),
	   (11,18),
	   (11,19),
	   (11,20),
	   (12,10),
       (12,11),
	   (12,12),
	   (12,13),
	   (12,14),
	   (12,15),
	   (12,16),
	   (12,17),
	   (12,18),
	   (12,19),
	   (12,20),
	   (13,10),
       (13,11),
	   (13,12),
	   (13,13),
	   (13,14),
	   (13,15),
	   (13,16),
	   (13,17),
	   (13,18),
	   (13,19),
	   (13,20),
	   (14,10),
       (14,11),
	   (14,12),
	   (14,13),
	   (14,14),
	   (14,15),
	   (14,16),
	   (14,17),
	   (14,18),
	   (14,19),
	   (14,20),
	   (15,10),
       (15,11),
	   (15,12),
	   (15,13),
	   (15,14),
	   (15,15),
	   (15,16),
	   (15,17),
	   (15,18),
	   (15,19),
	   (15,20);

UPDATE `SeasonWPAllocation` 
SET createdBy =1,   modifiedBy =1; 
	   

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 10 AND swpa.`seasonId` = 8 AND os.`SeasonID` = 7;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 11 AND swpa.`seasonId` = 8 AND os.`SeasonID` = 7;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 12 AND swpa.`seasonId` = 8 AND os.`SeasonID` = 7;


UPDATE `SeasonWPAllocation` 
SET `maxPax`= 0
WHERE `departmentProgramOptionId` = 13 AND `seasonId` = 8;

UPDATE `SeasonWPAllocation` 
SET `maxPax`= 0
WHERE `departmentProgramOptionId` = 14 AND `seasonId` = 8;

UPDATE `SeasonWPAllocation` 
SET `maxPax`= 0
WHERE `departmentProgramOptionId` = 15 AND `seasonId` = 8;

UPDATE `SeasonWPAllocation` 
SET `maxPax`= 0
WHERE `departmentProgramOptionId` = 16 AND `seasonId` = 8;

UPDATE `SeasonWPAllocation` 
SET `maxPax`= 0
WHERE `departmentProgramOptionId` = 17 AND `seasonId` = 8;

UPDATE `SeasonWPAllocation` 
SET `maxPax`= 0
WHERE `departmentProgramOptionId` = 18 AND `seasonId` = 8;

UPDATE `SeasonWPAllocation` 
SET `maxPax`= 0
WHERE `departmentProgramOptionId` = 19 AND `seasonId` = 8;

UPDATE `SeasonWPAllocation`
SET `maxPax`= 0
WHERE `departmentProgramOptionId` = 20 AND `seasonId` = 8;



UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 10 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 35;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 11 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 35;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 12 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 35;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 16 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 34;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 17 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 34;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 18 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 34;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 13 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 33;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 14 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 33;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 15 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 33;


UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationInternFP`+ os.`AllocationInternSP`
WHERE swpa.`departmentProgramOptionId` = 19 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 59;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationTraineeFP`+ os.`AllocationTraineeSP`
WHERE swpa.`departmentProgramOptionId` = 20 AND swpa.`seasonId` = 9 AND os.`SeasonID` = 59;
       

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 10 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 51;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 11 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 51;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 12 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 51;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 13 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 45;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 14 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 45;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 15 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 45;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 16 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 46;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 17 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 46;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 18 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 46;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationInternFP`+ os.`AllocationInternSP`
WHERE swpa.`departmentProgramOptionId` = 19 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 58;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationTraineeFP`+ os.`AllocationTraineeSP`
WHERE swpa.`departmentProgramOptionId` = 20 AND swpa.`seasonId` = 10 AND os.`SeasonID` = 58;


UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 10 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 66; 

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 11 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 66;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 12 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 66;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 13 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 57;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 14 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 57;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 15 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 57;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 16 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 62;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 17 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 62;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 18 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 62;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationInternFP`+ os.`AllocationInternSP`
WHERE swpa.`departmentProgramOptionId` = 19 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 68;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationTraineeFP`+ os.`AllocationTraineeSP`
WHERE swpa.`departmentProgramOptionId` = 20 AND swpa.`seasonId` = 11 AND os.`SeasonID` = 68;


UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 10 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 73;
UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 11 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 73;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 12 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 73;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 13 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 71;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 14 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 71;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 15 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 71;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 16 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 72;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 17 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 72;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 18 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 72;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationInternFP`+ os.`AllocationInternSP`
WHERE swpa.`departmentProgramOptionId` = 19 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 77;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationTraineeFP`+ os.`AllocationTraineeSP`
WHERE swpa.`departmentProgramOptionId` = 20 AND swpa.`seasonId` = 12 AND os.`SeasonID` = 77;



UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 10 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 87;
UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 11 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 87;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 12 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 87;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 13 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 79;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 14 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 79;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 15 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 79;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 16 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 80;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 17 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 80;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 18 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 80;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationInternFP`+ os.`AllocationInternSP`
WHERE swpa.`departmentProgramOptionId` = 19 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 86;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationTraineeFP`+ os.`AllocationTraineeSP`
WHERE swpa.`departmentProgramOptionId` = 20 AND swpa.`seasonId` = 13 AND os.`SeasonID` = 86;


UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 10 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 93;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 11 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 93;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 12 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 93;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 13 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 90;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 14 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 90;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 15 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 90;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 16 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 91;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 17 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 91;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 18 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 91;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationInternFP`+ os.`AllocationInternSP`
WHERE swpa.`departmentProgramOptionId` = 19 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 95;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationTraineeFP`+ os.`AllocationTraineeSP`
WHERE swpa.`departmentProgramOptionId` = 20 AND swpa.`seasonId` = 14 AND os.`SeasonID` = 95;


UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 10 AND swpa.`seasonId` = 15 AND os.`SeasonID` = 98;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 11 AND swpa.`seasonId` = 15 AND os.`SeasonID` = 98;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 12 AND swpa.`seasonId` = 15 AND os.`SeasonID` = 98;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 13 AND swpa.`seasonId` = 15 AND os.`SeasonID` = 96;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 14 AND swpa.`seasonId` = 15 AND os.`SeasonID` = 96;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 15 AND swpa.`seasonId` = 15 AND os.`SeasonID` = 96;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationJobFair`
WHERE swpa.`departmentProgramOptionId` = 16 AND swpa.`seasonId` = 15 AND os.`SeasonID` = 97;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationSelfPlaced`
WHERE swpa.`departmentProgramOptionId` = 17 AND swpa.`seasonId` = 15 AND os.`SeasonID` = 97;

UPDATE `SeasonWPAllocation` swpa,`cci_go`.`Season` os
SET swpa.`maxPax`= os.`AllocationDirectPlacement`
WHERE swpa.`departmentProgramOptionId` = 18 AND swpa.`seasonId` = 15 AND os.`SeasonID` = 97;

UPDATE `SeasonWPAllocation` swpa
SET swpa.`maxPax`=0
WHERE swpa.`departmentProgramOptionId` = 19 AND swpa.`seasonId` = 15;
UPDATE `SeasonWPAllocation` swpa
SET swpa.`maxPax`=0
WHERE swpa.`departmentProgramOptionId` = 20 AND swpa.`seasonId` = 15;

SET FOREIGN_KEY_CHECKS= 1;