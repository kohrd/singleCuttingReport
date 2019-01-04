package pl.singleCutting.report;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class AlertWindows {

	
	public static void wrongValueAlert(String error) {
		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle("Niepoprawna wartosc");
		errorAlert.setHeaderText("Wpisano nieporawna wartosc");
		
		TextArea textArea = new TextArea(error);
		errorAlert.getDialogPane().setContent(textArea);
		errorAlert.showAndWait();
		
	}
}