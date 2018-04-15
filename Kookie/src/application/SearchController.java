package application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SearchController {

	DBAccess db = new DBAccess();

	@FXML
	private Button btnSearch = new Button();

	@FXML
	private TextField txtID = new TextField();

	@FXML
	private TextField txtProd = new TextField();

	@FXML
	private TextField txtTimeStart = new TextField();

	@FXML
	private TextField txtTimeEnd = new TextField();

	@FXML
	private TextField txtRecipient = new TextField();

	@FXML
	private ListView lvPallets = new ListView();

	public void txtIDOnClick() {
		txtProd.clear();
		txtTimeStart.clear();
		txtTimeEnd.clear();
		txtRecipient.clear();
	}

	public void txtProdOnClick() {
		txtID.clear();
	}

	public void txtTimeStartOnClick() {
		txtID.clear();
	}

	public void txtTimeEndOnClick() {
		txtID.clear();
	}

	public void btnSearchBlocked() {
		lvPallets.setItems(FXCollections.observableArrayList(db.showAllBlocked()));
	}

	public void btnSearchOnClick() {
		if (!txtID.getText().equals("")) {
			lvPallets.setItems(FXCollections.observableArrayList(db.searchPalletID(txtID.getText())));
			return;
		}
		if (txtProd.getText().equals("") && !txtTimeStart.getText().equals("") && !txtTimeEnd.getText().equals("")
				&& txtRecipient.getText().equals("")) {
			lvPallets.setItems(
					FXCollections.observableArrayList(db.searchBetween(txtTimeStart.getText(), txtTimeEnd.getText())));
			return;
		}
		if (!(txtProd.getText().equals("") || txtTimeStart.getText().equals("") || txtTimeEnd.getText().equals(""))
				&& txtRecipient.getText().equals("")) {
			lvPallets.setItems(FXCollections.observableArrayList(
					db.searchRecipesBetween(txtProd.getText(), txtTimeStart.getText(), txtTimeEnd.getText())));
			return;
		} else if (!(txtProd.getText().equals("") || txtTimeStart.getText().equals("")
				|| txtTimeEnd.getText().equals("") || txtRecipient.getText().equals(""))) {
			lvPallets.setItems(FXCollections.observableArrayList(db.searchRecipesBetweenWithRecipient(txtProd.getText(),
					txtTimeStart.getText(), txtTimeEnd.getText(), txtRecipient.getText())));
			return;
		} else if (txtProd.getText().equals("") && txtTimeStart.getText().equals("") && txtTimeEnd.getText().equals("")
				&& !txtRecipient.getText().equals("")) {
			lvPallets.setItems(FXCollections.observableArrayList(db.searchRecipient(txtRecipient.getText())));
		} else {
			lvPallets.setItems(FXCollections.observableArrayList(db.searchRecipe(txtProd.getText())));
		}

	}

}