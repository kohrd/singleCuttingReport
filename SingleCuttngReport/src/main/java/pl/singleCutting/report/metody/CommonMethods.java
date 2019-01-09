package pl.singleCutting.report.metody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import pl.singleCutting.report.AlertWindows;

public class CommonMethods {

	public String dateFrom;
	public String dateTo;

	public static ObservableList<String> addDataToCombobox(String tableName, String columnName, String dbPathLocal) {

		final ObservableList<String> options = FXCollections.observableArrayList();
		String query = "SELECT " + columnName + " FROM " + tableName + " ORDER BY " + columnName;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPathLocal);
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				String modelName = resultSet.getString(columnName);
				options.add(modelName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return options;

	}

	public static void wymuszajLiczby(TextField textField) {
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				textField.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});
	}

	public static Integer parseToInt(String fieldName, String string, String windowHeader, String title) {
		// nie uzywana. pole int wymaga inta
		try {
			return Integer.valueOf(string);
		} catch (Exception e) {
			System.out.println(fieldName);
			AlertWindows.wrongValueAlert(
					"Podano zla wartosc w polu " + fieldName + "//n. Je¿eli jest to 0. Musisz wpisac tak¹ wartosc", windowHeader, title);
			System.out.println(e);
			return -1;
		}

	}

	public static ArrayList<String> getCurrentDate() {
		ArrayList<String> dateList = new ArrayList<>();
		String dateStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		dateList.add(0, dateStamp);
		dateList.add(1, timeStamp);
		return dateList;
	}
	
	public static String getIP() {
		
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ipAdress = IP.getHostAddress().toString();
		System.out.println("IP of my system is := "+IP.getHostAddress());	
		return ipAdress;
	}
		
	

}