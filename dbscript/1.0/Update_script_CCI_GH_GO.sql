
--------------------------------------------------------------------------------------------------------------------------------------------------
--- Update script for adding data to columns passwordSalt in Login Table and isVisibleToSeason in LookupDepartments tables on 6th August 2015-----
--------------------------------------------------------------------------------------------------------------------------------------------------
/* UPDATE `cci_gh_go_prod`.`Login` l ,`cci_go`.`userlogin` ul
SET l.`passwordSalt` = ul.`PasswordSalt`
WHERE l.`password` = ul.`Password` AND l.loginId = ul.LoginID; */

UPDATE `cci_gh_go`.`Login` 
SET passwordSalt = 'passwordSalt';

UPDATE `cci_gh_go`.`LookupDepartments`
SET `isVisibleToSeason` = 1
WHERE `departmentId` IN (1,2,3);