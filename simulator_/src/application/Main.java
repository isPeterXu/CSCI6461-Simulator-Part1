package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	private static final int APP_WIDTH = 1000, APP_HEIGHT = 750;
    private final String TITLE = "CSCI 6461 Simulator";
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,400,400);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FrontPanel.fxml"));
			Parent root = loader.load();
			primaryStage.setTitle(TITLE);
			primaryStage.setScene(new Scene(root, APP_WIDTH, APP_HEIGHT));
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
