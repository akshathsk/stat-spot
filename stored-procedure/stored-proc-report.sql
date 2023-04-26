DELIMITER $$
CREATE DEFINER=`root`@`%` PROCEDURE `ReportProc`(IN athlete varchar(128))
BEGIN 
  DECLARE varSeason	varchar(128);
  DECLARE varRound	varchar(128);
  DECLARE varGameDate date; 
  DECLARE varHomeClubGoals INT; 
  DECLARE varAwayClubGoals INT; 
  DECLARE varWinner varchar(128); 
  DECLARE varClubId INT; 
  DECLARE varYoungPlayerName varchar(128); 
  DECLARE varTotalMarketValue REAL; 
  
  DECLARE exit_loop BOOLEAN DEFAULT FALSE;
  
  DECLARE custCur CURSOR FOR 
                            (
                                SELECT 
									G.season, 
									G.round, 
									G.gameDate, 
									G.homeClubGoals, 
									G.awayClubGoals
								FROM 
									`stat-spot-db`.Game G
										INNER JOIN `stat-spot-db`.Club C on C.clubId = G.homeClubId
										INNER JOIN `stat-spot-db`.Athlete A on A.clubId = C.clubId
								WHERE
									A.name = athlete
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
												)
								GROUP BY 
									C.clubId
                            );
  
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = TRUE;
    
  DROP TABLE IF EXISTS FinalReportTable;
  
  CREATE TABLE FinalReportTable (
      season varchar(128),
      round varchar(128),
      gameDate date,
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
    FETCH custCur INTO varSeason, varRound, varGameDate, varHomeClubGoals, varAwayClubGoals;
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
    
    INSERT INTO FinalReportTable VALUE (varSeason, varRound, varGameDate, varHomeClubGoals, varAwayClubGoals, varWinner);
    
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

    SELECT  
        * 
    FROM 
        FinalReportTable2;
END$$
DELIMITER ;
