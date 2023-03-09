package org.uiuc;

import org.apache.maven.shared.utils.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.uiuc.AppConstants.ATHLETE_INSERT;
import static org.uiuc.AppConstants.CLUB_INSERT;
import static org.uiuc.AppConstants.COACH_INSERT;
import static org.uiuc.AppConstants.COMPLETE_INSERT;
import static org.uiuc.AppConstants.COUNTRY_INSERT;
import static org.uiuc.AppConstants.GAME_INSERT;
import static org.uiuc.AppConstants.LEAGUE_INSERT;
import static org.uiuc.AppConstants.MEDALS_INSERT;
import static org.uiuc.AppConstants.PART_OF_INSERT;
import static org.uiuc.AppConstants.SPORT_INSERT;
import static org.uiuc.AppConstants.SUBSCRIBE_INSERT;
import static org.uiuc.AppConstants.TRAIN_INSERT;
import static org.uiuc.AppConstants.USER_DETAILS_INSERT;
import static org.uiuc.util.UtilHelper.createRandomDate;
import static org.uiuc.util.UtilHelper.emailGenerator;
import static org.uiuc.util.UtilHelper.firstNameGenerator;
import static org.uiuc.util.UtilHelper.getClubAvgAge;
import static org.uiuc.util.UtilHelper.getClubEstimatedValue;
import static org.uiuc.util.UtilHelper.getClubOwners;
import static org.uiuc.util.UtilHelper.getDuration;
import static org.uiuc.util.UtilHelper.getPay;
import static org.uiuc.util.UtilHelper.getPlayerCount;
import static org.uiuc.util.UtilHelper.getRandomFromSet;
import static org.uiuc.util.UtilHelper.getRecordFromLine;
import static org.uiuc.util.UtilHelper.getClubEstablishedYear;
import static org.uiuc.util.UtilHelper.lastNameGenerator;
import static org.uiuc.util.UtilHelper.medalGenerator;
import static org.uiuc.util.UtilHelper.passwordGenerator;
import static org.uiuc.util.UtilHelper.userNameGenerator;
import static org.uiuc.util.UtilHelper.xlsToList;

public class Main {

  static String newLine = System.getProperty("line.separator");
  static Map<String, Integer> countryToIdMap = new HashMap<>();
  static Map<String, Integer> sportToId = new HashMap<>();

  static Map<String, String> clubIdToCoach = new HashMap<>();

  static Map<String, String> athleteToClubId = new HashMap<>();

  static Map<String, String> clubIdToCoachId = new HashMap<>();

  static AtomicInteger coachCounter = new AtomicInteger(1);

  static AtomicInteger maxAthleteIdFromFirstList = new AtomicInteger(0);

  static Map<String, String> uniqueCodeToLeagueId = new HashMap<>();

  static Map<String, String> clubIdToLeagueUniqueCode = new HashMap<>();

  static Set<String> olympicsAthletes = new HashSet<>();

  static List<String> gameIds = new ArrayList<>();

  static List<String> userIds = new ArrayList<>();

  public static void main(String[] args) throws IOException {

    processClubs();
    processCountry();
    processSports();
    processAthlete();
    processAthleteFromOlympics();
    processLeague();
    processUser();
    processCoach();
    processCoachFootball();
    processAdditionalCoach();
    processGames();
    processTrains();
    processCompete();
    processPartOf();
    processSubscribe();
    processMedals();
  }

  private static void processClubs() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream is = loader.getResourceAsStream("clubs.csv");

    File file = new File(loader.getResource(".").getFile() + "/1-club.sql");
    FileWriter outputFileObj = new FileWriter(file);

    try (Scanner scanner = new Scanner(new InputStreamReader(is))) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        List<String> row = getRecordFromLine(scanner.nextLine());
        String insertRow =
            CLUB_INSERT
                .replace("{clubId}", row.get(0))
                .replace("{name}", row.get(2))
                .replace("{owner}", getClubOwners())
                .replace("{established}", String.valueOf(getClubEstablishedYear()))
                .replace(
                    "{totalMarketValue}",
                    StringUtils.isEmpty(row.get(4))
                        ? String.valueOf(getClubEstimatedValue())
                        : row.get(4))
                .replace("{squadSize}", row.get(5))
                .replace(
                    "{averageAge}",
                    StringUtils.isEmpty(row.get(6)) ? String.valueOf(getClubAvgAge()) : row.get(6))
                .replace("{totalNationalTeamPlayers}", row.get(9));

        clubIdToLeagueUniqueCode.put(row.get(0), row.get(3));

        outputFileObj.write(insertRow + newLine);
      }
      outputFileObj.close();
    }
  }

  private static void processCountry() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream is = loader.getResourceAsStream("players.csv");

    File file = new File(loader.getResource(".").getFile() + "/2-country.sql");
    FileWriter outputFileObj = new FileWriter(file);

    int counter = 1;

    try (Scanner scanner = new Scanner(new InputStreamReader(is))) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        List<String> row = getRecordFromLine(scanner.nextLine());
        String citizenship = row.get(3).replace("\"", "");
        String birth = row.get(4).replace("\"", "").replace("\'", "");
        if (StringUtils.isNotEmpty(citizenship) && !countryToIdMap.containsKey(citizenship)) {
          countryToIdMap.put(citizenship, counter);
          counter++;
        }
        if (StringUtils.isNotEmpty(birth) && !countryToIdMap.containsKey(birth)) {
          countryToIdMap.put(birth, counter);
          counter++;
        }
      }
      countryToIdMap.forEach(
          (k, v) -> {
            String insertRow =
                COUNTRY_INSERT
                    .replace("{countryId}", String.valueOf(v))
                    .replace("{name}", k)
                    .replace("{continent}", "");
            try {
              outputFileObj.write(insertRow + newLine);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          });
      outputFileObj.close();
    }
  }

  private static void processSports() throws IOException {

    URL url = Main.class.getClassLoader().getResource("Athletes.xlsx");
    List<List<String>> rows = xlsToList(url);
    AtomicInteger counter = new AtomicInteger(1);
    rows.forEach(
        row -> {
          String sport = row.get(2);
          if (StringUtils.isNotEmpty(sport) && !sportToId.containsKey(sport)) {
            sportToId.put(sport, counter.get());
            counter.set(counter.get() + 1);
          }
        });

    URL url2 = Main.class.getClassLoader().getResource("Coaches.xlsx");
    List<List<String>> rows2 = xlsToList(url2);
    rows2.forEach(
        row -> {
          String sport = row.get(2);
          if (StringUtils.isNotEmpty(sport) && !sportToId.containsKey(sport)) {
            sportToId.put(sport, counter.get());
            counter.set(counter.get() + 1);
          }
        });

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    File file = new File(loader.getResource(".").getFile() + "/3-sport.sql");
    FileWriter outputFileObj = new FileWriter(file);

    sportToId.forEach(
        (k, v) -> {
          String insertRow =
              SPORT_INSERT
                  .replace("{sportId}", String.valueOf(v))
                  .replace("{name}", k)
                  .replace("{description}", k + " is a competitive sport.")
                  .replace("{playerCount}", String.valueOf(getPlayerCount()))
                  .replace("{duration}", String.valueOf(getDuration()));
          try {
            outputFileObj.write(insertRow + newLine);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
    outputFileObj.close();
  }

  private static void processAthlete() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream is = loader.getResourceAsStream("players.csv");

    File file = new File(loader.getResource(".").getFile() + "/4-athlete.sql");
    FileWriter outputFileObj = new FileWriter(file);

    try (Scanner scanner = new Scanner(new InputStreamReader(is))) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        List<String> row = getRecordFromLine(scanner.nextLine());
        if (row.size() == 23) {
          String insertRow =
              ATHLETE_INSERT
                  .replace("{athleteId}", row.get(0))
                  .replace("{name}", row.get(1).replace("\"", "").replace("\'", ""))
                  .replace(
                      "{dateOfBirth}",
                      StringUtils.isNotEmpty(row.get(7))
                          ? row.get(7)
                          : createRandomDate(1995, 2004))
                  .replace("{position}", row.get(8))
                  .replace("{marketValue}", "100")
                  .replace("{sex}", "Male")
                  .replace("{sportId}", String.valueOf(sportToId.get("Football")))
                  .replace(
                      "{countryId}",
                      String.valueOf(
                          countryToIdMap.get(row.get(4).replace("\"", "").replace("\'", ""))))
                  .replace("{clubId}", row.get(2));
          athleteToClubId.put(row.get(0), row.get(2));
          if (maxAthleteIdFromFirstList.get() < Integer.parseInt(row.get(0))) {
            maxAthleteIdFromFirstList.set(Integer.parseInt(row.get(0)));
          }
          outputFileObj.write(insertRow + newLine);
        }
      }
      outputFileObj.close();
    }
  }

  private static void processAthleteFromOlympics() throws IOException {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    File file = new File(loader.getResource(".").getFile() + "/4-athlete2.sql");
    FileWriter outputFileObj = new FileWriter(file);

    URL url = Main.class.getClassLoader().getResource("Athletes.xlsx");
    List<List<String>> rows = xlsToList(url);

    rows.forEach(
        row -> {
          Integer sportId = sportToId.get(row.get(2));
          Integer countryId = countryToIdMap.get(row.get(1));
          maxAthleteIdFromFirstList.set(maxAthleteIdFromFirstList.get() + 1);
          String insertRow =
              ATHLETE_INSERT
                  .replace("{athleteId}", String.valueOf(maxAthleteIdFromFirstList.get()))
                  .replace("{name}", row.get(0).replace("\"", "").replace("\'", ""))
                  .replace("{dateOfBirth}", createRandomDate(1995, 2004))
                  .replace("{position}", "")
                  .replace("{marketValue}", "100")
                  .replace("{sex}", "")
                  .replace("{sportId}", String.valueOf(sportId))
                  .replace("{countryId}", String.valueOf(countryId))
                  .replace("{clubId}", "null");
          olympicsAthletes.add(String.valueOf(maxAthleteIdFromFirstList.get()));
          try {
            outputFileObj.write(insertRow + newLine);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
    outputFileObj.close();
  }

  private static void processLeague() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream is = loader.getResourceAsStream("competitions.csv");

    File file = new File(loader.getResource(".").getFile() + "/5-league.sql");
    FileWriter outputFileObj = new FileWriter(file);

    AtomicInteger counter = new AtomicInteger(1);
    try (Scanner scanner = new Scanner(new InputStreamReader(is))) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        List<String> row = getRecordFromLine(scanner.nextLine());
        String insertRow =
            LEAGUE_INSERT
                .replace("{leagueId}", String.valueOf(counter.get()))
                .replace("{type}", row.get(2))
                .replace("{subType}", row.get(3))
                .replace("{name}", row.get(4));

        counter.set(counter.get() + 1);
        uniqueCodeToLeagueId.put(row.get(0), String.valueOf(counter.get()));

        outputFileObj.write(insertRow + newLine);
      }
      outputFileObj.close();
    }
  }

  private static void processUser() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    File file = new File(loader.getResource(".").getFile() + "/6-user.sql");
    FileWriter outputFileObj = new FileWriter(file);

    int counter = 1;

    for (int i = 0; i < 1500; i++) {
      String firstName = firstNameGenerator();
      String lastName = lastNameGenerator();
      String userName = userNameGenerator(firstName, lastName);
      String email = emailGenerator(userName);
      String insertRow =
          USER_DETAILS_INSERT
              .replace("{userId}", String.valueOf(counter))
              .replace("{userName}", userName)
              .replace("{password}", passwordGenerator())
              .replace("{email}", email)
              .replace("{firstName}", firstName)
              .replace("{lastName}", lastName);

      userIds.add(String.valueOf(counter));

      counter++;
      outputFileObj.write(insertRow + newLine);
    }
    outputFileObj.close();
  }

  private static void processCoach() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    File file = new File(loader.getResource(".").getFile() + "/7-coaches.sql");
    FileWriter outputFileObj = new FileWriter(file);

    URL url = Main.class.getClassLoader().getResource("Coaches.xlsx");
    List<List<String>> rows = xlsToList(url);

    rows.forEach(
        row -> {
          String insertRow =
              COACH_INSERT
                  .replace("{coachId}", String.valueOf(coachCounter))
                  .replace("{name}", row.get(0))
                  .replace(
                      "{countryId}",
                      String.valueOf(
                          countryToIdMap.get(row.get(1).replace("\"", "").replace("\'", ""))))
                  .replace("{dateOfBirth}", createRandomDate(1960, 1980))
                  .replace("{clubId}", "null");
          coachCounter.set(coachCounter.get() + 1);
          try {
            outputFileObj.write(insertRow + newLine);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
    outputFileObj.close();
  }

  private static void processCoachFootball() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream is = loader.getResourceAsStream("club_games.csv");

    try (Scanner scanner = new Scanner(new InputStreamReader(is))) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        List<String> row = getRecordFromLine(scanner.nextLine());
        String clubId = row.get(0);
        String manager = row.get(4);
        String otherClubId = row.get(5);
        String otherManager = row.get(8);
        if (StringUtils.isNotEmpty(clubId)
            && StringUtils.isNotEmpty(manager)
            && !clubIdToCoach.containsKey(clubId)) {
          clubIdToCoach.put(clubId, manager);
        }
        if (StringUtils.isNotEmpty(otherClubId)
            && StringUtils.isNotEmpty(otherManager)
            && !clubIdToCoach.containsKey(otherClubId)) {
          clubIdToCoach.put(otherClubId, otherManager);
        }
      }
    }

    File file = new File(loader.getResource(".").getFile() + "/7-coaches2.sql");
    FileWriter outputFileObj = new FileWriter(file);

    clubIdToCoach.forEach(
        (k, v) -> {
          if (clubIdToLeagueUniqueCode.keySet().contains(k)) {
            String insertRow =
                COACH_INSERT
                    .replace("{coachId}", String.valueOf(coachCounter))
                    .replace("{name}", v.replace("\"", "").replace("\'", ""))
                    .replace(
                        "{countryId}",
                        getRandomFromSet(
                            countryToIdMap.values().stream()
                                .map(String::valueOf)
                                .collect(Collectors.toList())))
                    .replace("{dateOfBirth}", createRandomDate(1960, 1980))
                    .replace("{clubId}", k);
            clubIdToCoachId.put(k, String.valueOf(coachCounter));
            try {
              outputFileObj.write(insertRow + newLine);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
            coachCounter.set(coachCounter.get() + 1);
          }
        });
    outputFileObj.close();
  }

  private static void processAdditionalCoach() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    File file = new File(loader.getResource(".").getFile() + "/7-coaches-3.sql");
    FileWriter outputFileObj = new FileWriter(file);

    URL url = Main.class.getClassLoader().getResource("Coaches.xlsx");
    List<List<String>> rows = xlsToList(url);

    for (int i = 0; i < 1500; i++) {
      String firstName = firstNameGenerator();
      String lastName = lastNameGenerator();
      String insertRow =
          COACH_INSERT
              .replace("{coachId}", String.valueOf(coachCounter))
              .replace("{name}", String.join(" ", firstName, lastName))
              .replace(
                  "{countryId}",
                  String.valueOf(
                      getRandomFromSet(
                          countryToIdMap.values().stream()
                              .map(String::valueOf)
                              .collect(Collectors.toList()))))
              .replace("{dateOfBirth}", createRandomDate(1960, 1980))
              .replace("{clubId}", "null");
      coachCounter.set(coachCounter.get() + 1);
      try {
        outputFileObj.write(insertRow + newLine);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    outputFileObj.close();
  }

  private static void processGames() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream is = loader.getResourceAsStream("games.csv");

    File file = new File(loader.getResource(".").getFile() + "/8-game.sql");
    FileWriter outputFileObj = new FileWriter(file);

    try (Scanner scanner = new Scanner(new InputStreamReader(is))) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        List<String> row = getRecordFromLine(scanner.nextLine());
        if (!gameIds.contains(row.get(0))
            && clubIdToLeagueUniqueCode.keySet().contains(row.get(6))
            && clubIdToLeagueUniqueCode.keySet().contains(row.get(7))) {
          String insertRow =
              GAME_INSERT
                  .replace("{gameId}", row.get(0))
                  .replace("{season}", row.get(3))
                  .replace("{round}", row.get(4))
                  .replace("{gameDate}", row.get(5))
                  .replace("{homeClubId}", row.get(6))
                  .replace("{awayClubId}", row.get(7))
                  .replace("{homeClubGoals}", row.get(8))
                  .replace("{awayClubGoals}", row.get(9));

          gameIds.add(row.get(0));
          outputFileObj.write(insertRow + newLine);
        }
      }
    }
    outputFileObj.close();
  }

  private static void processTrains() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    File file = new File(loader.getResource(".").getFile() + "/9-trains.sql");
    FileWriter outputFileObj = new FileWriter(file);

    athleteToClubId.forEach(
        (k, v) -> {
          String coachId = clubIdToCoachId.get(k);
          if (StringUtils.isNotEmpty(coachId)) {
            String insertRow = TRAIN_INSERT.replace("{athleteId}", k).replace("{coachId}", coachId);
            try {
              outputFileObj.write(insertRow + newLine);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          }
        });
    outputFileObj.close();
  }

  private static void processCompete() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    File file = new File(loader.getResource(".").getFile() + "/10-compete.sql");
    FileWriter outputFileObj = new FileWriter(file);

    olympicsAthletes.forEach(
        atlheteId -> {
          String insertRow =
              COMPLETE_INSERT
                  .replace("{tournamentId}", String.valueOf(1))
                  .replace("{athleteId}", atlheteId);
          try {
            outputFileObj.write(insertRow + newLine);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
    outputFileObj.close();
  }

  private static void processPartOf() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    File file = new File(loader.getResource(".").getFile() + "/11-partOf.sql");
    FileWriter outputFileObj = new FileWriter(file);

    clubIdToLeagueUniqueCode.forEach(
        (k, v) -> {
          String leagueId = uniqueCodeToLeagueId.get(clubIdToLeagueUniqueCode.get(k));
          String insertRow = PART_OF_INSERT.replace("{clubId}", k).replace("{leagueId}", leagueId);
          try {
            outputFileObj.write(insertRow + newLine);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
    outputFileObj.close();
  }

  private static void processSubscribe() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    File file = new File(loader.getResource(".").getFile() + "/12-subscribe.sql");
    FileWriter outputFileObj = new FileWriter(file);

    Set<String> localSet = new HashSet<>();

    userIds.forEach(
        user -> {
          String gameId = getRandomFromSet(gameIds);

          if (!localSet.contains(String.join("_", user, gameId))) {
            String insertRow =
                SUBSCRIBE_INSERT
                    .replace("{gameId}", gameId)
                    .replace("{userId}", user)
                    .replace("{pay}", String.valueOf(getPay()));
            try {
              outputFileObj.write(insertRow + newLine);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
            localSet.add(String.join("_", user, gameId));
          }
        });
    outputFileObj.close();
  }

  private static void processMedals() throws IOException {

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    File file = new File(loader.getResource(".").getFile() + "/13-medals.sql");
    FileWriter outputFileObj = new FileWriter(file);

    AtomicInteger counter = new AtomicInteger(1);
    olympicsAthletes.forEach(
        athlete -> {
          String insertRow =
              MEDALS_INSERT
                  .replace("{medalId}", String.valueOf(counter.get()))
                  .replace("{tournamentId}", String.valueOf(1))
                  .replace("{type}", medalGenerator())
                  .replace("{athleteId}", athlete);
          try {
            outputFileObj.write(insertRow + newLine);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          counter.set(counter.get() + 1);
        });
    outputFileObj.close();
  }
}
