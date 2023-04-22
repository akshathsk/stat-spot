package com.uiuc.statspot.service;

import com.uiuc.statspot.dto.AthleteMetadataDto;
import com.uiuc.statspot.dto.ClubDto;
import com.uiuc.statspot.dto.CountryDto;
import com.uiuc.statspot.dto.SportDto;
import com.uiuc.statspot.model.Athlete;
import com.uiuc.statspot.repository.AthleteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {

  AthleteRepository athleteRepository;

  public AthleteService(AthleteRepository athleteRepository) {
    this.athleteRepository = athleteRepository;
  }

  public void createAthlete(Athlete athlete) {
    athleteRepository.createAthlete(athlete);
  }

  public void updateAthlete(Athlete athlete, Integer athleteId) {
    athleteRepository.updateAthlete(athlete, athleteId);
  }

  public void deleteAthlete(String athleteId) {
    athleteRepository.deleteAthlete(athleteId);
  }

  public AthleteMetadataDto getAthleteMetadata() {

    List<ClubDto> clubDtos = athleteRepository.getClubsMetadata();
    List<CountryDto> countriesDtos = athleteRepository.getCountriesMetadata();
    List<SportDto> sportsDtos = athleteRepository.getSportsMetadata();
    AthleteMetadataDto athleteMetadataDto = new AthleteMetadataDto();
    athleteMetadataDto.setClubs(clubDtos);
    athleteMetadataDto.setCountries(countriesDtos);
    athleteMetadataDto.setSports(sportsDtos);
    return athleteMetadataDto;
  }

  public List<String> getAthleteNames() {
    return athleteRepository.getAthleteNames();
  }
}
