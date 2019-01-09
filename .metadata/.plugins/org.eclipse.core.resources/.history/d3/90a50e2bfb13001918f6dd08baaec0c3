package pl.singleCutting.report;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class AlertWindows {

	public static void wrongValueAlert(String error, String header, String windowTitle) {
		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle(windowTitle);
		errorAlert.setHeaderText(header);

		TextArea textArea = new TextArea(error);
		errorAlert.getDialogPane().setContent(textArea);
		errorAlert.showAndWait();

	}
}


