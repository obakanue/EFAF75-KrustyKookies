package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BlockController {
	
	/*
	 * Declare variables that represent the stuff in the view.
	 */
	@FXML
	private Button btnBlock = new Button();
	
	@FXML
	private TextField txtRecipe = new TextField();
	
	@FXML
	private TextField txtStartDate = new TextField();
	
	@FXML 
	private TextField txtEndDate = new TextField();
	
	/*
	 * Methods that are invoked when the stuff is clicked.
	 */
	
	public void btnBlockOnClick(){
		btnBlock.setText("LEL");
	}
	

}
