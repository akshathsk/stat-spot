DELIMITER //
CREATE TRIGGER AthleteHistory BEFORE DELETE ON Athlete FOR EACH ROW
BEGIN
    SET @dateOfBirth = (
					select
						dateOfBirth
					from 
						Athlete
					where
						athleteId = Old.athleteId
			   );
    
    IF @dateOfBirth > '2000-01-01' THEN
        SET @mktVal = Old.marketValue + 10.0;
    END IF;

delete from Compete where athleteId = Old.athleteId;

insert into AthleteHistory
values 
	(
		Old.athleteId, 
        Old.name, 
        Old.dateOfBirth,
		Old.position,
		@mktVal,
		Old.sex,
		Old.sportId,
		Old.countryId,
		Old.clubId
	);
END//
DELIMITER ;