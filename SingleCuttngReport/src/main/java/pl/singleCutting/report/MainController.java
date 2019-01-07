package pl.singleCutting.report;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.sun.javafx.scene.control.skin.Utils;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import pl.singleCutting.report.sekcje.PrzedrukPolaryzatorow;
import pl.singleCutting.report.metody.CommonMethods;
import pl.singleCutting.report.metody.GenerateReport;
import pl.singleCutting.report.sekcje.SprawdzanieMaterialu;
import pl.singleCutting.report.sekcje.KlejeniePolaryzatorow;
import pl.singleCutting.report.sekcje.OznakowaniePodkladek;

public class MainController {

	// Zmienic ścierzke
	String dbPathLocal = "/home/gosiakonrad/IdeaProjects/singleCuttingProject/SingleCuttngReport/src/main/resources/dB/raportSingleCutting.db";


	// SEKCJA SPRAWDZANIA MATERIA£U
	@FXML
	private ComboBox<String> operator_main;

	@FXML
	private ComboBox<String> model_polaryzatora_1;

	@FXML
	private ComboBox<String> tryb_pracy_1;

	@FXML
	private TextField ilosc_calkowita_1;

	@FXML
	private TextField ilosc_ok_1;

	@FXML
	private TextField ilosc_na_podkladki_1;

	// SEKCJA 2 PRZEDRUKU POLARYZATORÓW

	@FXML
	private ComboBox<String> model_polaryzatora_2;

	@FXML
	private TextField numer_LOT_2;

	@FXML
	private TextField ilosc_przedrukowanych_2;

	// SEKCJA 3 KLEJENIE PODK£ADEK

	@FXML
	private TextField ilosc_klejonych_3;

	// SEKCJA 4 OZNAKOWANIE
	@FXML
	private ComboBox<String> model_polaryzatora_4;

	@FXML
	private TextField ilosc_oznakowanych_4;

	// TWORZENIE RAPORTU
	@FXML
	private DatePicker date_from;

	@FXML
	private DatePicker date_to;

	@FXML
	private Button stworzRaportButton;

	public MainController() {
	}

	@FXML
	void initialize() {
		// dodawania operatorow do combobox
		ObservableList<String> operatorList = CommonMethods.addDataToCombobox("OPERATOR", "OPERATOR_NAME", dbPathLocal);
		operator_main.setItems(operatorList);

		// ustawianie wartosci domyslnej 0 w polach na liczby zeby nie bylo tego
		// podswietlanego z fxml 0
		// ilosc_calkowita_1.setText("0");

		// wymuszanie liczb w polach tekstowych
		CommonMethods.wymuszajLiczby(ilosc_calkowita_1);
		CommonMethods.wymuszajLiczby(ilosc_ok_1);
		CommonMethods.wymuszajLiczby(ilosc_na_podkladki_1);
		CommonMethods.wymuszajLiczby(ilosc_przedrukowanych_2);
		CommonMethods.wymuszajLiczby(ilosc_klejonych_3);
		CommonMethods.wymuszajLiczby(ilosc_oznakowanych_4);

		// ustawianie aktualnej daty w dataPicker
		date_from.setValue(LocalDate.now());
		date_to.setValue(LocalDate.now());

	}

	public void sprawdzanieMaterialuZatwierdz() {
		String operator;
		// POBIERANIE DANYCH Z FORMULARZA
//		String operator = operator_main.getValue();
		if (operator_main.getValue() == null) {
			// System.out.println("nie wybrales operatora! DODAC OKNO INFORMACYJNE");
			AlertWindows.wrongValueAlert("Wybierz operatora z listy rozwijanej", "Nie wybrano operaotra",
					"Wartosc niepoprawna");
		} else {
			operator = operator_main.getValue();
			int iloscCalkowita = CommonMethods.parseToInt(ilosc_calkowita_1.getId(), ilosc_calkowita_1.getText(),
					"NIEPORAWNA WARTOÆ", "Niepoprawna wartosc");
			System.out.println("ilosc CA£KOWITA:  " + iloscCalkowita);
			int iloscOk = CommonMethods.parseToInt(ilosc_ok_1.getId(), ilosc_ok_1.getText(), "NIEPORAWNA WARTOÆ",
					"Niepoprawna wartosc");
			int iloscNaPodkladki = CommonMethods.parseToInt(ilosc_na_podkladki_1.getId(),
					ilosc_na_podkladki_1.getText(), "NIEPORAWNA WARTOÆ", "Niepoprawna wartosc");
			String trybPracy = tryb_pracy_1.getValue();
			String modelPolaryzatora = model_polaryzatora_1.getValue();
			int iloscNg = iloscCalkowita - iloscOk;
			if (iloscCalkowita < 0 || iloscOk < 0 || iloscNaPodkladki < 0) {
				// ten if blokuje podanie liczby ilosc ponizej zera
			} else {

				// TWORZENIE OBIEKTU SETTERAMI BO ORMlITE TAK WYMAGA
				SprawdzanieMaterialu sprawdzanieMaterialu = new SprawdzanieMaterialu();
				sprawdzanieMaterialu.setIloscCalkowita(iloscCalkowita);
				sprawdzanieMaterialu.setIloscOk(iloscOk);
				sprawdzanieMaterialu.setIloscNaPodkladki(iloscNaPodkladki);
				sprawdzanieMaterialu.setModelPolaryzatora(modelPolaryzatora);
				sprawdzanieMaterialu.setOperator(modelPolaryzatora);
				sprawdzanieMaterialu.setTrybPracy(trybPracy);
				sprawdzanieMaterialu.setOperator(operator);
				sprawdzanieMaterialu.setIloscNg(iloscNg);

				sprawdzanieMaterialu.setDate(CommonMethods.getCurrentDate().get(0));
				sprawdzanieMaterialu.setTime(CommonMethods.getCurrentDate().get(1));

				// DODANIE DANYCH DO BAZY
				sprawdzanieMaterialu.dodajDaneDoBazy(dbPathLocal);
				sprawdzanieMaterialu = null;

				// czyszczeni formularza
				tryb_pracy_1.setValue(null);
				model_polaryzatora_1.setValue(null);
				ilosc_calkowita_1.clear();
				ilosc_ok_1.clear();
				ilosc_na_podkladki_1.clear();
			}
		}
	}

	public void przedrukPolaryzatorowZatwierdz() {
		String operator = "";
		// pobieranie danych z formularza
		if (operator_main.getValue() == null) {
			// System.out.println("nie wybrales operatora! DODAC OKNO INFORMACYJNE");
			AlertWindows.wrongValueAlert("Wybierz operatora z listy rozwijanej", "Nie wybrano operaotra",
					"Wartosc niepoprawna");
		} else {
			operator = operator_main.getValue();
			String modelPolaryzatora = model_polaryzatora_2.getValue();
			String numerLot = numer_LOT_2.getText();
			int iloscPrzedrukowanych = CommonMethods.parseToInt(ilosc_przedrukowanych_2.getId(),
					ilosc_przedrukowanych_2.getText(), "NIEPORAWNA WARTOÆ", "Niepoprawna wartosc");

			// Tworzenie obiektu PrzedrukPolaryzatorw i ustawianie pol setterami dla
			// potrrzeb orm

			if (iloscPrzedrukowanych < 0) {

			} else {

				PrzedrukPolaryzatorow przedrukPolaryzatorow = new PrzedrukPolaryzatorow();
				przedrukPolaryzatorow.setDate(CommonMethods.getCurrentDate().get(0));
				przedrukPolaryzatorow.setTime(CommonMethods.getCurrentDate().get(1));
				przedrukPolaryzatorow.setModel(modelPolaryzatora);
				przedrukPolaryzatorow.setLot(numerLot);
				przedrukPolaryzatorow.setAmount(iloscPrzedrukowanych);
				przedrukPolaryzatorow.setOperator(operator);

				przedrukPolaryzatorow.dodajDaneDoBazy(dbPathLocal);
				przedrukPolaryzatorow.toString();

				System.out.println(przedrukPolaryzatorow.getOperator());
				przedrukPolaryzatorow = null;

				// czyszczeni formularza
				model_polaryzatora_2.setValue(null);
				numer_LOT_2.clear();
				ilosc_calkowita_1.clear();
				ilosc_przedrukowanych_2.clear();
			}
		}

	}

	public void klejeniePolaryzatorowZatwierdz() {

		// pobieranie danych z formularza
		if (operator_main.getValue() == null) {
//			System.out.println("nie wybrales operatora! DODAC OKNO INFORMACYJNE");
			AlertWindows.wrongValueAlert("Wybierz operatora z listy rozwijanej", "Nie wybrano operaotra",
					"Wartosc niepoprawna");
		} else {
			String operator = operator_main.getValue();
			int iloscKlejonych = CommonMethods.parseToInt(ilosc_klejonych_3.getId(), ilosc_klejonych_3.getText(),
					"NIEPORAWNA WARTOÆ", "Niepoprawna wartosc");
			if (iloscKlejonych < 0) {

			} else {
				KlejeniePolaryzatorow klejeniePolaryzatorow = new KlejeniePolaryzatorow();
				klejeniePolaryzatorow.setDate(CommonMethods.getCurrentDate().get(0));
				klejeniePolaryzatorow.setDate(CommonMethods.getCurrentDate().get(1));
				klejeniePolaryzatorow.setOperator(operator);
				klejeniePolaryzatorow.setIloscKlejona(iloscKlejonych);
				System.out.println(klejeniePolaryzatorow.toString());
				klejeniePolaryzatorow.dodajDaneDoBazy(dbPathLocal);
				klejeniePolaryzatorow = null;
				ilosc_klejonych_3.clear();
			}

		}

	}

	public void oznakowaniePodkladekZatwierdz() {
		if (operator_main.getValue() == null) {
//			System.out.println("nie wybrales operatora! DODAC OKNO INFORMACYJNE");
			AlertWindows.wrongValueAlert("Wybierz operatora z listy rozwijanej", "Nie wybrano operaotra",
					"Wartosc niepoprawna");
		} else {
			String operator = operator_main.getValue();
			int iloscOznakowanych = CommonMethods.parseToInt(ilosc_oznakowanych_4.getId(),
					ilosc_oznakowanych_4.getText(), "NIEPORAWNA WARTOÆ", "Niepoprawna wartosc");
			if (iloscOznakowanych < 0) {

			} else {

				String model = model_polaryzatora_4.getValue();

				OznakowaniePodkladek oznakowaniePodkladek = new OznakowaniePodkladek();
				oznakowaniePodkladek.setDate(CommonMethods.getCurrentDate().get(0));
				oznakowaniePodkladek.setTime(CommonMethods.getCurrentDate().get(1));
				oznakowaniePodkladek.setOperator(operator);
				oznakowaniePodkladek.setAmount(iloscOznakowanych);
				oznakowaniePodkladek.setModel(model);

				oznakowaniePodkladek.dodajDaneDoBazy(dbPathLocal);
				System.out.println(oznakowaniePodkladek.toString());
				oznakowaniePodkladek = null;
				model_polaryzatora_4.setValue(null);
				ilosc_oznakowanych_4.clear();

			}
		}
	}

	public void stworzRaport() {

		String dateFrom = String.valueOf(date_from.getValue());
		String dateTo = String.valueOf(date_to.getValue());
//		DateTimeFormatter dateFromFormatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
//		String dateFromString = LocalDate.format(dateFromFormatter);
//		LocalDate dateTo = date_to.getValue();
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Zapisz raport ma³ego ciêcia w danej lokalizacji");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter ("XLS files (*.xls)", "*.xls");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setInitialFileName("dupa.xls");
		File raportFile = fileChooser.showSaveDialog(null);
		System.out.println(raportFile);
		
		if (raportFile != null) {
			try {
			
			GenerateReport.generateReportInExcel(dateFrom, dateTo, dbPathLocal, raportFile);
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		// https://stackoverflow.com/questions/29846602/how-to-save-file-with-savedilogue-javafx
		

	}



	public void onMouseClikedUpdateComboboxModelType() {
		// aktualizowanie comboBox po kliknieciu na niego
		ObservableList<String> list = CommonMethods.addDataToCombobox("POLARIZER_MODELS", "MODEL_NAME", dbPathLocal);
		model_polaryzatora_1.setItems(list);
		model_polaryzatora_2.setItems(list);
		model_polaryzatora_4.setItems(list);
	}

	public void onMouseClikedUpdateComboboxProductionMode() {
		// aktualizowanie comboBox po kliknieciu na niego
		ObservableList<String> list = CommonMethods.addDataToCombobox("PRODUCTION_MODE", "MODE", dbPathLocal);
		tryb_pracy_1.setItems(list);
	}
}