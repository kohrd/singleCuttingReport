package pl.singleCutting.report.sekcje;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

@DatabaseTable(tableName = "TABELA_KLEJENIE")
public class KlejeniePolaryzatorow {

	@DatabaseField(generatedId = true, columnName = "ID")
	private int id;
	@DatabaseField(columnName = "DATE")
	private String date;
	@DatabaseField(columnName = "TIME")
	private String time;
	@DatabaseField(columnName = "ILOSC_KLEJONA")
	private int iloscKlejona;
	@DatabaseField(columnName = "OPERATOR")
	private String operator;

	public int getId() {
		return id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getIloscKlejona() {
		return iloscKlejona;
	}

	public void setIloscKlejona(int iloscKlejona) {
		this.iloscKlejona = iloscKlejona;
	}
	public void dodajDaneDoBazy(String dbPath) {
		ConnectionSource connectionSource = null;
		try {
			connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + dbPath);
			TableUtils.createTableIfNotExists(connectionSource, KlejeniePolaryzatorow.class);
			Dao<KlejeniePolaryzatorow, ?> dao = DaoManager.createDao(connectionSource, KlejeniePolaryzatorow.class);
			dao.create(this);
		} catch (SQLException e) {
			System.out.println("!!!!!!!!!!!!!!!WYSTAPIL BLAD W POLACZENIU Z BAZA DANYCH: " + e);
			e.printStackTrace();
		}

		try {
			connectionSource.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "KlejeniePolaryzatorow [id=" + id + ", date=" + date + ", time=" + time + ", iloscKlejona="
				+ iloscKlejona + "]";
	}

	
}