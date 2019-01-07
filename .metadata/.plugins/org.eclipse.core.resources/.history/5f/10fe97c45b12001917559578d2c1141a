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

@DatabaseTable(tableName = "TABELA_PRZEDRUK_POLARYZATOROW")
public class PrzedrukPolaryzatorow {

//	String SPRAWDZANIE_MATERIALU_TABELA_SQL = "SPRAWDZANIE_MATERIALU";
//	String dbPathLocal = "jdbc:sqlite:C:\\__JavaMoje\\MOJE_Z_UBUNTU\\JavaFXzUbuntu\\JavaFX\\RaportMaleCiecie\\!_raportSingleCuttingTEST\\files\\raportSingleCuttingTEST.db";
//	String tableName = "TABELA_SPRAWDZANIE_MATERIALU";

	@DatabaseField(generatedId = true, columnName = "ID")
	private int id;
	@DatabaseField(columnDefinition = "DATE")
	private String date;
	@DatabaseField(columnDefinition = "TIME")
	private String time;
	@DatabaseField(columnDefinition = "LOT")
	private String lot;
	@DatabaseField(columnDefinition = "AMOUNT")
	private int amount;
	@DatabaseField(columnDefinition = "MODEL")
	private String model;
	@DatabaseField(columnDefinition = "OPERATOR")
	private String operator;

	public int getId() {
		return id;
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

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public PrzedrukPolaryzatorow() {
		//kontruktor
	}
	
	public void dodajDaneDoBazy(String dbPath) {
		ConnectionSource connectionSource = null;
		try {
			connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + dbPath);
			TableUtils.createTableIfNotExists(connectionSource, PrzedrukPolaryzatorow.class);
			Dao<PrzedrukPolaryzatorow, ?> dao = DaoManager.createDao(connectionSource, PrzedrukPolaryzatorow.class);
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
		return "PrzedrukPolaryzatorow [id=" + id + ", date=" + date + ", time=" + time + ", lot=" + lot + ", amount="
				+ amount + ", model=" + model + ", operator=" + operator + "]";
	}

	
	
}