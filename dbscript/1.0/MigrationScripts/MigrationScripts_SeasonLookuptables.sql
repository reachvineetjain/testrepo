-- USE cci_gh_go_qa;

SET FOREIGN_KEY_CHECKS= 0;


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Truncating Season Module tables.
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/


TRUNCATE `LookupDepartments` ;
TRUNCATE `DepartmentPrograms` ;
TRUNCATE `DepartmentProgramOptions` ;
TRUNCATE `SeasonStatus` ;
TRUNCATE `FieldStaffAgreement` ;
TRUNCATE `PaymentSchedule` ;
TRUNCATE `Season` ;
TRUNCATE `SeasonDepartmentDocument` ;
TRUNCATE `SeasonDepartmentNotes` ;
TRUNCATE `SeasonDepartmentUpdateLog` ;
TRUNCATE `SeasonProgramDocument`;
TRUNCATE `SeasonProgramNotes`;
TRUNCATE `SeasonProgramUpdateLog`;
TRUNCATE `Region` ;
TRUNCATE `RegionIHP` ;
TRUNCATE `SuperRegion`;



/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Populating Lookup tables for Seasons Module
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `LookupDepartments` (`departmentId`,`departmentName`,`acronym`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,`active`)
VALUES 
(1,'High School Programs','HSP', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(2,'Work Programs','WP', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(3,'Greenheart Travel','GHT', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(4,'Greenheart Club','GHC', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(5,'Greenheart Transforms','GT', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(6,'Accounting','ACC', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(7,'System','SYS', CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1);


INSERT INTO `DepartmentPrograms`(`departmentProgramId`,`departmentId`,`programName`,`description`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
VALUES
(1,1,'J-1HS','J1HS visa program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(2,1,'F-1','F1 visa program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(3,1,'STP-IHP','Short term program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(4,1,'STP-GHP','Short term greenheart program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(5,1,'STP-SSE','School to school exchange',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(6,2,'W&T Summer','Work and travel summer program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(7,2,'W&T Winter','Work and travel winter program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(8,2,'W&T Spring','Work and travel spring program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(9,2,'CAP','Career Advancement Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(10,3,'HS Abroad','Highschool abroad',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(11,3,'Language School','Language School',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(12,3,'Teach Abroad','Teach Abroad',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(13,3,'Volunteer Abroad','Volunteer Abroad',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(14,3,'Work Abroad','Work Abroad',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1); 

INSERT INTO `DepartmentProgramOptions` (`departmentProgramOptionId`, `departmentProgramId`, `programOptionCode`, `programOptionName`) 
VALUES 
(1, 1, 'Aug-FY', 'August - Full Year'),
(2, 1, 'Aug-1Sem', 'August - First Semester'),
(3, 1, 'Jan-FY', 'January - Full Year'),
(4, 1, 'Jan-2Sem', 'January - Second Semester'),
(5, 2, 'Aug-FY', 'August - Full Year'),
(7, 2, 'Aug-1Sem', 'August - First Semester'),
(8, 2, 'Jan-FY', 'January - Full Year'),
(9, 2, 'Jan-2Sem', 'January - Second Semester'),
(10, 6, 'JF', 'Job Fair'),
(11, 6, 'SP', 'Self Placed'),
(12, 6, 'DP', 'Direct Placement'),
(13, 7, 'JF', 'Job Fair'),
(14, 7, 'SP', 'Self Placed'),
(15, 7, 'DP', 'Direct Placement'),
(16, 8, 'JF', 'Job Fair'),
(17, 8, 'SP', 'Self Placed'),
(18, 8, 'DP', 'Direct Placement'),
(19, 9,'Int-SP', 'Internship - Self Placed'),
(20, 9,'Trn-SP', 'Trainee - Self Placed');

INSERT INTO `SeasonStatus` (`seasonStatusId`,`status`,`active`) 
VALUES 
(1,'Open',1),
(2,'Close',1),
(3,'Draft',1),
(4,'Archived',1);

INSERT INTO `FieldStaffAgreement` (`fieldStaffAgreementId`,`agreementName`)
VALUES
(1,'2009-2010'),
(2,'2010-2011'),
(3,'2011-2012'),
(4,'2012-2013'),
(5,'2013-2014'),
(6,'2014-2015'),
(7,'2015-2016'),
(8,'Undefined'),
(9,'2013-2014'),
(10,'2013-2014');

INSERT INTO `PaymentSchedule` (`paymentScheduleId`,`scheduleName`)
VALUES 
(1,'Area Representative Stipend Schedule'),
(2,'PSPP Area Representative Stipend Schedule'),
(3,'Field Staff Schedule'),
(4,'PSPP Field Staff Schedule'),
(6,'PSPP Field Staff Schedule'),
(21,'PSPP Area Representative Stipend Schedule'),
(24,'PSPP Area Representative Stipend Schedule'),
(30,'PSPP Area Representative Stipend Schedule'),
(31,'PSPP Area Representative Stipend Schedule'),
(32,'Undefined');

INSERT INTO `RegionIHP` (regionName,active,createdBy,modifiedOn,modifiedBy)
VALUES   
('Atlantic',1,1,CURRENT_TIMESTAMP,1),
('Midwest',1,1,CURRENT_TIMESTAMP,1),
('West',1,1,CURRENT_TIMESTAMP,1),
('California',1,1,CURRENT_TIMESTAMP,1),
('South',1,1,CURRENT_TIMESTAMP,1),
('Non-Contiguous',1,1,CURRENT_TIMESTAMP,1);

INSERT INTO `SuperRegion` (`superRegionId`, `superRegionName`, `active`, `createdOn`, `createdBy`, `modifiedOn`, `modifiedBy`) 
VALUES
(1,'West',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(2,'Central',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(3,'East',1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);

INSERT INTO `Region` (`regionId`, `regionName`, `active`, `createdOn`, `createdBy`, `modifiedOn`, `modifiedBy`) 
VALUES
( 1 ,'Region Leah', 1 , CURRENT_TIMESTAMP, 1 , CURRENT_TIMESTAMP , 1 ),
( 2 ,'Region Kendra', 1 , CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 3 ,'Region Lori', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 4 ,'Region Sally', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 5 ,'Region Connie', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 6 ,'Region Angela', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 7 ,'Region Emma', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 8 ,'Region Jennifer', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 9 ,'Region Rob', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ),
( 10 ,'Region Kathy', 1 ,CURRENT_TIMESTAMP, 1 ,CURRENT_TIMESTAMP, 1 ); 