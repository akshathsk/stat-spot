DELIMITER //
CREATE TRIGGER UpdateMarketValue AFTER UPDATE ON Athlete FOR EACH ROW
BEGIN
    IF New.marketValue != Old.marketValue THEN
		update 
			Club 
		set 
			totalMarketValue = totalMarketValue + (New.marketValue - Old.marketValue)
		where 
			clubId = (
						select 
							A.clubId 
						from 
							Athlete A 
						where 
							A.athleteId = New.athleteId
					 );
    END IF;
END//
DELIMITER ;