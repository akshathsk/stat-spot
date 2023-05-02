package com.uiuc.statspot.helper.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.uiuc.statspot.model.Report1;
import com.uiuc.statspot.model.Report2;
import jakarta.mail.util.ByteArrayDataSource;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelService {

  public static jakarta.activation.DataSource generateExcel(
      List<Report1> report1List, List<Report2> report2List) throws IOException {

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Athlete Club Details");
    int rowCount = 0;
    Row row = sheet.createRow(rowCount++);
    int columnCount = 0;
    Cell cell1 = row.createCell(columnCount++);
    cell1.setCellValue("Away Club");
    Cell cell4 = row.createCell(columnCount++);
    cell4.setCellValue("Home Club Goals");
    Cell cell5 = row.createCell(columnCount++);
    cell5.setCellValue("Away Club Goals");
    Cell cell6 = row.createCell(columnCount++);
    cell6.setCellValue("Winner");

    for (Report1 report1 : report1List) {
      Row row1 = sheet.createRow(rowCount++);
      int colCount = 0;
      Cell c1 = row1.createCell(colCount++);
      c1.setCellValue(report1.getAwayClubName());
      Cell c4 = row1.createCell(colCount++);
      c4.setCellValue(report1.getHomeClubGoals());
      Cell c5 = row1.createCell(colCount++);
      c5.setCellValue(report1.getAwayClubGoals());
      Cell c6 = row1.createCell(colCount++);
      c6.setCellValue(report1.getWinner());
    }

    XSSFSheet sheet2 = workbook.createSheet("Young Player Details");

    int rowCount2 = 0;
    Row row2 = sheet2.createRow(rowCount2++);
    int columnCount2 = 0;
    Cell c1 = row2.createCell(columnCount2++);
    c1.setCellValue("Player");
    Cell c2 = row2.createCell(columnCount2++);
    c2.setCellValue("Total Market Value");

    for (Report2 report2 : report2List) {
      Row row3 = sheet2.createRow(rowCount2++);
      int columnCount3 = 0;
      Cell cel1 = row3.createCell(columnCount3++);
      cel1.setCellValue(report2.getPlayerName());
      Cell cel2 = row3.createCell(columnCount3++);
      cel2.setCellValue(report2.getTotalMarketValue());
    }

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    workbook.write(bos);
    jakarta.activation.DataSource fds =
        new ByteArrayDataSource(bos.toByteArray(), "application/vnd.ms-excel");
    return fds;
  }
}
