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

	@FXML
	private TextField txtOrderID = new TextField();

	public void btnProduceOnClick() {
		if (!txtRecipe.getText().equals("") && !txtAmount.getText().equals("") && !txtOrderID.getText().equals("")) {
			if (!db.checkIfBlocked(txtRecipe.getText())) {
				if (db.producePallet(txtRecipe.getText(), txtOrderID.getText(), txtAmount.getText())) {
					textArea.setText("Pallets produced");
					return;
				} else {
					textArea.setText("Pallets couldn't be produced");
					return;
				}
			}
			
			textArea.setText("Recipe is currently blocked");
		}

	}

}
