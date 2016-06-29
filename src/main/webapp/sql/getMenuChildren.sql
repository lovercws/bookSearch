DROP FUNCTION IF EXISTS `getMenuChildren`;
DELIMITER // --这个分隔符不能少
CREATE FUNCTION `getMenuChildren`(rootId INT) 
RETURNS VARCHAR(1000)  
	BEGIN  
		DECLARE sTemp VARCHAR(1000) ;
		DECLARE sTempChd VARCHAR(1000);
		
		SET sTemp = '';
		SET sTempChd =rootId;
		
		WHILE sTempChd IS NOT NULL DO
		
		        IF sTemp='' 
		        THEN
		           SET sTemp=sTempChd;
		        ELSE
			    SET sTemp = CONCAT(sTemp,',',sTempChd);
		        END IF;
		        
			
			SELECT GROUP_CONCAT(menu_id) INTO sTempChd FROM tb_menu WHERE FIND_IN_SET(parent_menu_id,sTempChd)>0;
			
		END WHILE;
		
		RETURN sTemp;
	END;

