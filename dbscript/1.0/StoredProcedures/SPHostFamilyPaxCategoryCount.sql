DELIMITER $$

USE `cci_gh_go_qa`$$

DROP PROCEDURE IF EXISTS `SPHostFamilyPaxCategoryCount`$$

CREATE DEFINER=`madhavi`@`%` PROCEDURE `SPHostFamilyPaxCategoryCount`(IN hfId INT)
BEGIN
    
         DECLARE hfGoId INT;
         SET @hfGoId = hfId;
         
         DROP TABLE IF EXISTS HFCount;
         CREATE TEMPORARY TABLE HFCount (Categories VARCHAR(25),VALUE VARCHAR(15),COUNT INT);
                
          INSERT INTO HFCount
          VALUES ('presented Participants','PRP',2),
                 ('placed Participants','PLP',11),
                 ('participant History','PH',3);
                       
          SELECT * FROM HFCount;
    END$$

DELIMITER ;