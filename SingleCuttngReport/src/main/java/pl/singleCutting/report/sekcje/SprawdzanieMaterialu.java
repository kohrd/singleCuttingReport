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



@DatabaseTable(tableName = "TABELA_SPRAWDZANIE_MATERIALU")
public class SprawdzanieMaterialu {

//	String SPRAWDZANIE_MATERIALU_TABELA_SQL = "SPRAWDZANIE_MATERIALU";
//	String dbPathLocal = "jdbc:sqlite:C:\\__JavaMoje\\MOJE_Z_UBUNTU\\JavaFXzUbuntu\\JavaFX\\RaportMaleCiecie\\!_raportSingleCuttingTEST\\files\\raportSingleCuttingTEST.db";
//	String tableName = "TABELA_SPRAWDZANIE_MATERIALU";

	@DatabaseField(generatedId = true, columnName = "ID")
	private int id;
	@DatabaseField(columnName = "DATE")
	private String date; // jak pobraæ aktualny czas z metody i jak go dodac do kontruktora?
	@DatabaseField(columnName = "TIME")
	private String time;
	@DatabaseField(columnName = "MODEL_POLARYZATORA")
	private String modelPolaryzatora;
	@DatabaseField(columnName = "ILOSC_CALKOWITA")
	private int iloscCalkowita;
	@DatabaseField(columnName = "ILOSC_OK")
	private int iloscOk;
	@DatabaseField(columnName = "ILOSC_NG")
	private int iloscNg;
	@DatabaseField(columnName = "TRYB_PRACY")
	private String trybPracy;
	@DatabaseField(columnName = "ILOSC_NA_PODKLADKI")
	private int iloscNaPodkladki;
	@DatabaseField(columnName = "ILOSC_PODKLADEK")
	private int iloscPodkladek;
	@DatabaseField(columnName = "OPERATOR")
	private String operator;
	@DatabaseField(columnName = "IP")
	private String ip;
	
	
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public SprawdzanieMaterialu() {
		
	}
	
	public int getIloscNg() {
		return iloscNg;
	}

	public int getIloscPodkladek() {
		return iloscPodkladek;
	}

	public void setIloscPodkladek(int ilosc_podkladek) {
		this.iloscPodkladek = ilosc_podkladek;
	}

	public void setIloscNg(int iloscNg) {
		this.iloscNg = iloscNg;
	}

	// kontruktor bezargumentowy dla ormlite

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

	public String getModelPolaryzatora() {
		return modelPolaryzatora;
	}

	public void setModelPolaryzatora(String modelPolaryzatora) {
		this.modelPolaryzatora = modelPolaryzatora;
	}

	public int getIloscCalkowita() {
		return iloscCalkowita;
	}

	public void setIloscCalkowita(int iloscCalkowita) {
		this.iloscCalkowita = iloscCalkowita;
	}

	public int getIloscOk() {
		return iloscOk;
	}

	public void setIloscOk(int iloscOk) {
		this.iloscOk = iloscOk;
	}

	public String getTrybPracy() {
		return trybPracy;
	}

	public void setTrybPracy(String trybPracy) {
		this.trybPracy = trybPracy;
	}

	public int getIloscNaPodkladki() {
		return iloscNaPodkladki;
	}

	public void setIloscNaPodkladki(int iloscNaPodkladki) {
		this.iloscNaPodkladki = iloscNaPodkladki;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}



	
	public void dodajDaneDoBazy(String dbPath) {
		ConnectionSource connectionSource = null;
		try {
			connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + dbPath);
			TableUtils.createTableIfNotExists(connectionSource, SprawdzanieMaterialu.class);
			Dao<SprawdzanieMaterialu, ?> dao = DaoManager.createDao(connectionSource, SprawdzanieMaterialu.class);
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
		return "SprawdzanieMaterialu [id=" + id + ", date=" + date + ", time=" + time + ", modelPolaryzatora="
				+ modelPolaryzatora + ", iloscCalkowita=" + iloscCalkowita + ", iloscOk=" + iloscOk + ", iloscNg="
				+ iloscNg + ", trybPracy=" + trybPracy + ", iloscNaPodkladki=" + iloscNaPodkladki + ", operator="
				+ operator + "]";
	}
	


	
	

}