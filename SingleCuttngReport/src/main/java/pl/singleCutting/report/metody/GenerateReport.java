package pl.singleCutting.report.metody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javafx.stage.FileChooser;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class GenerateReport {

	static String xlsReportPath = "C:\\_raportSingleCuttingTestJava\\raportyExcel\\";

	public static void generateReportInExcel(String dateFrom, String dateTo, String dbPath, File file) {
//		File fileXls = null;
		Connection connection = null;
//		FileOutputStream fileOut = null;
//		try {
//			 fileOut = new FileOutputStream("raport.xls");
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			// ORGINALNE PREPARED STATEMENT
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT * FROM TABELA_SPRAWDZANIE_MATERIALU WHERE DATE BETWEEN ? AND ? GROUP BY MODEL_POLARYZATORA, TRYB_PRACY ORDER BY DATE ASC");
			pstmt.setString(1, dateFrom);
			pstmt.setString(2, dateTo);

			@SuppressWarnings("resource")
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Raport");

			// RAPORT SEKCJI 1 SPRAWDZANIE MATETRIA£U
			// tworzenie pliku excela
			// https://stackoverflow.com/questions/8563376/exporting-sql-query-result-to-a-csv-or-excel
			HSSFRow wierszNaglowek = sheet.createRow((short) 9);
			wierszNaglowek.createCell((short) 0).setCellValue("SEKCJA SPRAWDZANIE MATERIA£U");
			wierszNaglowek = sheet.createRow((short) 10);
			wierszNaglowek.createCell((short) 0).setCellValue("Data");
			wierszNaglowek.createCell((short) 1).setCellValue("Godzina");
			wierszNaglowek.createCell((short) 2).setCellValue("Model");
			wierszNaglowek.createCell((short) 3).setCellValue("Ilosc ca³kowita");
			wierszNaglowek.createCell((short) 4).setCellValue("Ilosc ok");
			wierszNaglowek.createCell((short) 5).setCellValue("Ilosc NG");
			wierszNaglowek.createCell((short) 6).setCellValue("Tryb pracy");
			wierszNaglowek.createCell((short) 7).setCellValue("Ilosc na podk³adki");
			wierszNaglowek.createCell((short) 8).setCellValue("Operator");

			ResultSet rs = pstmt.executeQuery();
			int indexExcel = 11;
			while (rs.next()) {
				String date = rs.getString("DATE");
				String time = rs.getString("TIME");
				String modelPolaryzatora = rs.getString("MODEL_POLARYZATORA");
				int iloscCalkowita = rs.getInt("ILOSC_CALKOWITA");
				int iloscOk = rs.getInt("ILOSC_OK");
				int iloscNG = rs.getInt("ILOSC_NG");
				String trybPracy = rs.getString("TRYB_PRACY");
				int iloscNaPodkladki = rs.getInt("ILOSC_NA_PODKLADKI");
				String operator = rs.getString("OPERATOR");
				System.out.println(date + "  " + time + "  " + modelPolaryzatora + "  " + iloscCalkowita + "  "
						+ iloscOk + "  " + iloscNG + "  " + trybPracy + "  " + iloscNaPodkladki + "  " + operator);

				// wstawianie danych do excela
				HSSFRow wierszExcel = sheet.createRow((short) indexExcel);
				wierszExcel.createCell((short) 0).setCellValue(date);
				wierszExcel.createCell((short) 1).setCellValue(time);
				wierszExcel.createCell((short) 2).setCellValue(modelPolaryzatora);
				wierszExcel.createCell((short) 3).setCellValue(iloscCalkowita);
				wierszExcel.createCell((short) 4).setCellValue(iloscOk);
				wierszExcel.createCell((short) 5).setCellValue(iloscNG);
				wierszExcel.createCell((short) 6).setCellValue(trybPracy);
				wierszExcel.createCell((short) 7).setCellValue(iloscNaPodkladki);
				wierszExcel.createCell((short) 8).setCellValue(operator);
				indexExcel = indexExcel + 1;
			}

			// RAPORT SEKCJI 2 PRZEDUK POLARYZATOROW
			indexExcel = indexExcel + 5;
			wierszNaglowek = sheet.createRow((short) indexExcel);
			wierszNaglowek.createCell((short) 0).setCellValue("SEKCJA PRZEDRUK POLARYZATORÓW");
			indexExcel = indexExcel + 1;
			wierszNaglowek = sheet.createRow((short) indexExcel);
			wierszNaglowek.createCell((short) 0).setCellValue("Data");
			wierszNaglowek.createCell((short) 1).setCellValue("Godzina");
			wierszNaglowek.createCell((short) 2).setCellValue("lot");
			wierszNaglowek.createCell((short) 3).setCellValue("Ilosc ca³kowita");
			wierszNaglowek.createCell((short) 4).setCellValue("Model");
			wierszNaglowek.createCell((short) 5).setCellValue("Operator");

			pstmt = null;
			rs = null;
			pstmt = connection.prepareStatement(
					"SELECT * FROM TABELA_PRZEDRUK_POLARYZATOROW WHERE DATE BETWEEN ? AND ?  ORDER BY DATE ASC");
			pstmt.setString(1, dateFrom);
			pstmt.setString(2, dateTo);
			rs = pstmt.executeQuery();

			System.out.println("indexExcelPrzedruk " + indexExcel);

			indexExcel = indexExcel + 1;
			while (rs.next()) {
				String date = rs.getString("DATE");
				String time = rs.getString("TIME");
				String lot = rs.getString("LOT");
				int iloscCalkowita = rs.getInt("AMOUNT");
				String model = rs.getString("MODEL");
				String operator = rs.getString("OPERATOR");
				System.out.println("PRZEDRUK POLARYZATOROW " + date + "  " + time + "  " + lot + "  " + iloscCalkowita
						+ "  " + model + "  " + operator);

				HSSFRow wierszExcel = sheet.createRow((short) indexExcel);
				wierszExcel.createCell((short) 0).setCellValue(date);
				wierszExcel.createCell((short) 1).setCellValue(time);
				System.out.println(time);
				wierszExcel.createCell((short) 2).setCellValue(lot);
				wierszExcel.createCell((short) 3).setCellValue(iloscCalkowita);
				wierszExcel.createCell((short) 4).setCellValue(model);
				wierszExcel.createCell((short) 5).setCellValue(operator);
				indexExcel = indexExcel + 1;

			}

			// RAPORT SEKCJI 3 KLEJENIE
			indexExcel = indexExcel + 5;
			wierszNaglowek = sheet.createRow((short) indexExcel);
			wierszNaglowek.createCell((short) 0).setCellValue("SEKCJA KLEJENIE PODK£ADEK");
			indexExcel = indexExcel + 1;
			wierszNaglowek = sheet.createRow((short) indexExcel);
			wierszNaglowek.createCell((short) 0).setCellValue("Data");
			wierszNaglowek.createCell((short) 1).setCellValue("Godzina");
			wierszNaglowek.createCell((short) 2).setCellValue("Ilosc klejona");
			wierszNaglowek.createCell((short) 3).setCellValue("Operator");

			pstmt = null;
			rs = null;
			pstmt = connection
					.prepareStatement("SELECT * FROM TABELA_KLEJENIE WHERE DATE BETWEEN ? AND ?  ORDER BY DATE ASC");
			pstmt.setString(1, dateFrom);
			pstmt.setString(2, dateTo);
			rs = pstmt.executeQuery();

			System.out.println("indexExcelKlejenie " + indexExcel);

			indexExcel = indexExcel + 1;
			while (rs.next()) {
				String date = rs.getString("DATE");
				String time = rs.getString("TIME");
				String operator = rs.getString("OPERATOR");
				int iloscKlejona = rs.getInt("ILOSC_KLEJONA");
				System.out.println(
						"KLEJENIE POLARYZATOROW " + date + "  " + time + "  " + iloscKlejona + "  " + operator);

				HSSFRow wierszExcel = sheet.createRow((short) indexExcel);
				wierszExcel.createCell((short) 0).setCellValue(date);
				wierszExcel.createCell((short) 1).setCellValue(time);
				wierszExcel.createCell((short) 2).setCellValue(iloscKlejona);
				wierszExcel.createCell((short) 3).setCellValue(operator);
				indexExcel = indexExcel + 1;

			}

			// RAPORT SEKCJI 4 OZNAKOWANIE
			indexExcel = indexExcel + 5;
			wierszNaglowek = sheet.createRow((short) indexExcel);
			wierszNaglowek.createCell((short) 0).setCellValue("SEKCJA OZNAKOWANIE");
			indexExcel = indexExcel + 1;
			wierszNaglowek = sheet.createRow((short) indexExcel);
			wierszNaglowek.createCell((short) 0).setCellValue("Data");
			wierszNaglowek.createCell((short) 1).setCellValue("Godzina");
			wierszNaglowek.createCell((short) 2).setCellValue("Model");
			wierszNaglowek.createCell((short) 3).setCellValue("Iloæ");
			wierszNaglowek.createCell((short) 4).setCellValue("Operator");

			pstmt = null;
			rs = null;
			pstmt = connection.prepareStatement(
					"SELECT * FROM TABELA_OZNAKOWANIE_PODKLADEK WHERE DATE BETWEEN ? AND ?  ORDER BY DATE ASC");
			pstmt.setString(1, dateFrom);
			pstmt.setString(2, dateTo);
			rs = pstmt.executeQuery();

			System.out.println("indexExcelOznakowanie " + indexExcel);

			indexExcel = indexExcel + 1;
			while (rs.next()) {
				String date = rs.getString("DATE");
				String time = rs.getString("TIME");
				String model = rs.getString("MODEL");
				int iloscOznakowana = rs.getInt("AMOUNT");
				String operator = rs.getString("OPERATOR");
				System.out.println(
						"OZNAKOWANIE POLARYZATOROW " + date + "  " + time + "  " + iloscOznakowana + "  " + operator);

				HSSFRow wierszExcel = sheet.createRow((short) indexExcel);
				wierszExcel.createCell((short) 0).setCellValue(date);
				wierszExcel.createCell((short) 1).setCellValue(time);
				wierszExcel.createCell((short) 2).setCellValue(model);
				wierszExcel.createCell((short) 3).setCellValue(iloscOznakowana);
				wierszExcel.createCell((short) 4).setCellValue(operator);
				indexExcel = indexExcel + 1;

			}

			

			String REPORT_FILENAME = "singleCuttingReport_" + dateFrom + "_" + dateTo + ".xls";
//				raportExcela = new FileOutputStream(xlsReportPath + REPORT_FILENAME);
			File fileXls = new File(xlsReportPath + REPORT_FILENAME);
			try {
				System.out.println("DDUUUUPPPDADDADADAD");
				//wb.write(fileXls);
				wb.write(file);
//				file.flush();
//				file.close();
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("zapisano dane");
			rs.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return fileXls;;
		

	}

}