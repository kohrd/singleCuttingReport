package pl.singleCutting.report.metody;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import org.apache.poi.hssf.usermodel.HSSFFont;

public class GenerateReport {

	// static String xlsReportPath =
	// "C:\\_raportSingleCuttingTestJava\\raportyExcel\\";



	//	@SuppressWarnings("null")
	public static void generateReportInExcel(String dateFrom, String dateTo, String reportFor, String dbPath,
			File file) {
		Connection connection = null;
//
//		try {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		// ORGINALNE PREPARED STATEMENT

//			@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Raport");

		// style
		HSSFFont boldFont = wb.createFont();
		boldFont.setBold(true);

		// RAPORT SEKCJI 1 SPRAWDZANIE MATETRIA£U

		HSSFRow wierszNaglowek = sheet.createRow((short) 2);
		// nag³ówek ISO z orginalnego dokumentu
		wierszNaglowek.createCell((short) 0).setCellValue("Data wprowadzenia");
//			wierszNaglowek.setRowStyle(eded);
		wierszNaglowek.createCell((short) 1).setCellValue("-");
		wierszNaglowek.createCell((short) 4).setCellValue("Wniosek");
//			wierszNaglowek.set
		wierszNaglowek.createCell((short) 5).setCellValue("Rozpatrzenie");
		wierszNaglowek.createCell((short) 6).setCellValue("Zatwierdzi³");

		wierszNaglowek = sheet.createRow((short) 3);
		wierszNaglowek.createCell((short) 0).setCellValue("Numer poprawki");
		wierszNaglowek.createCell((short) 1).setCellValue("1");
		wierszNaglowek.createCell((short) 3).setCellValue("Podpis");
		wierszNaglowek.createCell((short) 4).setCellValue("Micha³ Gabrysz");
		wierszNaglowek.createCell((short) 5).setCellValue("Ireneusz Szyszka");
		wierszNaglowek.createCell((short) 6).setCellValue("Ireneusz Szyszka");

		wierszNaglowek = sheet.createRow((short) 4);
		wierszNaglowek.createCell((short) 0).setCellValue("Data poprawki");
		wierszNaglowek.createCell((short) 1).setCellValue("16.12.2011");

		wierszNaglowek = sheet.createRow((short) 6);
		wierszNaglowek.createCell((short) 0).setCellValue("Formularz selekcji materia³u na dziale ciêcia próbek");
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 8));

		wierszNaglowek = sheet.createRow((short) 7);
		wierszNaglowek.createCell((short) 0).setCellValue("Dzia³");
		wierszNaglowek.createCell((short) 1).setCellValue("Ciêcie próbek");
		sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 6));

		wierszNaglowek = sheet.createRow((short) 8);
		wierszNaglowek.createCell((short) 0).setCellValue("Numer linii");
		wierszNaglowek.createCell((short) 1).setCellValue("1");
		sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 6));

		wierszNaglowek = sheet.createRow((short) 9);
		wierszNaglowek.createCell((short) 0).setCellValue("Osoba odpowiedzialna");
		wierszNaglowek.createCell((short) 1).setCellValue("Inspektorka ciêcia próbek");
		sheet.addMergedRegion(new CellRangeAddress(9, 9, 1, 6));

		wierszNaglowek = sheet.createRow((short) 10);
		wierszNaglowek.createCell((short) 0).setCellValue("Cel");
		wierszNaglowek.createCell((short) 1).setCellValue("Przygotowanie materia³u do obróbki na dziale ciêcia próbek");
		sheet.addMergedRegion(new CellRangeAddress(10, 10, 1, 6));

		// raport ma³ego ciêcia
		wierszNaglowek = sheet.createRow((short) 12);
//			wierszNaglowek.createCell((short) 0).setCellStyle(boldFontGreyCell);
		wierszNaglowek.createCell((short) 0).setCellValue("SEKCJA SPRAWDZANIE MATERIA£U");
		// mergowanie tytu³u nag³ówka
		sheet.addMergedRegion(new CellRangeAddress(12, 12, 0, 8));

		wierszNaglowek = sheet.createRow((short) 13);
		wierszNaglowek.createCell((short) 0).setCellValue("Data");
		wierszNaglowek.createCell((short) 1).setCellValue("Godzina");
		wierszNaglowek.createCell((short) 2).setCellValue("Model");
		wierszNaglowek.createCell((short) 3).setCellValue("Ilosc ca³kowita");
		wierszNaglowek.createCell((short) 4).setCellValue("Ilosc ok");
		wierszNaglowek.createCell((short) 5).setCellValue("Ilosc NG");
		wierszNaglowek.createCell((short) 6).setCellValue("Tryb pracy");
		wierszNaglowek.createCell((short) 7).setCellValue("Ilosc na podk³adki");
		wierszNaglowek.createCell((short) 8).setCellValue("Iloæ podk³adek");
		wierszNaglowek.createCell((short) 9).setCellValue("Operator");

		ResultSet rs = null;

		String querySprawdzanieMaterialuOperator = "SELECT * FROM TABELA_SPRAWDZANIE_MATERIALU WHERE OPERATOR = ? AND DATE BETWEEN ? AND ? ORDER BY DATE ASC";
		String querySprawdzanieMaterialuAll = "SELECT * FROM TABELA_SPRAWDZANIE_MATERIALU WHERE  DATE BETWEEN ? AND ? ORDER BY DATE ASC";
//		String querySprawdzanieMaterialuOperator = "SELECT * FROM TABELA_SPRAWDZANIE_MATERIALU WHERE OPERATOR = ? AND DATE BETWEEN ? AND ? GROUP BY MODEL_POLARYZATORA, TRYB_PRACY ORDER BY DATE ASC";
//		String querySprawdzanieMaterialuAll = "SELECT * FROM TABELA_SPRAWDZANIE_MATERIALU WHERE  DATE BETWEEN ? AND ? GROUP BY MODEL_POLARYZATORA, TRYB_PRACY ORDER BY DATE ASC";
		rs = createResultSet(connection, dateFrom, dateTo, reportFor, querySprawdzanieMaterialuOperator, querySprawdzanieMaterialuAll, dbPath);

		int indexExcel = 14;
		try {
			indexExcel = indexExcel + 1;
			while (rs.next()) {
				String date = rs.getString("DATE");
				String time = rs.getString("TIME");
				String modelPolaryzatora = rs.getString("MODEL_POLARYZATORA");
				int iloscCalkowita = rs.getInt("ILOSC_CALKOWITA");
				int iloscOk = rs.getInt("ILOSC_OK");
				int iloscNG = rs.getInt("ILOSC_NG");
				String trybPracy = rs.getString("TRYB_PRACY");
				int iloscNaPodkladki = rs.getInt("ILOSC_NA_PODKLADKI");
				int iloscPodkladek = rs.getInt("ILOSC_PODKLADEK");
				String operator = rs.getString("OPERATOR");
				System.out.println(date + "  " + time + "  " + modelPolaryzatora + "  " + iloscCalkowita + "  "
						+ iloscOk + "  " + iloscNG + "  " + trybPracy + "  " + iloscNaPodkladki + "  " + iloscPodkladek
						+ "  " + operator);
				

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
				wierszExcel.createCell((short) 8).setCellValue(iloscPodkladek);
				wierszExcel.createCell((short) 9).setCellValue(operator);
				indexExcel = indexExcel + 1;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// RAPORT SEKCJI 2 PRZEDUK POLARYZATOROW
		indexExcel = indexExcel + 2;
		wierszNaglowek = sheet.createRow((short) indexExcel);
		wierszNaglowek.createCell((short) 0).setCellValue("SEKCJA PRZEDRUK POLARYZATORÓW");
		sheet.addMergedRegion(new CellRangeAddress(indexExcel, indexExcel, 0, 8));
		indexExcel = indexExcel + 1;
		wierszNaglowek = sheet.createRow((short) indexExcel);
		wierszNaglowek.createCell((short) 0).setCellValue("Data");
		wierszNaglowek.createCell((short) 1).setCellValue("Godzina");
		wierszNaglowek.createCell((short) 2).setCellValue("lot");
		wierszNaglowek.createCell((short) 3).setCellValue("Ilosc ca³kowita");
		wierszNaglowek.createCell((short) 4).setCellValue("Model");
		wierszNaglowek.createCell((short) 5).setCellValue("Operator");

		try {
			rs.close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		indexExcel = indexExcel + 1;
		rs = null;
		String queryprzedrukPolaryzatoprowOperator = "SELECT * FROM TABELA_PRZEDRUK_POLARYZATOROW WHERE OPERATOR = ? AND DATE BETWEEN ? AND ?  ORDER BY DATE ASC";
		String queryprzedrukPolaryzatoprowQueryAll = "SELECT * FROM TABELA_PRZEDRUK_POLARYZATOROW WHERE DATE BETWEEN ? AND ?  ORDER BY DATE ASC";
		rs = createResultSet(connection, dateFrom, dateTo, reportFor, queryprzedrukPolaryzatoprowOperator, queryprzedrukPolaryzatoprowQueryAll, dbPath);
		try {
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
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// RAPORT SEKCJI 3 KLEJENIE
		indexExcel = indexExcel + 2;
		wierszNaglowek = sheet.createRow((short) indexExcel);
		wierszNaglowek.createCell((short) 0).setCellValue("SEKCJA KLEJENIE PODK£ADEK");
		sheet.addMergedRegion(new CellRangeAddress(indexExcel, indexExcel, 0, 8));
		indexExcel = indexExcel + 1;
		wierszNaglowek = sheet.createRow((short) indexExcel);
		wierszNaglowek.createCell((short) 0).setCellValue("Data");
		wierszNaglowek.createCell((short) 1).setCellValue("Godzina");
		wierszNaglowek.createCell((short) 2).setCellValue("Ilosc klejona");
		wierszNaglowek.createCell((short) 3).setCellValue("Iloc klejona na rdzeñ");
		wierszNaglowek.createCell((short) 4).setCellValue("typ klejony na rdzeñ");
		wierszNaglowek.createCell((short) 5).setCellValue("D³ugoæ nawiniêta na rdzeñ");
		wierszNaglowek.createCell((short) 6).setCellValue("Operator");
		
		try {
			rs.close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		System.out.println("indexExcelKlejenie " + indexExcel);
		indexExcel = indexExcel + 1;

		rs = null;
		String klejenieQueryAll = "SELECT * FROM TABELA_KLEJENIE WHERE  DATE BETWEEN ? AND ?  ORDER BY DATE ASC";
		String klejenieQueryOperator = "SELECT * FROM TABELA_KLEJENIE WHERE OPERATOR = ? AND  DATE BETWEEN ? AND ?  ORDER BY DATE ASC";
		rs = createResultSet(connection, dateFrom, dateTo, reportFor, klejenieQueryOperator, klejenieQueryAll, dbPath);

		try {
			while (rs.next()) {
				String date = rs.getString("DATE");
				String time = rs.getString("TIME");
				String operator = rs.getString("OPERATOR");
				int iloscKlejona = rs.getInt("ILOSC_KLEJONA");
				// klejenie na rdzeñ
				int iloscKlejonaNaRdzen = rs.getInt("ILOSC_KLEJONA_NA_RDZEN");
				String typKlejonychNaRdzen = rs.getString("TYP_KLEJONYCH_NA_RDZEN");
				int dlugoscNawinietaNaRdzen = rs.getInt("DLUGOSC_NAWINIETA_NA_RDZEN");
				
				
				
				System.out.println(
						"KLEJENIE POLARYZATOROW " + date + "  " + time + "  " + iloscKlejona + "  " + operator+ "  "+
				iloscKlejonaNaRdzen+"   "+ typKlejonychNaRdzen+"   "+dlugoscNawinietaNaRdzen);

				HSSFRow wierszExcel = sheet.createRow((short) indexExcel);
				wierszExcel.createCell((short) 0).setCellValue(date);
				wierszExcel.createCell((short) 1).setCellValue(time);
				wierszExcel.createCell((short) 2).setCellValue(iloscKlejona);
				wierszExcel.createCell((short) 3).setCellValue(iloscKlejonaNaRdzen);
				wierszExcel.createCell((short) 4).setCellValue(typKlejonychNaRdzen);
				wierszExcel.createCell((short) 5).setCellValue(dlugoscNawinietaNaRdzen);
				wierszExcel.createCell((short) 6).setCellValue(operator);
				indexExcel = indexExcel + 1;

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// RAPORT SEKCJI 4 OZNAKOWANIE
		indexExcel = indexExcel + 2;
		wierszNaglowek = sheet.createRow((short) indexExcel);
		wierszNaglowek.createCell((short) 0).setCellValue("SEKCJA OZNAKOWANIE");
		sheet.addMergedRegion(new CellRangeAddress(indexExcel, indexExcel, 0, 8));
		indexExcel = indexExcel + 1;
		wierszNaglowek = sheet.createRow((short) indexExcel);
		wierszNaglowek.createCell((short) 0).setCellValue("Data");
		wierszNaglowek.createCell((short) 1).setCellValue("Godzina");
		wierszNaglowek.createCell((short) 2).setCellValue("Model");
		wierszNaglowek.createCell((short) 3).setCellValue("Iloæ");
		wierszNaglowek.createCell((short) 4).setCellValue("Operator");

		indexExcel = indexExcel + 1;

		String oznakowanieQueryOperator = "SELECT * FROM TABELA_OZNAKOWANIE_PODKLADEK WHERE OPERATOR = ? AND  DATE BETWEEN ? AND ?  ORDER BY DATE ASC";
		String oznakowanieQueryAll = "SELECT * FROM TABELA_OZNAKOWANIE_PODKLADEK  WHERE DATE BETWEEN ? AND ?  ORDER BY DATE ASC";
		rs = createResultSet(connection, dateFrom, dateTo, reportFor, oznakowanieQueryOperator, oznakowanieQueryAll, dbPath);
		try {
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
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			System.out.println("DDUUUUPPPDADDADADAD");
			wb.write(file);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("zapisano dane");
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ResultSet createResultSet(Connection connection, String dateFrom, String dateTo, String reportFor,
			String queryOperator, String queryAll, String dbPath) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (reportFor.equals("Wszyscy")) {
			try {
				pstmt = connection.prepareStatement(queryAll);
//				pstmt.setString(1, reportFor);
				pstmt.setString(1, dateFrom);
				pstmt.setString(2, dateTo);
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (!reportFor.equals("Wszyscy")) {

			try {
				pstmt = connection.prepareStatement(queryOperator);
				pstmt.setString(1, reportFor);
				pstmt.setString(2, dateFrom);
				pstmt.setString(3, dateTo);
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return rs;

	}

}