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
	private int iloscKlejona = 0;
	@DatabaseField(columnName = "ILOSC_KLEJONA_NA_RDZEN")
	private int iloscKlejonaNaRdzen = 0;
	@DatabaseField (columnName = "TYP_KLEJONYCH_NA_RDZEN")
	private String typKlejonychNaRdzen = "";
	@DatabaseField (columnName = "DLUGOSC_NAWINIETA_NA_RDZEN")
	private Double dlugoscNawinietaNaRdzen = 0.0;
	@DatabaseField(columnName = "OPERATOR")
	private String operator;
	@DatabaseField(columnName = "IP")
	private String ip;
	
	
	
	public String getTypKlejonychNaRdzen() {
		return typKlejonychNaRdzen;
	}
	
	public void setTypKlejonychNaRdzen(String typKlejonychNaRdzen) {
		this.typKlejonychNaRdzen = typKlejonychNaRdzen;
	}
	
	public Double getDlugoscNawinietaNaRdzen() {
		return dlugoscNawinietaNaRdzen;
	}
	
	public void setDlugoscNawinietaNaRdzen(double dlugoscNawinietaNaRdzen) {
		this.dlugoscNawinietaNaRdzen = dlugoscNawinietaNaRdzen;
	}
	


	public int getIloscKlejonaNaRdzen() {
		return iloscKlejonaNaRdzen;
	}

	public void setIloscKlejonaNaRdzen(int iloscKlejonaNaRdzen) {
		this.iloscKlejonaNaRdzen = iloscKlejonaNaRdzen;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

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
	
	public static double liczDlugoscNawinietaNaRdzen(String typKlejonychNaRdzen, int iloscKlejonaNaRdzen) {
		double dlugoscNawinieta = 0.0;
		
		if (typKlejonychNaRdzen.equals("32")) {
			dlugoscNawinieta = iloscKlejonaNaRdzen * 0.4;
		} else if (typKlejonychNaRdzen.equals("43")) {
			dlugoscNawinieta = iloscKlejonaNaRdzen * 0.53;
		} else if (typKlejonychNaRdzen.equals("49")) {
			dlugoscNawinieta = iloscKlejonaNaRdzen * 0.61;
		} else if (typKlejonychNaRdzen.equals("55")) {
			dlugoscNawinieta = iloscKlejonaNaRdzen * 0.69;
		}
		return dlugoscNawinieta;
	}

	@Override
	public String toString() {
		return "KlejeniePolaryzatorow [date=" + date + ", time=" + time + ", iloscKlejona=" + iloscKlejona
				+ ", iloscKlejonaNaRdzen=" + iloscKlejonaNaRdzen + ", typKlejonychNaRdzen=" + typKlejonychNaRdzen
				+ ", dlugoscNawinietaNaRdzen=" + dlugoscNawinietaNaRdzen + ", operator=" + operator + ", ip=" + ip
				+ "]";
	}
	


}

