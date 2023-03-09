package org.uiuc.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UtilHelper {

  private static final String COMMA_DELIMITER = ",";

  // Define the characters to be used in the password
  private static final String CHARACTERS =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*<>,.?";

  // Define the length of the password
  private static final int PASSWORD_LENGTH = 10;

  // Define a secure random number generator
  private static final Random SECURE_RANDOM = new SecureRandom();

  public static List<String> getRecordFromLine(String line) {
    List<String> values = new ArrayList<>();
    try (Scanner rowScanner = new Scanner(line)) {
      rowScanner.useDelimiter(COMMA_DELIMITER);
      while (rowScanner.hasNext()) {
        values.add(rowScanner.next());
      }
    }
    return values;
  }

  public static String getClubOwners() {
    List<String> list =
        Arrays.asList(
            "Stan Kroenke",
            "Nassef Sawiris",
            "Wesley Edens",
            "William P. Foley",
            "Matthew Benham",
            "Tony Bloom",
            "Clearlake Capital Group",
            "Todd Boehly",
            "Hansjorg Wyss",
            "Steve Parish",
            "Andrea Radrizzani",
            "The Srivaddhanaprabha Family",
            "Abu Dhabi United Group",
            "Glazer Family",
            "Evangelos Marinakis");
    Random randomizer = new Random();
    String random = list.get(randomizer.nextInt(list.size()));
    return random;
  }

  public static int getClubEstablishedYear() {
    Random random = new Random();
    return random.nextInt(2010 - 1950) + 1950;
  }

  public static int getClubEstimatedValue() {
    Random random = new Random();
    return random.nextInt(100 - 10) + 10;
  }

  public static int getClubAvgAge() {
    Random random = new Random();
    return random.nextInt(32 - 15) + 15;
  }

  public static List<List<String>> xlsToList(URL url) {
    List<List<String>> list = new ArrayList<>();
    try {
      FileInputStream file = new FileInputStream(new File(url.toURI()));
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.iterator();
      rowIterator.next();
      while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();
        List<String> strList = new ArrayList<>();
        while (cellIterator.hasNext()) {
          strList.add(cellIterator.next().getStringCellValue());
        }
        list.add(strList);
      }
      file.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public static String passwordGenerator() {

    StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
    for (int i = 0; i < PASSWORD_LENGTH; i++) {
      int randomIndex = SECURE_RANDOM.nextInt(CHARACTERS.length());
      sb.append(CHARACTERS.charAt(randomIndex));
    }
    return sb.toString();
  }

  public static String userNameGenerator(String firstName, String lastName) {
    Random random = new Random();
    return String.join("", firstName, lastName, String.valueOf(random.nextInt(2010 - 1950) + 1950));
  }

  public static String emailGenerator(String userName) {
    List<String> list =
        Arrays.asList(
            "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "icloud.com", "aol.com");
    Random randomizer = new Random();
    String random = list.get(randomizer.nextInt(list.size()));
    return String.join("@", userName, random);
  }

  public static String firstNameGenerator() {
    List<String> list =
        Arrays.asList(
            "John", "Jane", "Bob", "Sarah", "David", "Lisa", "Jerry", "Amy", "Kevin", "Nancy",
            "Andrea", "Srivad", "Abu", "Smith", "Smith", "Wilson");
    Random randomizer = new Random();
    String random = list.get(randomizer.nextInt(list.size()));
    return random;
  }

  public static String lastNameGenerator() {
    List<String> list =
        Arrays.asList(
            "Doe",
            "Smith",
            "Brown",
            "Lee",
            "Chen",
            "Wu",
            "Nguyen",
            "Tran",
            "Anderson",
            "Baker",
            "Carter",
            "Davis",
            "Edwards",
            "Fisher",
            "Green",
            "Harris",
            "Johnson",
            "Kelly",
            "Lee",
            "Mitchell",
            "Nelson",
            "Olson",
            "Patel",
            "Quinn",
            "Robinson");
    Random randomizer = new Random();
    String random = list.get(randomizer.nextInt(list.size()));
    return random;
  }

  public static int getPay() {
    Random random = new Random();
    return random.nextInt(10 - 2) + 2;
  }

  public static String getRandomFromSet(List<String> list) {
    Random randomizer = new Random();
    return list.get(randomizer.nextInt(list.size()));
  }

  public static String medalGenerator() {
    List<String> list = Arrays.asList("Gold", "Silver", "Bronze");
    Random randomizer = new Random();
    String random = list.get(randomizer.nextInt(list.size()));
    return random;
  }

  public static int getPlayerCount() {
    Random random = new Random();
    return random.nextInt(25 - 2) + 2;
  }

  public static int getDuration() {
    Random random = new Random();
    return random.nextInt(32 - 15) + 15;
  }

  public static int createRandomIntBetween(int start, int end) {
    return start + (int) Math.round(Math.random() * (end - start));
  }

  public static String createRandomDate(int startYear, int endYear) {
    int day = createRandomIntBetween(1, 28);
    int month = createRandomIntBetween(1, 12);
    int year = createRandomIntBetween(startYear, endYear);
    return LocalDate.of(year, month, day).toString();
  }
}
