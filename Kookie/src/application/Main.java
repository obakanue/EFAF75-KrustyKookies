package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Stage searchStage = new Stage();
			Stage produceStage = new Stage();
			Parent search = FXMLLoader.load(getClass().getResource("pallet_search.fxml"));
			Parent produce = FXMLLoader.load(getClass().getResource("pallet_prod.fxml"));
			Parent blocked = FXMLLoader.load(getClass().getResource("pallet_block.fxml"));
			primaryStage.setScene(new Scene(blocked, 250, 250));
			searchStage.setScene(new Scene(search,750, 400));
			produceStage.setScene(new Scene(produce, 500, 325));
			produceStage.show();
			searchStage.show();
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
