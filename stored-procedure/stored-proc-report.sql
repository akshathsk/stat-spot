DELIMITER $$
CREATE DEFINER=`root`@`%` PROCEDURE `ReportProc`(IN athlete varchar(128))
BEGIN 
  DECLARE varAwayClubName	varchar(128);
  DECLARE varHomeClubGoals INT; 
  DECLARE varAwayClubGoals INT; 
  DECLARE varWinner varchar(128); 
  DECLARE varClubId INT; 
  DECLARE varYoungPlayerName varchar(128); 
  DECLARE varTotalMarketValue REAL; 
  
  DECLARE exit_loop BOOLEAN DEFAULT FALSE;
  
  -- Player's club's perf in games against away sides
  DECLARE custCur CURSOR FOR 
                            (
                                SELECT 
									C1.name as awayClubName, 
									temp.total_home_goals as homeGoalsTotal,
									temp.total_away_goals as awayGoalsTotal
								from 
									Club C1
										INNER JOIN 
											(
											SELECT 
												G.awayClubId,
												sum(G.homeClubGoals) as total_home_goals,
												sum(G.awayClubGoals) as total_away_goals
											FROM 
												`stat-spot-db`.Game G
													INNER JOIN `stat-spot-db`.Club C on C.clubId = G.homeClubId
													INNER JOIN `stat-spot-db`.Athlete A on A.clubId = C.clubId
											WHERE
												A.name = athlete
											GROUP BY
												awayClubId
											) AS temp ON C1.clubId = temp.awayClubId
                            );
                            
    DECLARE cur2 CURSOR FOR 
                            (
                                SELECT 
									C.clubId, 
									C.name, 
									sum(A.marketValue) as TotalMarketValue 
								FROM 
									`stat-spot-db`.Club C 
										LEFT JOIN `stat-spot-db`.Athlete A on C.clubId = A.clubId 
								WHERE 
									A.dateOfBirth > '1997-01-01' 
									and
									A.clubId = (
												select 
													A1.clubId
												from
													Athlete A1
												where
													A1.name = athlete
												LIMIT 1
												)
								GROUP BY 
									C.clubId
                            );
  
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = TRUE;
    
  DROP TABLE IF EXISTS FinalReportTable;
  
  CREATE TABLE FinalReportTable (
      awayClubName varchar(128),
      homeClubGoals INT,
      awayClubGoals	INT,
      winner varchar(128)
  );
  
  DROP TABLE IF EXISTS FinalReportTable2;
  
  CREATE TABLE FinalReportTable2 (
      clubId INT,
      playerName varchar(128),
      TotalMarketValue REAL
  );
  
  OPEN custCur;
    cloop: LOOP
    FETCH custCur INTO varAwayClubName, varHomeClubGoals, varAwayClubGoals;
    IF exit_loop THEN
        LEAVE cloop;
    END IF;  

    IF varHomeClubGoals > varAwayClubGoals THEN
        SET varWinner = 'WON';
    ELSEIF  varHomeClubGoals < varAwayClubGoals THEN
        SET varWinner = 'LOST';
	ELSEIF  varHomeClubGoals = varAwayClubGoals THEN
        SET varWinner = 'DRAW';
    END IF;
    
    INSERT INTO FinalReportTable VALUE (varAwayClubName, varHomeClubGoals, varAwayClubGoals, varWinner);
    
    END LOOP cloop;
    CLOSE custCur;
    
    SET exit_loop = FALSE;
    
    OPEN cur2;
	  cloop: LOOP
	  FETCH cur2 INTO varClubId, varYoungPlayerName, varTotalMarketValue;
	  IF exit_loop THEN
		LEAVE cloop;
	  END IF;  
	
	  INSERT INTO FinalReportTable2 VALUE (varClubId, varYoungPlayerName, varTotalMarketValue);
	
	  END LOOP cloop;
	  CLOSE cur2;
      
END$$
DELIMITER ;
