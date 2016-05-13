DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPPartnerPermissionsUpdate`$$

CREATE DEFINER=`surender.sarwa`@`%` PROCEDURE `SPPartnerPermissionsUpdate`(IN pPartnerId INT(11), IN pJ1HSPermissionId VARCHAR(500), IN pF1PermissionId VARCHAR(500), IN pIHPPermissionId VARCHAR(500), IN pWTPermissionId VARCHAR(500), IN pCAPPermissionId VARCHAR(500) )
BEGIN
-- ===================================================================================================
SET @V_JIHS = ''; SET @V_F1HS=''; SET @V_IHP = '';
-- ===================================================================================================
	DROP TEMPORARY TABLE IF EXISTS Temp_J1HS;
	CREATE TEMPORARY TABLE Temp_J1HS(
	AutoPermissionId INT(10),
	partnerUserId INT(11) NOT NULL,
	lookupDepartmentProgramId INT(11) DEFAULT NULL,
	partnerPermissionsCategoryId INT(11) DEFAULT NULL
	);
	DROP TEMPORARY TABLE IF EXISTS Temp_F1HS;
	CREATE TEMPORARY TABLE Temp_F1HS(
	AutoPermissionId INT(10),
	partnerUserId INT(11) NOT NULL,
	lookupDepartmentProgramId INT(11) DEFAULT NULL,
	partnerPermissionsCategoryId INT(11) DEFAULT NULL
	);
	
	DROP TEMPORARY TABLE IF EXISTS Temp_IHP;
	CREATE TEMPORARY TABLE Temp_IHP(
	AutoPermissionId INT(10),
	partnerUserId INT(11) NOT NULL,
	lookupDepartmentProgramId INT(11) DEFAULT NULL,
	partnerPermissionsCategoryId INT(11) DEFAULT NULL
	);
	
	DROP TEMPORARY TABLE IF EXISTS Temp_WT;
	CREATE TEMPORARY TABLE Temp_WT(
	AutoPermissionId INT(10),
	partnerUserId INT(11) NOT NULL,
	lookupDepartmentProgramId INT(11) DEFAULT NULL,
	partnerPermissionsCategoryId INT(11) DEFAULT NULL
	);
	
	DROP TEMPORARY TABLE IF EXISTS Temp_CAP;
	CREATE TEMPORARY TABLE Temp_CAP(
	AutoPermissionId INT(10),
	partnerUserId INT(11) NOT NULL,
	lookupDepartmentProgramId INT(11) DEFAULT NULL,
	partnerPermissionsCategoryId INT(11) DEFAULT NULL
	);
-- ===================================================================================================	
-- J1HS Permissions
IF IFNULL(pJ1HSPermissionId, '')<> '' THEN
	INSERT INTO Temp_J1HS
	SELECT NULL AS AutoPermissionId, pPartnerId AS partnerUserId, 1 AS lookupDepartmentProgramId, PPC.partnerPermissionsCategoryId AS partnerPermissionsCategoryId
	FROM PartnerPermissionsCategory PPC
	WHERE FIND_IN_SET(partnerPermissionsCategoryId, pJ1HSPermissionId);
	-- SELECT * FROM Temp_J1HS;
END IF;
-- =================================================================
-- F1 Permissions
IF IFNULL(pF1PermissionId, '')<> '' THEN
	INSERT INTO Temp_F1HS
	SELECT NULL AS AutoPermissionId, pPartnerId AS partnerUserId, 2 AS lookupDepartmentProgramId, PPC.partnerPermissionsCategoryId AS partnerPermissionsCategoryId
	FROM PartnerPermissionsCategory PPC
	WHERE FIND_IN_SET(partnerPermissionsCategoryId, pF1PermissionId);
	-- SELECT * FROM Temp_F1HS;
END IF;
-- =================================================================
-- IHP Permissions
IF IFNULL(pIHPPermissionId, '')<> '' THEN
	INSERT INTO Temp_IHP
	SELECT NULL AS AutoPermissionId, pPartnerId AS partnerUserId, 3 AS lookupDepartmentProgramId, PPC.partnerPermissionsCategoryId AS partnerPermissionsCategoryId
	FROM PartnerPermissionsCategory PPC
	WHERE FIND_IN_SET(partnerPermissionsCategoryId, pIHPPermissionId);
	-- SELECT * FROM Temp_IHP;
END IF;
-- =================================================================
-- W&T Permissions
IF IFNULL(pWTPermissionId, '')<> '' THEN
	INSERT INTO Temp_WT
	SELECT NULL AS AutoPermissionId, pPartnerId AS partnerUserId, 6 AS lookupDepartmentProgramId, PPC.partnerPermissionsCategoryId AS partnerPermissionsCategoryId
	FROM PartnerPermissionsCategory PPC
	WHERE FIND_IN_SET(partnerPermissionsCategoryId, pWTPermissionId);
	-- SELECT * FROM Temp_WT;
END IF;
-- =================================================================
-- CAP Permissions
IF IFNULL(pCAPPermissionId, '')<> '' THEN
	INSERT INTO Temp_CAP
	SELECT NULL AS AutoPermissionId, pPartnerId AS partnerUserId, 7 AS lookupDepartmentProgramId, PPC.partnerPermissionsCategoryId AS partnerPermissionsCategoryId
	FROM PartnerPermissionsCategory PPC
	WHERE FIND_IN_SET(partnerPermissionsCategoryId, pCAPPermissionId);
	-- SELECT * FROM Temp_CAP;
	
END IF;
-- ===================================================================================================
DROP TEMPORARY TABLE IF EXISTS New_permissions;
CREATE TEMPORARY TABLE New_permissions LIKE Temp_J1HS;
INSERT INTO New_permissions
SELECT * FROM Temp_J1HS
UNION 
SELECT * FROM Temp_F1HS
UNION
SELECT * FROM Temp_IHP
UNION
SELECT * FROM Temp_WT
UNION
SELECT * FROM Temp_CAP;
-- ===================================
-- SELECT * FROM PartnerPermissions WHERE partnerUserId= pPartnerId;
-- ===================================================================================================
UPDATE New_permissions A, PartnerPermissions B
SET A.AutoPermissionId =B.partnerPermissionId
WHERE A.lookupDepartmentProgramId=B.lookupDepartmentProgramId
AND A.partnerPermissionsCategoryId=B.partnerPermissionsCategoryId
AND B.partnerUserId= pPartnerId;
-- SELECT * FROM New_permissions;
-- ===================================================================================================
DELETE FROM PartnerPermissions WHERE partnerUserId= pPartnerId;
INSERT IGNORE INTO PartnerPermissions
SELECT * FROM New_permissions;
-- ===================================================================================================
END$$

DELIMITER ;