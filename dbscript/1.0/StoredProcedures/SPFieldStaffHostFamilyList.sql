DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPFieldStaffHostFamilyList`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPFieldStaffHostFamilyList`(IN fieldStaffId INT,IN flag TINYINT(1),IN category VARCHAR(50))
BEGIN
        DECLARE goId INT;
        DECLARE categoryName VARCHAR(50);
        DECLARE fsearch TINYINT(1);
        
        SET @goId = fieldstaffId;
        SET @fsearch = flag;
        SET @categoryName = category;
        
        IF ((@fsearch = 1) && (@categoryName IS NOT NULL)) THEN        
        
               SELECT hf.`hostFamilyGoId` AS GoId,
                      CONCAT(hf.`firstName`,' ',hf.`lastName`) AS NAME,
                     '7466 SW 150th, Augusta,KS67010 ' AS Address,
                      l.email,
                      'Josef Mcde' AS LocalCordinator,
                      'AYP 2016-2017 J1HS'  AS Seasons,
                      hfs.`hostFamilyStatusName` AS ApplicationStatus,
                      hf.phone,
                      'https://s3.amazonaws.com/%2Fccighgodocs-dev%2Fpartner/profile-1450399222670.jpg' AS photo
               FROM `HostFamily` hf 
               INNER JOIN `HostFamilyStatus` hfs ON hfs.`hostFamilyStatusId` = hf.`hostFamilyStatusId`
               INNER JOIN `Login` l ON l.goId = hf.hostFamilyGoId;
           
         
        END IF;
        
        IF ((@fsearch = 0) && (@categoryName IS NULL)) THEN       
        
               SELECT hf.`hostFamilyGoId` AS GoId,
                      CONCAT(hf.`firstName`,' ',hf.`lastName`) AS NAME,
                      CONCAT(hf.`mailingAddress1`,' ',hf.`mailingAddress2` ,' ',hf.`mailingCity`,' ',hf.`mailingZipCode`) AS Address,
                      l.email,
                      'Stain Lc' AS localCordinator,
                      'AYP 2016-2017 J1HS'  AS Seasons,
                       hfs.`hostFamilyStatusName` AS ApplicationStatus,
                      hf.`phone` AS phone,
                      'https://s3.amazonaws.com/%2Fccighgodocs-dev%2Fpartner/profile-1450399222670.jpg' AS photo,
                      'Eve Moertin' AS Participants       
               FROM `HostFamily` hf
               INNER JOIN `HostFamilyStatus` hfs ON hfs.`hostFamilyStatusId` = hf.`hostFamilyStatusId`
               INNER JOIN `Login` l ON l.goId = hf.hostFamilyGoId
               INNER JOIN `LookupUSStates` ls ON ls.`usStatesId` = hf.`mailingStateId`;
            
         ELSE    
            
            SELECT hf.`hostFamilyGoId` AS GoId,
                      CONCAT(hf.`firstName`,' ',hf.`lastName`) AS NAME,
                      CONCAT(hf.`mailingAddress1`,' ',hf.`mailingAddress2` ,' ',hf.`mailingCity`,' ',hf.`mailingZipCode`) AS Address,
		      l.email,	
		      'Stain Lc' AS localCordinator,
		      'AYP 2016-2017 J1HS'  AS Seasons,
                      hfs.`hostFamilyStatusName` AS ApplicationStatus,
                      hf.`phone` AS phone,
                      'https://s3.amazonaws.com/%2Fccighgodocs-dev%2Fpartner/profile-1450399222670.jpg' AS photo,
                      'Eve Moertin' AS Participants       
               FROM `HostFamily` hf
               INNER JOIN `HostFamilyStatus` hfs ON hfs.`hostFamilyStatusId` = hf.`hostFamilyStatusId`
               INNER JOIN `Login` l ON l.goId = hf.hostFamilyGoId
               INNER JOIN `LookupUSStates` ls ON ls.`usStatesId` = hf.`mailingStateId`;
            
               
         END IF;
    END$$

DELIMITER ;