
-- USE `cci_gh_go_prod`;

SET FOREIGN_KEY_CHECKS= 0;

TRUNCATE TABLE `SeasonStatus`;
TRUNCATE TABLE `DepartmentPrograms` ;
TRUNCATE TABLE `DepartmentProgramOptions` ;


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in `SeasonStatus` Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `SeasonStatus` (`seasonStatusId`,`status`,`active`) 
VALUES 
(1,'Open',1),
(2,'Close',1),
(3,'Draft',1),
(4,'Archived',1);


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in DepartmentPrograms Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
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

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in DepartmentProgramOptions Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

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

