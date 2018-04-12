package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBAccess {

	private Connection conn = null;

	public DBAccess() {
		openConnection("krusty-db.sqlite");
	}

	public boolean openConnection(String filename) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isConnected() {
		return conn != null;
	}

	/*
	 * Blocks a recipe from startDate to endDate.
	 * 
	 * @return true, if the recipe was successfully blocked, false if not.
	 */

	public boolean blockRecipe(String recipe, String startDate, String endDate) {

		return false;
	}

	/*
	 * Searches for a pallet with id palletID
	 */
	public ArrayList<Pallet> searchPalletID(String palletID) {

		try {
			int id = Integer.parseInt(palletID);
		} catch (NumberFormatException e) {
			// insert sql stuff here
			return null;
		}
		return null;

	}

	/**
	 * Search for the pallets produced of a certain recipe between the dates
	 * startDate and endDate.
	 * 
	 * @param recipe
	 * @param startDate
	 * @param endDate
	 */
	public ArrayList<Pallet> searchRecipesBetween(String recipe, String startDate, String endDate) {
		return null;

	}

	/**
	 * Search for pallets produced between startDate and endDate.
	 * 
	 * @param text
	 * @param text2
	 */

	public ArrayList<Pallet> searchBetween(String startDate, String endDate) {
		return null;

	}

	/**
	 * 
	 * @param recipe
	 * @param amount
	 * @return String in the format "X pallets of /recipe/ has been produced. 
	 * 			or "amount is not an integer"
	 * 			or "insufficient materials for the X pallets to be produced"
	 */
	public String producePallets(String recipe, String amount) {
		try {
			int amnt = Integer.parseInt(amount);
		} catch (NumberFormatException e) {
			return "amount is not an integer";
		}
		return amount + " pallets of " + recipe + "has been produced";
	}

}
