package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class prodController {
	
	DBAccess db = new DBAccess();
	
	@FXML
	private TextField txtRecipe = new TextField();
	
	@FXML
	private TextField txtAmount = new TextField();
	
	@FXML
	private Button btnProduce = new Button();
	
	@FXML
	private TextArea listView = new TextArea();
	
	public void btnProduceOnClick(){
		if(txtRecipe.getText() != null && txtAmount.getText() != null){
			
		}
		
	}
	
	
}
