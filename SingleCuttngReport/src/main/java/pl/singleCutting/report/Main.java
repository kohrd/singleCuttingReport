package pl.singleCutting.report;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static final String FXML_MAIN_FILE = "mainFxml.fxml";

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage mainStage) throws Exception {
		

		
		//setUserAgentStylesheet(STYLESHEET_CASPIAN);
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(this.getClass().getResource(FXML_MAIN_FILE));
		StackPane stackPane = fxmlLoader.load();

		MainController controller = fxmlLoader.getController();
		
		Scene scene = new Scene(stackPane);
		mainStage.setScene(scene);
		mainStage.setTitle("Wyniki produkcji ma³ego ciêcia");
		mainStage.isAlwaysOnTop();
		mainStage.show();

	}

}