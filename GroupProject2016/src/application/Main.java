package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class Main extends Application {

	public static Stage primaryStage;
	public AnchorPane rootLayout;


	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 */
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Top Trumps");

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Interface.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			Game game = new Game();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	public static void closeButtonAction(){
	    primaryStage.close();
	}
}
