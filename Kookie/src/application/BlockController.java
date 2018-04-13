package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BlockController {

	DBAccess db = new DBAccess();
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

	@FXML
	private Label lblMessage = new Label();

	/*
	 * Methods that are invoked when the stuff is clicked.
	 */

	public void btnBlockOnClick() {
		if (!txtRecipe.getText().equals("") && !txtStartDate.getText().equals("") && !txtEndDate.getText().equals("")) {
			if (db.blockRecipe(txtRecipe.getText(), txtStartDate.getText(), txtEndDate.getText())) {
				lblMessage.setText(txtRecipe.getText() + " has been blocked from " + txtStartDate.getText() + " to "
						+ txtEndDate.getText());
				return;
			}

			lblMessage.setText("Recipe couldn't be blocked");
			return;
		}

		lblMessage.setText("One or more fields are empty");

	}

}
