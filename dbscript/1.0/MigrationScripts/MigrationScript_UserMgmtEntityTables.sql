SET FOREIGN_KEY_CHECKS= 0;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Truncating UserManagement Module tables.
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

TRUNCATE TABLE `CCIStaffRoles`;
TRUNCATE TABLE `ResourceActions`;
TRUNCATE TABLE `DepartmentResourceGroups`;
TRUNCATE TABLE `ResourcePermissions`;
TRUNCATE TABLE `CCIStaffRolesDepartments`;


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in CCIStaffRoles Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO `CCIStaffRoles` (`cciStaffRoleName`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
VALUES 
('Assistant',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Compliance and Operations Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Director',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Director of Compliance and Operations',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('General Director of Academic and Grant Programs',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Grant Program Director',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Grant Program Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Marketing and Event Coordinator',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Placement Services Director',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Program Coordinator',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Program Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Student Counselor',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Technology Support Coordinator',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Business Development and Operations Director',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Business Development Specialist',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Cultural Outreach Assistant',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Cultural Outreach Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Database Coordinator',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Career Advancement Program Director',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Career Advancement Program Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Employer Relations Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Employer Services Coordinator',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Employer Services Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Intern',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Office Assistant',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Operations Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Partner Relations Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Policy and Compliance Assistant',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Policy and Compliance Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Program Assistant',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Services Director',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Self Placed Operations Coordinator',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Vice President of International Exchange',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('General Projects & Personnel Director',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Chief Executive Officer',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('IT Director',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('International Relations Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Internation Relations Assistant',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Accounting Manager ',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Accounting Administrative Associate',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('Systems Manager',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('HR and Special Projects Assistant',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
('System Administrator',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in ResourceActions Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
INSERT INTO `ResourceActions` (resourceActionID,resourceAction,active,visibleToUser,createdBy,modifiedBy)
VALUES (1,'Add',1,1,1,1),
	   (2,'Approve',1,1,1,1),
	   (3,'Create',1,1,1,1),
	   (4,'Edit',1,1,1,1),
	   (5,'Modify',1,1,1,1),
	   (6,'Review',1,1,1,1),
	   (7,'View',1,1,1,1),
	   (8,'Delete',1,1,1,1);

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in DepartmentResourceGroups Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
INSERT INTO `DepartmentResourceGroups` (`departmentResourceGroupId`, `departmentId`,`resourceGroupName`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`) 
VALUES 
(1,1,'Seasons',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(2,1,'SEVIS',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(3,1,'System Settings',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(4,1,'Partners',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(5,1,'Participants-J1HS',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(6,1,'Participants-F1HS',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(7,1,'Participants-STP',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(8,1,'Field Staff',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(9,1,'Host Families',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(10,1,'Change Q',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(11,1,'Schools',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
(12,1,'Accounting',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in ResourcePermissions Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/	   
INSERT INTO `ResourcePermissions` (`resourcePermissionId`, `departmentResourceGroupId`, `resourceActionID`, `resourceName`, `resourceDescription`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`,`active`) 
VALUES 
(1, 1, 4, 'Edit Season', 'Edit seasons Details',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(2, 1, 4, 'Edit Documents', 'Edit seasons Documents',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(3, 1, 4, 'Edit Terms & Contracts', 'Edit seasons Terms & Contracts',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(4, 1, 4, 'Edit Benchmarks', 'Edit seasons Benchmarks',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(5, 1, 7, 'View Season', 'View seasons Details',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(6, 1, 7, 'View Documents', 'View seasons Documents',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(7, 1, 7, 'View Terms & Contracts', 'View seasons Terms & Contracts',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(8, 1, 7, 'View Benchmarks', 'View seasons Benchmarks',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(9, 2, 4, 'Edit SEVIS', 'Edit SEVIS Tab',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(10, 2, 7, 'View SEVIS', 'View SEVIS tab and Functions',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(11, 3, 4, 'Edit User Settings', 'Edit User Settings',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(12, 4, 2, 'Approve Partners', 'Approve Partners',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(13, 4, 4, 'Edit Partners', 'Edit Partners',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(14, 4, 7, 'View Partner', 'View Partners',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(15, 4, 5, 'Setting the price for a group partner and the compenstaion for a group LC', 'Setting the price for a group partner and the compenstaion for a group LC',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(16, 5, 2, 'Approve Participants-J1HS', 'Approve Participants for J1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(17, 5, 4, 'Edit Participants-J1HS', 'Edit Participants for J1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(18, 5, 7, 'View Participants-J1HS', 'View Participants for J1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(19, 6, 2, 'Approve Participants-F1HS', 'Approve Participants for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(20, 6, 4, 'Edit Participants-F1HS', 'Edit Participants for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(21, 6, 7, 'View Participants-F1HS', 'View Participants for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(22, 7, 2, 'Approve Participants-IHP', 'Approve Participants for STP-IHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(23, 7, 4, 'Edit Participants-IHP', 'Edit Participants for STP-IHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(24, 7, 7, 'View Participants-IHP', 'View Participants for STP-IHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(25, 7, 2, 'Approve Participants-GHP', 'Approve Participants for STP-GHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(26, 7, 4, 'Edit Participants-GHP', 'Edit Participants for STP-GHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(27, 7, 7, 'View Participants-GHP', 'View Participants for STP-GHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(28, 7, 2, 'Approve Participants-SSE', 'Approve Participants for STP-SSE Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(29, 7, 4, 'Edit Participants-SSE', 'Edit Participants for STP-SSE Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(30, 7, 7, 'View Participants-SSE', 'View Participants for STP-SSE Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(31, 7, 3, 'Creating a group', 'Creating a group including application and modification of invoice schedule, for parnters, and payment schedule, for LCs',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(32, 7, 2, 'Deny/Approve Volunteer Placement for IHP', 'Deny/Approve Volunteer Placement for IHP',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(33, 8, 2, 'Approve Field Staff-J1HS', 'Approve Field Staff for J1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(34, 8, 4, 'Edit Field Staff-J1HS', 'Edit Field Staff for J1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(35, 8, 7, 'View Field Staff-J1HS', 'View Field Staff for J1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(36, 8, 2, 'Approve Field Staff-F1HS', 'Approve Field Staff for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(37, 8, 4, 'Edit Field Staff-F1HS', 'Edit Field Staff for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(38, 8, 7, 'View Field Staff-F1HS', 'View Field Staff for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(39, 8, 2, 'Approve Field Staff-IHP', 'Approve Field Staff for STP-IHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(40, 8, 4, 'Edit Field Staff-IHP', 'Edit Field Staff for STP-IHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(41, 8, 7, 'View Field Staff-IHP', 'View Field Staff for STP-IHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(42, 8, 2, 'Approve Field Staff-GHP', 'Approve Field Staff for STP-GHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(43, 8, 4, 'Edit Field Staff-GHP', 'Edit Field Staff for STP-GHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(44, 8, 7, 'View Field Staff-GHP', 'View Field Staff for STP-GHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(45, 8, 2, 'Approve Field Staff-SSE', 'Approve Field Staff for STP-SSE Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(46, 8, 4, 'Edit Field Staff-SSE', 'Edit Field Staff for STP-SSE Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(47, 8, 7, 'View Field Staff-SSE', 'View Field Staff for STP-SSE Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(48, 8, 4, 'Edit Field Staff Points', 'Edit Field Staff Points',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(49, 8, 7, 'View Field Staff Permissions', 'View Field Staff Permissions',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(50, 8, 4, 'Edit Contact Logs', 'Edit/Approve Field Staff Contact Logs',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(51, 8, 4, 'Edit Evaluations', 'Edit Field Staff Evaluations',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(52, 8, 4, 'Edit Monitoring Documents', 'Edit Field Staff Monitoring Documents',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(53, 8, 2, 'Complete Field Staff Background Checks', 'Complete Field Staff Background Checks',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(54, 8, 4, 'Edit Field Staff Permissions', 'Edit Field Staff Permissions',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(55, 9, 2, 'Approve Host Families-J1HS', 'Approve Host Families for J1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(56, 9, 4, 'Edit Host Families-J1HS', 'Edit Host Families for J1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(57, 9, 7, 'View Host Families-J1HS', 'View Host Families for J1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(58, 9, 2, 'Approve Host Families-F1HS', 'Approve Host Families for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(59, 9, 4, 'Edit Host Families-F1HS', 'Edit Host Families for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(60, 9, 7, 'View Host Families-F1HS', 'View Host Families for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(61, 9, 2, 'Approve Host Families-IHP', 'Approve Host Families for STP-IHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(62, 9, 4, 'Edit Host Families-IHP', 'Edit Host Families for STP-IHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(63, 9, 7, 'View Host Families-IHP', 'View Host Families for STP-IHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(64, 9, 2, 'Approve Host Families-GHP', 'Approve Host Families for STP-GHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(65, 9, 4, 'Edit Host Families-GHP', 'Edit Host Families for STP-GHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(66, 9, 7, 'View Host Families-GHP', 'View Host Families for STP-GHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(67, 9, 2, 'Approve Host Families-SSE', 'Approve Host Families for STP-SSE Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(68, 9, 4, 'Edit Host Families-SSE', 'Edit Host Families for STP-SSE Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(69, 9, 7, 'View Host Families-SSE', 'View Host Families for STP-SSE Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(70, 12, 7, 'View W9 forms for Accounting', 'View W9 forms for Accounting',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(71, 12, 4, 'Edit Point Line Items', 'Edit Point Line Items',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(72, 12, 1, 'Add Points', 'Add Points',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(73, 12, 4, 'Edit Fee Schedule', 'Edit Fee Schedule',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(74, 12, 5, 'Override Accounting Line Items', 'Override Accounting Line Items',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(75, 12, 2, 'Approve Invoice for Release to Accounting', 'Approve Invoice for Release to Accounting',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(76, 12, 3, 'Create Tuition Invoicing', 'Create Tuition Invoicing',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(77, 12, 5, 'Override Points', 'Override Points',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(78, 12, 5, 'Modifying Partners Invoices', 'Modifying Partners Invoices',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(79, 12, 5, 'Modifying group and IHP LC payments', 'Modifying group and IHP LC payments',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(80, 12, 5, 'Modifying Prices in General for Programs', 'Setting and modifying prices in genereal for programs in situation where prices are viewable to partner',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(81, 10, 6, 'Review School Change Queue', 'Review School Change Queue',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(82, 10, 6, 'Review participant Change Queue', 'Review participant Change Queue',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(83, 11, 4, 'Edit School Records', 'Edit School Records',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(84, 11, 4, 'Edit F1 School Records', 'Edit School Records for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(85, 11, 4, 'Edit J1 School Records', 'Edit School Records for J1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(86, 11, 4, 'Edit IHP School Records', 'Edit School Records for STP-IHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(87, 11, 4, 'Edit GHP School Records', 'Edit School Records for STP-GHP Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(88, 11, 4, 'Edit SSE School Records', 'Edit School Records for STP-SSE Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(89, 11, 3, 'Create F1 School', 'Create School for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(90, 11, 4, 'Edit F1 School Pricing', 'Edit School Pricing for F1HS Program',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(91, 11, 6, 'Initial School Review', 'Initial School Review',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(92, 11, 5, 'Propose School to another School, or partner', 'Propose School to another School, or partner, for School to School match',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(93,1,4,'Edit Announcements','Edit Seasons Announcements',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(94,4,7,'View Partner Season Details','View Partner Season Details',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(95,4,4,'Edit Partner Season Details','Edit Partner Season Details',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(96,4,5,'Change Status of Partners','Change Status of Partners',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(97,4,8,'Delete Partners','Delete Partners',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(98,4,6,'Receive Notification of Partner Changes','Receive Notification of Partner Changes',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(99,5,2,'Termination Manager Approval','Termination Manager Approval For J1',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(100,6,2,'Termination Manager Approval','Termination Manager Approval For F1',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(101,7,2,'Termination Manager Approval','Termination Manager Approval For IHP',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(102,12,4,'Access to Invoices and Upload to Quickbooks','Access to Invoices and Upload to Quickbooks',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1),
(103,9,2,'Complete Host Family Background Checks','Complete Host Family Background Checks',CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1,1);

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------
          Inserting data in CCIStaffRolesDepartments Table
-------------------------------------------------------------------------------------------------------------------------------------------------------------*/	 

INSERT INTO `CCIStaffRolesDepartments` (`cciStaffRoleId`,`departmentId`,`createdOn`,`createdBy`,`modifiedOn`,`modifiedBy`)
VALUES  (1,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (2,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (3,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (4,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (5,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (6,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (7,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (8,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (9,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (10,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (10,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (11,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (12,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (13,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (14,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (15,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (16,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (17,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (18,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (19,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (20,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (21,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (22,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (23,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (24,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (25,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (26,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (27,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (28,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (29,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (30,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (31,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (32,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (33,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (34,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (35,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (36,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (37,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (38,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (39,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (40,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (41,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (42,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (43,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (44,1,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (44,2,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1),
        (44,3,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);	