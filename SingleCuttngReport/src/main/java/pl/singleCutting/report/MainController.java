package pl.singleCutting.report;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MainController {

	String dbPath = "P:\\!_raportSingleCuttingTEST\\files\\raportSingleCuttingTEST.db";

	@FXML
	private ComboBox<String> model_polaryzatora;

	@FXML
	private ComboBox<String> tryb_pracy;

	@FXML
	private TextField ilosc_calkowita;

	@FXML
	private TextField ilosc_ok;

	@FXML
	private TextField ilosc_na_podkladki;

	public MainController() {
	}

	@FXML
	void initialize() {

		tryb_pracy.getItems().addAll("OM", "DRM");
		model_polaryzatora.getItems().addAll("6272", "7896", "6589"); // dodac wartosc domyslna i okno informacyjne
																		// jesli pozostala niezmieniona
		DbUtilities.dbConnection(dbPath);

	}

	public void sekcja1button() {
		int iloscCalkowita = parseToInt(ilosc_calkowita.getId(), ilosc_calkowita.getText());
		int iloscOk = parseToInt(ilosc_ok.getId(), ilosc_ok.getText());
		int iloscNaPodkladki = parseToInt(ilosc_na_podkladki.getId(), ilosc_na_podkladki.getText());
		String trybPracy = tryb_pracy.getValue();
		String modelPolaryzatora = model_polaryzatora.getValue();

		System.out.println(iloscCalkowita + "  " + iloscOk + "  " + iloscNaPodkladki + "  " + trybPracy + "  "
				+ modelPolaryzatora);

	}

	public static Integer parseToInt(String fieldName, String string) {

		try {

			return Integer.valueOf(string);

		} catch (Exception e) {
			System.out.println(fieldName);
			AlertWindows.wrongValueAlert("Podano zla wartosc w polu " + fieldName);

			System.out.println(e);
			return 0;
		}

	}

}