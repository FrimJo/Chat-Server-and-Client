package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * Main class for the Client. Setup the scene for the GUI.
 * 
 * @author Fredrik Johansson
 *
 */
public class Main extends Application {
	
	/**
	 * Override start method of class Application. This
	 * is invoked method launch is executet.
	 */
	@Override
	public void start(Stage primaryStage) {
	
			AnchorPane page;
			try {
				page = (AnchorPane) FXMLLoader.load(Main.class.getResource("Client_GUI_Blueprint.fxml"));
				Scene scene = new Scene(page);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setTitle("Chat App 2.0");
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {

			
				
			}

	}
	
	/**
	 * Main method for the client side of the program.
	 * @param args A string array of arguments passed on at runtime.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
