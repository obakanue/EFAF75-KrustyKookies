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
	private TextArea textArea = new TextArea();
	
	public void btnProduceOnClick(){
		if(!txtRecipe.getText().equals("")&& !txtAmount.getText().equals("")){
			textArea.setText(db.producePallets(txtRecipe.getText(), txtAmount.getText()));
		}
		
	}
	
	
}
